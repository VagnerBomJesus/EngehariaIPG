/*      */ package javax.media.j3d;
/*      */ 
/*      */ import com.sun.j3d.internal.HashCodeUtil;
/*      */ import javax.vecmath.AxisAngle4d;
/*      */ import javax.vecmath.AxisAngle4f;
/*      */ import javax.vecmath.GMatrix;
/*      */ import javax.vecmath.Matrix3d;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Matrix4d;
/*      */ import javax.vecmath.Matrix4f;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Point4d;
/*      */ import javax.vecmath.Quat4d;
/*      */ import javax.vecmath.Quat4f;
/*      */ import javax.vecmath.SingularMatrixException;
/*      */ import javax.vecmath.Vector3d;
/*      */ import javax.vecmath.Vector3f;
/*      */ import javax.vecmath.Vector4d;
/*      */ import javax.vecmath.Vector4f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Transform3D
/*      */ {
/*      */   double[] mat;
/*      */   double[] rot;
/*      */   double[] scales;
/*      */   private int type;
/*      */   private static final int AFFINE_BIT = 1;
/*      */   private static final int ORTHO_BIT = 2;
/*      */   private static final int CONGRUENT_BIT = 4;
/*      */   private static final int RIGID_BIT = 8;
/*      */   private static final int CLASSIFY_BIT = 16;
/*      */   private static final int SCALE_BIT = 32;
/*      */   private static final int ROTATION_BIT = 64;
/*      */   private static final int SVD_BIT = 128;
/*      */   private static final int CLASSIFY_ALL_DIRTY = 31;
/*      */   private static final int ROTSCALESVD_DIRTY = 224;
/*      */   private static final int ALL_DIRTY = 255;
/*      */   private int dirtyBits;
/*      */   boolean autoNormalize;
/*      */   private static final double EPS = 1.110223024E-16D;
/*      */   static final double EPSILON = 1.0E-10D;
/*      */   static final double EPSILON_ABSOLUTE = 1.0E-5D;
/*      */   static final double EPSILON_RELATIVE = 1.0E-4D;
/*      */   public static final int ZERO = 1;
/*      */   public static final int IDENTITY = 2;
/*      */   public static final int SCALE = 4;
/*      */   public static final int TRANSLATION = 8;
/*      */   public static final int ORTHOGONAL = 16;
/*      */   public static final int RIGID = 32;
/*      */   public static final int CONGRUENT = 64;
/*      */   public static final int AFFINE = 128;
/*      */   public static final int NEGATIVE_DETERMINANT = 256;
/*      */   private static final int ORTHO = 1073741824;
/*      */   
/*      */   public Transform3D(Matrix4f paramMatrix4f) {
/*   87 */     this.mat = new double[16];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   92 */     this.rot = null;
/*   93 */     this.scales = null;
/*      */ 
/*      */     
/*   96 */     this.type = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  124 */     this.autoNormalize = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  233 */     set(paramMatrix4f);
/*      */   }
/*      */   
/*      */   public Transform3D(Matrix4d paramMatrix4d) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  242 */     set(paramMatrix4d);
/*      */   }
/*      */   public Transform3D(Transform3D paramTransform3D) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  250 */     set(paramTransform3D);
/*      */   } public Transform3D() {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  257 */     setIdentity();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(float[] paramArrayOfFloat) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  268 */     set(paramArrayOfFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(double[] paramArrayOfDouble) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  279 */     set(paramArrayOfDouble);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(Quat4d paramQuat4d, Vector3d paramVector3d, double paramDouble) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  292 */     set(paramQuat4d, paramVector3d, paramDouble);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(Quat4f paramQuat4f, Vector3d paramVector3d, double paramDouble) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  305 */     set(paramQuat4f, paramVector3d, paramDouble);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(Quat4f paramQuat4f, Vector3f paramVector3f, float paramFloat) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  318 */     set(paramQuat4f, paramVector3f, paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(GMatrix paramGMatrix) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  329 */     set(paramGMatrix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(Matrix3f paramMatrix3f, Vector3d paramVector3d, double paramDouble) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  342 */     set(paramMatrix3f, paramVector3d, paramDouble);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(Matrix3d paramMatrix3d, Vector3d paramVector3d, double paramDouble) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  355 */     set(paramMatrix3d, paramVector3d, paramDouble);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Transform3D(Matrix3f paramMatrix3f, Vector3f paramVector3f, float paramFloat) {
/*      */     this.mat = new double[16];
/*      */     this.rot = null;
/*      */     this.scales = null;
/*      */     this.type = 0;
/*      */     this.autoNormalize = false;
/*  369 */     set(paramMatrix3f, paramVector3f, paramFloat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getType() {
/*  379 */     if ((this.dirtyBits & 0x10) != 0) {
/*  380 */       classify();
/*      */     }
/*      */     
/*  383 */     return this.type & 0xBFFFFFFF;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   final boolean isOrtho() {
/*  389 */     if ((this.dirtyBits & 0x2) != 0) {
/*  390 */       if (almostZero(this.mat[0] * this.mat[2] + this.mat[4] * this.mat[6] + this.mat[8] * this.mat[10]) && almostZero(this.mat[0] * this.mat[1] + this.mat[4] * this.mat[5] + this.mat[8] * this.mat[9]) && almostZero(this.mat[1] * this.mat[2] + this.mat[5] * this.mat[6] + this.mat[9] * this.mat[10])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  396 */         this.type |= 0x40000000;
/*  397 */         this.dirtyBits &= 0xFFFFFFFD;
/*  398 */         return true;
/*      */       } 
/*  400 */       this.type &= 0xBFFFFFFF;
/*  401 */       this.dirtyBits &= 0xFFFFFFFD;
/*  402 */       return false;
/*      */     } 
/*      */     
/*  405 */     return ((this.type & 0x40000000) != 0);
/*      */   }
/*      */   
/*      */   final boolean isCongruent() {
/*  409 */     if ((this.dirtyBits & 0x4) != 0)
/*      */     {
/*  411 */       classifyRigid();
/*      */     }
/*  413 */     return ((this.type & 0x40) != 0);
/*      */   }
/*      */   
/*      */   final boolean isAffine() {
/*  417 */     if ((this.dirtyBits & true) != 0) {
/*  418 */       classifyAffine();
/*      */     }
/*  420 */     return ((this.type & 0x80) != 0);
/*      */   }
/*      */   
/*      */   final boolean isRigid() {
/*  424 */     if ((this.dirtyBits & 0x8) != 0)
/*      */     {
/*      */ 
/*      */       
/*  428 */       if ((this.dirtyBits & 0x4) != 0) {
/*  429 */         classifyRigid();
/*      */       } else {
/*      */         
/*  432 */         if ((this.type & 0x40) != 0) {
/*      */           double d;
/*      */ 
/*      */           
/*  436 */           if ((this.dirtyBits & 0x20) != 0) {
/*  437 */             d = this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8];
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/*  444 */             if (this.scales == null)
/*  445 */               this.scales = new double[3]; 
/*  446 */             d = this.scales[0];
/*      */           } 
/*  448 */           if (almostOne(d)) {
/*  449 */             this.type |= 0x20;
/*      */           } else {
/*  451 */             this.type &= 0xFFFFFFDF;
/*      */           } 
/*      */         } else {
/*      */           
/*  455 */           this.type &= 0xFFFFFFDF;
/*      */         } 
/*  457 */         this.dirtyBits &= 0xFFFFFFF7;
/*      */       } 
/*      */     }
/*  460 */     return ((this.type & 0x20) != 0);
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
/*      */   public final int getBestType() {
/*  473 */     getType();
/*      */     
/*  475 */     if ((this.type & true) != 0) return 1; 
/*  476 */     if ((this.type & 0x2) != 0) return 2; 
/*  477 */     if ((this.type & 0x4) != 0) return 4; 
/*  478 */     if ((this.type & 0x8) != 0) return 8; 
/*  479 */     if ((this.type & 0x10) != 0) return 16; 
/*  480 */     if ((this.type & 0x20) != 0) return 32; 
/*  481 */     if ((this.type & 0x40) != 0) return 64; 
/*  482 */     if ((this.type & 0x80) != 0) return 128; 
/*  483 */     if ((this.type & 0x100) != 0) return 256; 
/*  484 */     return 0;
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
/*      */   public final boolean getDeterminantSign() {
/*  512 */     double d = determinant();
/*  513 */     if (Double.isNaN(d)) {
/*  514 */       return true;
/*      */     }
/*  516 */     return (d >= 0.0D);
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
/*      */   public final void setAutoNormalize(boolean paramBoolean) {
/*  531 */     this.autoNormalize = paramBoolean;
/*      */     
/*  533 */     if (paramBoolean) {
/*  534 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  543 */   public final boolean getAutoNormalize() { return this.autoNormalize; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void transform(Point3d paramPoint3d, Point4d paramPoint4d) {
/*  555 */     paramPoint4d.x = this.mat[0] * paramPoint3d.x + this.mat[1] * paramPoint3d.y + this.mat[2] * paramPoint3d.z + this.mat[3];
/*      */     
/*  557 */     paramPoint4d.y = this.mat[4] * paramPoint3d.x + this.mat[5] * paramPoint3d.y + this.mat[6] * paramPoint3d.z + this.mat[7];
/*      */     
/*  559 */     paramPoint4d.z = this.mat[8] * paramPoint3d.x + this.mat[9] * paramPoint3d.y + this.mat[10] * paramPoint3d.z + this.mat[11];
/*      */     
/*  561 */     paramPoint4d.w = this.mat[12] * paramPoint3d.x + this.mat[13] * paramPoint3d.y + this.mat[14] * paramPoint3d.z + this.mat[15];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  568 */   private static final boolean almostZero(double paramDouble) { return (paramDouble < 1.0E-5D && paramDouble > -1.0E-5D); }
/*      */ 
/*      */ 
/*      */   
/*  572 */   private static final boolean almostOne(double paramDouble) { return (paramDouble < 1.00001D && paramDouble > 0.99999D); }
/*      */ 
/*      */   
/*      */   private static final boolean almostEqual(double paramDouble1, double paramDouble2) {
/*  576 */     double d = paramDouble1 - paramDouble2;
/*      */     
/*  578 */     if (d >= 0.0D) {
/*  579 */       if (d < 1.0E-10D) {
/*  580 */         return true;
/*      */       }
/*      */       
/*  583 */       if (paramDouble2 > 0.0D || paramDouble1 > -paramDouble2) {
/*  584 */         return (d < 1.0E-4D * paramDouble1);
/*      */       }
/*  586 */       return (d < -1.0E-4D * paramDouble2);
/*      */     } 
/*      */ 
/*      */     
/*  590 */     if (d > -1.0E-10D) {
/*  591 */       return true;
/*      */     }
/*      */     
/*  594 */     if (paramDouble2 < 0.0D || -paramDouble1 > paramDouble2) {
/*  595 */       return (d > 1.0E-4D * paramDouble1);
/*      */     }
/*  597 */     return (d > -1.0E-4D * paramDouble2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final void classifyAffine() {
/*  603 */     if (!isInfOrNaN() && almostZero(this.mat[12]) && almostZero(this.mat[13]) && almostZero(this.mat[14]) && almostOne(this.mat[15])) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  608 */       this.type |= 0x80;
/*      */     } else {
/*  610 */       this.type &= 0xFFFFFF7F;
/*      */     } 
/*  612 */     this.dirtyBits &= 0xFFFFFFFE;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final void classifyRigid() {
/*  618 */     if ((this.dirtyBits & true) != 0) {
/*      */       
/*  620 */       this.type &= 0x40000000;
/*  621 */       classifyAffine();
/*      */     }
/*      */     else {
/*      */       
/*  625 */       this.type &= 0x40000080;
/*      */     } 
/*      */     
/*  628 */     if ((this.type & 0x80) != 0)
/*      */     {
/*  630 */       if (isOrtho()) {
/*  631 */         if ((this.dirtyBits & 0x20) != 0) {
/*  632 */           double d1 = this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8];
/*      */           
/*  634 */           double d2 = this.mat[1] * this.mat[1] + this.mat[5] * this.mat[5] + this.mat[9] * this.mat[9];
/*      */           
/*  636 */           if (almostEqual(d1, d2)) {
/*  637 */             double d = this.mat[2] * this.mat[2] + this.mat[6] * this.mat[6] + this.mat[10] * this.mat[10];
/*      */             
/*  639 */             if (almostEqual(d, d1)) {
/*  640 */               this.type |= 0x40;
/*      */               
/*  642 */               if (almostOne(d1)) {
/*  643 */                 this.type |= 0x20;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } else {
/*  648 */           if (this.scales == null) {
/*  649 */             this.scales = new double[3];
/*      */           }
/*  651 */           double d = this.scales[0];
/*  652 */           if (almostEqual(d, this.scales[1]) && almostEqual(d, this.scales[2])) {
/*      */             
/*  654 */             this.type |= 0x40;
/*  655 */             if (almostOne(d)) {
/*  656 */               this.type |= 0x20;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*  662 */     this.dirtyBits &= 0xFFFFFFFF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void classify() {
/*  670 */     if ((this.dirtyBits & 0xD) != 0)
/*      */     {
/*  672 */       classifyRigid();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  677 */     if ((this.type & 0x80) != 0) {
/*  678 */       if ((this.type & 0x40) != 0) {
/*  679 */         if ((this.type & 0x20) != 0) {
/*  680 */           if (zeroTranslation()) {
/*  681 */             this.type |= 0x10;
/*  682 */             if (rotateZero())
/*      */             {
/*      */               
/*  685 */               if (this.mat[0] > 0.0D && this.mat[5] > 0.0D && this.mat[10] > 0.0D)
/*      */               {
/*      */                 
/*  688 */                 this.type |= 0xE;
/*      */               }
/*      */             }
/*      */           }
/*  692 */           else if (rotateZero()) {
/*  693 */             this.type |= 0x8;
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  698 */         else if (zeroTranslation() && rotateZero()) {
/*  699 */           this.type |= 0x4;
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */     }
/*  706 */     else if (almostZero(this.mat[12]) && almostZero(this.mat[13]) && almostZero(this.mat[14]) && almostZero(this.mat[15]) && zeroTranslation() && rotateZero() && almostZero(this.mat[0]) && almostZero(this.mat[5]) && almostZero(this.mat[10])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  715 */       this.type |= 0x1;
/*      */     } 
/*      */ 
/*      */     
/*  719 */     if (!getDeterminantSign()) {
/*  720 */       this.type |= 0x100;
/*      */     }
/*  722 */     this.dirtyBits &= 0xFFFFFFEF;
/*      */   }
/*      */ 
/*      */   
/*  726 */   final boolean zeroTranslation() { return (almostZero(this.mat[3]) && almostZero(this.mat[7]) && almostZero(this.mat[11])); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  732 */   final boolean rotateZero() { return (almostZero(this.mat[1]) && almostZero(this.mat[2]) && almostZero(this.mat[4]) && almostZero(this.mat[6]) && almostZero(this.mat[8]) && almostZero(this.mat[9])); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  743 */   public String toString() { return this.mat[0] + ", " + this.mat[1] + ", " + this.mat[2] + ", " + this.mat[3] + "\n" + this.mat[4] + ", " + this.mat[5] + ", " + this.mat[6] + ", " + this.mat[7] + "\n" + this.mat[8] + ", " + this.mat[9] + ", " + this.mat[10] + ", " + this.mat[11] + "\n" + this.mat[12] + ", " + this.mat[13] + ", " + this.mat[14] + ", " + this.mat[15] + "\n"; }
/*      */ 
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
/*  755 */     this.mat[0] = 1.0D; this.mat[1] = 0.0D; this.mat[2] = 0.0D; this.mat[3] = 0.0D;
/*  756 */     this.mat[4] = 0.0D; this.mat[5] = 1.0D; this.mat[6] = 0.0D; this.mat[7] = 0.0D;
/*  757 */     this.mat[8] = 0.0D; this.mat[9] = 0.0D; this.mat[10] = 1.0D; this.mat[11] = 0.0D;
/*  758 */     this.mat[12] = 0.0D; this.mat[13] = 0.0D; this.mat[14] = 0.0D; this.mat[15] = 1.0D;
/*  759 */     this.type = 1073742078;
/*      */     
/*  761 */     this.dirtyBits = 96;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setZero() {
/*  769 */     this.mat[0] = 0.0D; this.mat[1] = 0.0D; this.mat[2] = 0.0D; this.mat[3] = 0.0D;
/*  770 */     this.mat[4] = 0.0D; this.mat[5] = 0.0D; this.mat[6] = 0.0D; this.mat[7] = 0.0D;
/*  771 */     this.mat[8] = 0.0D; this.mat[9] = 0.0D; this.mat[10] = 0.0D; this.mat[11] = 0.0D;
/*  772 */     this.mat[12] = 0.0D; this.mat[13] = 0.0D; this.mat[14] = 0.0D; this.mat[15] = 0.0D;
/*      */     
/*  774 */     this.type = 1073741825;
/*  775 */     this.dirtyBits = 96;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Transform3D paramTransform3D) {
/*  785 */     for (byte b = 0; b < 16; b++) {
/*  786 */       this.mat[b] = this.mat[b] + paramTransform3D.mat[b];
/*      */     }
/*      */     
/*  789 */     this.dirtyBits = 255;
/*      */     
/*  791 */     if (this.autoNormalize) {
/*  792 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void add(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/*  803 */     for (byte b = 0; b < 16; b++) {
/*  804 */       this.mat[b] = paramTransform3D1.mat[b] + paramTransform3D2.mat[b];
/*      */     }
/*      */     
/*  807 */     this.dirtyBits = 255;
/*      */     
/*  809 */     if (this.autoNormalize) {
/*  810 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void sub(Transform3D paramTransform3D) {
/*  821 */     for (byte b = 0; b < 16; b++) {
/*  822 */       this.mat[b] = this.mat[b] - paramTransform3D.mat[b];
/*      */     }
/*      */     
/*  825 */     this.dirtyBits = 255;
/*      */     
/*  827 */     if (this.autoNormalize) {
/*  828 */       normalize();
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
/*      */   public final void sub(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/*  840 */     for (byte b = 0; b < 16; b++) {
/*  841 */       this.mat[b] = paramTransform3D1.mat[b] - paramTransform3D2.mat[b];
/*      */     }
/*      */     
/*  844 */     this.dirtyBits = 255;
/*      */     
/*  846 */     if (this.autoNormalize) {
/*  847 */       normalize();
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
/*  859 */     double d = this.mat[4];
/*  860 */     this.mat[4] = this.mat[1];
/*  861 */     this.mat[1] = d;
/*      */     
/*  863 */     d = this.mat[8];
/*  864 */     this.mat[8] = this.mat[2];
/*  865 */     this.mat[2] = d;
/*      */     
/*  867 */     d = this.mat[12];
/*  868 */     this.mat[12] = this.mat[3];
/*  869 */     this.mat[3] = d;
/*      */     
/*  871 */     d = this.mat[9];
/*  872 */     this.mat[9] = this.mat[6];
/*  873 */     this.mat[6] = d;
/*      */     
/*  875 */     d = this.mat[13];
/*  876 */     this.mat[13] = this.mat[7];
/*  877 */     this.mat[7] = d;
/*      */     
/*  879 */     d = this.mat[14];
/*  880 */     this.mat[14] = this.mat[11];
/*  881 */     this.mat[11] = d;
/*      */     
/*  883 */     this.dirtyBits = 255;
/*      */     
/*  885 */     if (this.autoNormalize) {
/*  886 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transpose(Transform3D paramTransform3D) {
/*  897 */     if (this != paramTransform3D) {
/*  898 */       this.mat[0] = paramTransform3D.mat[0];
/*  899 */       this.mat[1] = paramTransform3D.mat[4];
/*  900 */       this.mat[2] = paramTransform3D.mat[8];
/*  901 */       this.mat[3] = paramTransform3D.mat[12];
/*  902 */       this.mat[4] = paramTransform3D.mat[1];
/*  903 */       this.mat[5] = paramTransform3D.mat[5];
/*  904 */       this.mat[6] = paramTransform3D.mat[9];
/*  905 */       this.mat[7] = paramTransform3D.mat[13];
/*  906 */       this.mat[8] = paramTransform3D.mat[2];
/*  907 */       this.mat[9] = paramTransform3D.mat[6];
/*  908 */       this.mat[10] = paramTransform3D.mat[10];
/*  909 */       this.mat[11] = paramTransform3D.mat[14];
/*  910 */       this.mat[12] = paramTransform3D.mat[3];
/*  911 */       this.mat[13] = paramTransform3D.mat[7];
/*  912 */       this.mat[14] = paramTransform3D.mat[11];
/*  913 */       this.mat[15] = paramTransform3D.mat[15];
/*      */       
/*  915 */       this.dirtyBits = 255;
/*      */       
/*  917 */       if (this.autoNormalize) {
/*  918 */         normalize();
/*      */       }
/*      */     } else {
/*  921 */       transpose();
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
/*      */   public final void set(Quat4f paramQuat4f) {
/*  934 */     this.mat[0] = (1.0F - 2.0F * paramQuat4f.y * paramQuat4f.y - 2.0F * paramQuat4f.z * paramQuat4f.z);
/*  935 */     this.mat[4] = (2.0F * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z));
/*  936 */     this.mat[8] = (2.0F * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y));
/*      */     
/*  938 */     this.mat[1] = (2.0F * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z));
/*  939 */     this.mat[5] = (1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.z * paramQuat4f.z);
/*  940 */     this.mat[9] = (2.0F * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x));
/*      */     
/*  942 */     this.mat[2] = (2.0F * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y));
/*  943 */     this.mat[6] = (2.0F * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x));
/*  944 */     this.mat[10] = (1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.y * paramQuat4f.y);
/*      */     
/*  946 */     this.mat[3] = 0.0D;
/*  947 */     this.mat[7] = 0.0D;
/*  948 */     this.mat[11] = 0.0D;
/*      */     
/*  950 */     this.mat[12] = 0.0D;
/*  951 */     this.mat[13] = 0.0D;
/*  952 */     this.mat[14] = 0.0D;
/*  953 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/*  956 */     if (isInfOrNaN(paramQuat4f)) {
/*  957 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/*  961 */     this.dirtyBits = 112;
/*  962 */     this.type = 1073742048;
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
/*  973 */     this.mat[0] = 1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z;
/*  974 */     this.mat[4] = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
/*  975 */     this.mat[8] = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
/*      */     
/*  977 */     this.mat[1] = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
/*  978 */     this.mat[5] = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z;
/*  979 */     this.mat[9] = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
/*      */     
/*  981 */     this.mat[2] = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
/*  982 */     this.mat[6] = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
/*  983 */     this.mat[10] = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y;
/*      */     
/*  985 */     this.mat[3] = 0.0D;
/*  986 */     this.mat[7] = 0.0D;
/*  987 */     this.mat[11] = 0.0D;
/*      */     
/*  989 */     this.mat[12] = 0.0D;
/*  990 */     this.mat[13] = 0.0D;
/*  991 */     this.mat[14] = 0.0D;
/*  992 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/*  995 */     if (isInfOrNaN(paramQuat4d)) {
/*  996 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1000 */     this.dirtyBits = 112;
/* 1001 */     this.type = 1073742048;
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
/*      */   public final void setRotation(Matrix3d paramMatrix3d) {
/* 1014 */     if ((this.dirtyBits & 0x20) != 0) {
/* 1015 */       computeScales(false);
/*      */     }
/*      */     
/* 1018 */     this.mat[0] = paramMatrix3d.m00 * this.scales[0];
/* 1019 */     this.mat[1] = paramMatrix3d.m01 * this.scales[1];
/* 1020 */     this.mat[2] = paramMatrix3d.m02 * this.scales[2];
/* 1021 */     this.mat[4] = paramMatrix3d.m10 * this.scales[0];
/* 1022 */     this.mat[5] = paramMatrix3d.m11 * this.scales[1];
/* 1023 */     this.mat[6] = paramMatrix3d.m12 * this.scales[2];
/* 1024 */     this.mat[8] = paramMatrix3d.m20 * this.scales[0];
/* 1025 */     this.mat[9] = paramMatrix3d.m21 * this.scales[1];
/* 1026 */     this.mat[10] = paramMatrix3d.m22 * this.scales[2];
/*      */ 
/*      */     
/* 1029 */     this.dirtyBits = 255;
/*      */     
/* 1031 */     if (this.autoNormalize)
/*      */     {
/* 1033 */       normalize();
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
/*      */   public final void setRotation(Matrix3f paramMatrix3f) {
/* 1047 */     if ((this.dirtyBits & 0x20) != 0) {
/* 1048 */       computeScales(false);
/*      */     }
/*      */     
/* 1051 */     this.mat[0] = paramMatrix3f.m00 * this.scales[0];
/* 1052 */     this.mat[1] = paramMatrix3f.m01 * this.scales[1];
/* 1053 */     this.mat[2] = paramMatrix3f.m02 * this.scales[2];
/* 1054 */     this.mat[4] = paramMatrix3f.m10 * this.scales[0];
/* 1055 */     this.mat[5] = paramMatrix3f.m11 * this.scales[1];
/* 1056 */     this.mat[6] = paramMatrix3f.m12 * this.scales[2];
/* 1057 */     this.mat[8] = paramMatrix3f.m20 * this.scales[0];
/* 1058 */     this.mat[9] = paramMatrix3f.m21 * this.scales[1];
/* 1059 */     this.mat[10] = paramMatrix3f.m22 * this.scales[2];
/*      */ 
/*      */     
/* 1062 */     this.dirtyBits = 255;
/*      */     
/* 1064 */     if (this.autoNormalize) {
/* 1065 */       normalize();
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
/*      */   public final void setRotation(Quat4f paramQuat4f) {
/* 1079 */     if ((this.dirtyBits & 0x20) != 0) {
/* 1080 */       computeScales(false);
/*      */     }
/*      */     
/* 1083 */     this.mat[0] = (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z) * this.scales[0];
/* 1084 */     this.mat[4] = 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z) * this.scales[0];
/* 1085 */     this.mat[8] = 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y) * this.scales[0];
/*      */     
/* 1087 */     this.mat[1] = 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z) * this.scales[1];
/* 1088 */     this.mat[5] = (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z) * this.scales[1];
/* 1089 */     this.mat[9] = 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x) * this.scales[1];
/*      */     
/* 1091 */     this.mat[2] = 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y) * this.scales[2];
/* 1092 */     this.mat[6] = 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x) * this.scales[2];
/* 1093 */     this.mat[10] = (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y) * this.scales[2];
/*      */ 
/*      */     
/* 1096 */     if (isInfOrNaN(paramQuat4f)) {
/* 1097 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1101 */     this.dirtyBits |= 0x50;
/* 1102 */     this.dirtyBits &= 0xFFFFFFFD;
/* 1103 */     this.type |= 0x40000000;
/* 1104 */     this.type &= 0xFFFFFFE0;
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
/*      */   public final void setRotation(Quat4d paramQuat4d) {
/* 1117 */     if ((this.dirtyBits & 0x20) != 0) {
/* 1118 */       computeScales(false);
/*      */     }
/*      */     
/* 1121 */     this.mat[0] = (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z) * this.scales[0];
/* 1122 */     this.mat[4] = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z) * this.scales[0];
/* 1123 */     this.mat[8] = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y) * this.scales[0];
/*      */     
/* 1125 */     this.mat[1] = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z) * this.scales[1];
/* 1126 */     this.mat[5] = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z) * this.scales[1];
/* 1127 */     this.mat[9] = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x) * this.scales[1];
/*      */     
/* 1129 */     this.mat[2] = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y) * this.scales[2];
/* 1130 */     this.mat[6] = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x) * this.scales[2];
/* 1131 */     this.mat[10] = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y) * this.scales[2];
/*      */ 
/*      */     
/* 1134 */     if (isInfOrNaN(paramQuat4d)) {
/* 1135 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1139 */     this.dirtyBits |= 0x50;
/* 1140 */     this.dirtyBits &= 0xFFFFFFFD;
/* 1141 */     this.type |= 0x40000000;
/* 1142 */     this.type &= 0xFFFFFFE0;
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
/*      */   public final void set(AxisAngle4f paramAxisAngle4f) {
/* 1154 */     double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/*      */     
/* 1156 */     if (almostZero(d)) {
/* 1157 */       setIdentity();
/*      */     } else {
/* 1159 */       d = 1.0D / d;
/* 1160 */       double d1 = paramAxisAngle4f.x * d;
/* 1161 */       double d2 = paramAxisAngle4f.y * d;
/* 1162 */       double d3 = paramAxisAngle4f.z * d;
/*      */       
/* 1164 */       double d4 = Math.sin(paramAxisAngle4f.angle);
/* 1165 */       double d5 = Math.cos(paramAxisAngle4f.angle);
/* 1166 */       double d6 = 1.0D - d5;
/*      */       
/* 1168 */       double d7 = d1 * d3;
/* 1169 */       double d8 = d1 * d2;
/* 1170 */       double d9 = d2 * d3;
/*      */       
/* 1172 */       this.mat[0] = d6 * d1 * d1 + d5;
/* 1173 */       this.mat[1] = d6 * d8 - d4 * d3;
/* 1174 */       this.mat[2] = d6 * d7 + d4 * d2;
/* 1175 */       this.mat[3] = 0.0D;
/*      */       
/* 1177 */       this.mat[4] = d6 * d8 + d4 * d3;
/* 1178 */       this.mat[5] = d6 * d2 * d2 + d5;
/* 1179 */       this.mat[6] = d6 * d9 - d4 * d1;
/* 1180 */       this.mat[7] = 0.0D;
/*      */       
/* 1182 */       this.mat[8] = d6 * d7 - d4 * d2;
/* 1183 */       this.mat[9] = d6 * d9 + d4 * d1;
/* 1184 */       this.mat[10] = d6 * d3 * d3 + d5;
/* 1185 */       this.mat[11] = 0.0D;
/*      */       
/* 1187 */       this.mat[12] = 0.0D;
/* 1188 */       this.mat[13] = 0.0D;
/* 1189 */       this.mat[14] = 0.0D;
/* 1190 */       this.mat[15] = 1.0D;
/*      */ 
/*      */       
/* 1193 */       if (isInfOrNaN(paramAxisAngle4f)) {
/* 1194 */         this.dirtyBits = 255;
/*      */         
/*      */         return;
/*      */       } 
/* 1198 */       this.type = 1073742048;
/* 1199 */       this.dirtyBits = 112;
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
/*      */   public final void set(AxisAngle4d paramAxisAngle4d) {
/* 1212 */     double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/*      */     
/* 1214 */     if (almostZero(d)) {
/* 1215 */       setIdentity();
/*      */     } else {
/* 1217 */       d = 1.0D / d;
/* 1218 */       double d1 = paramAxisAngle4d.x * d;
/* 1219 */       double d2 = paramAxisAngle4d.y * d;
/* 1220 */       double d3 = paramAxisAngle4d.z * d;
/*      */       
/* 1222 */       double d4 = Math.sin(paramAxisAngle4d.angle);
/* 1223 */       double d5 = Math.cos(paramAxisAngle4d.angle);
/* 1224 */       double d6 = 1.0D - d5;
/*      */       
/* 1226 */       double d7 = d1 * d3;
/* 1227 */       double d8 = d1 * d2;
/* 1228 */       double d9 = d2 * d3;
/*      */       
/* 1230 */       this.mat[0] = d6 * d1 * d1 + d5;
/* 1231 */       this.mat[1] = d6 * d8 - d4 * d3;
/* 1232 */       this.mat[2] = d6 * d7 + d4 * d2;
/* 1233 */       this.mat[3] = 0.0D;
/*      */       
/* 1235 */       this.mat[4] = d6 * d8 + d4 * d3;
/* 1236 */       this.mat[5] = d6 * d2 * d2 + d5;
/* 1237 */       this.mat[6] = d6 * d9 - d4 * d1;
/* 1238 */       this.mat[7] = 0.0D;
/*      */       
/* 1240 */       this.mat[8] = d6 * d7 - d4 * d2;
/* 1241 */       this.mat[9] = d6 * d9 + d4 * d1;
/* 1242 */       this.mat[10] = d6 * d3 * d3 + d5;
/* 1243 */       this.mat[11] = 0.0D;
/*      */       
/* 1245 */       this.mat[12] = 0.0D;
/* 1246 */       this.mat[13] = 0.0D;
/* 1247 */       this.mat[14] = 0.0D;
/* 1248 */       this.mat[15] = 1.0D;
/*      */ 
/*      */       
/* 1251 */       if (isInfOrNaN(paramAxisAngle4d)) {
/* 1252 */         this.dirtyBits = 255;
/*      */         
/*      */         return;
/*      */       } 
/* 1256 */       this.type = 1073742048;
/* 1257 */       this.dirtyBits = 112;
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
/*      */   public final void setRotation(AxisAngle4d paramAxisAngle4d) {
/* 1271 */     if ((this.dirtyBits & 0x20) != 0) {
/* 1272 */       computeScales(false);
/*      */     }
/*      */     
/* 1275 */     double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
/*      */     
/* 1277 */     if (almostZero(d)) {
/* 1278 */       this.mat[0] = this.scales[0];
/* 1279 */       this.mat[1] = 0.0D;
/* 1280 */       this.mat[2] = 0.0D;
/* 1281 */       this.mat[4] = 0.0D;
/* 1282 */       this.mat[5] = this.scales[1];
/* 1283 */       this.mat[6] = 0.0D;
/* 1284 */       this.mat[8] = 0.0D;
/* 1285 */       this.mat[9] = 0.0D;
/* 1286 */       this.mat[10] = this.scales[2];
/*      */     } else {
/* 1288 */       d = 1.0D / d;
/* 1289 */       double d1 = paramAxisAngle4d.x * d;
/* 1290 */       double d2 = paramAxisAngle4d.y * d;
/* 1291 */       double d3 = paramAxisAngle4d.z * d;
/*      */       
/* 1293 */       double d4 = Math.sin(paramAxisAngle4d.angle);
/* 1294 */       double d5 = Math.cos(paramAxisAngle4d.angle);
/* 1295 */       double d6 = 1.0D - d5;
/*      */       
/* 1297 */       double d7 = d1 * d3;
/* 1298 */       double d8 = d1 * d2;
/* 1299 */       double d9 = d2 * d3;
/*      */       
/* 1301 */       this.mat[0] = (d6 * d1 * d1 + d5) * this.scales[0];
/* 1302 */       this.mat[1] = (d6 * d8 - d4 * d3) * this.scales[1];
/* 1303 */       this.mat[2] = (d6 * d7 + d4 * d2) * this.scales[2];
/*      */       
/* 1305 */       this.mat[4] = (d6 * d8 + d4 * d3) * this.scales[0];
/* 1306 */       this.mat[5] = (d6 * d2 * d2 + d5) * this.scales[1];
/* 1307 */       this.mat[6] = (d6 * d9 - d4 * d1) * this.scales[2];
/*      */       
/* 1309 */       this.mat[8] = (d6 * d7 - d4 * d2) * this.scales[0];
/* 1310 */       this.mat[9] = (d6 * d9 + d4 * d1) * this.scales[1];
/* 1311 */       this.mat[10] = (d6 * d3 * d3 + d5) * this.scales[2];
/*      */     } 
/*      */ 
/*      */     
/* 1315 */     if (isInfOrNaN(paramAxisAngle4d)) {
/* 1316 */       this.dirtyBits = 255;
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1322 */     this.dirtyBits |= 0x50;
/* 1323 */     this.dirtyBits &= 0xFFFFFFFD;
/* 1324 */     this.type |= 0x40000000;
/* 1325 */     this.type &= 0xFFFFFFE0;
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
/*      */   public final void setRotation(AxisAngle4f paramAxisAngle4f) {
/* 1338 */     if ((this.dirtyBits & 0x20) != 0) {
/* 1339 */       computeScales(false);
/*      */     }
/*      */     
/* 1342 */     double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
/*      */     
/* 1344 */     if (almostZero(d)) {
/* 1345 */       this.mat[0] = this.scales[0];
/* 1346 */       this.mat[1] = 0.0D;
/* 1347 */       this.mat[2] = 0.0D;
/* 1348 */       this.mat[4] = 0.0D;
/* 1349 */       this.mat[5] = this.scales[1];
/* 1350 */       this.mat[6] = 0.0D;
/* 1351 */       this.mat[8] = 0.0D;
/* 1352 */       this.mat[9] = 0.0D;
/* 1353 */       this.mat[10] = this.scales[2];
/*      */     } else {
/* 1355 */       d = 1.0D / d;
/* 1356 */       double d1 = paramAxisAngle4f.x * d;
/* 1357 */       double d2 = paramAxisAngle4f.y * d;
/* 1358 */       double d3 = paramAxisAngle4f.z * d;
/*      */       
/* 1360 */       double d4 = Math.sin(paramAxisAngle4f.angle);
/* 1361 */       double d5 = Math.cos(paramAxisAngle4f.angle);
/* 1362 */       double d6 = 1.0D - d5;
/*      */       
/* 1364 */       double d7 = d1 * d3;
/* 1365 */       double d8 = d1 * d2;
/* 1366 */       double d9 = d2 * d3;
/*      */       
/* 1368 */       this.mat[0] = (d6 * d1 * d1 + d5) * this.scales[0];
/* 1369 */       this.mat[1] = (d6 * d8 - d4 * d3) * this.scales[1];
/* 1370 */       this.mat[2] = (d6 * d7 + d4 * d2) * this.scales[2];
/*      */       
/* 1372 */       this.mat[4] = (d6 * d8 + d4 * d3) * this.scales[0];
/* 1373 */       this.mat[5] = (d6 * d2 * d2 + d5) * this.scales[1];
/* 1374 */       this.mat[6] = (d6 * d9 - d4 * d1) * this.scales[2];
/*      */       
/* 1376 */       this.mat[8] = (d6 * d7 - d4 * d2) * this.scales[0];
/* 1377 */       this.mat[9] = (d6 * d9 + d4 * d1) * this.scales[1];
/* 1378 */       this.mat[10] = (d6 * d3 * d3 + d5) * this.scales[2];
/*      */     } 
/*      */ 
/*      */     
/* 1382 */     if (isInfOrNaN(paramAxisAngle4f)) {
/* 1383 */       this.dirtyBits = 255;
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1389 */     this.dirtyBits |= 0x50;
/* 1390 */     this.dirtyBits &= 0xFFFFFFFF;
/* 1391 */     this.type |= 0x40000000;
/* 1392 */     this.type &= 0xFFFFFFE0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rotX(double paramDouble) {
/* 1403 */     double d1 = Math.sin(paramDouble);
/* 1404 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 1406 */     this.mat[0] = 1.0D;
/* 1407 */     this.mat[1] = 0.0D;
/* 1408 */     this.mat[2] = 0.0D;
/* 1409 */     this.mat[3] = 0.0D;
/*      */     
/* 1411 */     this.mat[4] = 0.0D;
/* 1412 */     this.mat[5] = d2;
/* 1413 */     this.mat[6] = -d1;
/* 1414 */     this.mat[7] = 0.0D;
/*      */     
/* 1416 */     this.mat[8] = 0.0D;
/* 1417 */     this.mat[9] = d1;
/* 1418 */     this.mat[10] = d2;
/* 1419 */     this.mat[11] = 0.0D;
/*      */     
/* 1421 */     this.mat[12] = 0.0D;
/* 1422 */     this.mat[13] = 0.0D;
/* 1423 */     this.mat[14] = 0.0D;
/* 1424 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1427 */     if (isInfOrNaN(paramDouble)) {
/* 1428 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1432 */     this.type = 1073742048;
/* 1433 */     this.dirtyBits = 112;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rotY(double paramDouble) {
/* 1443 */     double d1 = Math.sin(paramDouble);
/* 1444 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 1446 */     this.mat[0] = d2;
/* 1447 */     this.mat[1] = 0.0D;
/* 1448 */     this.mat[2] = d1;
/* 1449 */     this.mat[3] = 0.0D;
/*      */     
/* 1451 */     this.mat[4] = 0.0D;
/* 1452 */     this.mat[5] = 1.0D;
/* 1453 */     this.mat[6] = 0.0D;
/* 1454 */     this.mat[7] = 0.0D;
/*      */     
/* 1456 */     this.mat[8] = -d1;
/* 1457 */     this.mat[9] = 0.0D;
/* 1458 */     this.mat[10] = d2;
/* 1459 */     this.mat[11] = 0.0D;
/*      */     
/* 1461 */     this.mat[12] = 0.0D;
/* 1462 */     this.mat[13] = 0.0D;
/* 1463 */     this.mat[14] = 0.0D;
/* 1464 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1467 */     if (isInfOrNaN(paramDouble)) {
/* 1468 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1472 */     this.type = 1073742048;
/* 1473 */     this.dirtyBits = 112;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rotZ(double paramDouble) {
/* 1484 */     double d1 = Math.sin(paramDouble);
/* 1485 */     double d2 = Math.cos(paramDouble);
/*      */     
/* 1487 */     this.mat[0] = d2;
/* 1488 */     this.mat[1] = -d1;
/* 1489 */     this.mat[2] = 0.0D;
/* 1490 */     this.mat[3] = 0.0D;
/*      */     
/* 1492 */     this.mat[4] = d1;
/* 1493 */     this.mat[5] = d2;
/* 1494 */     this.mat[6] = 0.0D;
/* 1495 */     this.mat[7] = 0.0D;
/*      */     
/* 1497 */     this.mat[8] = 0.0D;
/* 1498 */     this.mat[9] = 0.0D;
/* 1499 */     this.mat[10] = 1.0D;
/* 1500 */     this.mat[11] = 0.0D;
/*      */     
/* 1502 */     this.mat[12] = 0.0D;
/* 1503 */     this.mat[13] = 0.0D;
/* 1504 */     this.mat[14] = 0.0D;
/* 1505 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1508 */     if (isInfOrNaN(paramDouble)) {
/* 1509 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1513 */     this.type = 1073742048;
/* 1514 */     this.dirtyBits = 112;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Vector3f paramVector3f) {
/* 1525 */     this.mat[0] = 1.0D; this.mat[1] = 0.0D; this.mat[2] = 0.0D; this.mat[3] = paramVector3f.x;
/* 1526 */     this.mat[4] = 0.0D; this.mat[5] = 1.0D; this.mat[6] = 0.0D; this.mat[7] = paramVector3f.y;
/* 1527 */     this.mat[8] = 0.0D; this.mat[9] = 0.0D; this.mat[10] = 1.0D; this.mat[11] = paramVector3f.z;
/* 1528 */     this.mat[12] = 0.0D; this.mat[13] = 0.0D; this.mat[14] = 0.0D; this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1531 */     if (isInfOrNaN(paramVector3f)) {
/* 1532 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1536 */     this.type = 1073742048;
/* 1537 */     this.dirtyBits = 112;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Vector3d paramVector3d) {
/* 1547 */     this.mat[0] = 1.0D; this.mat[1] = 0.0D; this.mat[2] = 0.0D; this.mat[3] = paramVector3d.x;
/* 1548 */     this.mat[4] = 0.0D; this.mat[5] = 1.0D; this.mat[6] = 0.0D; this.mat[7] = paramVector3d.y;
/* 1549 */     this.mat[8] = 0.0D; this.mat[9] = 0.0D; this.mat[10] = 1.0D; this.mat[11] = paramVector3d.z;
/* 1550 */     this.mat[12] = 0.0D; this.mat[13] = 0.0D; this.mat[14] = 0.0D; this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1553 */     if (isInfOrNaN(paramVector3d)) {
/* 1554 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1558 */     this.type = 1073742048;
/* 1559 */     this.dirtyBits = 112;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setScale(double paramDouble) {
/* 1569 */     if ((this.dirtyBits & 0x40) != 0) {
/* 1570 */       computeScaleRotation(false);
/*      */     }
/*      */     
/* 1573 */     this.scales[2] = paramDouble; this.scales[1] = paramDouble; this.scales[0] = paramDouble;
/* 1574 */     this.mat[0] = this.rot[0] * paramDouble;
/* 1575 */     this.mat[1] = this.rot[1] * paramDouble;
/* 1576 */     this.mat[2] = this.rot[2] * paramDouble;
/* 1577 */     this.mat[4] = this.rot[3] * paramDouble;
/* 1578 */     this.mat[5] = this.rot[4] * paramDouble;
/* 1579 */     this.mat[6] = this.rot[5] * paramDouble;
/* 1580 */     this.mat[8] = this.rot[6] * paramDouble;
/* 1581 */     this.mat[9] = this.rot[7] * paramDouble;
/* 1582 */     this.mat[10] = this.rot[8] * paramDouble;
/*      */ 
/*      */     
/* 1585 */     if (isInfOrNaN(paramDouble)) {
/* 1586 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1590 */     this.dirtyBits |= 0x9C;
/* 1591 */     this.dirtyBits &= 0xFFFFFFDF;
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
/*      */   public final void setScale(Vector3d paramVector3d) {
/* 1603 */     if ((this.dirtyBits & 0x40) != 0) {
/* 1604 */       computeScaleRotation(false);
/*      */     }
/*      */     
/* 1607 */     this.scales[0] = paramVector3d.x;
/* 1608 */     this.scales[1] = paramVector3d.y;
/* 1609 */     this.scales[2] = paramVector3d.z;
/*      */     
/* 1611 */     this.mat[0] = this.rot[0] * paramVector3d.x;
/* 1612 */     this.mat[1] = this.rot[1] * paramVector3d.y;
/* 1613 */     this.mat[2] = this.rot[2] * paramVector3d.z;
/* 1614 */     this.mat[4] = this.rot[3] * paramVector3d.x;
/* 1615 */     this.mat[5] = this.rot[4] * paramVector3d.y;
/* 1616 */     this.mat[6] = this.rot[5] * paramVector3d.z;
/* 1617 */     this.mat[8] = this.rot[6] * paramVector3d.x;
/* 1618 */     this.mat[9] = this.rot[7] * paramVector3d.y;
/* 1619 */     this.mat[10] = this.rot[8] * paramVector3d.z;
/*      */ 
/*      */     
/* 1622 */     if (isInfOrNaN(paramVector3d)) {
/* 1623 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1627 */     this.dirtyBits |= 0x9C;
/* 1628 */     this.dirtyBits &= 0xFFFFFFDF;
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
/*      */   public final void setNonUniformScale(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 1644 */     if (this.scales == null) {
/* 1645 */       this.scales = new double[3];
/*      */     }
/* 1647 */     this.scales[0] = paramDouble1;
/* 1648 */     this.scales[1] = paramDouble2;
/* 1649 */     this.scales[2] = paramDouble3;
/* 1650 */     this.mat[0] = paramDouble1;
/* 1651 */     this.mat[1] = 0.0D;
/* 1652 */     this.mat[2] = 0.0D;
/* 1653 */     this.mat[3] = 0.0D;
/* 1654 */     this.mat[4] = 0.0D;
/* 1655 */     this.mat[5] = paramDouble2;
/* 1656 */     this.mat[6] = 0.0D;
/* 1657 */     this.mat[7] = 0.0D;
/* 1658 */     this.mat[8] = 0.0D;
/* 1659 */     this.mat[9] = 0.0D;
/* 1660 */     this.mat[10] = paramDouble3;
/* 1661 */     this.mat[11] = 0.0D;
/* 1662 */     this.mat[12] = 0.0D;
/* 1663 */     this.mat[13] = 0.0D;
/* 1664 */     this.mat[14] = 0.0D;
/* 1665 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1668 */     this.dirtyBits = 255;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setTranslation(Vector3f paramVector3f) {
/* 1678 */     this.mat[3] = paramVector3f.x;
/* 1679 */     this.mat[7] = paramVector3f.y;
/* 1680 */     this.mat[11] = paramVector3f.z;
/*      */ 
/*      */     
/* 1683 */     if (isInfOrNaN(paramVector3f)) {
/* 1684 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1689 */     this.type &= 0xFFFFFFE0;
/* 1690 */     this.dirtyBits |= 0x10;
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
/* 1701 */     this.mat[3] = paramVector3d.x;
/* 1702 */     this.mat[7] = paramVector3d.y;
/* 1703 */     this.mat[11] = paramVector3d.z;
/*      */ 
/*      */     
/* 1706 */     if (isInfOrNaN(paramVector3d)) {
/* 1707 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 1711 */     this.type &= 0xFFFFFFE0;
/* 1712 */     this.dirtyBits |= 0x10;
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
/* 1724 */     if (this.scales == null) {
/* 1725 */       this.scales = new double[3];
/*      */     }
/* 1727 */     this.scales[2] = paramDouble; this.scales[1] = paramDouble; this.scales[0] = paramDouble;
/*      */     
/* 1729 */     this.mat[0] = (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z) * paramDouble;
/* 1730 */     this.mat[4] = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z) * paramDouble;
/* 1731 */     this.mat[8] = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y) * paramDouble;
/*      */     
/* 1733 */     this.mat[1] = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z) * paramDouble;
/* 1734 */     this.mat[5] = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z) * paramDouble;
/* 1735 */     this.mat[9] = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x) * paramDouble;
/*      */     
/* 1737 */     this.mat[2] = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y) * paramDouble;
/* 1738 */     this.mat[6] = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x) * paramDouble;
/* 1739 */     this.mat[10] = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y) * paramDouble;
/*      */     
/* 1741 */     this.mat[3] = paramVector3d.x;
/* 1742 */     this.mat[7] = paramVector3d.y;
/* 1743 */     this.mat[11] = paramVector3d.z;
/* 1744 */     this.mat[12] = 0.0D;
/* 1745 */     this.mat[13] = 0.0D;
/* 1746 */     this.mat[14] = 0.0D;
/* 1747 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1750 */     this.dirtyBits = 255;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4f paramQuat4f, Vector3d paramVector3d, double paramDouble) {
/* 1761 */     if (this.scales == null) {
/* 1762 */       this.scales = new double[3];
/*      */     }
/* 1764 */     this.scales[2] = paramDouble; this.scales[1] = paramDouble; this.scales[0] = paramDouble;
/*      */     
/* 1766 */     this.mat[0] = (1.0F - 2.0F * paramQuat4f.y * paramQuat4f.y - 2.0F * paramQuat4f.z * paramQuat4f.z) * paramDouble;
/* 1767 */     this.mat[4] = (2.0F * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z)) * paramDouble;
/* 1768 */     this.mat[8] = (2.0F * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y)) * paramDouble;
/*      */     
/* 1770 */     this.mat[1] = (2.0F * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z)) * paramDouble;
/* 1771 */     this.mat[5] = (1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.z * paramQuat4f.z) * paramDouble;
/* 1772 */     this.mat[9] = (2.0F * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x)) * paramDouble;
/*      */     
/* 1774 */     this.mat[2] = (2.0F * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y)) * paramDouble;
/* 1775 */     this.mat[6] = (2.0F * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x)) * paramDouble;
/* 1776 */     this.mat[10] = (1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.y * paramQuat4f.y) * paramDouble;
/*      */     
/* 1778 */     this.mat[3] = paramVector3d.x;
/* 1779 */     this.mat[7] = paramVector3d.y;
/* 1780 */     this.mat[11] = paramVector3d.z;
/* 1781 */     this.mat[12] = 0.0D;
/* 1782 */     this.mat[13] = 0.0D;
/* 1783 */     this.mat[14] = 0.0D;
/* 1784 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1787 */     this.dirtyBits = 255;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Quat4f paramQuat4f, Vector3f paramVector3f, float paramFloat) {
/* 1798 */     if (this.scales == null) {
/* 1799 */       this.scales = new double[3];
/*      */     }
/* 1801 */     this.scales[2] = paramFloat; this.scales[1] = paramFloat; this.scales[0] = paramFloat;
/*      */     
/* 1803 */     this.mat[0] = ((1.0F - 2.0F * paramQuat4f.y * paramQuat4f.y - 2.0F * paramQuat4f.z * paramQuat4f.z) * paramFloat);
/* 1804 */     this.mat[4] = (2.0F * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z) * paramFloat);
/* 1805 */     this.mat[8] = (2.0F * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y) * paramFloat);
/*      */     
/* 1807 */     this.mat[1] = (2.0F * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z) * paramFloat);
/* 1808 */     this.mat[5] = ((1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.z * paramQuat4f.z) * paramFloat);
/* 1809 */     this.mat[9] = (2.0F * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x) * paramFloat);
/*      */     
/* 1811 */     this.mat[2] = (2.0F * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y) * paramFloat);
/* 1812 */     this.mat[6] = (2.0F * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x) * paramFloat);
/* 1813 */     this.mat[10] = ((1.0F - 2.0F * paramQuat4f.x * paramQuat4f.x - 2.0F * paramQuat4f.y * paramQuat4f.y) * paramFloat);
/*      */     
/* 1815 */     this.mat[3] = paramVector3f.x;
/* 1816 */     this.mat[7] = paramVector3f.y;
/* 1817 */     this.mat[11] = paramVector3f.z;
/* 1818 */     this.mat[12] = 0.0D;
/* 1819 */     this.mat[13] = 0.0D;
/* 1820 */     this.mat[14] = 0.0D;
/* 1821 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1824 */     this.dirtyBits = 255;
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
/*      */   public final void set(Matrix3f paramMatrix3f, Vector3f paramVector3f, float paramFloat) {
/* 1838 */     this.mat[0] = (paramMatrix3f.m00 * paramFloat);
/* 1839 */     this.mat[1] = (paramMatrix3f.m01 * paramFloat);
/* 1840 */     this.mat[2] = (paramMatrix3f.m02 * paramFloat);
/* 1841 */     this.mat[3] = paramVector3f.x;
/* 1842 */     this.mat[4] = (paramMatrix3f.m10 * paramFloat);
/* 1843 */     this.mat[5] = (paramMatrix3f.m11 * paramFloat);
/* 1844 */     this.mat[6] = (paramMatrix3f.m12 * paramFloat);
/* 1845 */     this.mat[7] = paramVector3f.y;
/* 1846 */     this.mat[8] = (paramMatrix3f.m20 * paramFloat);
/* 1847 */     this.mat[9] = (paramMatrix3f.m21 * paramFloat);
/* 1848 */     this.mat[10] = (paramMatrix3f.m22 * paramFloat);
/* 1849 */     this.mat[11] = paramVector3f.z;
/* 1850 */     this.mat[12] = 0.0D;
/* 1851 */     this.mat[13] = 0.0D;
/* 1852 */     this.mat[14] = 0.0D;
/* 1853 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1856 */     this.dirtyBits = 255;
/*      */     
/* 1858 */     if (this.autoNormalize)
/*      */     {
/* 1860 */       normalize();
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
/*      */   public final void set(Matrix3f paramMatrix3f, Vector3d paramVector3d, double paramDouble) {
/* 1875 */     this.mat[0] = paramMatrix3f.m00 * paramDouble;
/* 1876 */     this.mat[1] = paramMatrix3f.m01 * paramDouble;
/* 1877 */     this.mat[2] = paramMatrix3f.m02 * paramDouble;
/* 1878 */     this.mat[3] = paramVector3d.x;
/* 1879 */     this.mat[4] = paramMatrix3f.m10 * paramDouble;
/* 1880 */     this.mat[5] = paramMatrix3f.m11 * paramDouble;
/* 1881 */     this.mat[6] = paramMatrix3f.m12 * paramDouble;
/* 1882 */     this.mat[7] = paramVector3d.y;
/* 1883 */     this.mat[8] = paramMatrix3f.m20 * paramDouble;
/* 1884 */     this.mat[9] = paramMatrix3f.m21 * paramDouble;
/* 1885 */     this.mat[10] = paramMatrix3f.m22 * paramDouble;
/* 1886 */     this.mat[11] = paramVector3d.z;
/* 1887 */     this.mat[12] = 0.0D;
/* 1888 */     this.mat[13] = 0.0D;
/* 1889 */     this.mat[14] = 0.0D;
/* 1890 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1893 */     this.dirtyBits = 255;
/*      */     
/* 1895 */     if (this.autoNormalize) {
/* 1896 */       normalize();
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
/*      */   public final void set(Matrix3d paramMatrix3d, Vector3d paramVector3d, double paramDouble) {
/* 1911 */     this.mat[0] = paramMatrix3d.m00 * paramDouble;
/* 1912 */     this.mat[1] = paramMatrix3d.m01 * paramDouble;
/* 1913 */     this.mat[2] = paramMatrix3d.m02 * paramDouble;
/* 1914 */     this.mat[3] = paramVector3d.x;
/* 1915 */     this.mat[4] = paramMatrix3d.m10 * paramDouble;
/* 1916 */     this.mat[5] = paramMatrix3d.m11 * paramDouble;
/* 1917 */     this.mat[6] = paramMatrix3d.m12 * paramDouble;
/* 1918 */     this.mat[7] = paramVector3d.y;
/* 1919 */     this.mat[8] = paramMatrix3d.m20 * paramDouble;
/* 1920 */     this.mat[9] = paramMatrix3d.m21 * paramDouble;
/* 1921 */     this.mat[10] = paramMatrix3d.m22 * paramDouble;
/* 1922 */     this.mat[11] = paramVector3d.z;
/* 1923 */     this.mat[12] = 0.0D;
/* 1924 */     this.mat[13] = 0.0D;
/* 1925 */     this.mat[14] = 0.0D;
/* 1926 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 1929 */     this.dirtyBits = 255;
/*      */     
/* 1931 */     if (this.autoNormalize) {
/* 1932 */       normalize();
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
/*      */   public final void set(GMatrix paramGMatrix) {
/* 1946 */     int i = paramGMatrix.getNumRow();
/* 1947 */     int j = paramGMatrix.getNumCol();
/*      */     
/* 1949 */     for (byte b = 0; b < 4; b++) {
/* 1950 */       byte b2 = b * 4;
/* 1951 */       for (byte b1 = 0; b1 < 4; b1++) {
/* 1952 */         if (b >= i || b1 >= j) {
/* 1953 */           this.mat[b2 + b1] = 0.0D;
/*      */         } else {
/* 1955 */           this.mat[b2 + b1] = paramGMatrix.getElement(b, b1);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1959 */     this.dirtyBits = 255;
/*      */     
/* 1961 */     if (this.autoNormalize) {
/* 1962 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(Transform3D paramTransform3D) {
/* 1973 */     this.mat[0] = paramTransform3D.mat[0];
/* 1974 */     this.mat[1] = paramTransform3D.mat[1];
/* 1975 */     this.mat[2] = paramTransform3D.mat[2];
/* 1976 */     this.mat[3] = paramTransform3D.mat[3];
/* 1977 */     this.mat[4] = paramTransform3D.mat[4];
/* 1978 */     this.mat[5] = paramTransform3D.mat[5];
/* 1979 */     this.mat[6] = paramTransform3D.mat[6];
/* 1980 */     this.mat[7] = paramTransform3D.mat[7];
/* 1981 */     this.mat[8] = paramTransform3D.mat[8];
/* 1982 */     this.mat[9] = paramTransform3D.mat[9];
/* 1983 */     this.mat[10] = paramTransform3D.mat[10];
/* 1984 */     this.mat[11] = paramTransform3D.mat[11];
/* 1985 */     this.mat[12] = paramTransform3D.mat[12];
/* 1986 */     this.mat[13] = paramTransform3D.mat[13];
/* 1987 */     this.mat[14] = paramTransform3D.mat[14];
/* 1988 */     this.mat[15] = paramTransform3D.mat[15];
/* 1989 */     this.type = paramTransform3D.type;
/*      */ 
/*      */     
/* 1992 */     this.dirtyBits = paramTransform3D.dirtyBits | 0x40 | 0x20;
/* 1993 */     this.autoNormalize = paramTransform3D.autoNormalize;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1998 */   void setWithLock(Transform3D paramTransform3D) { set(paramTransform3D); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2003 */   void getWithLock(Transform3D paramTransform3D) { paramTransform3D.set(this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void set(double[] paramArrayOfDouble) {
/* 2013 */     this.mat[0] = paramArrayOfDouble[0];
/* 2014 */     this.mat[1] = paramArrayOfDouble[1];
/* 2015 */     this.mat[2] = paramArrayOfDouble[2];
/* 2016 */     this.mat[3] = paramArrayOfDouble[3];
/* 2017 */     this.mat[4] = paramArrayOfDouble[4];
/* 2018 */     this.mat[5] = paramArrayOfDouble[5];
/* 2019 */     this.mat[6] = paramArrayOfDouble[6];
/* 2020 */     this.mat[7] = paramArrayOfDouble[7];
/* 2021 */     this.mat[8] = paramArrayOfDouble[8];
/* 2022 */     this.mat[9] = paramArrayOfDouble[9];
/* 2023 */     this.mat[10] = paramArrayOfDouble[10];
/* 2024 */     this.mat[11] = paramArrayOfDouble[11];
/* 2025 */     this.mat[12] = paramArrayOfDouble[12];
/* 2026 */     this.mat[13] = paramArrayOfDouble[13];
/* 2027 */     this.mat[14] = paramArrayOfDouble[14];
/* 2028 */     this.mat[15] = paramArrayOfDouble[15];
/*      */     
/* 2030 */     this.dirtyBits = 255;
/*      */     
/* 2032 */     if (this.autoNormalize) {
/* 2033 */       normalize();
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
/*      */   public final void set(float[] paramArrayOfFloat) {
/* 2045 */     this.mat[0] = paramArrayOfFloat[0];
/* 2046 */     this.mat[1] = paramArrayOfFloat[1];
/* 2047 */     this.mat[2] = paramArrayOfFloat[2];
/* 2048 */     this.mat[3] = paramArrayOfFloat[3];
/* 2049 */     this.mat[4] = paramArrayOfFloat[4];
/* 2050 */     this.mat[5] = paramArrayOfFloat[5];
/* 2051 */     this.mat[6] = paramArrayOfFloat[6];
/* 2052 */     this.mat[7] = paramArrayOfFloat[7];
/* 2053 */     this.mat[8] = paramArrayOfFloat[8];
/* 2054 */     this.mat[9] = paramArrayOfFloat[9];
/* 2055 */     this.mat[10] = paramArrayOfFloat[10];
/* 2056 */     this.mat[11] = paramArrayOfFloat[11];
/* 2057 */     this.mat[12] = paramArrayOfFloat[12];
/* 2058 */     this.mat[13] = paramArrayOfFloat[13];
/* 2059 */     this.mat[14] = paramArrayOfFloat[14];
/* 2060 */     this.mat[15] = paramArrayOfFloat[15];
/*      */     
/* 2062 */     this.dirtyBits = 255;
/*      */     
/* 2064 */     if (this.autoNormalize) {
/* 2065 */       normalize();
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
/*      */   public final void set(Matrix4d paramMatrix4d) {
/* 2077 */     this.mat[0] = paramMatrix4d.m00;
/* 2078 */     this.mat[1] = paramMatrix4d.m01;
/* 2079 */     this.mat[2] = paramMatrix4d.m02;
/* 2080 */     this.mat[3] = paramMatrix4d.m03;
/* 2081 */     this.mat[4] = paramMatrix4d.m10;
/* 2082 */     this.mat[5] = paramMatrix4d.m11;
/* 2083 */     this.mat[6] = paramMatrix4d.m12;
/* 2084 */     this.mat[7] = paramMatrix4d.m13;
/* 2085 */     this.mat[8] = paramMatrix4d.m20;
/* 2086 */     this.mat[9] = paramMatrix4d.m21;
/* 2087 */     this.mat[10] = paramMatrix4d.m22;
/* 2088 */     this.mat[11] = paramMatrix4d.m23;
/* 2089 */     this.mat[12] = paramMatrix4d.m30;
/* 2090 */     this.mat[13] = paramMatrix4d.m31;
/* 2091 */     this.mat[14] = paramMatrix4d.m32;
/* 2092 */     this.mat[15] = paramMatrix4d.m33;
/*      */     
/* 2094 */     this.dirtyBits = 255;
/*      */     
/* 2096 */     if (this.autoNormalize) {
/* 2097 */       normalize();
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
/*      */   public final void set(Matrix4f paramMatrix4f) {
/* 2109 */     this.mat[0] = paramMatrix4f.m00;
/* 2110 */     this.mat[1] = paramMatrix4f.m01;
/* 2111 */     this.mat[2] = paramMatrix4f.m02;
/* 2112 */     this.mat[3] = paramMatrix4f.m03;
/* 2113 */     this.mat[4] = paramMatrix4f.m10;
/* 2114 */     this.mat[5] = paramMatrix4f.m11;
/* 2115 */     this.mat[6] = paramMatrix4f.m12;
/* 2116 */     this.mat[7] = paramMatrix4f.m13;
/* 2117 */     this.mat[8] = paramMatrix4f.m20;
/* 2118 */     this.mat[9] = paramMatrix4f.m21;
/* 2119 */     this.mat[10] = paramMatrix4f.m22;
/* 2120 */     this.mat[11] = paramMatrix4f.m23;
/* 2121 */     this.mat[12] = paramMatrix4f.m30;
/* 2122 */     this.mat[13] = paramMatrix4f.m31;
/* 2123 */     this.mat[14] = paramMatrix4f.m32;
/* 2124 */     this.mat[15] = paramMatrix4f.m33;
/*      */     
/* 2126 */     this.dirtyBits = 255;
/*      */     
/* 2128 */     if (this.autoNormalize) {
/* 2129 */       normalize();
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
/*      */   public final void set(Matrix3f paramMatrix3f) {
/* 2142 */     this.mat[0] = paramMatrix3f.m00;
/* 2143 */     this.mat[1] = paramMatrix3f.m01;
/* 2144 */     this.mat[2] = paramMatrix3f.m02;
/* 2145 */     this.mat[3] = 0.0D;
/* 2146 */     this.mat[4] = paramMatrix3f.m10;
/* 2147 */     this.mat[5] = paramMatrix3f.m11;
/* 2148 */     this.mat[6] = paramMatrix3f.m12;
/* 2149 */     this.mat[7] = 0.0D;
/* 2150 */     this.mat[8] = paramMatrix3f.m20;
/* 2151 */     this.mat[9] = paramMatrix3f.m21;
/* 2152 */     this.mat[10] = paramMatrix3f.m22;
/* 2153 */     this.mat[11] = 0.0D;
/* 2154 */     this.mat[12] = 0.0D;
/* 2155 */     this.mat[13] = 0.0D;
/* 2156 */     this.mat[14] = 0.0D;
/* 2157 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 2160 */     this.dirtyBits = 255;
/*      */     
/* 2162 */     if (this.autoNormalize) {
/* 2163 */       normalize();
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
/*      */   public final void set(Matrix3d paramMatrix3d) {
/* 2176 */     this.mat[0] = paramMatrix3d.m00;
/* 2177 */     this.mat[1] = paramMatrix3d.m01;
/* 2178 */     this.mat[2] = paramMatrix3d.m02;
/* 2179 */     this.mat[3] = 0.0D;
/* 2180 */     this.mat[4] = paramMatrix3d.m10;
/* 2181 */     this.mat[5] = paramMatrix3d.m11;
/* 2182 */     this.mat[6] = paramMatrix3d.m12;
/* 2183 */     this.mat[7] = 0.0D;
/* 2184 */     this.mat[8] = paramMatrix3d.m20;
/* 2185 */     this.mat[9] = paramMatrix3d.m21;
/* 2186 */     this.mat[10] = paramMatrix3d.m22;
/* 2187 */     this.mat[11] = 0.0D;
/* 2188 */     this.mat[12] = 0.0D;
/* 2189 */     this.mat[13] = 0.0D;
/* 2190 */     this.mat[14] = 0.0D;
/* 2191 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 2194 */     this.dirtyBits = 255;
/*      */     
/* 2196 */     if (this.autoNormalize) {
/* 2197 */       normalize();
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
/*      */   public final void setEuler(Vector3d paramVector3d) {
/* 2220 */     double d1 = Math.sin(paramVector3d.x);
/* 2221 */     double d2 = Math.sin(paramVector3d.y);
/* 2222 */     double d3 = Math.sin(paramVector3d.z);
/* 2223 */     double d4 = Math.cos(paramVector3d.x);
/* 2224 */     double d5 = Math.cos(paramVector3d.y);
/* 2225 */     double d6 = Math.cos(paramVector3d.z);
/*      */     
/* 2227 */     this.mat[0] = d5 * d6;
/* 2228 */     this.mat[1] = -(d4 * d3) + d1 * d2 * d6;
/* 2229 */     this.mat[2] = d1 * d3 + d4 * d2 * d6;
/* 2230 */     this.mat[3] = 0.0D;
/*      */     
/* 2232 */     this.mat[4] = d5 * d3;
/* 2233 */     this.mat[5] = d4 * d6 + d1 * d2 * d3;
/* 2234 */     this.mat[6] = -(d1 * d6) + d4 * d2 * d3;
/* 2235 */     this.mat[7] = 0.0D;
/*      */     
/* 2237 */     this.mat[8] = -d2;
/* 2238 */     this.mat[9] = d1 * d5;
/* 2239 */     this.mat[10] = d4 * d5;
/* 2240 */     this.mat[11] = 0.0D;
/*      */     
/* 2242 */     this.mat[12] = 0.0D;
/* 2243 */     this.mat[13] = 0.0D;
/* 2244 */     this.mat[14] = 0.0D;
/* 2245 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 2248 */     if (isInfOrNaN(paramVector3d)) {
/* 2249 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 2253 */     this.type = 1073742048;
/* 2254 */     this.dirtyBits = 112;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(double[] paramArrayOfDouble) {
/* 2265 */     paramArrayOfDouble[0] = this.mat[0];
/* 2266 */     paramArrayOfDouble[1] = this.mat[1];
/* 2267 */     paramArrayOfDouble[2] = this.mat[2];
/* 2268 */     paramArrayOfDouble[3] = this.mat[3];
/* 2269 */     paramArrayOfDouble[4] = this.mat[4];
/* 2270 */     paramArrayOfDouble[5] = this.mat[5];
/* 2271 */     paramArrayOfDouble[6] = this.mat[6];
/* 2272 */     paramArrayOfDouble[7] = this.mat[7];
/* 2273 */     paramArrayOfDouble[8] = this.mat[8];
/* 2274 */     paramArrayOfDouble[9] = this.mat[9];
/* 2275 */     paramArrayOfDouble[10] = this.mat[10];
/* 2276 */     paramArrayOfDouble[11] = this.mat[11];
/* 2277 */     paramArrayOfDouble[12] = this.mat[12];
/* 2278 */     paramArrayOfDouble[13] = this.mat[13];
/* 2279 */     paramArrayOfDouble[14] = this.mat[14];
/* 2280 */     paramArrayOfDouble[15] = this.mat[15];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(float[] paramArrayOfFloat) {
/* 2291 */     paramArrayOfFloat[0] = (float)this.mat[0];
/* 2292 */     paramArrayOfFloat[1] = (float)this.mat[1];
/* 2293 */     paramArrayOfFloat[2] = (float)this.mat[2];
/* 2294 */     paramArrayOfFloat[3] = (float)this.mat[3];
/* 2295 */     paramArrayOfFloat[4] = (float)this.mat[4];
/* 2296 */     paramArrayOfFloat[5] = (float)this.mat[5];
/* 2297 */     paramArrayOfFloat[6] = (float)this.mat[6];
/* 2298 */     paramArrayOfFloat[7] = (float)this.mat[7];
/* 2299 */     paramArrayOfFloat[8] = (float)this.mat[8];
/* 2300 */     paramArrayOfFloat[9] = (float)this.mat[9];
/* 2301 */     paramArrayOfFloat[10] = (float)this.mat[10];
/* 2302 */     paramArrayOfFloat[11] = (float)this.mat[11];
/* 2303 */     paramArrayOfFloat[12] = (float)this.mat[12];
/* 2304 */     paramArrayOfFloat[13] = (float)this.mat[13];
/* 2305 */     paramArrayOfFloat[14] = (float)this.mat[14];
/* 2306 */     paramArrayOfFloat[15] = (float)this.mat[15];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Matrix3d paramMatrix3d) {
/* 2316 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2317 */       computeScaleRotation(false);
/*      */     }
/* 2319 */     paramMatrix3d.m00 = this.rot[0];
/* 2320 */     paramMatrix3d.m01 = this.rot[1];
/* 2321 */     paramMatrix3d.m02 = this.rot[2];
/* 2322 */     paramMatrix3d.m10 = this.rot[3];
/* 2323 */     paramMatrix3d.m11 = this.rot[4];
/* 2324 */     paramMatrix3d.m12 = this.rot[5];
/* 2325 */     paramMatrix3d.m20 = this.rot[6];
/* 2326 */     paramMatrix3d.m21 = this.rot[7];
/* 2327 */     paramMatrix3d.m22 = this.rot[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Matrix3f paramMatrix3f) {
/* 2336 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2337 */       computeScaleRotation(false);
/*      */     }
/* 2339 */     paramMatrix3f.m00 = (float)this.rot[0];
/* 2340 */     paramMatrix3f.m01 = (float)this.rot[1];
/* 2341 */     paramMatrix3f.m02 = (float)this.rot[2];
/* 2342 */     paramMatrix3f.m10 = (float)this.rot[3];
/* 2343 */     paramMatrix3f.m11 = (float)this.rot[4];
/* 2344 */     paramMatrix3f.m12 = (float)this.rot[5];
/* 2345 */     paramMatrix3f.m20 = (float)this.rot[6];
/* 2346 */     paramMatrix3f.m21 = (float)this.rot[7];
/* 2347 */     paramMatrix3f.m22 = (float)this.rot[8];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Quat4f paramQuat4f) {
/* 2356 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2357 */       computeScaleRotation(false);
/*      */     }
/*      */     
/* 2360 */     double d = 0.25D * (1.0D + this.rot[0] + this.rot[4] + this.rot[8]);
/* 2361 */     if (((d < 0.0D) ? -d : d) >= 1.0E-10D) {
/* 2362 */       paramQuat4f.w = (float)Math.sqrt(d);
/* 2363 */       d = 0.25D / paramQuat4f.w;
/* 2364 */       paramQuat4f.x = (float)((this.rot[7] - this.rot[5]) * d);
/* 2365 */       paramQuat4f.y = (float)((this.rot[2] - this.rot[6]) * d);
/* 2366 */       paramQuat4f.z = (float)((this.rot[3] - this.rot[1]) * d);
/*      */       
/*      */       return;
/*      */     } 
/* 2370 */     paramQuat4f.w = 0.0F;
/* 2371 */     d = -0.5D * (this.rot[4] + this.rot[8]);
/* 2372 */     if (((d < 0.0D) ? -d : d) >= 1.0E-10D) {
/* 2373 */       paramQuat4f.x = (float)Math.sqrt(d);
/* 2374 */       d = 0.5D / paramQuat4f.x;
/* 2375 */       paramQuat4f.y = (float)(this.rot[3] * d);
/* 2376 */       paramQuat4f.z = (float)(this.rot[6] * d);
/*      */       
/*      */       return;
/*      */     } 
/* 2380 */     paramQuat4f.x = 0.0F;
/* 2381 */     d = 0.5D * (1.0D - this.rot[8]);
/* 2382 */     if (((d < 0.0D) ? -d : d) >= 1.0E-10D) {
/* 2383 */       paramQuat4f.y = (float)Math.sqrt(d);
/* 2384 */       paramQuat4f.z = (float)(this.rot[7] / 2.0D * paramQuat4f.y);
/*      */       
/*      */       return;
/*      */     } 
/* 2388 */     paramQuat4f.y = 0.0F;
/* 2389 */     paramQuat4f.z = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Quat4d paramQuat4d) {
/* 2398 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2399 */       computeScaleRotation(false);
/*      */     }
/*      */     
/* 2402 */     double d = 0.25D * (1.0D + this.rot[0] + this.rot[4] + this.rot[8]);
/* 2403 */     if (((d < 0.0D) ? -d : d) >= 1.0E-10D) {
/* 2404 */       paramQuat4d.w = Math.sqrt(d);
/* 2405 */       d = 0.25D / paramQuat4d.w;
/* 2406 */       paramQuat4d.x = (this.rot[7] - this.rot[5]) * d;
/* 2407 */       paramQuat4d.y = (this.rot[2] - this.rot[6]) * d;
/* 2408 */       paramQuat4d.z = (this.rot[3] - this.rot[1]) * d;
/*      */       
/*      */       return;
/*      */     } 
/* 2412 */     paramQuat4d.w = 0.0D;
/* 2413 */     d = -0.5D * (this.rot[4] + this.rot[8]);
/* 2414 */     if (((d < 0.0D) ? -d : d) >= 1.0E-10D) {
/* 2415 */       paramQuat4d.x = Math.sqrt(d);
/* 2416 */       d = 0.5D / paramQuat4d.x;
/* 2417 */       paramQuat4d.y = this.rot[3] * d;
/* 2418 */       paramQuat4d.z = this.rot[6] * d;
/*      */       
/*      */       return;
/*      */     } 
/* 2422 */     paramQuat4d.x = 0.0D;
/* 2423 */     d = 0.5D * (1.0D - this.rot[8]);
/* 2424 */     if (((d < 0.0D) ? -d : d) >= 1.0E-10D) {
/* 2425 */       paramQuat4d.y = Math.sqrt(d);
/* 2426 */       paramQuat4d.z = this.rot[7] / 2.0D * paramQuat4d.y;
/*      */       
/*      */       return;
/*      */     } 
/* 2430 */     paramQuat4d.y = 0.0D;
/* 2431 */     paramQuat4d.z = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Matrix4d paramMatrix4d) {
/* 2440 */     paramMatrix4d.m00 = this.mat[0];
/* 2441 */     paramMatrix4d.m01 = this.mat[1];
/* 2442 */     paramMatrix4d.m02 = this.mat[2];
/* 2443 */     paramMatrix4d.m03 = this.mat[3];
/* 2444 */     paramMatrix4d.m10 = this.mat[4];
/* 2445 */     paramMatrix4d.m11 = this.mat[5];
/* 2446 */     paramMatrix4d.m12 = this.mat[6];
/* 2447 */     paramMatrix4d.m13 = this.mat[7];
/* 2448 */     paramMatrix4d.m20 = this.mat[8];
/* 2449 */     paramMatrix4d.m21 = this.mat[9];
/* 2450 */     paramMatrix4d.m22 = this.mat[10];
/* 2451 */     paramMatrix4d.m23 = this.mat[11];
/* 2452 */     paramMatrix4d.m30 = this.mat[12];
/* 2453 */     paramMatrix4d.m31 = this.mat[13];
/* 2454 */     paramMatrix4d.m32 = this.mat[14];
/* 2455 */     paramMatrix4d.m33 = this.mat[15];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Matrix4f paramMatrix4f) {
/* 2464 */     paramMatrix4f.m00 = (float)this.mat[0];
/* 2465 */     paramMatrix4f.m01 = (float)this.mat[1];
/* 2466 */     paramMatrix4f.m02 = (float)this.mat[2];
/* 2467 */     paramMatrix4f.m03 = (float)this.mat[3];
/* 2468 */     paramMatrix4f.m10 = (float)this.mat[4];
/* 2469 */     paramMatrix4f.m11 = (float)this.mat[5];
/* 2470 */     paramMatrix4f.m12 = (float)this.mat[6];
/* 2471 */     paramMatrix4f.m13 = (float)this.mat[7];
/* 2472 */     paramMatrix4f.m20 = (float)this.mat[8];
/* 2473 */     paramMatrix4f.m21 = (float)this.mat[9];
/* 2474 */     paramMatrix4f.m22 = (float)this.mat[10];
/* 2475 */     paramMatrix4f.m23 = (float)this.mat[11];
/* 2476 */     paramMatrix4f.m30 = (float)this.mat[12];
/* 2477 */     paramMatrix4f.m31 = (float)this.mat[13];
/* 2478 */     paramMatrix4f.m32 = (float)this.mat[14];
/* 2479 */     paramMatrix4f.m33 = (float)this.mat[15];
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
/*      */   public final double get(Quat4d paramQuat4d, Vector3d paramVector3d) {
/* 2492 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2493 */       computeScaleRotation(false);
/* 2494 */     } else if ((this.dirtyBits & 0x20) != 0) {
/* 2495 */       computeScales(false);
/*      */     } 
/*      */     
/* 2498 */     paramVector3d.x = this.mat[3];
/* 2499 */     paramVector3d.y = this.mat[7];
/* 2500 */     paramVector3d.z = this.mat[11];
/*      */     
/* 2502 */     double d1 = max3(this.scales);
/*      */     
/* 2504 */     double d2 = 0.25D * (1.0D + this.rot[0] + this.rot[4] + this.rot[8]);
/* 2505 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2506 */       paramQuat4d.w = Math.sqrt(d2);
/* 2507 */       d2 = 0.25D / paramQuat4d.w;
/* 2508 */       paramQuat4d.x = (this.rot[7] - this.rot[5]) * d2;
/* 2509 */       paramQuat4d.y = (this.rot[2] - this.rot[6]) * d2;
/* 2510 */       paramQuat4d.z = (this.rot[3] - this.rot[1]) * d2;
/* 2511 */       return d1;
/*      */     } 
/*      */     
/* 2514 */     paramQuat4d.w = 0.0D;
/* 2515 */     d2 = -0.5D * (this.rot[4] + this.rot[8]);
/* 2516 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2517 */       paramQuat4d.x = Math.sqrt(d2);
/* 2518 */       d2 = 0.5D / paramQuat4d.x;
/* 2519 */       paramQuat4d.y = this.rot[3] * d2;
/* 2520 */       paramQuat4d.z = this.rot[6] * d2;
/* 2521 */       return d1;
/*      */     } 
/*      */     
/* 2524 */     paramQuat4d.x = 0.0D;
/* 2525 */     d2 = 0.5D * (1.0D - this.rot[8]);
/* 2526 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2527 */       paramQuat4d.y = Math.sqrt(d2);
/* 2528 */       paramQuat4d.z = this.rot[7] / 2.0D * paramQuat4d.y;
/* 2529 */       return d1;
/*      */     } 
/*      */     
/* 2532 */     paramQuat4d.y = 0.0D;
/* 2533 */     paramQuat4d.z = 1.0D;
/* 2534 */     return d1;
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
/*      */   public final float get(Quat4f paramQuat4f, Vector3f paramVector3f) {
/* 2548 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2549 */       computeScaleRotation(false);
/* 2550 */     } else if ((this.dirtyBits & 0x20) != 0) {
/* 2551 */       computeScales(false);
/*      */     } 
/*      */     
/* 2554 */     double d1 = max3(this.scales);
/* 2555 */     paramVector3f.x = (float)this.mat[3];
/* 2556 */     paramVector3f.y = (float)this.mat[7];
/* 2557 */     paramVector3f.z = (float)this.mat[11];
/*      */     
/* 2559 */     double d2 = 0.25D * (1.0D + this.rot[0] + this.rot[4] + this.rot[8]);
/* 2560 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2561 */       paramQuat4f.w = (float)Math.sqrt(d2);
/* 2562 */       d2 = 0.25D / paramQuat4f.w;
/* 2563 */       paramQuat4f.x = (float)((this.rot[7] - this.rot[5]) * d2);
/* 2564 */       paramQuat4f.y = (float)((this.rot[2] - this.rot[6]) * d2);
/* 2565 */       paramQuat4f.z = (float)((this.rot[3] - this.rot[1]) * d2);
/* 2566 */       return (float)d1;
/*      */     } 
/*      */     
/* 2569 */     paramQuat4f.w = 0.0F;
/* 2570 */     d2 = -0.5D * (this.rot[4] + this.rot[8]);
/* 2571 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2572 */       paramQuat4f.x = (float)Math.sqrt(d2);
/* 2573 */       d2 = 0.5D / paramQuat4f.x;
/* 2574 */       paramQuat4f.y = (float)(this.rot[3] * d2);
/* 2575 */       paramQuat4f.z = (float)(this.rot[6] * d2);
/* 2576 */       return (float)d1;
/*      */     } 
/*      */     
/* 2579 */     paramQuat4f.x = 0.0F;
/* 2580 */     d2 = 0.5D * (1.0D - this.rot[8]);
/* 2581 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2582 */       paramQuat4f.y = (float)Math.sqrt(d2);
/* 2583 */       paramQuat4f.z = (float)(this.rot[7] / 2.0D * paramQuat4f.y);
/* 2584 */       return (float)d1;
/*      */     } 
/*      */     
/* 2587 */     paramQuat4f.y = 0.0F;
/* 2588 */     paramQuat4f.z = 1.0F;
/* 2589 */     return (float)d1;
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
/*      */   public final double get(Quat4f paramQuat4f, Vector3d paramVector3d) {
/* 2602 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2603 */       computeScaleRotation(false);
/* 2604 */     } else if ((this.dirtyBits & 0x20) != 0) {
/* 2605 */       computeScales(false);
/*      */     } 
/*      */     
/* 2608 */     double d1 = max3(this.scales);
/*      */     
/* 2610 */     paramVector3d.x = this.mat[3];
/* 2611 */     paramVector3d.y = this.mat[7];
/* 2612 */     paramVector3d.z = this.mat[11];
/*      */     
/* 2614 */     double d2 = 0.25D * (1.0D + this.rot[0] + this.rot[4] + this.rot[8]);
/* 2615 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2616 */       paramQuat4f.w = (float)Math.sqrt(d2);
/* 2617 */       d2 = 0.25D / paramQuat4f.w;
/* 2618 */       paramQuat4f.x = (float)((this.rot[7] - this.rot[5]) * d2);
/* 2619 */       paramQuat4f.y = (float)((this.rot[2] - this.rot[6]) * d2);
/* 2620 */       paramQuat4f.z = (float)((this.rot[3] - this.rot[1]) * d2);
/* 2621 */       return d1;
/*      */     } 
/*      */     
/* 2624 */     paramQuat4f.w = 0.0F;
/* 2625 */     d2 = -0.5D * (this.rot[4] + this.rot[8]);
/* 2626 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2627 */       paramQuat4f.x = (float)Math.sqrt(d2);
/* 2628 */       d2 = 0.5D / paramQuat4f.x;
/* 2629 */       paramQuat4f.y = (float)(this.rot[3] * d2);
/* 2630 */       paramQuat4f.z = (float)(this.rot[6] * d2);
/* 2631 */       return d1;
/*      */     } 
/*      */     
/* 2634 */     paramQuat4f.x = 0.0F;
/* 2635 */     d2 = 0.5D * (1.0D - this.rot[8]);
/* 2636 */     if (((d2 < 0.0D) ? -d2 : d2) >= 1.0E-10D) {
/* 2637 */       paramQuat4f.y = (float)Math.sqrt(d2);
/* 2638 */       paramQuat4f.z = (float)(this.rot[7] / 2.0D * paramQuat4f.y);
/* 2639 */       return d1;
/*      */     } 
/*      */     
/* 2642 */     paramQuat4f.y = 0.0F;
/* 2643 */     paramQuat4f.z = 1.0F;
/* 2644 */     return d1;
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
/*      */   public final double get(Matrix3d paramMatrix3d, Vector3d paramVector3d) {
/* 2657 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2658 */       computeScaleRotation(false);
/* 2659 */     } else if ((this.dirtyBits & 0x20) != 0) {
/* 2660 */       computeScales(false);
/*      */     } 
/*      */     
/* 2663 */     paramVector3d.x = this.mat[3];
/* 2664 */     paramVector3d.y = this.mat[7];
/* 2665 */     paramVector3d.z = this.mat[11];
/*      */     
/* 2667 */     paramMatrix3d.m00 = this.rot[0];
/* 2668 */     paramMatrix3d.m01 = this.rot[1];
/* 2669 */     paramMatrix3d.m02 = this.rot[2];
/*      */     
/* 2671 */     paramMatrix3d.m10 = this.rot[3];
/* 2672 */     paramMatrix3d.m11 = this.rot[4];
/* 2673 */     paramMatrix3d.m12 = this.rot[5];
/*      */     
/* 2675 */     paramMatrix3d.m20 = this.rot[6];
/* 2676 */     paramMatrix3d.m21 = this.rot[7];
/* 2677 */     paramMatrix3d.m22 = this.rot[8];
/*      */     
/* 2679 */     return max3(this.scales);
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
/*      */   public final float get(Matrix3f paramMatrix3f, Vector3f paramVector3f) {
/* 2693 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2694 */       computeScaleRotation(false);
/* 2695 */     } else if ((this.dirtyBits & 0x20) != 0) {
/* 2696 */       computeScales(false);
/*      */     } 
/*      */     
/* 2699 */     paramVector3f.x = (float)this.mat[3];
/* 2700 */     paramVector3f.y = (float)this.mat[7];
/* 2701 */     paramVector3f.z = (float)this.mat[11];
/*      */     
/* 2703 */     paramMatrix3f.m00 = (float)this.rot[0];
/* 2704 */     paramMatrix3f.m01 = (float)this.rot[1];
/* 2705 */     paramMatrix3f.m02 = (float)this.rot[2];
/*      */     
/* 2707 */     paramMatrix3f.m10 = (float)this.rot[3];
/* 2708 */     paramMatrix3f.m11 = (float)this.rot[4];
/* 2709 */     paramMatrix3f.m12 = (float)this.rot[5];
/*      */     
/* 2711 */     paramMatrix3f.m20 = (float)this.rot[6];
/* 2712 */     paramMatrix3f.m21 = (float)this.rot[7];
/* 2713 */     paramMatrix3f.m22 = (float)this.rot[8];
/*      */     
/* 2715 */     return (float)max3(this.scales);
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
/*      */   public final double get(Matrix3f paramMatrix3f, Vector3d paramVector3d) {
/* 2728 */     if ((this.dirtyBits & 0x40) != 0) {
/* 2729 */       computeScaleRotation(false);
/* 2730 */     } else if ((this.dirtyBits & 0x20) != 0) {
/* 2731 */       computeScales(false);
/*      */     } 
/*      */     
/* 2734 */     paramVector3d.x = this.mat[3];
/* 2735 */     paramVector3d.y = this.mat[7];
/* 2736 */     paramVector3d.z = this.mat[11];
/*      */     
/* 2738 */     paramMatrix3f.m00 = (float)this.rot[0];
/* 2739 */     paramMatrix3f.m01 = (float)this.rot[1];
/* 2740 */     paramMatrix3f.m02 = (float)this.rot[2];
/*      */     
/* 2742 */     paramMatrix3f.m10 = (float)this.rot[3];
/* 2743 */     paramMatrix3f.m11 = (float)this.rot[4];
/* 2744 */     paramMatrix3f.m12 = (float)this.rot[5];
/*      */     
/* 2746 */     paramMatrix3f.m20 = (float)this.rot[6];
/* 2747 */     paramMatrix3f.m21 = (float)this.rot[7];
/* 2748 */     paramMatrix3f.m22 = (float)this.rot[8];
/*      */     
/* 2750 */     return max3(this.scales);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getScale() {
/* 2761 */     if ((this.dirtyBits & 0x20) != 0) {
/* 2762 */       computeScales(false);
/*      */     }
/* 2764 */     return max3(this.scales);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getScale(Vector3d paramVector3d) {
/* 2774 */     if ((this.dirtyBits & 0x20) != 0) {
/* 2775 */       computeScales(false);
/*      */     }
/* 2777 */     paramVector3d.x = this.scales[0];
/* 2778 */     paramVector3d.y = this.scales[1];
/* 2779 */     paramVector3d.z = this.scales[2];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Vector3f paramVector3f) {
/* 2788 */     paramVector3f.x = (float)this.mat[3];
/* 2789 */     paramVector3f.y = (float)this.mat[7];
/* 2790 */     paramVector3f.z = (float)this.mat[11];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void get(Vector3d paramVector3d) {
/* 2799 */     paramVector3d.x = this.mat[3];
/* 2800 */     paramVector3d.y = this.mat[7];
/* 2801 */     paramVector3d.z = this.mat[11];
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
/*      */   public final void invert(Transform3D paramTransform3D) {
/* 2813 */     if (paramTransform3D == this) {
/* 2814 */       invert();
/* 2815 */     } else if (paramTransform3D.isAffine()) {
/*      */ 
/*      */       
/* 2818 */       invertAffine(paramTransform3D);
/*      */     } else {
/* 2820 */       invertGeneral(paramTransform3D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void invert() {
/* 2831 */     if (isAffine()) {
/* 2832 */       invertAffine();
/*      */     } else {
/* 2834 */       invertGeneral(this);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void invertAffine(Transform3D paramTransform3D) {
/* 2961 */     double d1 = paramTransform3D.affineDeterminant();
/*      */     
/* 2963 */     if (d1 == 0.0D) {
/* 2964 */       throw new SingularMatrixException(J3dI18N.getString("Transform3D1"));
/*      */     }
/*      */     
/* 2967 */     double d2 = (paramTransform3D.mat[0] * paramTransform3D.mat[0] + paramTransform3D.mat[1] * paramTransform3D.mat[1] + paramTransform3D.mat[2] * paramTransform3D.mat[2] + paramTransform3D.mat[3] * paramTransform3D.mat[3]) * (paramTransform3D.mat[4] * paramTransform3D.mat[4] + paramTransform3D.mat[5] * paramTransform3D.mat[5] + paramTransform3D.mat[6] * paramTransform3D.mat[6] + paramTransform3D.mat[7] * paramTransform3D.mat[7]) * (paramTransform3D.mat[8] * paramTransform3D.mat[8] + paramTransform3D.mat[9] * paramTransform3D.mat[9] + paramTransform3D.mat[10] * paramTransform3D.mat[10] + paramTransform3D.mat[11] * paramTransform3D.mat[11]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2974 */     if (d1 * d1 < 1.110223024E-16D * d2) {
/*      */ 
/*      */       
/* 2977 */       invertGeneral(paramTransform3D);
/*      */       return;
/*      */     } 
/* 2980 */     d2 = 1.0D / d1;
/*      */     
/* 2982 */     this.mat[0] = (paramTransform3D.mat[5] * paramTransform3D.mat[10] - paramTransform3D.mat[9] * paramTransform3D.mat[6]) * d2;
/* 2983 */     this.mat[1] = -(paramTransform3D.mat[1] * paramTransform3D.mat[10] - paramTransform3D.mat[9] * paramTransform3D.mat[2]) * d2;
/* 2984 */     this.mat[2] = (paramTransform3D.mat[1] * paramTransform3D.mat[6] - paramTransform3D.mat[5] * paramTransform3D.mat[2]) * d2;
/* 2985 */     this.mat[4] = -(paramTransform3D.mat[4] * paramTransform3D.mat[10] - paramTransform3D.mat[8] * paramTransform3D.mat[6]) * d2;
/* 2986 */     this.mat[5] = (paramTransform3D.mat[0] * paramTransform3D.mat[10] - paramTransform3D.mat[8] * paramTransform3D.mat[2]) * d2;
/* 2987 */     this.mat[6] = -(paramTransform3D.mat[0] * paramTransform3D.mat[6] - paramTransform3D.mat[4] * paramTransform3D.mat[2]) * d2;
/* 2988 */     this.mat[8] = (paramTransform3D.mat[4] * paramTransform3D.mat[9] - paramTransform3D.mat[8] * paramTransform3D.mat[5]) * d2;
/* 2989 */     this.mat[9] = -(paramTransform3D.mat[0] * paramTransform3D.mat[9] - paramTransform3D.mat[8] * paramTransform3D.mat[1]) * d2;
/* 2990 */     this.mat[10] = (paramTransform3D.mat[0] * paramTransform3D.mat[5] - paramTransform3D.mat[4] * paramTransform3D.mat[1]) * d2;
/* 2991 */     this.mat[3] = -(paramTransform3D.mat[3] * this.mat[0] + paramTransform3D.mat[7] * this.mat[1] + paramTransform3D.mat[11] * this.mat[2]);
/*      */     
/* 2993 */     this.mat[7] = -(paramTransform3D.mat[3] * this.mat[4] + paramTransform3D.mat[7] * this.mat[5] + paramTransform3D.mat[11] * this.mat[6]);
/*      */     
/* 2995 */     this.mat[11] = -(paramTransform3D.mat[3] * this.mat[8] + paramTransform3D.mat[7] * this.mat[9] + paramTransform3D.mat[11] * this.mat[10]);
/*      */ 
/*      */     
/* 2998 */     this.mat[14] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D;
/* 2999 */     this.mat[15] = 1.0D;
/*      */     
/* 3001 */     this.dirtyBits = paramTransform3D.dirtyBits | 0xE0 | 0x10 | 0x2;
/* 3002 */     this.type = paramTransform3D.type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void invertAffine() {
/* 3009 */     double d1 = affineDeterminant();
/*      */     
/* 3011 */     if (d1 == 0.0D) {
/* 3012 */       throw new SingularMatrixException(J3dI18N.getString("Transform3D1"));
/*      */     }
/* 3014 */     double d2 = (this.mat[0] * this.mat[0] + this.mat[1] * this.mat[1] + this.mat[2] * this.mat[2] + this.mat[3] * this.mat[3]) * (this.mat[4] * this.mat[4] + this.mat[5] * this.mat[5] + this.mat[6] * this.mat[6] + this.mat[7] * this.mat[7]) * (this.mat[8] * this.mat[8] + this.mat[9] * this.mat[9] + this.mat[10] * this.mat[10] + this.mat[11] * this.mat[11]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3021 */     if (d1 * d1 < 1.110223024E-16D * d2) {
/* 3022 */       invertGeneral(this);
/*      */       return;
/*      */     } 
/* 3025 */     d2 = 1.0D / d1;
/* 3026 */     double d3 = (this.mat[5] * this.mat[10] - this.mat[9] * this.mat[6]) * d2;
/* 3027 */     double d4 = -(this.mat[1] * this.mat[10] - this.mat[9] * this.mat[2]) * d2;
/* 3028 */     double d5 = (this.mat[1] * this.mat[6] - this.mat[5] * this.mat[2]) * d2;
/* 3029 */     double d6 = -(this.mat[4] * this.mat[10] - this.mat[8] * this.mat[6]) * d2;
/* 3030 */     double d7 = (this.mat[0] * this.mat[10] - this.mat[8] * this.mat[2]) * d2;
/* 3031 */     double d8 = -(this.mat[0] * this.mat[6] - this.mat[4] * this.mat[2]) * d2;
/* 3032 */     double d9 = (this.mat[4] * this.mat[9] - this.mat[8] * this.mat[5]) * d2;
/* 3033 */     double d10 = -(this.mat[0] * this.mat[9] - this.mat[8] * this.mat[1]) * d2;
/* 3034 */     double d11 = (this.mat[0] * this.mat[5] - this.mat[4] * this.mat[1]) * d2;
/* 3035 */     double d12 = -(this.mat[3] * d3 + this.mat[7] * d4 + this.mat[11] * d5);
/* 3036 */     double d13 = -(this.mat[3] * d6 + this.mat[7] * d7 + this.mat[11] * d8);
/* 3037 */     this.mat[11] = -(this.mat[3] * d9 + this.mat[7] * d10 + this.mat[11] * d11);
/*      */     
/* 3039 */     this.mat[0] = d3; this.mat[1] = d4; this.mat[2] = d5; this.mat[3] = d12;
/* 3040 */     this.mat[4] = d6; this.mat[5] = d7; this.mat[6] = d8; this.mat[7] = d13;
/* 3041 */     this.mat[8] = d9; this.mat[9] = d10; this.mat[10] = d11;
/* 3042 */     this.mat[14] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D;
/* 3043 */     this.mat[15] = 1.0D;
/* 3044 */     this.dirtyBits |= 0xF2;
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
/*      */   final void invertGeneral(Transform3D paramTransform3D) {
/* 3056 */     double[] arrayOfDouble = new double[16];
/* 3057 */     int[] arrayOfInt = new int[4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3064 */     System.arraycopy(paramTransform3D.mat, 0, arrayOfDouble, 0, arrayOfDouble.length);
/*      */ 
/*      */     
/* 3067 */     if (!luDecomposition(arrayOfDouble, arrayOfInt))
/*      */     {
/* 3069 */       throw new SingularMatrixException(J3dI18N.getString("Transform3D1"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3075 */     this.mat[0] = 1.0D; this.mat[1] = 0.0D; this.mat[2] = 0.0D; this.mat[3] = 0.0D;
/* 3076 */     this.mat[4] = 0.0D; this.mat[5] = 1.0D; this.mat[6] = 0.0D; this.mat[7] = 0.0D;
/* 3077 */     this.mat[8] = 0.0D; this.mat[9] = 0.0D; this.mat[10] = 1.0D; this.mat[11] = 0.0D;
/* 3078 */     this.mat[12] = 0.0D; this.mat[13] = 0.0D; this.mat[14] = 0.0D; this.mat[15] = 1.0D;
/* 3079 */     luBacksubstitution(arrayOfDouble, arrayOfInt, this.mat);
/*      */     
/* 3081 */     this.type = 0;
/* 3082 */     this.dirtyBits = 255;
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
/*      */   static boolean luDecomposition(double[] paramArrayOfDouble, int[] paramArrayOfInt) {
/* 3110 */     double[] arrayOfDouble = new double[4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3118 */     byte b3 = 0;
/* 3119 */     byte b4 = 0;
/*      */ 
/*      */     
/* 3122 */     byte b1 = 4;
/* 3123 */     while (b1-- != 0) {
/* 3124 */       double d = 0.0D;
/*      */ 
/*      */       
/* 3127 */       byte b = 4;
/* 3128 */       while (b-- != 0) {
/* 3129 */         double d1 = paramArrayOfDouble[b3++];
/* 3130 */         d1 = Math.abs(d1);
/* 3131 */         if (d1 > d) {
/* 3132 */           d = d1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 3137 */       if (d == 0.0D) {
/* 3138 */         return false;
/*      */       }
/* 3140 */       arrayOfDouble[b4++] = 1.0D / d;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3148 */     byte b2 = 0;
/*      */ 
/*      */     
/* 3151 */     for (b1 = 0; b1 < 4; b1++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3157 */       for (b3 = 0; b3 < b1; b3++) {
/* 3158 */         byte b6 = b2 + 4 * b3 + b1;
/* 3159 */         double d1 = paramArrayOfDouble[b6];
/* 3160 */         byte b5 = b3;
/* 3161 */         byte b7 = b2 + 4 * b3;
/* 3162 */         byte b8 = b2 + b1;
/* 3163 */         while (b5-- != 0) {
/* 3164 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 3165 */           b7++;
/* 3166 */           b8 += 4;
/*      */         } 
/* 3168 */         paramArrayOfDouble[b6] = d1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3173 */       double d = 0.0D;
/* 3174 */       b4 = -1;
/* 3175 */       for (b3 = b1; b3 < 4; b3++) {
/* 3176 */         byte b6 = b2 + 4 * b3 + b1;
/* 3177 */         double d1 = paramArrayOfDouble[b6];
/* 3178 */         byte b5 = b1;
/* 3179 */         byte b7 = b2 + 4 * b3;
/* 3180 */         byte b8 = b2 + b1;
/* 3181 */         while (b5-- != 0) {
/* 3182 */           d1 -= paramArrayOfDouble[b7] * paramArrayOfDouble[b8];
/* 3183 */           b7++;
/* 3184 */           b8 += 4;
/*      */         } 
/* 3186 */         paramArrayOfDouble[b6] = d1;
/*      */         
/*      */         double d2;
/* 3189 */         if ((d2 = arrayOfDouble[b3] * Math.abs(d1)) >= d) {
/* 3190 */           d = d2;
/* 3191 */           b4 = b3;
/*      */         } 
/*      */       } 
/*      */       
/* 3195 */       if (b4 < 0) {
/* 3196 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 3200 */       if (b1 != b4) {
/*      */         
/* 3202 */         byte b = 4;
/* 3203 */         byte b5 = b2 + 4 * b4;
/* 3204 */         byte b6 = b2 + 4 * b1;
/* 3205 */         while (b-- != 0) {
/* 3206 */           double d1 = paramArrayOfDouble[b5];
/* 3207 */           paramArrayOfDouble[b5++] = paramArrayOfDouble[b6];
/* 3208 */           paramArrayOfDouble[b6++] = d1;
/*      */         } 
/*      */ 
/*      */         
/* 3212 */         arrayOfDouble[b4] = arrayOfDouble[b1];
/*      */       } 
/*      */ 
/*      */       
/* 3216 */       paramArrayOfInt[b1] = b4;
/*      */ 
/*      */       
/* 3219 */       if (paramArrayOfDouble[b2 + 4 * b1 + b1] == 0.0D) {
/* 3220 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 3224 */       if (b1 != 3) {
/* 3225 */         double d1 = 1.0D / paramArrayOfDouble[b2 + 4 * b1 + b1];
/* 3226 */         byte b = b2 + 4 * (b1 + 1) + b1;
/* 3227 */         b3 = 3 - b1;
/* 3228 */         while (b3-- != 0) {
/* 3229 */           paramArrayOfDouble[b] = paramArrayOfDouble[b] * d1;
/* 3230 */           b += 4;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3236 */     return true;
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
/*      */   static void luBacksubstitution(double[] paramArrayOfDouble1, int[] paramArrayOfInt, double[] paramArrayOfDouble2) {
/* 3267 */     byte b2 = 0;
/*      */ 
/*      */     
/* 3270 */     for (byte b1 = 0; b1 < 4; b1++) {
/*      */       
/* 3272 */       int i = b1;
/* 3273 */       byte b4 = -1;
/*      */ 
/*      */       
/* 3276 */       for (byte b3 = 0; b3 < 4; b3++) {
/*      */ 
/*      */         
/* 3279 */         int j = paramArrayOfInt[b2 + b3];
/* 3280 */         double d = paramArrayOfDouble2[i + 4 * j];
/* 3281 */         paramArrayOfDouble2[i + 4 * j] = paramArrayOfDouble2[i + 4 * b3];
/* 3282 */         if (b4 >= 0) {
/*      */           
/* 3284 */           byte b6 = b3 * 4;
/* 3285 */           for (byte b5 = b4; b5 <= b3 - 1; b5++) {
/* 3286 */             d -= paramArrayOfDouble1[b6 + b5] * paramArrayOfDouble2[i + 4 * b5];
/*      */           }
/*      */         }
/* 3289 */         else if (d != 0.0D) {
/* 3290 */           b4 = b3;
/*      */         } 
/* 3292 */         paramArrayOfDouble2[i + 4 * b3] = d;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3297 */       byte b = 12;
/* 3298 */       paramArrayOfDouble2[i + 12] = paramArrayOfDouble2[i + 12] / paramArrayOfDouble1[b + 3];
/*      */       
/* 3300 */       b -= 4;
/* 3301 */       paramArrayOfDouble2[i + 8] = (paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 2];
/*      */ 
/*      */       
/* 3304 */       b -= 4;
/* 3305 */       paramArrayOfDouble2[i + 4] = (paramArrayOfDouble2[i + 4] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 1];
/*      */ 
/*      */ 
/*      */       
/* 3309 */       b -= 4;
/* 3310 */       paramArrayOfDouble2[i + 0] = (paramArrayOfDouble2[i + 0] - paramArrayOfDouble1[b + 1] * paramArrayOfDouble2[i + 4] - paramArrayOfDouble1[b + 2] * paramArrayOfDouble2[i + 8] - paramArrayOfDouble1[b + 3] * paramArrayOfDouble2[i + 12]) / paramArrayOfDouble1[b + 0];
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3319 */   final double affineDeterminant() { return this.mat[0] * (this.mat[5] * this.mat[10] - this.mat[6] * this.mat[9]) - this.mat[1] * (this.mat[4] * this.mat[10] - this.mat[6] * this.mat[8]) + this.mat[2] * (this.mat[4] * this.mat[9] - this.mat[5] * this.mat[8]); }
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
/* 3330 */     if (isAffine()) {
/* 3331 */       return this.mat[0] * (this.mat[5] * this.mat[10] - this.mat[6] * this.mat[9]) - this.mat[1] * (this.mat[4] * this.mat[10] - this.mat[6] * this.mat[8]) + this.mat[2] * (this.mat[4] * this.mat[9] - this.mat[5] * this.mat[8]);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3336 */     return this.mat[0] * (this.mat[5] * (this.mat[10] * this.mat[15] - this.mat[11] * this.mat[14]) - this.mat[6] * (this.mat[9] * this.mat[15] - this.mat[11] * this.mat[13]) + this.mat[7] * (this.mat[9] * this.mat[14] - this.mat[10] * this.mat[13])) - this.mat[1] * (this.mat[4] * (this.mat[10] * this.mat[15] - this.mat[11] * this.mat[14]) - this.mat[6] * (this.mat[8] * this.mat[15] - this.mat[11] * this.mat[12]) + this.mat[7] * (this.mat[8] * this.mat[14] - this.mat[10] * this.mat[12])) + this.mat[2] * (this.mat[4] * (this.mat[9] * this.mat[15] - this.mat[11] * this.mat[13]) - this.mat[5] * (this.mat[8] * this.mat[15] - this.mat[11] * this.mat[12]) + this.mat[7] * (this.mat[8] * this.mat[13] - this.mat[9] * this.mat[12])) - this.mat[3] * (this.mat[4] * (this.mat[9] * this.mat[14] - this.mat[10] * this.mat[13]) - this.mat[5] * (this.mat[8] * this.mat[14] - this.mat[10] * this.mat[12]) + this.mat[6] * (this.mat[8] * this.mat[13] - this.mat[9] * this.mat[12]));
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
/* 3356 */   public final void set(double paramDouble) { setScaleTranslation(0.0D, 0.0D, 0.0D, paramDouble); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3368 */   public final void set(double paramDouble, Vector3d paramVector3d) { setScaleTranslation(paramVector3d.x, paramVector3d.y, paramVector3d.z, paramDouble); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3380 */   public final void set(float paramFloat, Vector3f paramVector3f) { setScaleTranslation(paramVector3f.x, paramVector3f.y, paramVector3f.z, paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3391 */   public final void set(Vector3d paramVector3d, double paramDouble) { setScaleTranslation(paramVector3d.x * paramDouble, paramVector3d.y * paramDouble, paramVector3d.z * paramDouble, paramDouble); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3402 */   public final void set(Vector3f paramVector3f, float paramFloat) { setScaleTranslation((paramVector3f.x * paramFloat), (paramVector3f.y * paramFloat), (paramVector3f.z * paramFloat), paramFloat); }
/*      */ 
/*      */ 
/*      */   
/*      */   private final void setScaleTranslation(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 3407 */     this.mat[0] = paramDouble4;
/* 3408 */     this.mat[1] = 0.0D;
/* 3409 */     this.mat[2] = 0.0D;
/* 3410 */     this.mat[3] = paramDouble1;
/* 3411 */     this.mat[4] = 0.0D;
/* 3412 */     this.mat[5] = paramDouble4;
/* 3413 */     this.mat[6] = 0.0D;
/* 3414 */     this.mat[7] = paramDouble2;
/* 3415 */     this.mat[8] = 0.0D;
/* 3416 */     this.mat[9] = 0.0D;
/* 3417 */     this.mat[10] = paramDouble4;
/* 3418 */     this.mat[11] = paramDouble3;
/* 3419 */     this.mat[12] = 0.0D;
/* 3420 */     this.mat[13] = 0.0D;
/* 3421 */     this.mat[14] = 0.0D;
/* 3422 */     this.mat[15] = 1.0D;
/*      */     
/* 3424 */     if (this.scales == null) {
/* 3425 */       this.scales = new double[3];
/*      */     }
/* 3427 */     this.scales[2] = paramDouble4; this.scales[1] = paramDouble4; this.scales[0] = paramDouble4;
/*      */ 
/*      */     
/* 3430 */     if (isInfOrNaN(paramDouble1) || isInfOrNaN(paramDouble2) || isInfOrNaN(paramDouble3) || isInfOrNaN(paramDouble4)) {
/* 3431 */       this.dirtyBits = 255;
/*      */       
/*      */       return;
/*      */     } 
/* 3435 */     this.type = 1073742016;
/* 3436 */     this.dirtyBits = 88;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(double paramDouble) {
/* 3446 */     for (byte b = 0; b < 16; b++) {
/* 3447 */       this.mat[b] = this.mat[b] * paramDouble;
/*      */     }
/* 3449 */     this.dirtyBits = 255;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mul(double paramDouble, Transform3D paramTransform3D) {
/* 3459 */     for (byte b = 0; b < 16; b++) {
/* 3460 */       this.mat[b] = paramTransform3D.mat[b] * paramDouble;
/*      */     }
/* 3462 */     this.dirtyBits = 255;
/*      */     
/* 3464 */     if (this.autoNormalize) {
/* 3465 */       normalize();
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
/*      */   public final void mul(Transform3D paramTransform3D) {
/* 3480 */     boolean bool = false;
/*      */ 
/*      */     
/* 3483 */     double d1 = this.mat[0] * paramTransform3D.mat[0] + this.mat[1] * paramTransform3D.mat[4] + this.mat[2] * paramTransform3D.mat[8];
/* 3484 */     double d2 = this.mat[0] * paramTransform3D.mat[1] + this.mat[1] * paramTransform3D.mat[5] + this.mat[2] * paramTransform3D.mat[9];
/* 3485 */     double d3 = this.mat[0] * paramTransform3D.mat[2] + this.mat[1] * paramTransform3D.mat[6] + this.mat[2] * paramTransform3D.mat[10];
/* 3486 */     double d4 = this.mat[0] * paramTransform3D.mat[3] + this.mat[1] * paramTransform3D.mat[7] + this.mat[2] * paramTransform3D.mat[11] + this.mat[3];
/* 3487 */     double d5 = this.mat[4] * paramTransform3D.mat[0] + this.mat[5] * paramTransform3D.mat[4] + this.mat[6] * paramTransform3D.mat[8];
/* 3488 */     double d6 = this.mat[4] * paramTransform3D.mat[1] + this.mat[5] * paramTransform3D.mat[5] + this.mat[6] * paramTransform3D.mat[9];
/* 3489 */     double d7 = this.mat[4] * paramTransform3D.mat[2] + this.mat[5] * paramTransform3D.mat[6] + this.mat[6] * paramTransform3D.mat[10];
/* 3490 */     double d8 = this.mat[4] * paramTransform3D.mat[3] + this.mat[5] * paramTransform3D.mat[7] + this.mat[6] * paramTransform3D.mat[11] + this.mat[7];
/* 3491 */     double d9 = this.mat[8] * paramTransform3D.mat[0] + this.mat[9] * paramTransform3D.mat[4] + this.mat[10] * paramTransform3D.mat[8];
/* 3492 */     double d10 = this.mat[8] * paramTransform3D.mat[1] + this.mat[9] * paramTransform3D.mat[5] + this.mat[10] * paramTransform3D.mat[9];
/* 3493 */     double d11 = this.mat[8] * paramTransform3D.mat[2] + this.mat[9] * paramTransform3D.mat[6] + this.mat[10] * paramTransform3D.mat[10];
/* 3494 */     double d12 = this.mat[8] * paramTransform3D.mat[3] + this.mat[9] * paramTransform3D.mat[7] + this.mat[10] * paramTransform3D.mat[11] + this.mat[11];
/*      */     
/* 3496 */     this.mat[14] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D;
/* 3497 */     this.mat[15] = 1.0D;
/* 3498 */     bool = true;
/*      */     
/* 3500 */     double d13 = this.mat[12] * paramTransform3D.mat[0] + this.mat[13] * paramTransform3D.mat[4] + this.mat[14] * paramTransform3D.mat[8];
/*      */     
/* 3502 */     double d14 = this.mat[12] * paramTransform3D.mat[1] + this.mat[13] * paramTransform3D.mat[5] + this.mat[14] * paramTransform3D.mat[9];
/*      */     
/* 3504 */     double d15 = this.mat[12] * paramTransform3D.mat[2] + this.mat[13] * paramTransform3D.mat[6] + this.mat[14] * paramTransform3D.mat[10];
/*      */     
/* 3506 */     double d16 = this.mat[12] * paramTransform3D.mat[3] + this.mat[13] * paramTransform3D.mat[7] + this.mat[14] * paramTransform3D.mat[11] + this.mat[15];
/*      */     
/* 3508 */     this.mat[12] = d13;
/* 3509 */     this.mat[13] = d14;
/* 3510 */     this.mat[14] = d15;
/* 3511 */     this.mat[15] = d16;
/*      */ 
/*      */     
/* 3514 */     d1 = this.mat[0] * paramTransform3D.mat[0] + this.mat[1] * paramTransform3D.mat[4] + this.mat[2] * paramTransform3D.mat[8] + this.mat[3] * paramTransform3D.mat[12];
/*      */     
/* 3516 */     d2 = this.mat[0] * paramTransform3D.mat[1] + this.mat[1] * paramTransform3D.mat[5] + this.mat[2] * paramTransform3D.mat[9] + this.mat[3] * paramTransform3D.mat[13];
/*      */     
/* 3518 */     d3 = this.mat[0] * paramTransform3D.mat[2] + this.mat[1] * paramTransform3D.mat[6] + this.mat[2] * paramTransform3D.mat[10] + this.mat[3] * paramTransform3D.mat[14];
/*      */     
/* 3520 */     d4 = this.mat[0] * paramTransform3D.mat[3] + this.mat[1] * paramTransform3D.mat[7] + this.mat[2] * paramTransform3D.mat[11] + this.mat[3] * paramTransform3D.mat[15];
/*      */     
/* 3522 */     d5 = this.mat[4] * paramTransform3D.mat[0] + this.mat[5] * paramTransform3D.mat[4] + this.mat[6] * paramTransform3D.mat[8] + this.mat[7] * paramTransform3D.mat[12];
/*      */     
/* 3524 */     d6 = this.mat[4] * paramTransform3D.mat[1] + this.mat[5] * paramTransform3D.mat[5] + this.mat[6] * paramTransform3D.mat[9] + this.mat[7] * paramTransform3D.mat[13];
/*      */     
/* 3526 */     d7 = this.mat[4] * paramTransform3D.mat[2] + this.mat[5] * paramTransform3D.mat[6] + this.mat[6] * paramTransform3D.mat[10] + this.mat[7] * paramTransform3D.mat[14];
/*      */     
/* 3528 */     d8 = this.mat[4] * paramTransform3D.mat[3] + this.mat[5] * paramTransform3D.mat[7] + this.mat[6] * paramTransform3D.mat[11] + this.mat[7] * paramTransform3D.mat[15];
/*      */     
/* 3530 */     d9 = this.mat[8] * paramTransform3D.mat[0] + this.mat[9] * paramTransform3D.mat[4] + this.mat[10] * paramTransform3D.mat[8] + this.mat[11] * paramTransform3D.mat[12];
/*      */     
/* 3532 */     d10 = this.mat[8] * paramTransform3D.mat[1] + this.mat[9] * paramTransform3D.mat[5] + this.mat[10] * paramTransform3D.mat[9] + this.mat[11] * paramTransform3D.mat[13];
/*      */     
/* 3534 */     d11 = this.mat[8] * paramTransform3D.mat[2] + this.mat[9] * paramTransform3D.mat[6] + this.mat[10] * paramTransform3D.mat[10] + this.mat[11] * paramTransform3D.mat[14];
/*      */     
/* 3536 */     d12 = this.mat[8] * paramTransform3D.mat[3] + this.mat[9] * paramTransform3D.mat[7] + this.mat[10] * paramTransform3D.mat[11] + this.mat[11] * paramTransform3D.mat[15];
/*      */ 
/*      */     
/* 3539 */     if (isAffine()) {
/* 3540 */       this.mat[12] = paramTransform3D.mat[12];
/* 3541 */       this.mat[13] = paramTransform3D.mat[13];
/* 3542 */       this.mat[14] = paramTransform3D.mat[14];
/* 3543 */       this.mat[15] = paramTransform3D.mat[15];
/*      */     } else {
/* 3545 */       d13 = this.mat[12] * paramTransform3D.mat[0] + this.mat[13] * paramTransform3D.mat[4] + this.mat[14] * paramTransform3D.mat[8] + this.mat[15] * paramTransform3D.mat[12];
/*      */       
/* 3547 */       d14 = this.mat[12] * paramTransform3D.mat[1] + this.mat[13] * paramTransform3D.mat[5] + this.mat[14] * paramTransform3D.mat[9] + this.mat[15] * paramTransform3D.mat[13];
/*      */       
/* 3549 */       d15 = this.mat[12] * paramTransform3D.mat[2] + this.mat[13] * paramTransform3D.mat[6] + this.mat[14] * paramTransform3D.mat[10] + this.mat[15] * paramTransform3D.mat[14];
/*      */       
/* 3551 */       d16 = this.mat[12] * paramTransform3D.mat[3] + this.mat[13] * paramTransform3D.mat[7] + this.mat[14] * paramTransform3D.mat[11] + this.mat[15] * paramTransform3D.mat[15];
/*      */       
/* 3553 */       this.mat[12] = d13;
/* 3554 */       this.mat[13] = d14;
/* 3555 */       this.mat[14] = d15;
/* 3556 */       this.mat[15] = d16;
/*      */     } 
/*      */ 
/*      */     
/* 3560 */     this.mat[0] = d1;
/* 3561 */     this.mat[1] = d2;
/* 3562 */     this.mat[2] = d3;
/* 3563 */     this.mat[3] = d4;
/* 3564 */     this.mat[4] = d5;
/* 3565 */     this.mat[5] = d6;
/* 3566 */     this.mat[6] = d7;
/* 3567 */     this.mat[7] = d8;
/* 3568 */     this.mat[8] = d9;
/* 3569 */     this.mat[9] = d10;
/* 3570 */     this.mat[10] = d11;
/* 3571 */     this.mat[11] = d12;
/*      */     
/* 3573 */     if ((this.dirtyBits & 0x4) == 0 && (this.type & 0x40) != 0 && (paramTransform3D.dirtyBits & 0x4) == 0 && (paramTransform3D.type & 0x40) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 3577 */       this.type &= paramTransform3D.type;
/* 3578 */       this.dirtyBits |= paramTransform3D.dirtyBits | 0x10 | 0xE0 | 0x8;
/*      */     
/*      */     }
/* 3581 */     else if (bool) {
/* 3582 */       this.dirtyBits = 254;
/*      */     } else {
/*      */       
/* 3585 */       this.dirtyBits = 255;
/*      */     } 
/*      */ 
/*      */     
/* 3589 */     if (this.autoNormalize) {
/* 3590 */       normalize();
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
/*      */   public final void mul(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 3602 */     boolean bool = false;
/*      */ 
/*      */ 
/*      */     
/* 3606 */     this.mat[0] = paramTransform3D1.mat[0] * paramTransform3D2.mat[0] + paramTransform3D1.mat[1] * paramTransform3D2.mat[4] + paramTransform3D1.mat[2] * paramTransform3D2.mat[8];
/* 3607 */     this.mat[1] = paramTransform3D1.mat[0] * paramTransform3D2.mat[1] + paramTransform3D1.mat[1] * paramTransform3D2.mat[5] + paramTransform3D1.mat[2] * paramTransform3D2.mat[9];
/* 3608 */     this.mat[2] = paramTransform3D1.mat[0] * paramTransform3D2.mat[2] + paramTransform3D1.mat[1] * paramTransform3D2.mat[6] + paramTransform3D1.mat[2] * paramTransform3D2.mat[10];
/* 3609 */     this.mat[3] = paramTransform3D1.mat[0] * paramTransform3D2.mat[3] + paramTransform3D1.mat[1] * paramTransform3D2.mat[7] + paramTransform3D1.mat[2] * paramTransform3D2.mat[11] + paramTransform3D1.mat[3];
/*      */     
/* 3611 */     this.mat[4] = paramTransform3D1.mat[4] * paramTransform3D2.mat[0] + paramTransform3D1.mat[5] * paramTransform3D2.mat[4] + paramTransform3D1.mat[6] * paramTransform3D2.mat[8];
/* 3612 */     this.mat[5] = paramTransform3D1.mat[4] * paramTransform3D2.mat[1] + paramTransform3D1.mat[5] * paramTransform3D2.mat[5] + paramTransform3D1.mat[6] * paramTransform3D2.mat[9];
/* 3613 */     this.mat[6] = paramTransform3D1.mat[4] * paramTransform3D2.mat[2] + paramTransform3D1.mat[5] * paramTransform3D2.mat[6] + paramTransform3D1.mat[6] * paramTransform3D2.mat[10];
/* 3614 */     this.mat[7] = paramTransform3D1.mat[4] * paramTransform3D2.mat[3] + paramTransform3D1.mat[5] * paramTransform3D2.mat[7] + paramTransform3D1.mat[6] * paramTransform3D2.mat[11] + paramTransform3D1.mat[7];
/*      */     
/* 3616 */     this.mat[8] = paramTransform3D1.mat[8] * paramTransform3D2.mat[0] + paramTransform3D1.mat[9] * paramTransform3D2.mat[4] + paramTransform3D1.mat[10] * paramTransform3D2.mat[8];
/* 3617 */     this.mat[9] = paramTransform3D1.mat[8] * paramTransform3D2.mat[1] + paramTransform3D1.mat[9] * paramTransform3D2.mat[5] + paramTransform3D1.mat[10] * paramTransform3D2.mat[9];
/* 3618 */     this.mat[10] = paramTransform3D1.mat[8] * paramTransform3D2.mat[2] + paramTransform3D1.mat[9] * paramTransform3D2.mat[6] + paramTransform3D1.mat[10] * paramTransform3D2.mat[10];
/* 3619 */     this.mat[11] = paramTransform3D1.mat[8] * paramTransform3D2.mat[3] + paramTransform3D1.mat[9] * paramTransform3D2.mat[7] + paramTransform3D1.mat[10] * paramTransform3D2.mat[11] + paramTransform3D1.mat[11];
/*      */ 
/*      */     
/* 3622 */     bool = true;
/* 3623 */     this.mat[14] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D;
/* 3624 */     this.mat[15] = 1.0D;
/*      */     
/* 3626 */     this.mat[12] = paramTransform3D1.mat[12] * paramTransform3D2.mat[0] + paramTransform3D1.mat[13] * paramTransform3D2.mat[4] + paramTransform3D1.mat[14] * paramTransform3D2.mat[8];
/*      */     
/* 3628 */     this.mat[13] = paramTransform3D1.mat[12] * paramTransform3D2.mat[1] + paramTransform3D1.mat[13] * paramTransform3D2.mat[5] + paramTransform3D1.mat[14] * paramTransform3D2.mat[9];
/*      */     
/* 3630 */     this.mat[14] = paramTransform3D1.mat[12] * paramTransform3D2.mat[2] + paramTransform3D1.mat[13] * paramTransform3D2.mat[6] + paramTransform3D1.mat[14] * paramTransform3D2.mat[10];
/*      */     
/* 3632 */     this.mat[15] = paramTransform3D1.mat[12] * paramTransform3D2.mat[3] + paramTransform3D1.mat[13] * paramTransform3D2.mat[7] + paramTransform3D1.mat[14] * paramTransform3D2.mat[11] + paramTransform3D1.mat[15];
/*      */ 
/*      */ 
/*      */     
/* 3636 */     this.mat[0] = paramTransform3D1.mat[0] * paramTransform3D2.mat[0] + paramTransform3D1.mat[1] * paramTransform3D2.mat[4] + paramTransform3D1.mat[2] * paramTransform3D2.mat[8] + paramTransform3D1.mat[3] * paramTransform3D2.mat[12];
/*      */     
/* 3638 */     this.mat[1] = paramTransform3D1.mat[0] * paramTransform3D2.mat[1] + paramTransform3D1.mat[1] * paramTransform3D2.mat[5] + paramTransform3D1.mat[2] * paramTransform3D2.mat[9] + paramTransform3D1.mat[3] * paramTransform3D2.mat[13];
/*      */     
/* 3640 */     this.mat[2] = paramTransform3D1.mat[0] * paramTransform3D2.mat[2] + paramTransform3D1.mat[1] * paramTransform3D2.mat[6] + paramTransform3D1.mat[2] * paramTransform3D2.mat[10] + paramTransform3D1.mat[3] * paramTransform3D2.mat[14];
/*      */     
/* 3642 */     this.mat[3] = paramTransform3D1.mat[0] * paramTransform3D2.mat[3] + paramTransform3D1.mat[1] * paramTransform3D2.mat[7] + paramTransform3D1.mat[2] * paramTransform3D2.mat[11] + paramTransform3D1.mat[3] * paramTransform3D2.mat[15];
/*      */     
/* 3644 */     this.mat[4] = paramTransform3D1.mat[4] * paramTransform3D2.mat[0] + paramTransform3D1.mat[5] * paramTransform3D2.mat[4] + paramTransform3D1.mat[6] * paramTransform3D2.mat[8] + paramTransform3D1.mat[7] * paramTransform3D2.mat[12];
/*      */     
/* 3646 */     this.mat[5] = paramTransform3D1.mat[4] * paramTransform3D2.mat[1] + paramTransform3D1.mat[5] * paramTransform3D2.mat[5] + paramTransform3D1.mat[6] * paramTransform3D2.mat[9] + paramTransform3D1.mat[7] * paramTransform3D2.mat[13];
/*      */     
/* 3648 */     this.mat[6] = paramTransform3D1.mat[4] * paramTransform3D2.mat[2] + paramTransform3D1.mat[5] * paramTransform3D2.mat[6] + paramTransform3D1.mat[6] * paramTransform3D2.mat[10] + paramTransform3D1.mat[7] * paramTransform3D2.mat[14];
/*      */     
/* 3650 */     this.mat[7] = paramTransform3D1.mat[4] * paramTransform3D2.mat[3] + paramTransform3D1.mat[5] * paramTransform3D2.mat[7] + paramTransform3D1.mat[6] * paramTransform3D2.mat[11] + paramTransform3D1.mat[7] * paramTransform3D2.mat[15];
/*      */     
/* 3652 */     this.mat[8] = paramTransform3D1.mat[8] * paramTransform3D2.mat[0] + paramTransform3D1.mat[9] * paramTransform3D2.mat[4] + paramTransform3D1.mat[10] * paramTransform3D2.mat[8] + paramTransform3D1.mat[11] * paramTransform3D2.mat[12];
/*      */     
/* 3654 */     this.mat[9] = paramTransform3D1.mat[8] * paramTransform3D2.mat[1] + paramTransform3D1.mat[9] * paramTransform3D2.mat[5] + paramTransform3D1.mat[10] * paramTransform3D2.mat[9] + paramTransform3D1.mat[11] * paramTransform3D2.mat[13];
/*      */     
/* 3656 */     this.mat[10] = paramTransform3D1.mat[8] * paramTransform3D2.mat[2] + paramTransform3D1.mat[9] * paramTransform3D2.mat[6] + paramTransform3D1.mat[10] * paramTransform3D2.mat[10] + paramTransform3D1.mat[11] * paramTransform3D2.mat[14];
/*      */     
/* 3658 */     this.mat[11] = paramTransform3D1.mat[8] * paramTransform3D2.mat[3] + paramTransform3D1.mat[9] * paramTransform3D2.mat[7] + paramTransform3D1.mat[10] * paramTransform3D2.mat[11] + paramTransform3D1.mat[11] * paramTransform3D2.mat[15];
/*      */     
/* 3660 */     if (paramTransform3D1.isAffine()) {
/* 3661 */       this.mat[12] = paramTransform3D2.mat[12];
/* 3662 */       this.mat[13] = paramTransform3D2.mat[13];
/* 3663 */       this.mat[14] = paramTransform3D2.mat[14];
/* 3664 */       this.mat[15] = paramTransform3D2.mat[15];
/*      */     } else {
/* 3666 */       this.mat[12] = paramTransform3D1.mat[12] * paramTransform3D2.mat[0] + paramTransform3D1.mat[13] * paramTransform3D2.mat[4] + paramTransform3D1.mat[14] * paramTransform3D2.mat[8] + paramTransform3D1.mat[15] * paramTransform3D2.mat[12];
/*      */       
/* 3668 */       this.mat[13] = paramTransform3D1.mat[12] * paramTransform3D2.mat[1] + paramTransform3D1.mat[13] * paramTransform3D2.mat[5] + paramTransform3D1.mat[14] * paramTransform3D2.mat[9] + paramTransform3D1.mat[15] * paramTransform3D2.mat[13];
/*      */       
/* 3670 */       this.mat[14] = paramTransform3D1.mat[12] * paramTransform3D2.mat[2] + paramTransform3D1.mat[13] * paramTransform3D2.mat[6] + paramTransform3D1.mat[14] * paramTransform3D2.mat[10] + paramTransform3D1.mat[15] * paramTransform3D2.mat[14];
/*      */       
/* 3672 */       this.mat[15] = paramTransform3D1.mat[12] * paramTransform3D2.mat[3] + paramTransform3D1.mat[13] * paramTransform3D2.mat[7] + paramTransform3D1.mat[14] * paramTransform3D2.mat[11] + paramTransform3D1.mat[15] * paramTransform3D2.mat[15];
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3682 */     double d1 = paramTransform3D1.mat[0] * paramTransform3D2.mat[0] + paramTransform3D1.mat[1] * paramTransform3D2.mat[4] + paramTransform3D1.mat[2] * paramTransform3D2.mat[8];
/* 3683 */     double d2 = paramTransform3D1.mat[0] * paramTransform3D2.mat[1] + paramTransform3D1.mat[1] * paramTransform3D2.mat[5] + paramTransform3D1.mat[2] * paramTransform3D2.mat[9];
/* 3684 */     double d3 = paramTransform3D1.mat[0] * paramTransform3D2.mat[2] + paramTransform3D1.mat[1] * paramTransform3D2.mat[6] + paramTransform3D1.mat[2] * paramTransform3D2.mat[10];
/* 3685 */     double d4 = paramTransform3D1.mat[0] * paramTransform3D2.mat[3] + paramTransform3D1.mat[1] * paramTransform3D2.mat[7] + paramTransform3D1.mat[2] * paramTransform3D2.mat[11] + paramTransform3D1.mat[3];
/*      */     
/* 3687 */     double d5 = paramTransform3D1.mat[4] * paramTransform3D2.mat[0] + paramTransform3D1.mat[5] * paramTransform3D2.mat[4] + paramTransform3D1.mat[6] * paramTransform3D2.mat[8];
/* 3688 */     double d6 = paramTransform3D1.mat[4] * paramTransform3D2.mat[1] + paramTransform3D1.mat[5] * paramTransform3D2.mat[5] + paramTransform3D1.mat[6] * paramTransform3D2.mat[9];
/* 3689 */     double d7 = paramTransform3D1.mat[4] * paramTransform3D2.mat[2] + paramTransform3D1.mat[5] * paramTransform3D2.mat[6] + paramTransform3D1.mat[6] * paramTransform3D2.mat[10];
/* 3690 */     double d8 = paramTransform3D1.mat[4] * paramTransform3D2.mat[3] + paramTransform3D1.mat[5] * paramTransform3D2.mat[7] + paramTransform3D1.mat[6] * paramTransform3D2.mat[11] + paramTransform3D1.mat[7];
/*      */     
/* 3692 */     double d9 = paramTransform3D1.mat[8] * paramTransform3D2.mat[0] + paramTransform3D1.mat[9] * paramTransform3D2.mat[4] + paramTransform3D1.mat[10] * paramTransform3D2.mat[8];
/* 3693 */     double d10 = paramTransform3D1.mat[8] * paramTransform3D2.mat[1] + paramTransform3D1.mat[9] * paramTransform3D2.mat[5] + paramTransform3D1.mat[10] * paramTransform3D2.mat[9];
/* 3694 */     double d11 = paramTransform3D1.mat[8] * paramTransform3D2.mat[2] + paramTransform3D1.mat[9] * paramTransform3D2.mat[6] + paramTransform3D1.mat[10] * paramTransform3D2.mat[10];
/* 3695 */     double d12 = paramTransform3D1.mat[8] * paramTransform3D2.mat[3] + paramTransform3D1.mat[9] * paramTransform3D2.mat[7] + paramTransform3D1.mat[10] * paramTransform3D2.mat[11] + paramTransform3D1.mat[11];
/*      */ 
/*      */     
/* 3698 */     bool = true;
/* 3699 */     this.mat[14] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D;
/* 3700 */     this.mat[15] = 1.0D;
/*      */     
/* 3702 */     double d13 = paramTransform3D1.mat[12] * paramTransform3D2.mat[0] + paramTransform3D1.mat[13] * paramTransform3D2.mat[4] + paramTransform3D1.mat[14] * paramTransform3D2.mat[8];
/*      */     
/* 3704 */     double d14 = paramTransform3D1.mat[12] * paramTransform3D2.mat[1] + paramTransform3D1.mat[13] * paramTransform3D2.mat[5] + paramTransform3D1.mat[14] * paramTransform3D2.mat[9];
/*      */     
/* 3706 */     double d15 = paramTransform3D1.mat[12] * paramTransform3D2.mat[2] + paramTransform3D1.mat[13] * paramTransform3D2.mat[6] + paramTransform3D1.mat[14] * paramTransform3D2.mat[10];
/*      */     
/* 3708 */     double d16 = paramTransform3D1.mat[12] * paramTransform3D2.mat[3] + paramTransform3D1.mat[13] * paramTransform3D2.mat[7] + paramTransform3D1.mat[14] * paramTransform3D2.mat[11] + paramTransform3D1.mat[15];
/*      */     
/* 3710 */     this.mat[12] = d13;
/* 3711 */     this.mat[13] = d14;
/* 3712 */     this.mat[14] = d15;
/* 3713 */     this.mat[15] = d16;
/*      */ 
/*      */     
/* 3716 */     d1 = paramTransform3D1.mat[0] * paramTransform3D2.mat[0] + paramTransform3D1.mat[1] * paramTransform3D2.mat[4] + paramTransform3D1.mat[2] * paramTransform3D2.mat[8] + paramTransform3D1.mat[3] * paramTransform3D2.mat[12];
/*      */     
/* 3718 */     d2 = paramTransform3D1.mat[0] * paramTransform3D2.mat[1] + paramTransform3D1.mat[1] * paramTransform3D2.mat[5] + paramTransform3D1.mat[2] * paramTransform3D2.mat[9] + paramTransform3D1.mat[3] * paramTransform3D2.mat[13];
/*      */     
/* 3720 */     d3 = paramTransform3D1.mat[0] * paramTransform3D2.mat[2] + paramTransform3D1.mat[1] * paramTransform3D2.mat[6] + paramTransform3D1.mat[2] * paramTransform3D2.mat[10] + paramTransform3D1.mat[3] * paramTransform3D2.mat[14];
/*      */     
/* 3722 */     d4 = paramTransform3D1.mat[0] * paramTransform3D2.mat[3] + paramTransform3D1.mat[1] * paramTransform3D2.mat[7] + paramTransform3D1.mat[2] * paramTransform3D2.mat[11] + paramTransform3D1.mat[3] * paramTransform3D2.mat[15];
/*      */     
/* 3724 */     d5 = paramTransform3D1.mat[4] * paramTransform3D2.mat[0] + paramTransform3D1.mat[5] * paramTransform3D2.mat[4] + paramTransform3D1.mat[6] * paramTransform3D2.mat[8] + paramTransform3D1.mat[7] * paramTransform3D2.mat[12];
/*      */     
/* 3726 */     d6 = paramTransform3D1.mat[4] * paramTransform3D2.mat[1] + paramTransform3D1.mat[5] * paramTransform3D2.mat[5] + paramTransform3D1.mat[6] * paramTransform3D2.mat[9] + paramTransform3D1.mat[7] * paramTransform3D2.mat[13];
/*      */     
/* 3728 */     d7 = paramTransform3D1.mat[4] * paramTransform3D2.mat[2] + paramTransform3D1.mat[5] * paramTransform3D2.mat[6] + paramTransform3D1.mat[6] * paramTransform3D2.mat[10] + paramTransform3D1.mat[7] * paramTransform3D2.mat[14];
/*      */     
/* 3730 */     d8 = paramTransform3D1.mat[4] * paramTransform3D2.mat[3] + paramTransform3D1.mat[5] * paramTransform3D2.mat[7] + paramTransform3D1.mat[6] * paramTransform3D2.mat[11] + paramTransform3D1.mat[7] * paramTransform3D2.mat[15];
/*      */     
/* 3732 */     d9 = paramTransform3D1.mat[8] * paramTransform3D2.mat[0] + paramTransform3D1.mat[9] * paramTransform3D2.mat[4] + paramTransform3D1.mat[10] * paramTransform3D2.mat[8] + paramTransform3D1.mat[11] * paramTransform3D2.mat[12];
/*      */     
/* 3734 */     d10 = paramTransform3D1.mat[8] * paramTransform3D2.mat[1] + paramTransform3D1.mat[9] * paramTransform3D2.mat[5] + paramTransform3D1.mat[10] * paramTransform3D2.mat[9] + paramTransform3D1.mat[11] * paramTransform3D2.mat[13];
/*      */     
/* 3736 */     d11 = paramTransform3D1.mat[8] * paramTransform3D2.mat[2] + paramTransform3D1.mat[9] * paramTransform3D2.mat[6] + paramTransform3D1.mat[10] * paramTransform3D2.mat[10] + paramTransform3D1.mat[11] * paramTransform3D2.mat[14];
/*      */     
/* 3738 */     d12 = paramTransform3D1.mat[8] * paramTransform3D2.mat[3] + paramTransform3D1.mat[9] * paramTransform3D2.mat[7] + paramTransform3D1.mat[10] * paramTransform3D2.mat[11] + paramTransform3D1.mat[11] * paramTransform3D2.mat[15];
/*      */ 
/*      */     
/* 3741 */     if (paramTransform3D1.isAffine()) {
/* 3742 */       this.mat[12] = paramTransform3D2.mat[12];
/* 3743 */       this.mat[13] = paramTransform3D2.mat[13];
/* 3744 */       this.mat[14] = paramTransform3D2.mat[14];
/* 3745 */       this.mat[15] = paramTransform3D2.mat[15];
/*      */     } else {
/* 3747 */       d13 = paramTransform3D1.mat[12] * paramTransform3D2.mat[0] + paramTransform3D1.mat[13] * paramTransform3D2.mat[4] + paramTransform3D1.mat[14] * paramTransform3D2.mat[8] + paramTransform3D1.mat[15] * paramTransform3D2.mat[12];
/*      */       
/* 3749 */       d14 = paramTransform3D1.mat[12] * paramTransform3D2.mat[1] + paramTransform3D1.mat[13] * paramTransform3D2.mat[5] + paramTransform3D1.mat[14] * paramTransform3D2.mat[9] + paramTransform3D1.mat[15] * paramTransform3D2.mat[13];
/*      */       
/* 3751 */       d15 = paramTransform3D1.mat[12] * paramTransform3D2.mat[2] + paramTransform3D1.mat[13] * paramTransform3D2.mat[6] + paramTransform3D1.mat[14] * paramTransform3D2.mat[10] + paramTransform3D1.mat[15] * paramTransform3D2.mat[14];
/*      */       
/* 3753 */       d16 = paramTransform3D1.mat[12] * paramTransform3D2.mat[3] + paramTransform3D1.mat[13] * paramTransform3D2.mat[7] + paramTransform3D1.mat[14] * paramTransform3D2.mat[11] + paramTransform3D1.mat[15] * paramTransform3D2.mat[15];
/*      */       
/* 3755 */       this.mat[12] = d13;
/* 3756 */       this.mat[13] = d14;
/* 3757 */       this.mat[14] = d15;
/* 3758 */       this.mat[15] = d16;
/*      */     } 
/*      */     
/* 3761 */     this.mat[0] = d1;
/* 3762 */     this.mat[1] = d2;
/* 3763 */     this.mat[2] = d3;
/* 3764 */     this.mat[3] = d4;
/* 3765 */     this.mat[4] = d5;
/* 3766 */     this.mat[5] = d6;
/* 3767 */     this.mat[6] = d7;
/* 3768 */     this.mat[7] = d8;
/* 3769 */     this.mat[8] = d9;
/* 3770 */     this.mat[9] = d10;
/* 3771 */     this.mat[10] = d11;
/* 3772 */     this.mat[11] = d12;
/*      */ 
/*      */ 
/*      */     
/* 3776 */     if ((paramTransform3D1.dirtyBits & 0x4) == 0 && (paramTransform3D1.type & 0x40) != 0 && (paramTransform3D2.dirtyBits & 0x4) == 0 && (paramTransform3D2.type & 0x40) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 3780 */       paramTransform3D1.type &= paramTransform3D2.type;
/* 3781 */       this.dirtyBits = paramTransform3D1.dirtyBits | paramTransform3D2.dirtyBits | 0x10 | 0xE0 | 0x8;
/*      */     
/*      */     }
/* 3784 */     else if (bool) {
/* 3785 */       this.dirtyBits = 254;
/*      */     } else {
/*      */       
/* 3788 */       this.dirtyBits = 255;
/*      */     } 
/*      */ 
/*      */     
/* 3792 */     if (this.autoNormalize) {
/* 3793 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mulInverse(Transform3D paramTransform3D) {
/* 3803 */     Transform3D transform3D = new Transform3D();
/* 3804 */     transform3D.autoNormalize = false;
/* 3805 */     transform3D.invert(paramTransform3D);
/* 3806 */     mul(transform3D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mulInverse(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 3817 */     Transform3D transform3D = new Transform3D();
/* 3818 */     transform3D.autoNormalize = false;
/* 3819 */     transform3D.invert(paramTransform3D2);
/* 3820 */     mul(paramTransform3D1, transform3D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mulTransposeRight(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 3830 */     Transform3D transform3D = new Transform3D();
/* 3831 */     transform3D.autoNormalize = false;
/* 3832 */     transform3D.transpose(paramTransform3D2);
/* 3833 */     mul(paramTransform3D1, transform3D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mulTransposeLeft(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 3844 */     Transform3D transform3D = new Transform3D();
/* 3845 */     transform3D.autoNormalize = false;
/* 3846 */     transform3D.transpose(paramTransform3D1);
/* 3847 */     mul(transform3D, paramTransform3D2);
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
/*      */   public final void mulTransposeBoth(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 3859 */     Transform3D transform3D1 = new Transform3D();
/* 3860 */     Transform3D transform3D2 = new Transform3D();
/* 3861 */     transform3D1.autoNormalize = false;
/* 3862 */     transform3D2.autoNormalize = false;
/* 3863 */     transform3D1.transpose(paramTransform3D1);
/* 3864 */     transform3D2.transpose(paramTransform3D2);
/* 3865 */     mul(transform3D1, transform3D2);
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
/*      */   public final void normalize() {
/* 3880 */     if (!isAffine() && isInfOrNaN()) {
/*      */       return;
/*      */     }
/*      */     
/* 3884 */     if ((this.dirtyBits & 0xC0) != 0) {
/* 3885 */       computeScaleRotation(true);
/* 3886 */     } else if ((this.dirtyBits & 0xA0) != 0) {
/* 3887 */       computeScales(true);
/*      */     } 
/*      */     
/* 3890 */     this.mat[0] = this.rot[0] * this.scales[0];
/* 3891 */     this.mat[1] = this.rot[1] * this.scales[1];
/* 3892 */     this.mat[2] = this.rot[2] * this.scales[2];
/* 3893 */     this.mat[4] = this.rot[3] * this.scales[0];
/* 3894 */     this.mat[5] = this.rot[4] * this.scales[1];
/* 3895 */     this.mat[6] = this.rot[5] * this.scales[2];
/* 3896 */     this.mat[8] = this.rot[6] * this.scales[0];
/* 3897 */     this.mat[9] = this.rot[7] * this.scales[1];
/* 3898 */     this.mat[10] = this.rot[8] * this.scales[2];
/* 3899 */     this.dirtyBits |= 0x10;
/* 3900 */     this.dirtyBits &= 0xFFFFFFFD;
/* 3901 */     this.type |= 0x40000000;
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
/*      */   public final void normalize(Transform3D paramTransform3D) {
/* 3917 */     set(paramTransform3D);
/* 3918 */     normalize();
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
/*      */   public final void normalizeCP() {
/* 3932 */     if (!isAffine() && isInfOrNaN()) {
/*      */       return;
/*      */     }
/*      */     
/* 3936 */     if ((this.dirtyBits & 0x20) != 0) {
/* 3937 */       computeScales(false);
/*      */     }
/*      */     
/* 3940 */     double d = this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8];
/*      */ 
/*      */     
/* 3943 */     if (d != 0.0D) {
/* 3944 */       d = 1.0D / Math.sqrt(d);
/* 3945 */       this.mat[0] = this.mat[0] * d;
/* 3946 */       this.mat[4] = this.mat[4] * d;
/* 3947 */       this.mat[8] = this.mat[8] * d;
/*      */     } 
/*      */     
/* 3950 */     d = this.mat[1] * this.mat[1] + this.mat[5] * this.mat[5] + this.mat[9] * this.mat[9];
/*      */ 
/*      */     
/* 3953 */     if (d != 0.0D) {
/* 3954 */       d = 1.0D / Math.sqrt(d);
/* 3955 */       this.mat[1] = this.mat[1] * d;
/* 3956 */       this.mat[5] = this.mat[5] * d;
/* 3957 */       this.mat[9] = this.mat[9] * d;
/*      */     } 
/* 3959 */     this.mat[2] = (this.mat[4] * this.mat[9] - this.mat[5] * this.mat[8]) * this.scales[0];
/* 3960 */     this.mat[6] = (this.mat[1] * this.mat[8] - this.mat[0] * this.mat[9]) * this.scales[1];
/* 3961 */     this.mat[10] = (this.mat[0] * this.mat[5] - this.mat[1] * this.mat[4]) * this.scales[2];
/*      */     
/* 3963 */     this.mat[0] = this.mat[0] * this.scales[0];
/* 3964 */     this.mat[1] = this.mat[1] * this.scales[0];
/* 3965 */     this.mat[4] = this.mat[4] * this.scales[1];
/* 3966 */     this.mat[5] = this.mat[5] * this.scales[1];
/* 3967 */     this.mat[8] = this.mat[8] * this.scales[2];
/* 3968 */     this.mat[9] = this.mat[9] * this.scales[2];
/*      */ 
/*      */     
/* 3971 */     this.dirtyBits |= 0xDC;
/* 3972 */     this.dirtyBits &= 0xFFFFFFFD;
/* 3973 */     this.type |= 0x40000000;
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
/*      */   public final void normalizeCP(Transform3D paramTransform3D) {
/* 3990 */     set(paramTransform3D);
/* 3991 */     normalizeCP();
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
/* 4002 */   public boolean equals(Transform3D paramTransform3D) { return (paramTransform3D != null && this.mat[0] == paramTransform3D.mat[0] && this.mat[1] == paramTransform3D.mat[1] && this.mat[2] == paramTransform3D.mat[2] && this.mat[3] == paramTransform3D.mat[3] && this.mat[4] == paramTransform3D.mat[4] && this.mat[5] == paramTransform3D.mat[5] && this.mat[6] == paramTransform3D.mat[6] && this.mat[7] == paramTransform3D.mat[7] && this.mat[8] == paramTransform3D.mat[8] && this.mat[9] == paramTransform3D.mat[9] && this.mat[10] == paramTransform3D.mat[10] && this.mat[11] == paramTransform3D.mat[11] && this.mat[12] == paramTransform3D.mat[12] && this.mat[13] == paramTransform3D.mat[13] && this.mat[14] == paramTransform3D.mat[14] && this.mat[15] == paramTransform3D.mat[15]); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4022 */   public boolean equals(Object paramObject) { return (paramObject instanceof Transform3D && equals((Transform3D)paramObject)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean epsilonEquals(Transform3D paramTransform3D, double paramDouble) {
/* 4038 */     for (byte b = 0; b < 16; b++) {
/* 4039 */       double d = this.mat[b] - paramTransform3D.mat[b];
/* 4040 */       if (((d < 0.0D) ? -d : d) > paramDouble) {
/* 4041 */         return false;
/*      */       }
/*      */     } 
/* 4044 */     return true;
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
/* 4058 */     long l = 1L;
/*      */     
/* 4060 */     for (byte b = 0; b < 16; b++) {
/* 4061 */       l = 31L * l + HashCodeUtil.doubleToLongBits(this.mat[b]);
/*      */     }
/* 4063 */     return (int)(l ^ l >> 32);
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
/*      */   public final void transform(Vector4d paramVector4d1, Vector4d paramVector4d2) {
/* 4075 */     if (paramVector4d1 != paramVector4d2) {
/* 4076 */       paramVector4d2.x = this.mat[0] * paramVector4d1.x + this.mat[1] * paramVector4d1.y + this.mat[2] * paramVector4d1.z + this.mat[3] * paramVector4d1.w;
/*      */       
/* 4078 */       paramVector4d2.y = this.mat[4] * paramVector4d1.x + this.mat[5] * paramVector4d1.y + this.mat[6] * paramVector4d1.z + this.mat[7] * paramVector4d1.w;
/*      */       
/* 4080 */       paramVector4d2.z = this.mat[8] * paramVector4d1.x + this.mat[9] * paramVector4d1.y + this.mat[10] * paramVector4d1.z + this.mat[11] * paramVector4d1.w;
/*      */       
/* 4082 */       paramVector4d2.w = this.mat[12] * paramVector4d1.x + this.mat[13] * paramVector4d1.y + this.mat[14] * paramVector4d1.z + this.mat[15] * paramVector4d1.w;
/*      */     } else {
/*      */       
/* 4085 */       transform(paramVector4d1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Vector4d paramVector4d) {
/* 4096 */     double d1 = this.mat[0] * paramVector4d.x + this.mat[1] * paramVector4d.y + this.mat[2] * paramVector4d.z + this.mat[3] * paramVector4d.w;
/*      */     
/* 4098 */     double d2 = this.mat[4] * paramVector4d.x + this.mat[5] * paramVector4d.y + this.mat[6] * paramVector4d.z + this.mat[7] * paramVector4d.w;
/*      */     
/* 4100 */     double d3 = this.mat[8] * paramVector4d.x + this.mat[9] * paramVector4d.y + this.mat[10] * paramVector4d.z + this.mat[11] * paramVector4d.w;
/*      */     
/* 4102 */     paramVector4d.w = this.mat[12] * paramVector4d.x + this.mat[13] * paramVector4d.y + this.mat[14] * paramVector4d.z + this.mat[15] * paramVector4d.w;
/*      */     
/* 4104 */     paramVector4d.x = d1;
/* 4105 */     paramVector4d.y = d2;
/* 4106 */     paramVector4d.z = d3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Vector4f paramVector4f1, Vector4f paramVector4f2) {
/* 4117 */     if (paramVector4f2 != paramVector4f1) {
/* 4118 */       paramVector4f2.x = (float)(this.mat[0] * paramVector4f1.x + this.mat[1] * paramVector4f1.y + this.mat[2] * paramVector4f1.z + this.mat[3] * paramVector4f1.w);
/*      */       
/* 4120 */       paramVector4f2.y = (float)(this.mat[4] * paramVector4f1.x + this.mat[5] * paramVector4f1.y + this.mat[6] * paramVector4f1.z + this.mat[7] * paramVector4f1.w);
/*      */       
/* 4122 */       paramVector4f2.z = (float)(this.mat[8] * paramVector4f1.x + this.mat[9] * paramVector4f1.y + this.mat[10] * paramVector4f1.z + this.mat[11] * paramVector4f1.w);
/*      */       
/* 4124 */       paramVector4f2.w = (float)(this.mat[12] * paramVector4f1.x + this.mat[13] * paramVector4f1.y + this.mat[14] * paramVector4f1.z + this.mat[15] * paramVector4f1.w);
/*      */     } else {
/*      */       
/* 4127 */       transform(paramVector4f1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Vector4f paramVector4f) {
/* 4138 */     float f1 = (float)(this.mat[0] * paramVector4f.x + this.mat[1] * paramVector4f.y + this.mat[2] * paramVector4f.z + this.mat[3] * paramVector4f.w);
/*      */     
/* 4140 */     float f2 = (float)(this.mat[4] * paramVector4f.x + this.mat[5] * paramVector4f.y + this.mat[6] * paramVector4f.z + this.mat[7] * paramVector4f.w);
/*      */     
/* 4142 */     float f3 = (float)(this.mat[8] * paramVector4f.x + this.mat[9] * paramVector4f.y + this.mat[10] * paramVector4f.z + this.mat[11] * paramVector4f.w);
/*      */     
/* 4144 */     paramVector4f.w = (float)(this.mat[12] * paramVector4f.x + this.mat[13] * paramVector4f.y + this.mat[14] * paramVector4f.z + this.mat[15] * paramVector4f.w);
/*      */     
/* 4146 */     paramVector4f.x = f1;
/* 4147 */     paramVector4f.y = f2;
/* 4148 */     paramVector4f.z = f3;
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
/*      */   public final void transform(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/* 4160 */     if (paramPoint3d1 != paramPoint3d2) {
/* 4161 */       paramPoint3d2.x = this.mat[0] * paramPoint3d1.x + this.mat[1] * paramPoint3d1.y + this.mat[2] * paramPoint3d1.z + this.mat[3];
/*      */       
/* 4163 */       paramPoint3d2.y = this.mat[4] * paramPoint3d1.x + this.mat[5] * paramPoint3d1.y + this.mat[6] * paramPoint3d1.z + this.mat[7];
/*      */       
/* 4165 */       paramPoint3d2.z = this.mat[8] * paramPoint3d1.x + this.mat[9] * paramPoint3d1.y + this.mat[10] * paramPoint3d1.z + this.mat[11];
/*      */     } else {
/*      */       
/* 4168 */       transform(paramPoint3d1);
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
/*      */   public final void transform(Point3d paramPoint3d) {
/* 4180 */     double d1 = this.mat[0] * paramPoint3d.x + this.mat[1] * paramPoint3d.y + this.mat[2] * paramPoint3d.z + this.mat[3];
/* 4181 */     double d2 = this.mat[4] * paramPoint3d.x + this.mat[5] * paramPoint3d.y + this.mat[6] * paramPoint3d.z + this.mat[7];
/* 4182 */     paramPoint3d.z = this.mat[8] * paramPoint3d.x + this.mat[9] * paramPoint3d.y + this.mat[10] * paramPoint3d.z + this.mat[11];
/* 4183 */     paramPoint3d.x = d1;
/* 4184 */     paramPoint3d.y = d2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Vector3d paramVector3d1, Vector3d paramVector3d2) {
/* 4195 */     if (paramVector3d2 != paramVector3d1) {
/* 4196 */       paramVector3d2.x = this.mat[0] * paramVector3d1.x + this.mat[1] * paramVector3d1.y + this.mat[2] * paramVector3d1.z;
/* 4197 */       paramVector3d2.y = this.mat[4] * paramVector3d1.x + this.mat[5] * paramVector3d1.y + this.mat[6] * paramVector3d1.z;
/* 4198 */       paramVector3d2.z = this.mat[8] * paramVector3d1.x + this.mat[9] * paramVector3d1.y + this.mat[10] * paramVector3d1.z;
/*      */     } else {
/* 4200 */       transform(paramVector3d1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void transform(Vector3d paramVector3d) {
/* 4211 */     double d1 = this.mat[0] * paramVector3d.x + this.mat[1] * paramVector3d.y + this.mat[2] * paramVector3d.z;
/* 4212 */     double d2 = this.mat[4] * paramVector3d.x + this.mat[5] * paramVector3d.y + this.mat[6] * paramVector3d.z;
/* 4213 */     paramVector3d.z = this.mat[8] * paramVector3d.x + this.mat[9] * paramVector3d.y + this.mat[10] * paramVector3d.z;
/* 4214 */     paramVector3d.x = d1;
/* 4215 */     paramVector3d.y = d2;
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
/*      */   public final void transform(Point3f paramPoint3f1, Point3f paramPoint3f2) {
/* 4227 */     if (paramPoint3f1 != paramPoint3f2) {
/* 4228 */       paramPoint3f2.x = (float)(this.mat[0] * paramPoint3f1.x + this.mat[1] * paramPoint3f1.y + this.mat[2] * paramPoint3f1.z + this.mat[3]);
/*      */       
/* 4230 */       paramPoint3f2.y = (float)(this.mat[4] * paramPoint3f1.x + this.mat[5] * paramPoint3f1.y + this.mat[6] * paramPoint3f1.z + this.mat[7]);
/*      */       
/* 4232 */       paramPoint3f2.z = (float)(this.mat[8] * paramPoint3f1.x + this.mat[9] * paramPoint3f1.y + this.mat[10] * paramPoint3f1.z + this.mat[11]);
/*      */     } else {
/*      */       
/* 4235 */       transform(paramPoint3f1);
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
/*      */   public final void transform(Point3f paramPoint3f) {
/* 4247 */     float f1 = (float)(this.mat[0] * paramPoint3f.x + this.mat[1] * paramPoint3f.y + this.mat[2] * paramPoint3f.z + this.mat[3]);
/*      */     
/* 4249 */     float f2 = (float)(this.mat[4] * paramPoint3f.x + this.mat[5] * paramPoint3f.y + this.mat[6] * paramPoint3f.z + this.mat[7]);
/*      */     
/* 4251 */     paramPoint3f.z = (float)(this.mat[8] * paramPoint3f.x + this.mat[9] * paramPoint3f.y + this.mat[10] * paramPoint3f.z + this.mat[11]);
/*      */     
/* 4253 */     paramPoint3f.x = f1;
/* 4254 */     paramPoint3f.y = f2;
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
/*      */   public final void transform(Vector3f paramVector3f1, Vector3f paramVector3f2) {
/* 4269 */     if (paramVector3f1 != paramVector3f2) {
/* 4270 */       paramVector3f2.x = (float)(this.mat[0] * paramVector3f1.x + this.mat[1] * paramVector3f1.y + this.mat[2] * paramVector3f1.z);
/*      */       
/* 4272 */       paramVector3f2.y = (float)(this.mat[4] * paramVector3f1.x + this.mat[5] * paramVector3f1.y + this.mat[6] * paramVector3f1.z);
/*      */       
/* 4274 */       paramVector3f2.z = (float)(this.mat[8] * paramVector3f1.x + this.mat[9] * paramVector3f1.y + this.mat[10] * paramVector3f1.z);
/*      */     } else {
/*      */       
/* 4277 */       transform(paramVector3f1);
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
/*      */   public final void transform(Vector3f paramVector3f) {
/* 4291 */     float f1 = (float)(this.mat[0] * paramVector3f.x + this.mat[1] * paramVector3f.y + this.mat[2] * paramVector3f.z);
/*      */     
/* 4293 */     float f2 = (float)(this.mat[4] * paramVector3f.x + this.mat[5] * paramVector3f.y + this.mat[6] * paramVector3f.z);
/*      */     
/* 4295 */     paramVector3f.z = (float)(this.mat[8] * paramVector3f.x + this.mat[9] * paramVector3f.y + this.mat[10] * paramVector3f.z);
/*      */     
/* 4297 */     paramVector3f.x = f1;
/* 4298 */     paramVector3f.y = f2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRotationScale(Matrix3f paramMatrix3f) {
/* 4308 */     this.mat[0] = paramMatrix3f.m00; this.mat[1] = paramMatrix3f.m01; this.mat[2] = paramMatrix3f.m02;
/* 4309 */     this.mat[4] = paramMatrix3f.m10; this.mat[5] = paramMatrix3f.m11; this.mat[6] = paramMatrix3f.m12;
/* 4310 */     this.mat[8] = paramMatrix3f.m20; this.mat[9] = paramMatrix3f.m21; this.mat[10] = paramMatrix3f.m22;
/*      */ 
/*      */     
/* 4313 */     this.dirtyBits = 255;
/*      */     
/* 4315 */     if (this.autoNormalize) {
/* 4316 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRotationScale(Matrix3d paramMatrix3d) {
/* 4327 */     this.mat[0] = paramMatrix3d.m00; this.mat[1] = paramMatrix3d.m01; this.mat[2] = paramMatrix3d.m02;
/* 4328 */     this.mat[4] = paramMatrix3d.m10; this.mat[5] = paramMatrix3d.m11; this.mat[6] = paramMatrix3d.m12;
/* 4329 */     this.mat[8] = paramMatrix3d.m20; this.mat[9] = paramMatrix3d.m21; this.mat[10] = paramMatrix3d.m22;
/*      */ 
/*      */     
/* 4332 */     this.dirtyBits = 255;
/*      */     
/* 4334 */     if (this.autoNormalize) {
/* 4335 */       normalize();
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
/*      */   public final void scaleAdd(double paramDouble, Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 4347 */     for (byte b = 0; b < 16; b++) {
/* 4348 */       this.mat[b] = paramDouble * paramTransform3D1.mat[b] + paramTransform3D2.mat[b];
/*      */     }
/*      */     
/* 4351 */     this.dirtyBits = 255;
/*      */     
/* 4353 */     if (this.autoNormalize) {
/* 4354 */       normalize();
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
/*      */   public final void scaleAdd(double paramDouble, Transform3D paramTransform3D) {
/* 4366 */     for (byte b = 0; b < 16; b++) {
/* 4367 */       this.mat[b] = paramDouble * this.mat[b] + paramTransform3D.mat[b];
/*      */     }
/*      */     
/* 4370 */     this.dirtyBits = 255;
/*      */     
/* 4372 */     if (this.autoNormalize) {
/* 4373 */       normalize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRotationScale(Matrix3f paramMatrix3f) {
/* 4384 */     paramMatrix3f.m00 = (float)this.mat[0];
/* 4385 */     paramMatrix3f.m01 = (float)this.mat[1];
/* 4386 */     paramMatrix3f.m02 = (float)this.mat[2];
/* 4387 */     paramMatrix3f.m10 = (float)this.mat[4];
/* 4388 */     paramMatrix3f.m11 = (float)this.mat[5];
/* 4389 */     paramMatrix3f.m12 = (float)this.mat[6];
/* 4390 */     paramMatrix3f.m20 = (float)this.mat[8];
/* 4391 */     paramMatrix3f.m21 = (float)this.mat[9];
/* 4392 */     paramMatrix3f.m22 = (float)this.mat[10];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void getRotationScale(Matrix3d paramMatrix3d) {
/* 4402 */     paramMatrix3d.m00 = this.mat[0];
/* 4403 */     paramMatrix3d.m01 = this.mat[1];
/* 4404 */     paramMatrix3d.m02 = this.mat[2];
/* 4405 */     paramMatrix3d.m10 = this.mat[4];
/* 4406 */     paramMatrix3d.m11 = this.mat[5];
/* 4407 */     paramMatrix3d.m12 = this.mat[6];
/* 4408 */     paramMatrix3d.m20 = this.mat[8];
/* 4409 */     paramMatrix3d.m21 = this.mat[9];
/* 4410 */     paramMatrix3d.m22 = this.mat[10];
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
/*      */   public void lookAt(Point3d paramPoint3d1, Point3d paramPoint3d2, Vector3d paramVector3d) {
/* 4427 */     double d1 = paramPoint3d1.x - paramPoint3d2.x;
/* 4428 */     double d2 = paramPoint3d1.y - paramPoint3d2.y;
/* 4429 */     double d3 = paramPoint3d1.z - paramPoint3d2.z;
/*      */     
/* 4431 */     double d4 = 1.0D / Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/* 4432 */     d1 *= d4;
/* 4433 */     d2 *= d4;
/* 4434 */     d3 *= d4;
/*      */ 
/*      */     
/* 4437 */     d4 = 1.0D / Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
/* 4438 */     double d5 = paramVector3d.x * d4;
/* 4439 */     double d6 = paramVector3d.y * d4;
/* 4440 */     double d7 = paramVector3d.z * d4;
/*      */ 
/*      */     
/* 4443 */     double d8 = d6 * d3 - d2 * d7;
/* 4444 */     double d9 = d7 * d1 - d5 * d3;
/* 4445 */     double d10 = d5 * d2 - d6 * d1;
/*      */     
/* 4447 */     d4 = 1.0D / Math.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
/* 4448 */     d8 *= d4;
/* 4449 */     d9 *= d4;
/* 4450 */     d10 *= d4;
/*      */ 
/*      */ 
/*      */     
/* 4454 */     d5 = d2 * d10 - d9 * d3;
/* 4455 */     d6 = d3 * d8 - d1 * d10;
/* 4456 */     d7 = d1 * d9 - d2 * d8;
/*      */ 
/*      */     
/* 4459 */     this.mat[0] = d8;
/* 4460 */     this.mat[1] = d9;
/* 4461 */     this.mat[2] = d10;
/*      */     
/* 4463 */     this.mat[4] = d5;
/* 4464 */     this.mat[5] = d6;
/* 4465 */     this.mat[6] = d7;
/*      */     
/* 4467 */     this.mat[8] = d1;
/* 4468 */     this.mat[9] = d2;
/* 4469 */     this.mat[10] = d3;
/*      */     
/* 4471 */     this.mat[3] = -paramPoint3d1.x * this.mat[0] + -paramPoint3d1.y * this.mat[1] + -paramPoint3d1.z * this.mat[2];
/* 4472 */     this.mat[7] = -paramPoint3d1.x * this.mat[4] + -paramPoint3d1.y * this.mat[5] + -paramPoint3d1.z * this.mat[6];
/* 4473 */     this.mat[11] = -paramPoint3d1.x * this.mat[8] + -paramPoint3d1.y * this.mat[9] + -paramPoint3d1.z * this.mat[10];
/*      */     
/* 4475 */     this.mat[14] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D;
/* 4476 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 4479 */     this.dirtyBits = 255;
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
/*      */   public void frustum(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
/* 4515 */     double d1 = 1.0D / (paramDouble2 - paramDouble1);
/* 4516 */     double d2 = 1.0D / (paramDouble4 - paramDouble3);
/* 4517 */     double d3 = 1.0D / (paramDouble6 - paramDouble5);
/*      */     
/* 4519 */     this.mat[0] = 2.0D * paramDouble5 * d1;
/* 4520 */     this.mat[5] = 2.0D * paramDouble5 * d2;
/* 4521 */     this.mat[10] = (paramDouble6 + paramDouble5) * d3;
/* 4522 */     this.mat[2] = (paramDouble2 + paramDouble1) * d1;
/* 4523 */     this.mat[6] = (paramDouble4 + paramDouble3) * d2;
/* 4524 */     this.mat[11] = 2.0D * paramDouble6 * paramDouble5 * d3;
/* 4525 */     this.mat[14] = -1.0D;
/* 4526 */     this.mat[15] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D; this.mat[9] = 0.0D; this.mat[8] = 0.0D; this.mat[7] = 0.0D; this.mat[4] = 0.0D; this.mat[3] = 0.0D; this.mat[1] = 0.0D;
/*      */ 
/*      */ 
/*      */     
/* 4530 */     this.type = 0;
/*      */     
/* 4532 */     this.dirtyBits = 255;
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
/*      */   public void perspective(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 4557 */     double d4 = paramDouble1 * 0.5D;
/*      */ 
/*      */     
/* 4560 */     Vector3d vector3d = new Vector3d();
/*      */     
/* 4562 */     double d3 = paramDouble4 - paramDouble3;
/* 4563 */     double d1 = Math.sin(d4);
/*      */ 
/*      */ 
/*      */     
/* 4567 */     double d2 = Math.cos(d4) / d1;
/*      */     
/* 4569 */     this.mat[0] = d2;
/* 4570 */     this.mat[5] = d2 * paramDouble2;
/* 4571 */     this.mat[10] = (paramDouble4 + paramDouble3) / d3;
/* 4572 */     this.mat[11] = 2.0D * paramDouble3 * paramDouble4 / d3;
/* 4573 */     this.mat[14] = -1.0D;
/* 4574 */     this.mat[15] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D; this.mat[9] = 0.0D; this.mat[8] = 0.0D; this.mat[7] = 0.0D; this.mat[6] = 0.0D; this.mat[4] = 0.0D; this.mat[3] = 0.0D; this.mat[2] = 0.0D; this.mat[1] = 0.0D;
/*      */ 
/*      */ 
/*      */     
/* 4578 */     this.type = 0;
/*      */     
/* 4580 */     this.dirtyBits = 255;
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
/*      */   public void ortho(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
/* 4606 */     double d1 = 1.0D / (paramDouble2 - paramDouble1);
/* 4607 */     double d2 = 1.0D / (paramDouble4 - paramDouble3);
/* 4608 */     double d3 = 1.0D / (paramDouble6 - paramDouble5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4614 */     this.mat[0] = 2.0D * d1;
/* 4615 */     this.mat[3] = -(paramDouble2 + paramDouble1) * d1;
/* 4616 */     this.mat[5] = 2.0D * d2;
/* 4617 */     this.mat[7] = -(paramDouble4 + paramDouble3) * d2;
/* 4618 */     this.mat[10] = 2.0D * d3;
/* 4619 */     this.mat[11] = (paramDouble6 + paramDouble5) * d3;
/* 4620 */     this.mat[14] = 0.0D; this.mat[13] = 0.0D; this.mat[12] = 0.0D; this.mat[9] = 0.0D; this.mat[8] = 0.0D; this.mat[6] = 0.0D; this.mat[4] = 0.0D; this.mat[2] = 0.0D; this.mat[1] = 0.0D;
/*      */     
/* 4622 */     this.mat[15] = 1.0D;
/*      */ 
/*      */     
/* 4625 */     this.dirtyBits = 255;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double getDistanceScale() {
/* 4636 */     if ((this.dirtyBits & 0x20) != 0) {
/* 4637 */       double d1 = this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8];
/*      */       
/* 4639 */       if ((this.dirtyBits & 0x4) == 0 && (this.type & 0x40) != 0)
/*      */       {
/*      */         
/* 4642 */         return Math.sqrt(d1);
/*      */       }
/* 4644 */       double d2 = this.mat[1] * this.mat[1] + this.mat[5] * this.mat[5] + this.mat[9] * this.mat[9];
/*      */       
/* 4646 */       if (d2 > d1) {
/* 4647 */         d1 = d2;
/*      */       }
/* 4649 */       d2 = this.mat[2] * this.mat[2] + this.mat[6] * this.mat[6] + this.mat[10] * this.mat[10];
/* 4650 */       return Math.sqrt((d2 > d1) ? d2 : d1);
/*      */     } 
/* 4652 */     return max3(this.scales);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void mat_mul(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3) {
/* 4658 */     double[] arrayOfDouble = paramArrayOfDouble3;
/* 4659 */     if (paramArrayOfDouble1 == paramArrayOfDouble3 || paramArrayOfDouble2 == paramArrayOfDouble3) {
/* 4660 */       arrayOfDouble = new double[9];
/*      */     }
/*      */     
/* 4663 */     arrayOfDouble[0] = paramArrayOfDouble1[0] * paramArrayOfDouble2[0] + paramArrayOfDouble1[1] * paramArrayOfDouble2[3] + paramArrayOfDouble1[2] * paramArrayOfDouble2[6];
/* 4664 */     arrayOfDouble[1] = paramArrayOfDouble1[0] * paramArrayOfDouble2[1] + paramArrayOfDouble1[1] * paramArrayOfDouble2[4] + paramArrayOfDouble1[2] * paramArrayOfDouble2[7];
/* 4665 */     arrayOfDouble[2] = paramArrayOfDouble1[0] * paramArrayOfDouble2[2] + paramArrayOfDouble1[1] * paramArrayOfDouble2[5] + paramArrayOfDouble1[2] * paramArrayOfDouble2[8];
/*      */     
/* 4667 */     arrayOfDouble[3] = paramArrayOfDouble1[3] * paramArrayOfDouble2[0] + paramArrayOfDouble1[4] * paramArrayOfDouble2[3] + paramArrayOfDouble1[5] * paramArrayOfDouble2[6];
/* 4668 */     arrayOfDouble[4] = paramArrayOfDouble1[3] * paramArrayOfDouble2[1] + paramArrayOfDouble1[4] * paramArrayOfDouble2[4] + paramArrayOfDouble1[5] * paramArrayOfDouble2[7];
/* 4669 */     arrayOfDouble[5] = paramArrayOfDouble1[3] * paramArrayOfDouble2[2] + paramArrayOfDouble1[4] * paramArrayOfDouble2[5] + paramArrayOfDouble1[5] * paramArrayOfDouble2[8];
/*      */     
/* 4671 */     arrayOfDouble[6] = paramArrayOfDouble1[6] * paramArrayOfDouble2[0] + paramArrayOfDouble1[7] * paramArrayOfDouble2[3] + paramArrayOfDouble1[8] * paramArrayOfDouble2[6];
/* 4672 */     arrayOfDouble[7] = paramArrayOfDouble1[6] * paramArrayOfDouble2[1] + paramArrayOfDouble1[7] * paramArrayOfDouble2[4] + paramArrayOfDouble1[8] * paramArrayOfDouble2[7];
/* 4673 */     arrayOfDouble[8] = paramArrayOfDouble1[6] * paramArrayOfDouble2[2] + paramArrayOfDouble1[7] * paramArrayOfDouble2[5] + paramArrayOfDouble1[8] * paramArrayOfDouble2[8];
/*      */     
/* 4675 */     if (arrayOfDouble != paramArrayOfDouble3) {
/* 4676 */       for (byte b = 0; b < 9; b++) {
/* 4677 */         paramArrayOfDouble3[b] = arrayOfDouble[b];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static void transpose_mat(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 4683 */     paramArrayOfDouble2[0] = paramArrayOfDouble1[0];
/* 4684 */     paramArrayOfDouble2[1] = paramArrayOfDouble1[3];
/* 4685 */     paramArrayOfDouble2[2] = paramArrayOfDouble1[6];
/*      */     
/* 4687 */     paramArrayOfDouble2[3] = paramArrayOfDouble1[1];
/* 4688 */     paramArrayOfDouble2[4] = paramArrayOfDouble1[4];
/* 4689 */     paramArrayOfDouble2[5] = paramArrayOfDouble1[7];
/*      */     
/* 4691 */     paramArrayOfDouble2[6] = paramArrayOfDouble1[2];
/* 4692 */     paramArrayOfDouble2[7] = paramArrayOfDouble1[5];
/* 4693 */     paramArrayOfDouble2[8] = paramArrayOfDouble1[8];
/*      */   }
/*      */ 
/*      */   
/*      */   private static final void multipleScale(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 4698 */     paramArrayOfDouble1[0] = paramArrayOfDouble1[0] * paramArrayOfDouble2[0];
/* 4699 */     paramArrayOfDouble1[1] = paramArrayOfDouble1[1] * paramArrayOfDouble2[0];
/* 4700 */     paramArrayOfDouble1[2] = paramArrayOfDouble1[2] * paramArrayOfDouble2[0];
/* 4701 */     paramArrayOfDouble1[4] = paramArrayOfDouble1[4] * paramArrayOfDouble2[1];
/* 4702 */     paramArrayOfDouble1[5] = paramArrayOfDouble1[5] * paramArrayOfDouble2[1];
/* 4703 */     paramArrayOfDouble1[6] = paramArrayOfDouble1[6] * paramArrayOfDouble2[1];
/* 4704 */     paramArrayOfDouble1[8] = paramArrayOfDouble1[8] * paramArrayOfDouble2[2];
/* 4705 */     paramArrayOfDouble1[9] = paramArrayOfDouble1[9] * paramArrayOfDouble2[2];
/* 4706 */     paramArrayOfDouble1[10] = paramArrayOfDouble1[10] * paramArrayOfDouble2[2];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void compute_svd(Transform3D paramTransform3D, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 4714 */     double[] arrayOfDouble1 = new double[9];
/*      */ 
/*      */     
/* 4717 */     double[] arrayOfDouble2 = new double[9];
/* 4718 */     double[] arrayOfDouble3 = new double[9];
/* 4719 */     double[] arrayOfDouble4 = new double[9];
/* 4720 */     double[] arrayOfDouble5 = new double[9];
/*      */ 
/*      */     
/* 4723 */     double[] arrayOfDouble6 = new double[9];
/*      */ 
/*      */     
/* 4726 */     double[] arrayOfDouble7 = new double[3];
/* 4727 */     double[] arrayOfDouble8 = new double[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4733 */     byte b = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4740 */     arrayOfDouble1[0] = paramTransform3D.mat[0]; arrayOfDouble6[0] = paramTransform3D.mat[0];
/* 4741 */     arrayOfDouble1[1] = paramTransform3D.mat[1]; arrayOfDouble6[1] = paramTransform3D.mat[1];
/* 4742 */     arrayOfDouble1[2] = paramTransform3D.mat[2]; arrayOfDouble6[2] = paramTransform3D.mat[2];
/* 4743 */     arrayOfDouble1[3] = paramTransform3D.mat[4]; arrayOfDouble6[3] = paramTransform3D.mat[4];
/* 4744 */     arrayOfDouble1[4] = paramTransform3D.mat[5]; arrayOfDouble6[4] = paramTransform3D.mat[5];
/* 4745 */     arrayOfDouble1[5] = paramTransform3D.mat[6]; arrayOfDouble6[5] = paramTransform3D.mat[6];
/* 4746 */     arrayOfDouble1[6] = paramTransform3D.mat[8]; arrayOfDouble6[6] = paramTransform3D.mat[8];
/* 4747 */     arrayOfDouble1[7] = paramTransform3D.mat[9]; arrayOfDouble6[7] = paramTransform3D.mat[9];
/* 4748 */     arrayOfDouble1[8] = paramTransform3D.mat[10]; arrayOfDouble6[8] = paramTransform3D.mat[10];
/*      */ 
/*      */ 
/*      */     
/* 4752 */     if (arrayOfDouble1[3] * arrayOfDouble1[3] < 1.110223024E-16D) {
/* 4753 */       arrayOfDouble2[0] = 1.0D; arrayOfDouble2[1] = 0.0D; arrayOfDouble2[2] = 0.0D;
/* 4754 */       arrayOfDouble2[3] = 0.0D; arrayOfDouble2[4] = 1.0D; arrayOfDouble2[5] = 0.0D;
/* 4755 */       arrayOfDouble2[6] = 0.0D; arrayOfDouble2[7] = 0.0D; arrayOfDouble2[8] = 1.0D;
/* 4756 */     } else if (arrayOfDouble1[0] * arrayOfDouble1[0] < 1.110223024E-16D) {
/* 4757 */       arrayOfDouble4[0] = arrayOfDouble1[0];
/* 4758 */       arrayOfDouble4[1] = arrayOfDouble1[1];
/* 4759 */       arrayOfDouble4[2] = arrayOfDouble1[2];
/* 4760 */       arrayOfDouble1[0] = arrayOfDouble1[3];
/* 4761 */       arrayOfDouble1[1] = arrayOfDouble1[4];
/* 4762 */       arrayOfDouble1[2] = arrayOfDouble1[5];
/*      */       
/* 4764 */       arrayOfDouble1[3] = -arrayOfDouble4[0];
/* 4765 */       arrayOfDouble1[4] = -arrayOfDouble4[1];
/* 4766 */       arrayOfDouble1[5] = -arrayOfDouble4[2];
/*      */       
/* 4768 */       arrayOfDouble2[0] = 0.0D; arrayOfDouble2[1] = 1.0D; arrayOfDouble2[2] = 0.0D;
/* 4769 */       arrayOfDouble2[3] = -1.0D; arrayOfDouble2[4] = 0.0D; arrayOfDouble2[5] = 0.0D;
/* 4770 */       arrayOfDouble2[6] = 0.0D; arrayOfDouble2[7] = 0.0D; arrayOfDouble2[8] = 1.0D;
/*      */     } else {
/* 4772 */       double d1 = 1.0D / Math.sqrt(arrayOfDouble1[0] * arrayOfDouble1[0] + arrayOfDouble1[3] * arrayOfDouble1[3]);
/* 4773 */       double d2 = arrayOfDouble1[0] * d1;
/* 4774 */       double d3 = arrayOfDouble1[3] * d1;
/* 4775 */       arrayOfDouble4[0] = d2 * arrayOfDouble1[0] + d3 * arrayOfDouble1[3];
/* 4776 */       arrayOfDouble4[1] = d2 * arrayOfDouble1[1] + d3 * arrayOfDouble1[4];
/* 4777 */       arrayOfDouble4[2] = d2 * arrayOfDouble1[2] + d3 * arrayOfDouble1[5];
/*      */       
/* 4779 */       arrayOfDouble1[3] = -d3 * arrayOfDouble1[0] + d2 * arrayOfDouble1[3];
/* 4780 */       arrayOfDouble1[4] = -d3 * arrayOfDouble1[1] + d2 * arrayOfDouble1[4];
/* 4781 */       arrayOfDouble1[5] = -d3 * arrayOfDouble1[2] + d2 * arrayOfDouble1[5];
/*      */       
/* 4783 */       arrayOfDouble1[0] = arrayOfDouble4[0];
/* 4784 */       arrayOfDouble1[1] = arrayOfDouble4[1];
/* 4785 */       arrayOfDouble1[2] = arrayOfDouble4[2];
/* 4786 */       arrayOfDouble2[0] = d2; arrayOfDouble2[1] = d3; arrayOfDouble2[2] = 0.0D;
/* 4787 */       arrayOfDouble2[3] = -d3; arrayOfDouble2[4] = d2; arrayOfDouble2[5] = 0.0D;
/* 4788 */       arrayOfDouble2[6] = 0.0D; arrayOfDouble2[7] = 0.0D; arrayOfDouble2[8] = 1.0D;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4793 */     if (arrayOfDouble1[6] * arrayOfDouble1[6] >= 1.110223024E-16D) {
/* 4794 */       if (arrayOfDouble1[0] * arrayOfDouble1[0] < 1.110223024E-16D) {
/* 4795 */         arrayOfDouble4[0] = arrayOfDouble1[0];
/* 4796 */         arrayOfDouble4[1] = arrayOfDouble1[1];
/* 4797 */         arrayOfDouble4[2] = arrayOfDouble1[2];
/* 4798 */         arrayOfDouble1[0] = arrayOfDouble1[6];
/* 4799 */         arrayOfDouble1[1] = arrayOfDouble1[7];
/* 4800 */         arrayOfDouble1[2] = arrayOfDouble1[8];
/*      */         
/* 4802 */         arrayOfDouble1[6] = -arrayOfDouble4[0];
/* 4803 */         arrayOfDouble1[7] = -arrayOfDouble4[1];
/* 4804 */         arrayOfDouble1[8] = -arrayOfDouble4[2];
/*      */         
/* 4806 */         arrayOfDouble4[0] = arrayOfDouble2[0];
/* 4807 */         arrayOfDouble4[1] = arrayOfDouble2[1];
/* 4808 */         arrayOfDouble4[2] = arrayOfDouble2[2];
/* 4809 */         arrayOfDouble2[0] = arrayOfDouble2[6];
/* 4810 */         arrayOfDouble2[1] = arrayOfDouble2[7];
/* 4811 */         arrayOfDouble2[2] = arrayOfDouble2[8];
/*      */         
/* 4813 */         arrayOfDouble2[6] = -arrayOfDouble4[0];
/* 4814 */         arrayOfDouble2[7] = -arrayOfDouble4[1];
/* 4815 */         arrayOfDouble2[8] = -arrayOfDouble4[2];
/*      */       } else {
/* 4817 */         double d1 = 1.0D / Math.sqrt(arrayOfDouble1[0] * arrayOfDouble1[0] + arrayOfDouble1[6] * arrayOfDouble1[6]);
/* 4818 */         double d2 = arrayOfDouble1[0] * d1;
/* 4819 */         double d3 = arrayOfDouble1[6] * d1;
/* 4820 */         arrayOfDouble4[0] = d2 * arrayOfDouble1[0] + d3 * arrayOfDouble1[6];
/* 4821 */         arrayOfDouble4[1] = d2 * arrayOfDouble1[1] + d3 * arrayOfDouble1[7];
/* 4822 */         arrayOfDouble4[2] = d2 * arrayOfDouble1[2] + d3 * arrayOfDouble1[8];
/*      */         
/* 4824 */         arrayOfDouble1[6] = -d3 * arrayOfDouble1[0] + d2 * arrayOfDouble1[6];
/* 4825 */         arrayOfDouble1[7] = -d3 * arrayOfDouble1[1] + d2 * arrayOfDouble1[7];
/* 4826 */         arrayOfDouble1[8] = -d3 * arrayOfDouble1[2] + d2 * arrayOfDouble1[8];
/* 4827 */         arrayOfDouble1[0] = arrayOfDouble4[0];
/* 4828 */         arrayOfDouble1[1] = arrayOfDouble4[1];
/* 4829 */         arrayOfDouble1[2] = arrayOfDouble4[2];
/*      */         
/* 4831 */         arrayOfDouble4[0] = d2 * arrayOfDouble2[0];
/* 4832 */         arrayOfDouble4[1] = d2 * arrayOfDouble2[1];
/* 4833 */         arrayOfDouble2[2] = d3;
/*      */         
/* 4835 */         arrayOfDouble4[6] = -arrayOfDouble2[0] * d3;
/* 4836 */         arrayOfDouble4[7] = -arrayOfDouble2[1] * d3;
/* 4837 */         arrayOfDouble2[8] = d2;
/* 4838 */         arrayOfDouble2[0] = arrayOfDouble4[0];
/* 4839 */         arrayOfDouble2[1] = arrayOfDouble4[1];
/* 4840 */         arrayOfDouble2[6] = arrayOfDouble4[6];
/* 4841 */         arrayOfDouble2[7] = arrayOfDouble4[7];
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 4846 */     if (arrayOfDouble1[2] * arrayOfDouble1[2] < 1.110223024E-16D) {
/* 4847 */       arrayOfDouble3[0] = 1.0D; arrayOfDouble3[1] = 0.0D; arrayOfDouble3[2] = 0.0D;
/* 4848 */       arrayOfDouble3[3] = 0.0D; arrayOfDouble3[4] = 1.0D; arrayOfDouble3[5] = 0.0D;
/* 4849 */       arrayOfDouble3[6] = 0.0D; arrayOfDouble3[7] = 0.0D; arrayOfDouble3[8] = 1.0D;
/* 4850 */     } else if (arrayOfDouble1[1] * arrayOfDouble1[1] < 1.110223024E-16D) {
/* 4851 */       arrayOfDouble4[2] = arrayOfDouble1[2];
/* 4852 */       arrayOfDouble4[5] = arrayOfDouble1[5];
/* 4853 */       arrayOfDouble4[8] = arrayOfDouble1[8];
/* 4854 */       arrayOfDouble1[2] = -arrayOfDouble1[1];
/* 4855 */       arrayOfDouble1[5] = -arrayOfDouble1[4];
/* 4856 */       arrayOfDouble1[8] = -arrayOfDouble1[7];
/*      */       
/* 4858 */       arrayOfDouble1[1] = arrayOfDouble4[2];
/* 4859 */       arrayOfDouble1[4] = arrayOfDouble4[5];
/* 4860 */       arrayOfDouble1[7] = arrayOfDouble4[8];
/*      */       
/* 4862 */       arrayOfDouble3[0] = 1.0D; arrayOfDouble3[1] = 0.0D; arrayOfDouble3[2] = 0.0D;
/* 4863 */       arrayOfDouble3[3] = 0.0D; arrayOfDouble3[4] = 0.0D; arrayOfDouble3[5] = -1.0D;
/* 4864 */       arrayOfDouble3[6] = 0.0D; arrayOfDouble3[7] = 1.0D; arrayOfDouble3[8] = 0.0D;
/*      */     } else {
/* 4866 */       double d1 = 1.0D / Math.sqrt(arrayOfDouble1[1] * arrayOfDouble1[1] + arrayOfDouble1[2] * arrayOfDouble1[2]);
/* 4867 */       double d2 = arrayOfDouble1[1] * d1;
/* 4868 */       double d3 = arrayOfDouble1[2] * d1;
/* 4869 */       arrayOfDouble4[1] = d2 * arrayOfDouble1[1] + d3 * arrayOfDouble1[2];
/* 4870 */       arrayOfDouble1[2] = -d3 * arrayOfDouble1[1] + d2 * arrayOfDouble1[2];
/* 4871 */       arrayOfDouble1[1] = arrayOfDouble4[1];
/*      */       
/* 4873 */       arrayOfDouble4[4] = d2 * arrayOfDouble1[4] + d3 * arrayOfDouble1[5];
/* 4874 */       arrayOfDouble1[5] = -d3 * arrayOfDouble1[4] + d2 * arrayOfDouble1[5];
/* 4875 */       arrayOfDouble1[4] = arrayOfDouble4[4];
/*      */       
/* 4877 */       arrayOfDouble4[7] = d2 * arrayOfDouble1[7] + d3 * arrayOfDouble1[8];
/* 4878 */       arrayOfDouble1[8] = -d3 * arrayOfDouble1[7] + d2 * arrayOfDouble1[8];
/* 4879 */       arrayOfDouble1[7] = arrayOfDouble4[7];
/*      */       
/* 4881 */       arrayOfDouble3[0] = 1.0D; arrayOfDouble3[1] = 0.0D; arrayOfDouble3[2] = 0.0D;
/* 4882 */       arrayOfDouble3[3] = 0.0D; arrayOfDouble3[4] = d2; arrayOfDouble3[5] = -d3;
/* 4883 */       arrayOfDouble3[6] = 0.0D; arrayOfDouble3[7] = d3; arrayOfDouble3[8] = d2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4888 */     if (arrayOfDouble1[7] * arrayOfDouble1[7] >= 1.110223024E-16D) {
/* 4889 */       if (arrayOfDouble1[4] * arrayOfDouble1[4] < 1.110223024E-16D) {
/* 4890 */         arrayOfDouble4[3] = arrayOfDouble1[3];
/* 4891 */         arrayOfDouble4[4] = arrayOfDouble1[4];
/* 4892 */         arrayOfDouble4[5] = arrayOfDouble1[5];
/* 4893 */         arrayOfDouble1[3] = arrayOfDouble1[6];
/* 4894 */         arrayOfDouble1[4] = arrayOfDouble1[7];
/* 4895 */         arrayOfDouble1[5] = arrayOfDouble1[8];
/*      */         
/* 4897 */         arrayOfDouble1[6] = -arrayOfDouble4[3];
/* 4898 */         arrayOfDouble1[7] = -arrayOfDouble4[4];
/* 4899 */         arrayOfDouble1[8] = -arrayOfDouble4[5];
/*      */         
/* 4901 */         arrayOfDouble4[3] = arrayOfDouble2[3];
/* 4902 */         arrayOfDouble4[4] = arrayOfDouble2[4];
/* 4903 */         arrayOfDouble4[5] = arrayOfDouble2[5];
/* 4904 */         arrayOfDouble2[3] = arrayOfDouble2[6];
/* 4905 */         arrayOfDouble2[4] = arrayOfDouble2[7];
/* 4906 */         arrayOfDouble2[5] = arrayOfDouble2[8];
/*      */         
/* 4908 */         arrayOfDouble2[6] = -arrayOfDouble4[3];
/* 4909 */         arrayOfDouble2[7] = -arrayOfDouble4[4];
/* 4910 */         arrayOfDouble2[8] = -arrayOfDouble4[5];
/*      */       } else {
/*      */         
/* 4913 */         double d1 = 1.0D / Math.sqrt(arrayOfDouble1[4] * arrayOfDouble1[4] + arrayOfDouble1[7] * arrayOfDouble1[7]);
/* 4914 */         double d2 = arrayOfDouble1[4] * d1;
/* 4915 */         double d3 = arrayOfDouble1[7] * d1;
/* 4916 */         arrayOfDouble4[3] = d2 * arrayOfDouble1[3] + d3 * arrayOfDouble1[6];
/* 4917 */         arrayOfDouble1[6] = -d3 * arrayOfDouble1[3] + d2 * arrayOfDouble1[6];
/* 4918 */         arrayOfDouble1[3] = arrayOfDouble4[3];
/*      */         
/* 4920 */         arrayOfDouble4[4] = d2 * arrayOfDouble1[4] + d3 * arrayOfDouble1[7];
/* 4921 */         arrayOfDouble1[7] = -d3 * arrayOfDouble1[4] + d2 * arrayOfDouble1[7];
/* 4922 */         arrayOfDouble1[4] = arrayOfDouble4[4];
/*      */         
/* 4924 */         arrayOfDouble4[5] = d2 * arrayOfDouble1[5] + d3 * arrayOfDouble1[8];
/* 4925 */         arrayOfDouble1[8] = -d3 * arrayOfDouble1[5] + d2 * arrayOfDouble1[8];
/* 4926 */         arrayOfDouble1[5] = arrayOfDouble4[5];
/*      */         
/* 4928 */         arrayOfDouble4[3] = d2 * arrayOfDouble2[3] + d3 * arrayOfDouble2[6];
/* 4929 */         arrayOfDouble2[6] = -d3 * arrayOfDouble2[3] + d2 * arrayOfDouble2[6];
/* 4930 */         arrayOfDouble2[3] = arrayOfDouble4[3];
/*      */         
/* 4932 */         arrayOfDouble4[4] = d2 * arrayOfDouble2[4] + d3 * arrayOfDouble2[7];
/* 4933 */         arrayOfDouble2[7] = -d3 * arrayOfDouble2[4] + d2 * arrayOfDouble2[7];
/* 4934 */         arrayOfDouble2[4] = arrayOfDouble4[4];
/*      */         
/* 4936 */         arrayOfDouble4[5] = d2 * arrayOfDouble2[5] + d3 * arrayOfDouble2[8];
/* 4937 */         arrayOfDouble2[8] = -d3 * arrayOfDouble2[5] + d2 * arrayOfDouble2[8];
/* 4938 */         arrayOfDouble2[5] = arrayOfDouble4[5];
/*      */       } 
/*      */     }
/* 4941 */     arrayOfDouble5[0] = arrayOfDouble1[0];
/* 4942 */     arrayOfDouble5[1] = arrayOfDouble1[4];
/* 4943 */     arrayOfDouble5[2] = arrayOfDouble1[8];
/* 4944 */     arrayOfDouble7[0] = arrayOfDouble1[1];
/* 4945 */     arrayOfDouble7[1] = arrayOfDouble1[5];
/*      */     
/* 4947 */     if (arrayOfDouble7[0] * arrayOfDouble7[0] > 1.110223024E-16D || arrayOfDouble7[1] * arrayOfDouble7[1] > 1.110223024E-16D) {
/* 4948 */       compute_qr(arrayOfDouble5, arrayOfDouble7, arrayOfDouble2, arrayOfDouble3);
/*      */     }
/*      */     
/* 4951 */     arrayOfDouble8[0] = arrayOfDouble5[0];
/* 4952 */     arrayOfDouble8[1] = arrayOfDouble5[1];
/* 4953 */     arrayOfDouble8[2] = arrayOfDouble5[2];
/*      */ 
/*      */ 
/*      */     
/* 4957 */     if (almostOne(Math.abs(arrayOfDouble8[0])) && almostOne(Math.abs(arrayOfDouble8[1])) && almostOne(Math.abs(arrayOfDouble8[2]))) {
/*      */       byte b1;
/*      */ 
/*      */       
/* 4961 */       for (b1 = 0; b1 < 3; b1++) {
/* 4962 */         if (arrayOfDouble8[b1] < 0.0D)
/* 4963 */           b++; 
/*      */       } 
/* 4965 */       if (b == 0 || b == 2) {
/*      */         
/* 4967 */         paramArrayOfDouble1[2] = 1.0D; paramArrayOfDouble1[1] = 1.0D; paramArrayOfDouble1[0] = 1.0D;
/* 4968 */         for (b1 = 0; b1 < 9; b1++) {
/* 4969 */           paramArrayOfDouble2[b1] = arrayOfDouble6[b1];
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/* 4977 */     transpose_mat(arrayOfDouble2, arrayOfDouble4);
/* 4978 */     transpose_mat(arrayOfDouble3, arrayOfDouble5);
/*      */ 
/*      */     
/* 4981 */     svdReorder(arrayOfDouble1, arrayOfDouble4, arrayOfDouble5, arrayOfDouble6, arrayOfDouble8, paramArrayOfDouble2, paramArrayOfDouble1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void svdReorder(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, double[] paramArrayOfDouble6, double[] paramArrayOfDouble7) {
/* 4989 */     int[] arrayOfInt = new int[3];
/* 4990 */     double[] arrayOfDouble = new double[3];
/*      */ 
/*      */ 
/*      */     
/* 4994 */     if (paramArrayOfDouble5[0] < 0.0D) {
/* 4995 */       paramArrayOfDouble5[0] = -paramArrayOfDouble5[0];
/* 4996 */       paramArrayOfDouble3[0] = -paramArrayOfDouble3[0];
/* 4997 */       paramArrayOfDouble3[1] = -paramArrayOfDouble3[1];
/* 4998 */       paramArrayOfDouble3[2] = -paramArrayOfDouble3[2];
/*      */     } 
/* 5000 */     if (paramArrayOfDouble5[1] < 0.0D) {
/* 5001 */       paramArrayOfDouble5[1] = -paramArrayOfDouble5[1];
/* 5002 */       paramArrayOfDouble3[3] = -paramArrayOfDouble3[3];
/* 5003 */       paramArrayOfDouble3[4] = -paramArrayOfDouble3[4];
/* 5004 */       paramArrayOfDouble3[5] = -paramArrayOfDouble3[5];
/*      */     } 
/* 5006 */     if (paramArrayOfDouble5[2] < 0.0D) {
/* 5007 */       paramArrayOfDouble5[2] = -paramArrayOfDouble5[2];
/* 5008 */       paramArrayOfDouble3[6] = -paramArrayOfDouble3[6];
/* 5009 */       paramArrayOfDouble3[7] = -paramArrayOfDouble3[7];
/* 5010 */       paramArrayOfDouble3[8] = -paramArrayOfDouble3[8];
/*      */     } 
/*      */ 
/*      */     
/* 5014 */     mat_mul(paramArrayOfDouble2, paramArrayOfDouble3, paramArrayOfDouble4);
/*      */ 
/*      */     
/* 5017 */     if (almostEqual(Math.abs(paramArrayOfDouble5[0]), Math.abs(paramArrayOfDouble5[1])) && almostEqual(Math.abs(paramArrayOfDouble5[1]), Math.abs(paramArrayOfDouble5[2]))) {
/*      */       byte b;
/* 5019 */       for (b = 0; b < 9; b++) {
/* 5020 */         paramArrayOfDouble6[b] = paramArrayOfDouble4[b];
/*      */       }
/* 5022 */       for (b = 0; b < 3; b++) {
/* 5023 */         paramArrayOfDouble7[b] = paramArrayOfDouble5[b];
/*      */       }
/*      */     } else {
/*      */       boolean bool2, bool1;
/*      */       
/*      */       byte b;
/* 5029 */       if (paramArrayOfDouble5[0] > paramArrayOfDouble5[1]) {
/* 5030 */         if (paramArrayOfDouble5[0] > paramArrayOfDouble5[2]) {
/* 5031 */           if (paramArrayOfDouble5[2] > paramArrayOfDouble5[1]) {
/* 5032 */             arrayOfInt[0] = 0; arrayOfInt[1] = 2; arrayOfInt[2] = 1;
/*      */           } else {
/* 5034 */             arrayOfInt[0] = 0; arrayOfInt[1] = 1; arrayOfInt[2] = 2;
/*      */           } 
/*      */         } else {
/* 5037 */           arrayOfInt[0] = 2; arrayOfInt[1] = 0; arrayOfInt[2] = 1;
/*      */         }
/*      */       
/* 5040 */       } else if (paramArrayOfDouble5[1] > paramArrayOfDouble5[2]) {
/* 5041 */         if (paramArrayOfDouble5[2] > paramArrayOfDouble5[0]) {
/* 5042 */           arrayOfInt[0] = 1; arrayOfInt[1] = 2; arrayOfInt[2] = 0;
/*      */         } else {
/* 5044 */           arrayOfInt[0] = 1; arrayOfInt[1] = 0; arrayOfInt[2] = 2;
/*      */         } 
/*      */       } else {
/* 5047 */         arrayOfInt[0] = 2; arrayOfInt[1] = 1; arrayOfInt[2] = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5053 */       arrayOfDouble[0] = paramArrayOfDouble1[0] * paramArrayOfDouble1[0] + paramArrayOfDouble1[1] * paramArrayOfDouble1[1] + paramArrayOfDouble1[2] * paramArrayOfDouble1[2];
/* 5054 */       arrayOfDouble[1] = paramArrayOfDouble1[3] * paramArrayOfDouble1[3] + paramArrayOfDouble1[4] * paramArrayOfDouble1[4] + paramArrayOfDouble1[5] * paramArrayOfDouble1[5];
/* 5055 */       arrayOfDouble[2] = paramArrayOfDouble1[6] * paramArrayOfDouble1[6] + paramArrayOfDouble1[7] * paramArrayOfDouble1[7] + paramArrayOfDouble1[8] * paramArrayOfDouble1[8];
/*      */ 
/*      */       
/* 5058 */       if (arrayOfDouble[0] > arrayOfDouble[1]) {
/* 5059 */         if (arrayOfDouble[0] > arrayOfDouble[2]) {
/* 5060 */           if (arrayOfDouble[2] > arrayOfDouble[1]) {
/*      */             
/* 5062 */             b = 0; bool2 = true; bool1 = true;
/*      */           } else {
/*      */             
/* 5065 */             b = 0; bool1 = true; bool2 = true;
/*      */           } 
/*      */         } else {
/*      */           
/* 5069 */           bool2 = false; b = 1; bool1 = true;
/*      */         }
/*      */       
/* 5072 */       } else if (arrayOfDouble[1] > arrayOfDouble[2]) {
/* 5073 */         if (arrayOfDouble[2] > arrayOfDouble[0]) {
/*      */           
/* 5075 */           bool1 = false; bool2 = true; b = 2;
/*      */         } else {
/*      */           
/* 5078 */           bool1 = false; b = 1; bool2 = true;
/*      */         } 
/*      */       } else {
/*      */         
/* 5082 */         bool2 = false; bool1 = true; b = 2;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 5087 */       int i = arrayOfInt[b];
/* 5088 */       paramArrayOfDouble7[0] = paramArrayOfDouble5[i];
/*      */       
/* 5090 */       i = arrayOfInt[bool1];
/* 5091 */       paramArrayOfDouble7[1] = paramArrayOfDouble5[i];
/*      */       
/* 5093 */       i = arrayOfInt[bool2];
/* 5094 */       paramArrayOfDouble7[2] = paramArrayOfDouble5[i];
/*      */       
/* 5096 */       i = arrayOfInt[b];
/* 5097 */       if (paramArrayOfDouble6 == null) {
/* 5098 */         MasterControl.getCoreLogger().severe("outRot == null");
/*      */       }
/* 5100 */       if (paramArrayOfDouble4 == null) {
/* 5101 */         MasterControl.getCoreLogger().severe("rot == null");
/*      */       }
/*      */       
/* 5104 */       paramArrayOfDouble6[0] = paramArrayOfDouble4[i];
/*      */       
/* 5106 */       i = arrayOfInt[b] + 3;
/* 5107 */       paramArrayOfDouble6[3] = paramArrayOfDouble4[i];
/*      */       
/* 5109 */       i = arrayOfInt[b] + 6;
/* 5110 */       paramArrayOfDouble6[6] = paramArrayOfDouble4[i];
/*      */       
/* 5112 */       i = arrayOfInt[bool1];
/* 5113 */       paramArrayOfDouble6[1] = paramArrayOfDouble4[i];
/*      */       
/* 5115 */       i = arrayOfInt[bool1] + 3;
/* 5116 */       paramArrayOfDouble6[4] = paramArrayOfDouble4[i];
/*      */       
/* 5118 */       i = arrayOfInt[bool1] + 6;
/* 5119 */       paramArrayOfDouble6[7] = paramArrayOfDouble4[i];
/*      */       
/* 5121 */       i = arrayOfInt[bool2];
/* 5122 */       paramArrayOfDouble6[2] = paramArrayOfDouble4[i];
/*      */       
/* 5124 */       i = arrayOfInt[bool2] + 3;
/* 5125 */       paramArrayOfDouble6[5] = paramArrayOfDouble4[i];
/*      */       
/* 5127 */       i = arrayOfInt[bool2] + 6;
/* 5128 */       paramArrayOfDouble6[8] = paramArrayOfDouble4[i];
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
/*      */   private int compute_qr(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4) {
/* 5144 */     double[] arrayOfDouble1 = new double[2];
/* 5145 */     double[] arrayOfDouble2 = new double[2];
/* 5146 */     double[] arrayOfDouble3 = new double[2];
/* 5147 */     double[] arrayOfDouble4 = new double[2];
/* 5148 */     double[] arrayOfDouble5 = new double[9];
/*      */ 
/*      */     
/* 5151 */     double d1 = 1.0D;
/* 5152 */     double d2 = -1.0D;
/*      */     
/* 5154 */     boolean bool = false;
/*      */     
/* 5156 */     byte b2 = 1;
/*      */     
/* 5158 */     if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-15D || Math.abs(paramArrayOfDouble2[0]) < 4.89E-15D) bool = true;
/*      */     
/* 5160 */     for (byte b1 = 0; b1 < 10 && !bool; b1++) {
/* 5161 */       double d3 = compute_shift(paramArrayOfDouble1[1], paramArrayOfDouble2[1], paramArrayOfDouble1[2]);
/* 5162 */       double d7 = (Math.abs(paramArrayOfDouble1[0]) - d3) * (d_sign(d1, paramArrayOfDouble1[0]) + d3 / paramArrayOfDouble1[0]);
/* 5163 */       double d8 = paramArrayOfDouble2[0];
/* 5164 */       double d4 = compute_rot(d7, d8, arrayOfDouble4, arrayOfDouble2, 0, b2);
/* 5165 */       d7 = arrayOfDouble2[0] * paramArrayOfDouble1[0] + arrayOfDouble4[0] * paramArrayOfDouble2[0];
/* 5166 */       paramArrayOfDouble2[0] = arrayOfDouble2[0] * paramArrayOfDouble2[0] - arrayOfDouble4[0] * paramArrayOfDouble1[0];
/* 5167 */       d8 = arrayOfDouble4[0] * paramArrayOfDouble1[1];
/* 5168 */       paramArrayOfDouble1[1] = arrayOfDouble2[0] * paramArrayOfDouble1[1];
/*      */       
/* 5170 */       d4 = compute_rot(d7, d8, arrayOfDouble3, arrayOfDouble1, 0, b2);
/* 5171 */       b2 = 0;
/* 5172 */       paramArrayOfDouble1[0] = d4;
/* 5173 */       d7 = arrayOfDouble1[0] * paramArrayOfDouble2[0] + arrayOfDouble3[0] * paramArrayOfDouble1[1];
/* 5174 */       paramArrayOfDouble1[1] = arrayOfDouble1[0] * paramArrayOfDouble1[1] - arrayOfDouble3[0] * paramArrayOfDouble2[0];
/* 5175 */       d8 = arrayOfDouble3[0] * paramArrayOfDouble2[1];
/* 5176 */       paramArrayOfDouble2[1] = arrayOfDouble1[0] * paramArrayOfDouble2[1];
/*      */       
/* 5178 */       d4 = compute_rot(d7, d8, arrayOfDouble4, arrayOfDouble2, 1, b2);
/* 5179 */       paramArrayOfDouble2[0] = d4;
/* 5180 */       d7 = arrayOfDouble2[1] * paramArrayOfDouble1[1] + arrayOfDouble4[1] * paramArrayOfDouble2[1];
/* 5181 */       paramArrayOfDouble2[1] = arrayOfDouble2[1] * paramArrayOfDouble2[1] - arrayOfDouble4[1] * paramArrayOfDouble1[1];
/* 5182 */       d8 = arrayOfDouble4[1] * paramArrayOfDouble1[2];
/* 5183 */       paramArrayOfDouble1[2] = arrayOfDouble2[1] * paramArrayOfDouble1[2];
/*      */       
/* 5185 */       d4 = compute_rot(d7, d8, arrayOfDouble3, arrayOfDouble1, 1, b2);
/* 5186 */       paramArrayOfDouble1[1] = d4;
/* 5187 */       d7 = arrayOfDouble1[1] * paramArrayOfDouble2[1] + arrayOfDouble3[1] * paramArrayOfDouble1[2];
/* 5188 */       paramArrayOfDouble1[2] = arrayOfDouble1[1] * paramArrayOfDouble1[2] - arrayOfDouble3[1] * paramArrayOfDouble2[1];
/* 5189 */       paramArrayOfDouble2[1] = d7;
/*      */ 
/*      */       
/* 5192 */       double d5 = paramArrayOfDouble3[0];
/* 5193 */       paramArrayOfDouble3[0] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfDouble3[3];
/* 5194 */       paramArrayOfDouble3[3] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfDouble3[3];
/* 5195 */       d5 = paramArrayOfDouble3[1];
/* 5196 */       paramArrayOfDouble3[1] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfDouble3[4];
/* 5197 */       paramArrayOfDouble3[4] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfDouble3[4];
/* 5198 */       d5 = paramArrayOfDouble3[2];
/* 5199 */       paramArrayOfDouble3[2] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfDouble3[5];
/* 5200 */       paramArrayOfDouble3[5] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfDouble3[5];
/*      */       
/* 5202 */       d5 = paramArrayOfDouble3[3];
/* 5203 */       paramArrayOfDouble3[3] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfDouble3[6];
/* 5204 */       paramArrayOfDouble3[6] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfDouble3[6];
/* 5205 */       d5 = paramArrayOfDouble3[4];
/* 5206 */       paramArrayOfDouble3[4] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfDouble3[7];
/* 5207 */       paramArrayOfDouble3[7] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfDouble3[7];
/* 5208 */       d5 = paramArrayOfDouble3[5];
/* 5209 */       paramArrayOfDouble3[5] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfDouble3[8];
/* 5210 */       paramArrayOfDouble3[8] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfDouble3[8];
/*      */ 
/*      */ 
/*      */       
/* 5214 */       double d6 = paramArrayOfDouble4[0];
/* 5215 */       paramArrayOfDouble4[0] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfDouble4[1];
/* 5216 */       paramArrayOfDouble4[1] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfDouble4[1];
/* 5217 */       d6 = paramArrayOfDouble4[3];
/* 5218 */       paramArrayOfDouble4[3] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfDouble4[4];
/* 5219 */       paramArrayOfDouble4[4] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfDouble4[4];
/* 5220 */       d6 = paramArrayOfDouble4[6];
/* 5221 */       paramArrayOfDouble4[6] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfDouble4[7];
/* 5222 */       paramArrayOfDouble4[7] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfDouble4[7];
/*      */       
/* 5224 */       d6 = paramArrayOfDouble4[1];
/* 5225 */       paramArrayOfDouble4[1] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfDouble4[2];
/* 5226 */       paramArrayOfDouble4[2] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfDouble4[2];
/* 5227 */       d6 = paramArrayOfDouble4[4];
/* 5228 */       paramArrayOfDouble4[4] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfDouble4[5];
/* 5229 */       paramArrayOfDouble4[5] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfDouble4[5];
/* 5230 */       d6 = paramArrayOfDouble4[7];
/* 5231 */       paramArrayOfDouble4[7] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfDouble4[8];
/* 5232 */       paramArrayOfDouble4[8] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfDouble4[8];
/*      */ 
/*      */ 
/*      */       
/* 5236 */       arrayOfDouble5[0] = paramArrayOfDouble1[0]; arrayOfDouble5[1] = paramArrayOfDouble2[0]; arrayOfDouble5[2] = 0.0D;
/* 5237 */       arrayOfDouble5[3] = 0.0D; arrayOfDouble5[4] = paramArrayOfDouble1[1]; arrayOfDouble5[5] = paramArrayOfDouble2[1];
/* 5238 */       arrayOfDouble5[6] = 0.0D; arrayOfDouble5[7] = 0.0D; arrayOfDouble5[8] = paramArrayOfDouble1[2];
/*      */       
/* 5240 */       if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-15D || Math.abs(paramArrayOfDouble2[0]) < 4.89E-15D) bool = true;
/*      */     
/*      */     } 
/* 5243 */     if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-15D) {
/* 5244 */       compute_2X2(paramArrayOfDouble1[0], paramArrayOfDouble2[0], paramArrayOfDouble1[1], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 0);
/*      */       
/* 5246 */       double d3 = paramArrayOfDouble3[0];
/* 5247 */       paramArrayOfDouble3[0] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[3];
/* 5248 */       paramArrayOfDouble3[3] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[3];
/* 5249 */       d3 = paramArrayOfDouble3[1];
/* 5250 */       paramArrayOfDouble3[1] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[4];
/* 5251 */       paramArrayOfDouble3[4] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[4];
/* 5252 */       d3 = paramArrayOfDouble3[2];
/* 5253 */       paramArrayOfDouble3[2] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[5];
/* 5254 */       paramArrayOfDouble3[5] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[5];
/*      */ 
/*      */ 
/*      */       
/* 5258 */       double d4 = paramArrayOfDouble4[0];
/* 5259 */       paramArrayOfDouble4[0] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[1];
/* 5260 */       paramArrayOfDouble4[1] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[1];
/* 5261 */       d4 = paramArrayOfDouble4[3];
/* 5262 */       paramArrayOfDouble4[3] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[4];
/* 5263 */       paramArrayOfDouble4[4] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[4];
/* 5264 */       d4 = paramArrayOfDouble4[6];
/* 5265 */       paramArrayOfDouble4[6] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[7];
/* 5266 */       paramArrayOfDouble4[7] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[7];
/*      */     } else {
/* 5268 */       compute_2X2(paramArrayOfDouble1[1], paramArrayOfDouble2[1], paramArrayOfDouble1[2], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 1);
/*      */       
/* 5270 */       double d3 = paramArrayOfDouble3[3];
/* 5271 */       paramArrayOfDouble3[3] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[6];
/* 5272 */       paramArrayOfDouble3[6] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[6];
/* 5273 */       d3 = paramArrayOfDouble3[4];
/* 5274 */       paramArrayOfDouble3[4] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[7];
/* 5275 */       paramArrayOfDouble3[7] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[7];
/* 5276 */       d3 = paramArrayOfDouble3[5];
/* 5277 */       paramArrayOfDouble3[5] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[8];
/* 5278 */       paramArrayOfDouble3[8] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[8];
/*      */ 
/*      */ 
/*      */       
/* 5282 */       double d4 = paramArrayOfDouble4[1];
/* 5283 */       paramArrayOfDouble4[1] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[2];
/* 5284 */       paramArrayOfDouble4[2] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[2];
/* 5285 */       d4 = paramArrayOfDouble4[4];
/* 5286 */       paramArrayOfDouble4[4] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[5];
/* 5287 */       paramArrayOfDouble4[5] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[5];
/* 5288 */       d4 = paramArrayOfDouble4[7];
/* 5289 */       paramArrayOfDouble4[7] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[8];
/* 5290 */       paramArrayOfDouble4[8] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[8];
/*      */     } 
/*      */     
/* 5293 */     return 0;
/*      */   }
/*      */ 
/*      */   
/* 5297 */   static final double max(double paramDouble1, double paramDouble2) { return (paramDouble1 > paramDouble2) ? paramDouble1 : paramDouble2; }
/*      */ 
/*      */ 
/*      */   
/* 5301 */   static final double min(double paramDouble1, double paramDouble2) { return (paramDouble1 < paramDouble2) ? paramDouble1 : paramDouble2; }
/*      */ 
/*      */   
/*      */   static final double d_sign(double paramDouble1, double paramDouble2) {
/* 5305 */     double d = (paramDouble1 >= 0.0D) ? paramDouble1 : -paramDouble1;
/* 5306 */     return (paramDouble2 >= 0.0D) ? d : -d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final double compute_shift(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 5314 */     double d6, d3 = Math.abs(paramDouble1);
/* 5315 */     double d4 = Math.abs(paramDouble2);
/* 5316 */     double d5 = Math.abs(paramDouble3);
/* 5317 */     double d1 = min(d3, d5);
/* 5318 */     double d2 = max(d3, d5);
/* 5319 */     if (d1 == 0.0D) {
/* 5320 */       d6 = 0.0D;
/* 5321 */       if (d2 != 0.0D)
/*      */       {
/* 5323 */         double d = min(d2, d4) / max(d2, d4);
/*      */       }
/*      */     }
/* 5326 */     else if (d4 < d2) {
/* 5327 */       double d9 = d1 / d2 + 1.0D;
/* 5328 */       double d10 = (d2 - d1) / d2;
/* 5329 */       double d7 = d4 / d2;
/* 5330 */       double d11 = d7 * d7;
/* 5331 */       double d8 = 2.0D / (Math.sqrt(d9 * d9 + d11) + Math.sqrt(d10 * d10 + d11));
/* 5332 */       d6 = d1 * d8;
/*      */     } else {
/* 5334 */       double d = d2 / d4;
/* 5335 */       if (d == 0.0D) {
/*      */ 
/*      */         
/* 5338 */         d6 = d1 * d2 / d4;
/*      */       } else {
/* 5340 */         double d10 = d1 / d2 + 1.0D;
/* 5341 */         double d11 = (d2 - d1) / d2;
/* 5342 */         double d7 = d10 * d;
/* 5343 */         double d8 = d11 * d;
/* 5344 */         double d9 = 1.0D / (Math.sqrt(d7 * d7 + 1.0D) + Math.sqrt(d8 * d8 + 1.0D));
/* 5345 */         d6 = d1 * d9 * d;
/* 5346 */         d6 += d6;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 5351 */     return d6;
/*      */   }
/*      */ 
/*      */   
/*      */   static int compute_2X2(double paramDouble1, double paramDouble2, double paramDouble3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, int paramInt) {
/*      */     boolean bool;
/* 5357 */     double d1 = 2.0D;
/* 5358 */     double d2 = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5370 */     double d15 = paramArrayOfDouble1[0];
/* 5371 */     double d14 = paramArrayOfDouble1[1];
/* 5372 */     double d10 = 0.0D;
/* 5373 */     double d11 = 0.0D;
/* 5374 */     double d12 = 0.0D;
/* 5375 */     double d13 = 0.0D;
/* 5376 */     double d3 = 0.0D;
/*      */     
/* 5378 */     double d7 = paramDouble1;
/* 5379 */     double d4 = Math.abs(d7);
/* 5380 */     double d9 = paramDouble3;
/* 5381 */     double d6 = Math.abs(paramDouble3);
/*      */     
/* 5383 */     byte b = 1;
/* 5384 */     if (d6 > d4) {
/* 5385 */       bool = true;
/*      */     } else {
/* 5387 */       bool = false;
/*      */     } 
/* 5389 */     if (bool) {
/* 5390 */       b = 3;
/* 5391 */       double d = d7;
/* 5392 */       d7 = d9;
/* 5393 */       d9 = d;
/* 5394 */       d = d4;
/* 5395 */       d4 = d6;
/* 5396 */       d6 = d;
/*      */     } 
/*      */     
/* 5399 */     double d8 = paramDouble2;
/* 5400 */     double d5 = Math.abs(d8);
/* 5401 */     if (d5 == 0.0D) {
/*      */       
/* 5403 */       paramArrayOfDouble1[1] = d6;
/* 5404 */       paramArrayOfDouble1[0] = d4;
/* 5405 */       d10 = 1.0D;
/* 5406 */       d11 = 1.0D;
/* 5407 */       d12 = 0.0D;
/* 5408 */       d13 = 0.0D;
/*      */     } else {
/* 5410 */       boolean bool1 = true;
/*      */       
/* 5412 */       if (d5 > d4) {
/* 5413 */         b = 2;
/* 5414 */         if (d4 / d5 < 1.110223024E-16D) {
/*      */           
/* 5416 */           bool1 = false;
/* 5417 */           d15 = d5;
/* 5418 */           if (d6 > 1.0D) {
/* 5419 */             d14 = d4 / d5 / d6;
/*      */           } else {
/* 5421 */             d14 = d4 / d5 * d6;
/*      */           } 
/* 5423 */           d10 = 1.0D;
/* 5424 */           d12 = d9 / d8;
/* 5425 */           d13 = 1.0D;
/* 5426 */           d11 = d7 / d8;
/*      */         } 
/*      */       } 
/* 5429 */       if (bool1) {
/*      */         
/* 5431 */         double d20, d18, d17 = d4 - d6;
/* 5432 */         if (d17 == d4) {
/*      */           
/* 5434 */           d18 = 1.0D;
/*      */         } else {
/* 5436 */           d18 = d17 / d4;
/*      */         } 
/*      */         
/* 5439 */         double d19 = d8 / d7;
/*      */         
/* 5441 */         double d22 = 2.0D - d18;
/*      */         
/* 5443 */         double d23 = d19 * d19;
/* 5444 */         double d24 = d22 * d22;
/* 5445 */         double d21 = Math.sqrt(d24 + d23);
/*      */         
/* 5447 */         if (d18 == 0.0D) {
/* 5448 */           d20 = Math.abs(d19);
/*      */         } else {
/* 5450 */           d20 = Math.sqrt(d18 * d18 + d23);
/*      */         } 
/*      */         
/* 5453 */         double d16 = (d21 + d20) * 0.5D;
/*      */         
/* 5455 */         if (d5 > d4) {
/* 5456 */           b = 2;
/* 5457 */           if (d4 / d5 < 1.110223024E-16D) {
/*      */             
/* 5459 */             bool1 = false;
/* 5460 */             d15 = d5;
/* 5461 */             if (d6 > 1.0D) {
/* 5462 */               d14 = d4 / d5 / d6;
/*      */             } else {
/* 5464 */               d14 = d4 / d5 * d6;
/*      */             } 
/* 5466 */             d10 = 1.0D;
/* 5467 */             d12 = d9 / d8;
/* 5468 */             d13 = 1.0D;
/* 5469 */             d11 = d7 / d8;
/*      */           } 
/*      */         } 
/* 5472 */         if (bool1) {
/*      */           
/* 5474 */           d17 = d4 - d6;
/* 5475 */           if (d17 == d4) {
/*      */             
/* 5477 */             d18 = 1.0D;
/*      */           } else {
/* 5479 */             d18 = d17 / d4;
/*      */           } 
/*      */           
/* 5482 */           d19 = d8 / d7;
/*      */           
/* 5484 */           d22 = 2.0D - d18;
/*      */           
/* 5486 */           d23 = d19 * d19;
/* 5487 */           d24 = d22 * d22;
/* 5488 */           d21 = Math.sqrt(d24 + d23);
/*      */           
/* 5490 */           if (d18 == 0.0D) {
/* 5491 */             d20 = Math.abs(d19);
/*      */           } else {
/* 5493 */             d20 = Math.sqrt(d18 * d18 + d23);
/*      */           } 
/*      */           
/* 5496 */           d16 = (d21 + d20) * 0.5D;
/*      */ 
/*      */           
/* 5499 */           d14 = d6 / d16;
/* 5500 */           d15 = d4 * d16;
/* 5501 */           if (d23 == 0.0D) {
/*      */             
/* 5503 */             if (d18 == 0.0D) {
/* 5504 */               d22 = d_sign(d1, d7) * d_sign(d2, d8);
/*      */             } else {
/* 5506 */               d22 = d8 / d_sign(d17, d7) + d19 / d22;
/*      */             } 
/*      */           } else {
/* 5509 */             d22 = (d19 / (d21 + d22) + d19 / (d20 + d18)) * (d16 + 1.0D);
/*      */           } 
/* 5511 */           d18 = Math.sqrt(d22 * d22 + 4.0D);
/* 5512 */           d11 = 2.0D / d18;
/* 5513 */           d13 = d22 / d18;
/* 5514 */           d10 = (d11 + d13 * d19) / d16;
/* 5515 */           d12 = d9 / d7 * d13 / d16;
/*      */         } 
/*      */       } 
/* 5518 */       if (bool) {
/* 5519 */         paramArrayOfDouble3[0] = d13;
/* 5520 */         paramArrayOfDouble2[0] = d11;
/* 5521 */         paramArrayOfDouble5[0] = d12;
/* 5522 */         paramArrayOfDouble4[0] = d10;
/*      */       } else {
/* 5524 */         paramArrayOfDouble3[0] = d10;
/* 5525 */         paramArrayOfDouble2[0] = d12;
/* 5526 */         paramArrayOfDouble5[0] = d11;
/* 5527 */         paramArrayOfDouble4[0] = d13;
/*      */       } 
/*      */       
/* 5530 */       if (b == 1) {
/* 5531 */         d3 = d_sign(d2, paramArrayOfDouble5[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble1);
/*      */       }
/* 5533 */       if (b == 2) {
/* 5534 */         d3 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble2);
/*      */       }
/* 5536 */       if (b == 3) {
/* 5537 */         d3 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble2[0]) * d_sign(d2, paramDouble3);
/*      */       }
/* 5539 */       paramArrayOfDouble1[paramInt] = d_sign(d15, d3);
/* 5540 */       double d = d3 * d_sign(d2, paramDouble1) * d_sign(d2, paramDouble3);
/* 5541 */       paramArrayOfDouble1[paramInt + 1] = d_sign(d14, d);
/*      */     } 
/*      */ 
/*      */     
/* 5545 */     return 0;
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
/*      */   static double compute_rot(double paramDouble1, double paramDouble2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2) {
/*      */     double d3, d2, d1;
/* 5560 */     if (paramDouble2 == 0.0D) {
/* 5561 */       d1 = 1.0D;
/* 5562 */       d2 = 0.0D;
/* 5563 */       d3 = paramDouble1;
/* 5564 */     } else if (paramDouble1 == 0.0D) {
/* 5565 */       d1 = 0.0D;
/* 5566 */       d2 = 1.0D;
/* 5567 */       d3 = paramDouble2;
/*      */     } else {
/* 5569 */       double d5 = paramDouble1;
/* 5570 */       double d6 = paramDouble2;
/* 5571 */       double d4 = max(Math.abs(d5), Math.abs(d6));
/* 5572 */       if (d4 >= 4.9947976805055876E145D) {
/* 5573 */         byte b3 = 0;
/* 5574 */         while (d4 >= 4.9947976805055876E145D) {
/* 5575 */           b3++;
/* 5576 */           d5 *= 2.002083095183101E-146D;
/* 5577 */           d6 *= 2.002083095183101E-146D;
/* 5578 */           d4 = max(Math.abs(d5), Math.abs(d6));
/*      */         } 
/* 5580 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 5581 */         d1 = d5 / d3;
/* 5582 */         d2 = d6 / d3;
/* 5583 */         byte b1 = b3;
/* 5584 */         for (byte b2 = 1; b2 <= b3; b2++) {
/* 5585 */           d3 *= 4.9947976805055876E145D;
/*      */         }
/* 5587 */       } else if (d4 <= 2.002083095183101E-146D) {
/* 5588 */         byte b3 = 0;
/* 5589 */         while (d4 <= 2.002083095183101E-146D) {
/* 5590 */           b3++;
/* 5591 */           d5 *= 4.9947976805055876E145D;
/* 5592 */           d6 *= 4.9947976805055876E145D;
/* 5593 */           d4 = max(Math.abs(d5), Math.abs(d6));
/*      */         } 
/* 5595 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 5596 */         d1 = d5 / d3;
/* 5597 */         d2 = d6 / d3;
/* 5598 */         byte b1 = b3;
/* 5599 */         for (byte b2 = 1; b2 <= b3; b2++) {
/* 5600 */           d3 *= 2.002083095183101E-146D;
/*      */         }
/*      */       } else {
/* 5603 */         d3 = Math.sqrt(d5 * d5 + d6 * d6);
/* 5604 */         d1 = d5 / d3;
/* 5605 */         d2 = d6 / d3;
/*      */       } 
/* 5607 */       if (Math.abs(paramDouble1) > Math.abs(paramDouble2) && d1 < 0.0D) {
/* 5608 */         d1 = -d1;
/* 5609 */         d2 = -d2;
/* 5610 */         d3 = -d3;
/*      */       } 
/*      */     } 
/* 5613 */     paramArrayOfDouble1[paramInt1] = d2;
/* 5614 */     paramArrayOfDouble2[paramInt1] = d1;
/* 5615 */     return d3;
/*      */   }
/*      */ 
/*      */   
/*      */   private static final double max3(double[] paramArrayOfDouble) {
/* 5620 */     if (paramArrayOfDouble[0] > paramArrayOfDouble[1]) {
/* 5621 */       if (paramArrayOfDouble[0] > paramArrayOfDouble[2]) {
/* 5622 */         return paramArrayOfDouble[0];
/*      */       }
/* 5624 */       return paramArrayOfDouble[2];
/*      */     } 
/* 5626 */     if (paramArrayOfDouble[1] > paramArrayOfDouble[2]) {
/* 5627 */       return paramArrayOfDouble[1];
/*      */     }
/* 5629 */     return paramArrayOfDouble[2];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void computeScales(boolean paramBoolean) {
/* 5636 */     if (this.scales == null) {
/* 5637 */       this.scales = new double[3];
/*      */     }
/* 5639 */     if ((!paramBoolean || (this.dirtyBits & 0x80) == 0) && isAffine()) {
/* 5640 */       if (isCongruent()) {
/* 5641 */         if ((this.dirtyBits & 0x8) == 0 && (this.type & 0x20) != 0) {
/*      */           
/* 5643 */           this.scales[2] = 1.0D; this.scales[1] = 1.0D; this.scales[0] = 1.0D;
/* 5644 */           this.dirtyBits &= 0xFFFFFFDF;
/*      */           return;
/*      */         } 
/* 5647 */         this.scales[2] = Math.sqrt(this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8]); this.scales[1] = Math.sqrt(this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8]); this.scales[0] = Math.sqrt(this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8]);
/*      */ 
/*      */         
/* 5650 */         this.dirtyBits &= 0xFFFFFFDF;
/*      */         return;
/*      */       } 
/* 5653 */       if (isOrtho()) {
/* 5654 */         this.scales[0] = Math.sqrt(this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8]);
/*      */         
/* 5656 */         this.scales[1] = Math.sqrt(this.mat[1] * this.mat[1] + this.mat[5] * this.mat[5] + this.mat[9] * this.mat[9]);
/*      */         
/* 5658 */         this.scales[2] = Math.sqrt(this.mat[2] * this.mat[2] + this.mat[6] * this.mat[6] + this.mat[10] * this.mat[10]);
/*      */         
/* 5660 */         this.dirtyBits &= 0xFFFFFFDF;
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/* 5665 */     if (this.rot == null) {
/* 5666 */       this.rot = new double[9];
/*      */     }
/* 5668 */     compute_svd(this, this.scales, this.rot);
/* 5669 */     this.dirtyBits &= 0xFFFFFF1F;
/*      */   }
/*      */ 
/*      */   
/*      */   private final void computeScaleRotation(boolean paramBoolean) {
/* 5674 */     if (this.rot == null) {
/* 5675 */       this.rot = new double[9];
/*      */     }
/* 5677 */     if (this.scales == null) {
/* 5678 */       this.scales = new double[3];
/*      */     }
/* 5680 */     if ((!paramBoolean || (this.dirtyBits & 0x80) == 0) && isAffine()) {
/* 5681 */       if (isCongruent()) {
/* 5682 */         if ((this.dirtyBits & 0x8) == 0 && (this.type & 0x20) != 0) {
/*      */           
/* 5684 */           this.rot[0] = this.mat[0];
/* 5685 */           this.rot[1] = this.mat[1];
/* 5686 */           this.rot[2] = this.mat[2];
/* 5687 */           this.rot[3] = this.mat[4];
/* 5688 */           this.rot[4] = this.mat[5];
/* 5689 */           this.rot[5] = this.mat[6];
/* 5690 */           this.rot[6] = this.mat[8];
/* 5691 */           this.rot[7] = this.mat[9];
/* 5692 */           this.rot[8] = this.mat[10];
/* 5693 */           this.scales[2] = 1.0D; this.scales[1] = 1.0D; this.scales[0] = 1.0D;
/* 5694 */           this.dirtyBits &= 0xFFFFFFFF;
/*      */           return;
/*      */         } 
/* 5697 */         double d = Math.sqrt(this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8]);
/* 5698 */         if (d == 0.0D) {
/* 5699 */           compute_svd(this, this.scales, this.rot);
/*      */           return;
/*      */         } 
/* 5702 */         this.scales[2] = d; this.scales[1] = d; this.scales[0] = d;
/* 5703 */         d = 1.0D / d;
/* 5704 */         this.rot[0] = this.mat[0] * d;
/* 5705 */         this.rot[1] = this.mat[1] * d;
/* 5706 */         this.rot[2] = this.mat[2] * d;
/* 5707 */         this.rot[3] = this.mat[4] * d;
/* 5708 */         this.rot[4] = this.mat[5] * d;
/* 5709 */         this.rot[5] = this.mat[6] * d;
/* 5710 */         this.rot[6] = this.mat[8] * d;
/* 5711 */         this.rot[7] = this.mat[9] * d;
/* 5712 */         this.rot[8] = this.mat[10] * d;
/* 5713 */         this.dirtyBits &= 0xFFFFFFFF;
/*      */         return;
/*      */       } 
/* 5716 */       if (isOrtho()) {
/*      */ 
/*      */         
/* 5719 */         this.scales[0] = Math.sqrt(this.mat[0] * this.mat[0] + this.mat[4] * this.mat[4] + this.mat[8] * this.mat[8]);
/* 5720 */         this.scales[1] = Math.sqrt(this.mat[1] * this.mat[1] + this.mat[5] * this.mat[5] + this.mat[9] * this.mat[9]);
/* 5721 */         this.scales[2] = Math.sqrt(this.mat[2] * this.mat[2] + this.mat[6] * this.mat[6] + this.mat[10] * this.mat[10]);
/*      */         
/* 5723 */         if (this.scales[0] == 0.0D || this.scales[1] == 0.0D || this.scales[2] == 0.0D) {
/* 5724 */           compute_svd(this, this.scales, this.rot);
/*      */           return;
/*      */         } 
/* 5727 */         double d = 1.0D / this.scales[0];
/* 5728 */         this.rot[0] = this.mat[0] * d;
/* 5729 */         this.rot[3] = this.mat[4] * d;
/* 5730 */         this.rot[6] = this.mat[8] * d;
/* 5731 */         d = 1.0D / this.scales[1];
/* 5732 */         this.rot[1] = this.mat[1] * d;
/* 5733 */         this.rot[4] = this.mat[5] * d;
/* 5734 */         this.rot[7] = this.mat[9] * d;
/* 5735 */         d = 1.0D / this.scales[2];
/* 5736 */         this.rot[2] = this.mat[2] * d;
/* 5737 */         this.rot[5] = this.mat[6] * d;
/* 5738 */         this.rot[8] = this.mat[10] * d;
/* 5739 */         this.dirtyBits &= 0xFFFFFFFF;
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/* 5744 */     compute_svd(this, this.scales, this.rot);
/* 5745 */     this.dirtyBits &= 0xFFFFFF1F;
/*      */   }
/*      */ 
/*      */   
/*      */   final void getRotation(Transform3D paramTransform3D) {
/* 5750 */     if ((this.dirtyBits & 0x40) != 0) {
/* 5751 */       computeScaleRotation(false);
/*      */     }
/*      */     
/* 5754 */     paramTransform3D.mat[14] = 0.0D; paramTransform3D.mat[13] = 0.0D; paramTransform3D.mat[12] = 0.0D; paramTransform3D.mat[11] = 0.0D; paramTransform3D.mat[7] = 0.0D; paramTransform3D.mat[3] = 0.0D;
/*      */     
/* 5756 */     paramTransform3D.mat[15] = 1.0D;
/* 5757 */     paramTransform3D.mat[0] = this.rot[0];
/* 5758 */     paramTransform3D.mat[1] = this.rot[1];
/* 5759 */     paramTransform3D.mat[2] = this.rot[2];
/* 5760 */     paramTransform3D.mat[4] = this.rot[3];
/* 5761 */     paramTransform3D.mat[5] = this.rot[4];
/* 5762 */     paramTransform3D.mat[6] = this.rot[5];
/* 5763 */     paramTransform3D.mat[8] = this.rot[6];
/* 5764 */     paramTransform3D.mat[9] = this.rot[7];
/* 5765 */     paramTransform3D.mat[10] = this.rot[8];
/*      */ 
/*      */     
/* 5768 */     paramTransform3D.dirtyBits = 255;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void setOrthoDirtyBit() {
/* 5775 */     this.dirtyBits = 255;
/* 5776 */     this.type = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean isInfOrNaN() {
/* 5785 */     double d = 0.0D;
/* 5786 */     for (byte b = 0; b < 16; b++) {
/* 5787 */       d *= this.mat[b];
/*      */     }
/*      */     
/* 5790 */     return (d != 0.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5796 */   private final boolean isInfOrNaN(Quat4f paramQuat4f) { return (Float.isNaN(paramQuat4f.x) || Float.isInfinite(paramQuat4f.x) || Float.isNaN(paramQuat4f.y) || Float.isInfinite(paramQuat4f.y) || Float.isNaN(paramQuat4f.z) || Float.isInfinite(paramQuat4f.z) || Float.isNaN(paramQuat4f.w) || Float.isInfinite(paramQuat4f.w)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5803 */   private boolean isInfOrNaN(Quat4d paramQuat4d) { return (Double.isNaN(paramQuat4d.x) || Double.isInfinite(paramQuat4d.x) || Double.isNaN(paramQuat4d.y) || Double.isInfinite(paramQuat4d.y) || Double.isNaN(paramQuat4d.z) || Double.isInfinite(paramQuat4d.z) || Double.isNaN(paramQuat4d.w) || Double.isInfinite(paramQuat4d.w)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5810 */   private boolean isInfOrNaN(AxisAngle4f paramAxisAngle4f) { return (Float.isNaN(paramAxisAngle4f.x) || Float.isInfinite(paramAxisAngle4f.x) || Float.isNaN(paramAxisAngle4f.y) || Float.isInfinite(paramAxisAngle4f.y) || Float.isNaN(paramAxisAngle4f.z) || Float.isInfinite(paramAxisAngle4f.z) || Float.isNaN(paramAxisAngle4f.angle) || Float.isInfinite(paramAxisAngle4f.angle)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5817 */   private boolean isInfOrNaN(AxisAngle4d paramAxisAngle4d) { return (Double.isNaN(paramAxisAngle4d.x) || Double.isInfinite(paramAxisAngle4d.x) || Double.isNaN(paramAxisAngle4d.y) || Double.isInfinite(paramAxisAngle4d.y) || Double.isNaN(paramAxisAngle4d.z) || Double.isInfinite(paramAxisAngle4d.z) || Double.isNaN(paramAxisAngle4d.angle) || Double.isInfinite(paramAxisAngle4d.angle)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5824 */   private boolean isInfOrNaN(double paramDouble) { return (Double.isNaN(paramDouble) || Double.isInfinite(paramDouble)); }
/*      */ 
/*      */ 
/*      */   
/* 5828 */   private boolean isInfOrNaN(Vector3f paramVector3f) { return (Float.isNaN(paramVector3f.x) || Float.isInfinite(paramVector3f.x) || Float.isNaN(paramVector3f.y) || Float.isInfinite(paramVector3f.y) || Float.isNaN(paramVector3f.z) || Float.isInfinite(paramVector3f.z)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5834 */   private boolean isInfOrNaN(Vector3d paramVector3d) { return (Double.isNaN(paramVector3d.x) || Double.isInfinite(paramVector3d.x) || Double.isNaN(paramVector3d.y) || Double.isInfinite(paramVector3d.y) || Double.isNaN(paramVector3d.z) || Double.isInfinite(paramVector3d.z)); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Transform3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */