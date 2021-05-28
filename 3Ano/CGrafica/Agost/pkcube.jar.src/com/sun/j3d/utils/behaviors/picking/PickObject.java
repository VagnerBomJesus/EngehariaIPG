/*     */ package com.sun.j3d.utils.behaviors.picking;
/*     */ 
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.Morph;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.PickRay;
/*     */ import javax.media.j3d.PickShape;
/*     */ import javax.media.j3d.SceneGraphPath;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Vector3d;
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
/*     */ public class PickObject
/*     */ {
/*     */   public static final int SHAPE3D = 1;
/*     */   public static final int MORPH = 2;
/*     */   public static final int PRIMITIVE = 4;
/*     */   public static final int LINK = 8;
/*     */   public static final int GROUP = 16;
/*     */   public static final int TRANSFORM_GROUP = 32;
/*     */   public static final int BRANCH_GROUP = 64;
/*     */   public static final int SWITCH = 128;
/*     */   public static final int USE_GEOMETRY = 256;
/*     */   public static final int USE_BOUNDS = 512;
/*     */   BranchGroup pickRoot;
/*     */   Canvas3D canvas;
/*     */   Point3d origin;
/*     */   Vector3d direction;
/*     */   PickRay pickRay;
/*     */   SceneGraphPath sceneGraphPath;
/*     */   SceneGraphPath[] sceneGraphPathArr;
/*     */   int pickBy;
/*     */   static final boolean debug = false;
/*     */   private double[] distance;
/*     */   private int[] position;
/*     */   
/*     */   public PickObject(Canvas3D paramCanvas3D, BranchGroup paramBranchGroup) {
/* 197 */     this.origin = new Point3d();
/* 198 */     this.direction = new Vector3d();
/* 199 */     this.pickRay = new PickRay();
/* 200 */     this.sceneGraphPath = null;
/* 201 */     this.sceneGraphPathArr = null;
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
/* 217 */     this.pickRoot = paramBranchGroup;
/* 218 */     this.canvas = paramCanvas3D;
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
/*     */   public PickShape generatePickRay(int paramInt1, int paramInt2) {
/* 232 */     Transform3D transform3D = new Transform3D();
/* 233 */     Point3d point3d1 = new Point3d();
/* 234 */     Point3d point3d2 = new Point3d();
/* 235 */     Vector3d vector3d = new Vector3d();
/*     */     
/* 237 */     this.canvas.getCenterEyeInImagePlate(point3d1);
/* 238 */     this.canvas.getPixelLocationInImagePlate(paramInt1, paramInt2, point3d2);
/* 239 */     if (this.canvas.getView().getProjectionPolicy() == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 244 */       point3d1.x = point3d2.x;
/* 245 */       point3d1.y = point3d2.y;
/*     */     } 
/*     */     
/* 248 */     this.canvas.getImagePlateToVworld(transform3D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     transform3D.transform(point3d1);
/* 256 */     transform3D.transform(point3d2);
/* 257 */     vector3d.sub(point3d2, point3d1);
/* 258 */     vector3d.normalize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     this.pickRay.set(point3d1, vector3d);
/*     */     
/* 268 */     return this.pickRay;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath[] pickAll(int paramInt1, int paramInt2) {
/* 289 */     this.pickRay = (PickRay)generatePickRay(paramInt1, paramInt2);
/* 290 */     this.sceneGraphPathArr = this.pickRoot.pickAll(this.pickRay);
/* 291 */     return this.sceneGraphPathArr;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath[] pickAllSorted(int paramInt1, int paramInt2) {
/* 313 */     this.pickRay = (PickRay)generatePickRay(paramInt1, paramInt2);
/* 314 */     this.sceneGraphPathArr = this.pickRoot.pickAllSorted(this.pickRay);
/* 315 */     return this.sceneGraphPathArr;
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
/*     */ 
/*     */   
/*     */   public SceneGraphPath pickAny(int paramInt1, int paramInt2) {
/* 335 */     this.pickRay = (PickRay)generatePickRay(paramInt1, paramInt2);
/* 336 */     this.sceneGraphPath = this.pickRoot.pickAny(this.pickRay);
/* 337 */     return this.sceneGraphPath;
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
/*     */   
/*     */   public SceneGraphPath pickClosest(int paramInt1, int paramInt2) {
/* 356 */     this.pickRay = (PickRay)generatePickRay(paramInt1, paramInt2);
/* 357 */     this.sceneGraphPath = this.pickRoot.pickClosest(this.pickRay);
/* 358 */     return this.sceneGraphPath;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath[] pickAll(int paramInt1, int paramInt2, int paramInt3) {
/* 381 */     if (paramInt3 == 512) {
/* 382 */       return pickAll(paramInt1, paramInt2);
/*     */     }
/* 384 */     if (paramInt3 == 256) {
/* 385 */       return pickGeomAll(paramInt1, paramInt2);
/*     */     }
/*     */     
/* 388 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath[] pickAllSorted(int paramInt1, int paramInt2, int paramInt3) {
/* 412 */     if (paramInt3 == 512) {
/* 413 */       return pickAllSorted(paramInt1, paramInt2);
/*     */     }
/* 415 */     if (paramInt3 == 256) {
/* 416 */       return pickGeomAllSorted(paramInt1, paramInt2);
/*     */     }
/*     */     
/* 419 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath pickAny(int paramInt1, int paramInt2, int paramInt3) {
/* 442 */     if (paramInt3 == 512) {
/* 443 */       return pickAny(paramInt1, paramInt2);
/*     */     }
/* 445 */     if (paramInt3 == 256) {
/* 446 */       return pickGeomAny(paramInt1, paramInt2);
/*     */     }
/*     */     
/* 449 */     return null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath pickClosest(int paramInt1, int paramInt2, int paramInt3) {
/* 470 */     if (paramInt3 == 512) {
/* 471 */       return pickClosest(paramInt1, paramInt2);
/*     */     }
/* 473 */     if (paramInt3 == 256) {
/* 474 */       return pickGeomClosest(paramInt1, paramInt2);
/*     */     }
/*     */     
/* 477 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphPath[] pickGeomAll(int paramInt1, int paramInt2) {
/* 483 */     byte b2 = 0;
/*     */     
/* 485 */     this.pickRay = (PickRay)generatePickRay(paramInt1, paramInt2);
/* 486 */     this.sceneGraphPathArr = this.pickRoot.pickAll(this.pickRay);
/*     */     
/* 488 */     if (this.sceneGraphPathArr == null) {
/* 489 */       return null;
/*     */     }
/* 491 */     boolean[] arrayOfBoolean = new boolean[this.sceneGraphPathArr.length];
/*     */     byte b1;
/* 493 */     for (b1 = 0; b1 < this.sceneGraphPathArr.length; b1++) {
/* 494 */       Node node = this.sceneGraphPathArr[b1].getObject();
/* 495 */       if (node instanceof Shape3D) {
/* 496 */         arrayOfBoolean[b1] = ((Shape3D)node).intersect(this.sceneGraphPathArr[b1], this.pickRay);
/*     */       }
/* 498 */       else if (node instanceof Morph) {
/* 499 */         arrayOfBoolean[b1] = ((Morph)node).intersect(this.sceneGraphPathArr[b1], this.pickRay);
/*     */       } 
/*     */       
/* 502 */       if (arrayOfBoolean[b1] == true) {
/* 503 */         b2++;
/*     */       }
/*     */     } 
/* 506 */     if (b2 == 0) {
/* 507 */       return null;
/*     */     }
/* 509 */     SceneGraphPath[] arrayOfSceneGraphPath = new SceneGraphPath[b2];
/*     */     
/* 511 */     b2 = 0;
/* 512 */     for (b1 = 0; b1 < this.sceneGraphPathArr.length; b1++) {
/* 513 */       if (arrayOfBoolean[b1] == true) {
/* 514 */         arrayOfSceneGraphPath[b2++] = this.sceneGraphPathArr[b1];
/*     */       }
/*     */     } 
/* 517 */     return arrayOfSceneGraphPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphPath[] pickGeomAllSorted(int paramInt1, int paramInt2) {
/* 525 */     byte b2 = 0;
/* 526 */     double[] arrayOfDouble1 = new double[1];
/*     */ 
/*     */     
/* 529 */     this.pickRay = (PickRay)generatePickRay(paramInt1, paramInt2);
/* 530 */     this.sceneGraphPathArr = this.pickRoot.pickAll(this.pickRay);
/*     */     
/* 532 */     if (this.sceneGraphPathArr == null) {
/* 533 */       return null;
/*     */     }
/* 535 */     boolean[] arrayOfBoolean = new boolean[this.sceneGraphPathArr.length];
/* 536 */     double[] arrayOfDouble2 = new double[this.sceneGraphPathArr.length];
/*     */     byte b1;
/* 538 */     for (b1 = 0; b1 < this.sceneGraphPathArr.length; b1++) {
/* 539 */       Node node = this.sceneGraphPathArr[b1].getObject();
/* 540 */       if (node instanceof Shape3D) {
/* 541 */         arrayOfBoolean[b1] = ((Shape3D)node).intersect(this.sceneGraphPathArr[b1], this.pickRay, arrayOfDouble1);
/*     */         
/* 543 */         arrayOfDouble2[b1] = arrayOfDouble1[0];
/* 544 */       } else if (node instanceof Morph) {
/* 545 */         arrayOfBoolean[b1] = ((Morph)node).intersect(this.sceneGraphPathArr[b1], this.pickRay, arrayOfDouble1);
/*     */         
/* 547 */         arrayOfDouble2[b1] = arrayOfDouble1[0];
/*     */       } 
/* 549 */       if (arrayOfBoolean[b1] == true) {
/* 550 */         b2++;
/*     */       }
/*     */     } 
/* 553 */     if (b2 == 0) {
/* 554 */       return null;
/*     */     }
/* 556 */     SceneGraphPath[] arrayOfSceneGraphPath = new SceneGraphPath[b2];
/* 557 */     this.distance = new double[b2];
/*     */     
/* 559 */     b2 = 0;
/* 560 */     for (b1 = 0; b1 < this.sceneGraphPathArr.length; b1++) {
/* 561 */       if (arrayOfBoolean[b1] == true) {
/* 562 */         arrayOfSceneGraphPath[b2] = this.sceneGraphPathArr[b1];
/* 563 */         this.distance[b2++] = arrayOfDouble2[b1];
/*     */       } 
/*     */     } 
/*     */     
/* 567 */     return sort(arrayOfSceneGraphPath);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphPath pickGeomClosest(int paramInt1, int paramInt2) {
/* 573 */     SceneGraphPath[] arrayOfSceneGraphPath = pickGeomAllSorted(paramInt1, paramInt2);
/*     */     
/* 575 */     if (arrayOfSceneGraphPath == null) {
/* 576 */       return null;
/*     */     }
/* 578 */     return arrayOfSceneGraphPath[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphPath pickGeomAny(int paramInt1, int paramInt2) {
/* 587 */     this.pickRay = (PickRay)generatePickRay(paramInt1, paramInt2);
/* 588 */     this.sceneGraphPathArr = this.pickRoot.pickAll(this.pickRay);
/* 589 */     for (byte b = 0; b < this.sceneGraphPathArr.length; b++) {
/* 590 */       Node node = this.sceneGraphPathArr[b].getObject();
/* 591 */       if (node instanceof Shape3D) {
/* 592 */         if (((Shape3D)node).intersect(this.sceneGraphPathArr[b], this.pickRay))
/* 593 */           return this.sceneGraphPathArr[b]; 
/* 594 */       } else if (node instanceof Morph && (
/* 595 */         (Morph)node).intersect(this.sceneGraphPathArr[b], this.pickRay)) {
/* 596 */         return this.sceneGraphPathArr[b];
/*     */       } 
/*     */     } 
/*     */     
/* 600 */     return null;
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
/*     */   
/*     */   private SceneGraphPath[] sort(SceneGraphPath[] paramArrayOfSceneGraphPath) {
/* 619 */     if (paramArrayOfSceneGraphPath == null) {
/* 620 */       return null;
/*     */     }
/* 622 */     SceneGraphPath[] arrayOfSceneGraphPath = new SceneGraphPath[paramArrayOfSceneGraphPath.length];
/* 623 */     this.position = new int[paramArrayOfSceneGraphPath.length];
/*     */     byte b;
/* 625 */     for (b = 0; b < paramArrayOfSceneGraphPath.length; b++) {
/* 626 */       this.position[b] = b;
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
/*     */     
/* 638 */     quicksort(0, this.distance.length - 1);
/*     */     
/* 640 */     for (b = 0; b < this.distance.length; b++) {
/* 641 */       arrayOfSceneGraphPath[b] = paramArrayOfSceneGraphPath[this.position[b]];
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
/* 652 */     return arrayOfSceneGraphPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void quicksort(int paramInt1, int paramInt2) {
/* 660 */     int i = paramInt1;
/* 661 */     int j = paramInt2;
/* 662 */     double d = this.distance[(paramInt1 + paramInt2) / 2];
/*     */     do {
/* 664 */       for (; this.distance[i] < d; i++);
/* 665 */       for (; d < this.distance[j]; j--);
/* 666 */       if (i > j)
/* 667 */         continue;  double d1 = this.distance[i];
/* 668 */       this.distance[i] = this.distance[j];
/* 669 */       this.distance[j] = d1;
/*     */       
/* 671 */       int k = this.position[i];
/* 672 */       this.position[i] = this.position[j];
/* 673 */       this.position[j] = k;
/* 674 */       i++;
/* 675 */       j--;
/*     */     }
/* 677 */     while (i <= j);
/*     */     
/* 679 */     if (paramInt1 < j) quicksort(paramInt1, j); 
/* 680 */     if (paramInt1 < paramInt2) quicksort(i, paramInt2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node pickNode(SceneGraphPath paramSceneGraphPath, int paramInt) {
/* 701 */     if (paramSceneGraphPath != null) {
/* 702 */       Node node = paramSceneGraphPath.getObject();
/*     */       
/* 704 */       if (node instanceof Shape3D && (paramInt & true) != 0)
/*     */       {
/* 706 */         return node;
/*     */       }
/* 708 */       if (node instanceof Morph && (paramInt & 0x2) != 0)
/*     */       {
/* 710 */         return node;
/*     */       }
/*     */       
/* 713 */       for (int i = paramSceneGraphPath.nodeCount() - 1; i >= 0; i--) {
/* 714 */         node = paramSceneGraphPath.getNode(i);
/*     */ 
/*     */         
/* 717 */         if (node instanceof com.sun.j3d.utils.geometry.Primitive && (paramInt & 0x4) != 0)
/*     */         {
/*     */           
/* 720 */           return node;
/*     */         }
/* 722 */         if (node instanceof javax.media.j3d.Link && (paramInt & 0x8) != 0)
/*     */         {
/* 724 */           return node;
/*     */         }
/* 726 */         if (node instanceof javax.media.j3d.Switch && (paramInt & 0x80) != 0)
/*     */         {
/* 728 */           return node;
/*     */         }
/* 730 */         if (node instanceof javax.media.j3d.TransformGroup && (paramInt & 0x20) != 0)
/*     */         {
/*     */           
/* 733 */           return node;
/*     */         }
/* 735 */         if (node instanceof BranchGroup && (paramInt & 0x40) != 0)
/*     */         {
/*     */           
/* 738 */           return node;
/*     */         }
/* 740 */         if (node instanceof javax.media.j3d.Group && (paramInt & 0x10) != 0)
/*     */         {
/* 742 */           return node;
/*     */         }
/*     */       } 
/*     */       
/* 746 */       if (node == null);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 752 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node pickNode(SceneGraphPath paramSceneGraphPath, int paramInt1, int paramInt2) {
/* 776 */     byte b = 0;
/*     */     
/* 778 */     if (paramSceneGraphPath != null) {
/* 779 */       Node node = paramSceneGraphPath.getObject();
/*     */ 
/*     */ 
/*     */       
/* 783 */       if (node instanceof Shape3D && (paramInt1 & true) != 0)
/*     */       {
/* 785 */         return node; } 
/* 786 */       if (node instanceof Morph && (paramInt1 & 0x2) != 0)
/*     */       {
/* 788 */         return node;
/*     */       }
/*     */       
/* 791 */       for (byte b1 = 0; b1 < paramSceneGraphPath.nodeCount(); b1++) {
/* 792 */         node = paramSceneGraphPath.getNode(b1);
/*     */ 
/*     */         
/* 795 */         if (node instanceof javax.media.j3d.Group && (paramInt1 & 0x10) != 0) {
/*     */           
/* 797 */           b++;
/* 798 */           if (b == paramInt2) {
/* 799 */             return node;
/*     */           }
/* 801 */         } else if (node instanceof BranchGroup && (paramInt1 & 0x40) != 0) {
/*     */ 
/*     */           
/* 804 */           b++;
/* 805 */           if (b == paramInt2) {
/* 806 */             return node;
/*     */           }
/* 808 */         } else if (node instanceof javax.media.j3d.TransformGroup && (paramInt1 & 0x20) != 0) {
/*     */ 
/*     */           
/* 811 */           b++;
/* 812 */           if (b == paramInt2) {
/* 813 */             return node;
/*     */           }
/* 815 */         } else if (node instanceof com.sun.j3d.utils.geometry.Primitive && (paramInt1 & 0x4) != 0) {
/*     */ 
/*     */           
/* 818 */           b++;
/* 819 */           if (b == paramInt2) {
/* 820 */             return node;
/*     */           }
/* 822 */         } else if (node instanceof javax.media.j3d.Link && (paramInt1 & 0x8) != 0) {
/*     */           
/* 824 */           b++;
/* 825 */           if (b == paramInt2) {
/* 826 */             return node;
/*     */           }
/*     */         } 
/*     */       } 
/* 830 */       if (node == null);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 836 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\picking\PickObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */