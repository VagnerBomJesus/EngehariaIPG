/*     */ package com.sun.j3d.utils.pickfast;
/*     */ 
/*     */ import javax.media.j3d.Bounds;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Locale;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.PickBounds;
/*     */ import javax.media.j3d.PickConeRay;
/*     */ import javax.media.j3d.PickConeSegment;
/*     */ import javax.media.j3d.PickCylinderRay;
/*     */ import javax.media.j3d.PickCylinderSegment;
/*     */ import javax.media.j3d.PickInfo;
/*     */ import javax.media.j3d.PickRay;
/*     */ import javax.media.j3d.PickSegment;
/*     */ import javax.media.j3d.PickShape;
/*     */ import javax.media.j3d.SceneGraphPath;
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
/*     */ public class PickTool
/*     */ {
/*     */   public static final int TYPE_SHAPE3D = 1;
/*     */   public static final int TYPE_MORPH = 2;
/*     */   public static final int TYPE_PRIMITIVE = 4;
/*     */   public static final int TYPE_LINK = 8;
/*     */   public static final int TYPE_GROUP = 16;
/*     */   public static final int TYPE_TRANSFORM_GROUP = 32;
/*     */   public static final int TYPE_BRANCH_GROUP = 64;
/*     */   public static final int TYPE_SWITCH = 128;
/*     */   private static final int ALL_FLAGS = 127;
/*     */   private final boolean debug = false;
/*     */   protected boolean userDefineShape;
/*     */   PickShape pickShape;
/*     */   BranchGroup pickRootBG;
/*     */   Locale pickRootL;
/*     */   Point3d start;
/*     */   int mode;
/*     */   int flags;
/*     */   
/*     */   public PickTool(BranchGroup paramBranchGroup) {
/* 175 */     this.debug = false;
/* 176 */     this.userDefineShape = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     this.pickRootBG = null;
/*     */     
/* 183 */     this.pickRootL = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     this.start = null;
/*     */     
/* 190 */     this.mode = 1;
/* 191 */     this.flags = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     this.pickRootBG = paramBranchGroup; } public PickTool(Locale paramLocale) { this.debug = false;
/*     */     this.userDefineShape = false;
/*     */     this.pickRootBG = null;
/*     */     this.pickRootL = null;
/*     */     this.start = null;
/*     */     this.mode = 1;
/*     */     this.flags = 2;
/* 206 */     this.pickRootL = paramLocale; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public BranchGroup getBranchGroup() { return this.pickRootBG; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public Locale getLocale() { return this.pickRootL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShape(PickShape paramPickShape, Point3d paramPoint3d) {
/* 231 */     this.pickShape = paramPickShape;
/* 232 */     this.start = paramPoint3d;
/* 233 */     this.userDefineShape = (paramPickShape != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeBounds(Bounds paramBounds, Point3d paramPoint3d) {
/* 241 */     this.pickShape = new PickBounds(paramBounds);
/* 242 */     this.start = paramPoint3d;
/* 243 */     this.userDefineShape = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMode(int paramInt) {
/* 251 */     if (paramInt != 1 && paramInt != 2) {
/* 252 */       throw new IllegalArgumentException();
/*     */     }
/* 254 */     this.mode = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public int getMode() { return this.mode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int paramInt) {
/* 278 */     if ((paramInt & 0xFFFFFF80) != 0) {
/* 279 */       throw new IllegalArgumentException();
/*     */     }
/* 281 */     this.flags = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public int getFlags() { return this.flags; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeRay(Point3d paramPoint3d, Vector3d paramVector3d) {
/* 295 */     this.pickShape = new PickRay(paramPoint3d, paramVector3d);
/* 296 */     this.start = paramPoint3d;
/* 297 */     this.userDefineShape = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeSegment(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/* 305 */     this.pickShape = new PickSegment(paramPoint3d1, paramPoint3d2);
/* 306 */     this.start = paramPoint3d1;
/* 307 */     this.userDefineShape = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeCylinderSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, double paramDouble) {
/* 317 */     this.pickShape = new PickCylinderSegment(paramPoint3d1, paramPoint3d2, paramDouble);
/*     */     
/* 319 */     this.start = paramPoint3d1;
/* 320 */     this.userDefineShape = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeCylinderRay(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/* 330 */     this.pickShape = new PickCylinderRay(paramPoint3d, paramVector3d, paramDouble);
/* 331 */     this.start = paramPoint3d;
/* 332 */     this.userDefineShape = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeConeSegment(Point3d paramPoint3d1, Point3d paramPoint3d2, double paramDouble) {
/* 342 */     this.pickShape = new PickConeSegment(paramPoint3d1, paramPoint3d2, paramDouble);
/* 343 */     this.start = paramPoint3d1;
/* 344 */     this.userDefineShape = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeConeRay(Point3d paramPoint3d, Vector3d paramVector3d, double paramDouble) {
/* 354 */     this.pickShape = new PickConeRay(paramPoint3d, paramVector3d, paramDouble);
/* 355 */     this.start = paramPoint3d;
/* 356 */     this.userDefineShape = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 361 */   public PickShape getPickShape() { return this.pickShape; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 366 */   public Point3d getStartPosition() { return this.start; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickInfo[] pickAll() {
/* 375 */     PickInfo[] arrayOfPickInfo = null;
/* 376 */     if (this.pickRootBG != null) {
/* 377 */       arrayOfPickInfo = this.pickRootBG.pickAll(this.mode, this.flags, this.pickShape);
/* 378 */     } else if (this.pickRootL != null) {
/* 379 */       arrayOfPickInfo = this.pickRootL.pickAll(this.mode, this.flags, this.pickShape);
/*     */     } 
/* 381 */     return arrayOfPickInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickInfo pickAny() {
/* 390 */     PickInfo pickInfo = null;
/* 391 */     if (this.pickRootBG != null) {
/* 392 */       pickInfo = this.pickRootBG.pickAny(this.mode, this.flags, this.pickShape);
/* 393 */     } else if (this.pickRootL != null) {
/* 394 */       pickInfo = this.pickRootL.pickAny(this.mode, this.flags, this.pickShape);
/*     */     } 
/* 396 */     return pickInfo;
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
/*     */   public PickInfo[] pickAllSorted() {
/* 408 */     PickInfo[] arrayOfPickInfo = null;
/* 409 */     if (this.pickRootBG != null) {
/* 410 */       arrayOfPickInfo = this.pickRootBG.pickAllSorted(this.mode, this.flags, this.pickShape);
/* 411 */     } else if (this.pickRootL != null) {
/* 412 */       arrayOfPickInfo = this.pickRootL.pickAllSorted(this.mode, this.flags, this.pickShape);
/*     */     } 
/* 414 */     return arrayOfPickInfo;
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
/*     */   public PickInfo pickClosest() {
/* 427 */     PickInfo pickInfo = null;
/* 428 */     if (this.pickRootBG != null) {
/* 429 */       pickInfo = this.pickRootBG.pickClosest(this.mode, this.flags, this.pickShape);
/* 430 */     } else if (this.pickRootL != null) {
/* 431 */       pickInfo = this.pickRootL.pickClosest(this.mode, this.flags, this.pickShape);
/*     */     } 
/*     */ 
/*     */     
/* 435 */     return pickInfo;
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
/*     */   public Node getNode(PickInfo paramPickInfo, int paramInt) {
/* 450 */     if (paramPickInfo == null) {
/* 451 */       return null;
/*     */     }
/*     */     
/* 454 */     SceneGraphPath sceneGraphPath = paramPickInfo.getSceneGraphPath();
/* 455 */     Node node = paramPickInfo.getNode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 464 */     if (node instanceof javax.media.j3d.Shape3D && (paramInt & true) != 0)
/*     */     {
/* 466 */       return node;
/*     */     }
/* 468 */     if (node instanceof javax.media.j3d.Morph && (paramInt & 0x2) != 0)
/*     */     {
/* 470 */       return node;
/*     */     }
/*     */     
/* 473 */     for (int i = sceneGraphPath.nodeCount() - 1; i >= 0; i--) {
/* 474 */       Node node1 = sceneGraphPath.getNode(i);
/*     */ 
/*     */       
/* 477 */       if (node1 instanceof com.sun.j3d.utils.geometry.Primitive && (paramInt & 0x4) != 0)
/*     */       {
/*     */         
/* 480 */         return node1;
/*     */       }
/* 482 */       if (node1 instanceof javax.media.j3d.Link && (paramInt & 0x8) != 0)
/*     */       {
/* 484 */         return node1;
/*     */       }
/* 486 */       if (node1 instanceof javax.media.j3d.Switch && (paramInt & 0x80) != 0)
/*     */       {
/* 488 */         return node1;
/*     */       }
/* 490 */       if (node1 instanceof javax.media.j3d.TransformGroup && (paramInt & 0x20) != 0)
/*     */       {
/*     */         
/* 493 */         return node1;
/*     */       }
/* 495 */       if (node1 instanceof BranchGroup && (paramInt & 0x40) != 0)
/*     */       {
/*     */         
/* 498 */         return node1;
/*     */       }
/* 500 */       if (node1 instanceof javax.media.j3d.Group && (paramInt & 0x10) != 0)
/*     */       {
/* 502 */         return node1;
/*     */       }
/*     */     } 
/*     */     
/* 506 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\pickfast\PickTool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */