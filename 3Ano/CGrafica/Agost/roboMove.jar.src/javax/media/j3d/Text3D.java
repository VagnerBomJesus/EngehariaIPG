/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Text3D
/*     */   extends Geometry
/*     */ {
/*     */   public static final int ALLOW_FONT3D_READ = 0;
/*     */   public static final int ALLOW_FONT3D_WRITE = 1;
/*     */   public static final int ALLOW_STRING_READ = 2;
/*     */   public static final int ALLOW_STRING_WRITE = 3;
/*     */   public static final int ALLOW_POSITION_READ = 4;
/*     */   public static final int ALLOW_POSITION_WRITE = 5;
/*     */   public static final int ALLOW_ALIGNMENT_READ = 6;
/*     */   public static final int ALLOW_ALIGNMENT_WRITE = 7;
/*     */   public static final int ALLOW_PATH_READ = 8;
/*     */   public static final int ALLOW_PATH_WRITE = 9;
/*     */   public static final int ALLOW_CHARACTER_SPACING_READ = 10;
/*     */   public static final int ALLOW_CHARACTER_SPACING_WRITE = 11;
/*     */   public static final int ALLOW_BOUNDING_BOX_READ = 12;
/*     */   public static final int ALIGN_CENTER = 0;
/*     */   public static final int ALIGN_FIRST = 1;
/*     */   public static final int ALIGN_LAST = 2;
/*     */   public static final int PATH_LEFT = 0;
/*     */   public static final int PATH_RIGHT = 1;
/*     */   public static final int PATH_UP = 2;
/*     */   public static final int PATH_DOWN = 3;
/* 210 */   private static final int[] readCapabilities = { 0, 2, 4, 6, 8, 10, 12 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public Text3D() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Text3D(Font3D paramFont3D) {
/* 244 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 246 */     ((Text3DRetained)this.retained).setFont3D(paramFont3D);
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
/*     */   public Text3D(Font3D paramFont3D, String paramString) {
/* 259 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 261 */     ((Text3DRetained)this.retained).setFont3D(paramFont3D);
/* 262 */     ((Text3DRetained)this.retained).setString(paramString);
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
/*     */   public Text3D(Font3D paramFont3D, String paramString, Point3f paramPoint3f) {
/* 275 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 277 */     ((Text3DRetained)this.retained).setFont3D(paramFont3D);
/* 278 */     ((Text3DRetained)this.retained).setString(paramString);
/* 279 */     ((Text3DRetained)this.retained).setPosition(paramPoint3f);
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
/*     */   public Text3D(Font3D paramFont3D, String paramString, Point3f paramPoint3f, int paramInt1, int paramInt2) {
/* 294 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 296 */     ((Text3DRetained)this.retained).setFont3D(paramFont3D);
/* 297 */     ((Text3DRetained)this.retained).setString(paramString);
/* 298 */     ((Text3DRetained)this.retained).setPosition(paramPoint3f);
/* 299 */     ((Text3DRetained)this.retained).setAlignment(paramInt1);
/* 300 */     ((Text3DRetained)this.retained).setPath(paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 308 */     this.retained = new Text3DRetained();
/* 309 */     this.retained.setSource(this);
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
/*     */   public Font3D getFont3D() {
/* 323 */     if (isLiveOrCompiled() && 
/* 324 */       !getCapability(0))
/* 325 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D0")); 
/* 326 */     return ((Text3DRetained)this.retained).getFont3D();
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
/*     */   public void setFont3D(Font3D paramFont3D) {
/* 339 */     if (isLiveOrCompiled() && 
/* 340 */       !getCapability(1))
/* 341 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D1")); 
/* 342 */     ((Text3DRetained)this.retained).setFont3D(paramFont3D);
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
/*     */   public String getString() {
/* 356 */     if (isLiveOrCompiled() && 
/* 357 */       !getCapability(2))
/* 358 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D2")); 
/* 359 */     return ((Text3DRetained)this.retained).getString();
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
/*     */   public void setString(String paramString) {
/* 372 */     if (isLiveOrCompiled() && 
/* 373 */       !getCapability(3))
/* 374 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D3")); 
/* 375 */     ((Text3DRetained)this.retained).setString(paramString);
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
/*     */   public void getPosition(Point3f paramPoint3f) {
/* 393 */     if (isLiveOrCompiled() && 
/* 394 */       !getCapability(4))
/* 395 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D4")); 
/* 396 */     ((Text3DRetained)this.retained).getPosition(paramPoint3f);
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
/*     */   public void setPosition(Point3f paramPoint3f) {
/* 414 */     if (isLiveOrCompiled() && 
/* 415 */       !getCapability(5))
/* 416 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D5")); 
/* 417 */     ((Text3DRetained)this.retained).setPosition(paramPoint3f);
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
/*     */   public int getAlignment() {
/* 444 */     if (isLiveOrCompiled() && 
/* 445 */       !getCapability(6))
/* 446 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D6")); 
/* 447 */     return ((Text3DRetained)this.retained).getAlignment();
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
/*     */   public void setAlignment(int paramInt) {
/* 475 */     if (isLiveOrCompiled() && 
/* 476 */       !getCapability(7))
/* 477 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D7")); 
/* 478 */     ((Text3DRetained)this.retained).setAlignment(paramInt);
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
/*     */   public int getPath() {
/* 502 */     if (isLiveOrCompiled() && 
/* 503 */       !getCapability(8))
/* 504 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D8")); 
/* 505 */     return ((Text3DRetained)this.retained).getPath();
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
/*     */   public void setPath(int paramInt) {
/* 529 */     if (isLiveOrCompiled() && 
/* 530 */       !getCapability(9))
/* 531 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D9")); 
/* 532 */     ((Text3DRetained)this.retained).setPath(paramInt);
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
/*     */   public void getBoundingBox(BoundingBox paramBoundingBox) {
/* 546 */     if (isLiveOrCompiled() && 
/* 547 */       !getCapability(12))
/* 548 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D10")); 
/* 549 */     ((Text3DRetained)this.retained).getBoundingBox(paramBoundingBox);
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
/*     */   public float getCharacterSpacing() {
/* 565 */     if (isLiveOrCompiled() && 
/* 566 */       !getCapability(10))
/* 567 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D11")); 
/* 568 */     return ((Text3DRetained)this.retained).getCharacterSpacing();
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
/*     */   public void setCharacterSpacing(float paramFloat) {
/* 584 */     if (isLiveOrCompiled() && 
/* 585 */       !getCapability(11))
/* 586 */       throw new CapabilityNotSetException(J3dI18N.getString("Text3D12")); 
/* 587 */     ((Text3DRetained)this.retained).setCharacterSpacing(paramFloat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 596 */     Text3D text3D = new Text3D();
/* 597 */     text3D.duplicateNodeComponent(this);
/* 598 */     return text3D;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 621 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 623 */     Text3DRetained text3DRetained1 = (Text3DRetained)paramNodeComponent.retained;
/* 624 */     Text3DRetained text3DRetained2 = (Text3DRetained)this.retained;
/*     */     
/* 626 */     Font3D font3D = text3DRetained1.getFont3D();
/* 627 */     if (font3D != null) {
/* 628 */       text3DRetained2.setFont3D(font3D);
/*     */     }
/*     */     
/* 631 */     String str = text3DRetained1.getString();
/* 632 */     if (str != null) {
/* 633 */       text3DRetained2.setString(str);
/*     */     }
/*     */     
/* 636 */     Point3f point3f = new Point3f();
/* 637 */     text3DRetained1.getPosition(point3f);
/* 638 */     text3DRetained2.setPosition(point3f);
/* 639 */     text3DRetained2.setAlignment(text3DRetained1.getAlignment());
/* 640 */     text3DRetained2.setPath(text3DRetained1.getPath());
/* 641 */     text3DRetained2.setCharacterSpacing(text3DRetained1.getCharacterSpacing());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Text3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */