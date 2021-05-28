/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OrientedShape3D
/*     */   extends Shape3D
/*     */ {
/*     */   public static final int ROTATE_ABOUT_AXIS = 0;
/*     */   public static final int ROTATE_ABOUT_POINT = 1;
/*     */   public static final int ROTATE_NONE = 2;
/*     */   public static final int ALLOW_MODE_READ = 20;
/*     */   public static final int ALLOW_MODE_WRITE = 21;
/*     */   public static final int ALLOW_AXIS_READ = 22;
/*     */   public static final int ALLOW_AXIS_WRITE = 23;
/*     */   public static final int ALLOW_POINT_READ = 24;
/*     */   public static final int ALLOW_POINT_WRITE = 25;
/*     */   public static final int ALLOW_SCALE_READ = 26;
/*     */   public static final int ALLOW_SCALE_WRITE = 27;
/* 152 */   private static final int[] readCapabilities = { 20, 22, 24, 26 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public OrientedShape3D() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OrientedShape3D(Geometry paramGeometry, Appearance paramAppearance, int paramInt, Vector3f paramVector3f) {
/* 198 */     super(paramGeometry, paramAppearance);
/*     */ 
/*     */     
/* 201 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 203 */     ((OrientedShape3DRetained)this.retained).initAlignmentMode(paramInt);
/* 204 */     ((OrientedShape3DRetained)this.retained).initAlignmentAxis(paramVector3f);
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
/*     */   public OrientedShape3D(Geometry paramGeometry, Appearance paramAppearance, int paramInt, Point3f paramPoint3f) {
/* 223 */     super(paramGeometry, paramAppearance);
/*     */ 
/*     */     
/* 226 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 228 */     ((OrientedShape3DRetained)this.retained).initAlignmentMode(paramInt);
/* 229 */     ((OrientedShape3DRetained)this.retained).initRotationPoint(paramPoint3f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OrientedShape3D(Geometry paramGeometry, Appearance paramAppearance, int paramInt, Vector3f paramVector3f, boolean paramBoolean, double paramDouble) {
/* 263 */     super(paramGeometry, paramAppearance);
/*     */ 
/*     */     
/* 266 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 268 */     ((OrientedShape3DRetained)this.retained).initAlignmentMode(paramInt);
/* 269 */     ((OrientedShape3DRetained)this.retained).initAlignmentAxis(paramVector3f);
/* 270 */     ((OrientedShape3DRetained)this.retained).initConstantScaleEnable(paramBoolean);
/*     */     
/* 272 */     ((OrientedShape3DRetained)this.retained).initScale(paramDouble);
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
/*     */ 
/*     */   
/*     */   public OrientedShape3D(Geometry paramGeometry, Appearance paramAppearance, int paramInt, Point3f paramPoint3f, boolean paramBoolean, double paramDouble) {
/* 298 */     super(paramGeometry, paramAppearance);
/*     */ 
/*     */     
/* 301 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 303 */     ((OrientedShape3DRetained)this.retained).initAlignmentMode(paramInt);
/* 304 */     ((OrientedShape3DRetained)this.retained).initRotationPoint(paramPoint3f);
/* 305 */     ((OrientedShape3DRetained)this.retained).initConstantScaleEnable(paramBoolean);
/*     */     
/* 307 */     ((OrientedShape3DRetained)this.retained).initScale(paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 316 */     this.retained = new OrientedShape3DRetained();
/* 317 */     this.retained.setSource(this);
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
/*     */   public void setAlignmentMode(int paramInt) {
/* 331 */     if (isLiveOrCompiled() && 
/* 332 */       !getCapability(21))
/* 333 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D0")); 
/* 334 */     if (isLive()) {
/* 335 */       ((OrientedShape3DRetained)this.retained).setAlignmentMode(paramInt);
/*     */     } else {
/* 337 */       ((OrientedShape3DRetained)this.retained).initAlignmentMode(paramInt);
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
/*     */   public int getAlignmentMode() {
/* 351 */     if (isLiveOrCompiled() && 
/* 352 */       !getCapability(20))
/* 353 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D1")); 
/* 354 */     return ((OrientedShape3DRetained)this.retained).getAlignmentMode();
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
/*     */   public void setAlignmentAxis(Vector3f paramVector3f) {
/* 373 */     if (isLiveOrCompiled() && 
/* 374 */       !getCapability(23))
/* 375 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D2")); 
/* 376 */     if (isLive()) {
/* 377 */       ((OrientedShape3DRetained)this.retained).setAlignmentAxis(paramVector3f);
/*     */     } else {
/* 379 */       ((OrientedShape3DRetained)this.retained).initAlignmentAxis(paramVector3f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlignmentAxis(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 400 */     if (isLiveOrCompiled() && 
/* 401 */       !getCapability(23))
/* 402 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D2")); 
/* 403 */     if (isLive()) {
/* 404 */       ((OrientedShape3DRetained)this.retained).setAlignmentAxis(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 406 */       ((OrientedShape3DRetained)this.retained).initAlignmentAxis(paramFloat1, paramFloat2, paramFloat3);
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
/*     */   public void getAlignmentAxis(Vector3f paramVector3f) {
/* 420 */     if (isLiveOrCompiled() && 
/* 421 */       !getCapability(22))
/* 422 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D3")); 
/* 423 */     ((OrientedShape3DRetained)this.retained).getAlignmentAxis(paramVector3f);
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
/*     */   public void setRotationPoint(Point3f paramPoint3f) {
/* 436 */     if (isLiveOrCompiled() && 
/* 437 */       !getCapability(25))
/* 438 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D4")); 
/* 439 */     if (isLive()) {
/* 440 */       ((OrientedShape3DRetained)this.retained).setRotationPoint(paramPoint3f);
/*     */     } else {
/* 442 */       ((OrientedShape3DRetained)this.retained).initRotationPoint(paramPoint3f);
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
/*     */   public void setRotationPoint(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 458 */     if (isLiveOrCompiled() && 
/* 459 */       !getCapability(25))
/* 460 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D4")); 
/* 461 */     if (isLive()) {
/* 462 */       ((OrientedShape3DRetained)this.retained).setRotationPoint(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 464 */       ((OrientedShape3DRetained)this.retained).initRotationPoint(paramFloat1, paramFloat2, paramFloat3);
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
/*     */   public void getRotationPoint(Point3f paramPoint3f) {
/* 478 */     if (isLiveOrCompiled() && 
/* 479 */       !getCapability(24))
/* 480 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D5")); 
/* 481 */     ((OrientedShape3DRetained)this.retained).getRotationPoint(paramPoint3f);
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
/*     */   public void setConstantScaleEnable(boolean paramBoolean) {
/* 497 */     if (isLiveOrCompiled() && 
/* 498 */       !getCapability(27)) {
/* 499 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D6"));
/*     */     }
/* 501 */     if (isLive()) {
/* 502 */       ((OrientedShape3DRetained)this.retained).setConstantScaleEnable(paramBoolean);
/*     */     } else {
/*     */       
/* 505 */       ((OrientedShape3DRetained)this.retained).initConstantScaleEnable(paramBoolean);
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
/*     */   public boolean getConstantScaleEnable() {
/* 521 */     if (isLiveOrCompiled() && 
/* 522 */       !getCapability(26)) {
/* 523 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D7"));
/*     */     }
/* 525 */     return ((OrientedShape3DRetained)this.retained).getConstantScaleEnable();
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
/*     */   public void setScale(double paramDouble) {
/* 541 */     if (isLiveOrCompiled() && 
/* 542 */       !getCapability(27)) {
/* 543 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D8"));
/*     */     }
/* 545 */     if (isLive()) {
/* 546 */       ((OrientedShape3DRetained)this.retained).setScale(paramDouble);
/*     */     } else {
/* 548 */       ((OrientedShape3DRetained)this.retained).initScale(paramDouble);
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
/*     */   public double getScale() {
/* 563 */     if (isLiveOrCompiled() && 
/* 564 */       !getCapability(26)) {
/* 565 */       throw new CapabilityNotSetException(J3dI18N.getString("OrientedShape3D9"));
/*     */     }
/* 567 */     return ((OrientedShape3DRetained)this.retained).getScale();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 595 */     OrientedShape3D orientedShape3D = new OrientedShape3D();
/* 596 */     orientedShape3D.duplicateNode(this, paramBoolean);
/* 597 */     return orientedShape3D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 632 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { checkDuplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 660 */     super.duplicateAttributes(paramNode, paramBoolean);
/* 661 */     OrientedShape3DRetained orientedShape3DRetained1 = (OrientedShape3DRetained)paramNode.retained;
/*     */     
/* 663 */     OrientedShape3DRetained orientedShape3DRetained2 = (OrientedShape3DRetained)this.retained;
/*     */     
/* 665 */     orientedShape3DRetained2.setAlignmentMode(orientedShape3DRetained1.getAlignmentMode());
/* 666 */     Vector3f vector3f = new Vector3f();
/* 667 */     orientedShape3DRetained1.getAlignmentAxis(vector3f);
/* 668 */     orientedShape3DRetained2.setAlignmentAxis(vector3f);
/* 669 */     Point3f point3f = new Point3f();
/* 670 */     orientedShape3DRetained1.getRotationPoint(point3f);
/* 671 */     orientedShape3DRetained2.setRotationPoint(point3f);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\OrientedShape3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */