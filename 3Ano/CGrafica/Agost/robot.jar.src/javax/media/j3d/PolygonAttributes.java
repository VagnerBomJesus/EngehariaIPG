/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PolygonAttributes
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_CULL_FACE_READ = 0;
/*     */   public static final int ALLOW_CULL_FACE_WRITE = 1;
/*     */   public static final int ALLOW_NORMAL_FLIP_READ = 6;
/*     */   public static final int ALLOW_NORMAL_FLIP_WRITE = 7;
/*     */   public static final int ALLOW_MODE_READ = 2;
/*     */   public static final int ALLOW_MODE_WRITE = 3;
/*     */   public static final int ALLOW_OFFSET_READ = 4;
/*     */   public static final int ALLOW_OFFSET_WRITE = 5;
/*     */   public static final int POLYGON_POINT = 0;
/*     */   public static final int POLYGON_LINE = 1;
/*     */   public static final int POLYGON_FILL = 2;
/*     */   public static final int CULL_NONE = 0;
/*     */   public static final int CULL_BACK = 1;
/*     */   public static final int CULL_FRONT = 2;
/* 150 */   private static final int[] readCapabilities = { 0, 2, 6, 4 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public PolygonAttributes() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public PolygonAttributes(int paramInt1, int paramInt2, float paramFloat) { this(paramInt1, paramInt2, paramFloat, false, 0.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public PolygonAttributes(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean) { this(paramInt1, paramInt2, paramFloat, paramBoolean, 0.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PolygonAttributes(int paramInt1, int paramInt2, float paramFloat1, boolean paramBoolean, float paramFloat2) {
/* 223 */     if (paramInt1 < 0 || paramInt1 > 2) {
/* 224 */       throw new IllegalArgumentException(J3dI18N.getString("PolygonAttributes0"));
/*     */     }
/* 226 */     if (paramInt2 < 0 || paramInt2 > 2) {
/* 227 */       throw new IllegalArgumentException(J3dI18N.getString("PolygonAttributes12"));
/*     */     }
/*     */     
/* 230 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 232 */     ((PolygonAttributesRetained)this.retained).initPolygonMode(paramInt1);
/* 233 */     ((PolygonAttributesRetained)this.retained).initCullFace(paramInt2);
/* 234 */     ((PolygonAttributesRetained)this.retained).initPolygonOffset(paramFloat1);
/* 235 */     ((PolygonAttributesRetained)this.retained).initBackFaceNormalFlip(paramBoolean);
/* 236 */     ((PolygonAttributesRetained)this.retained).initPolygonOffsetFactor(paramFloat2);
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
/*     */   public void setCullFace(int paramInt) {
/* 248 */     if (isLiveOrCompiled() && 
/* 249 */       !getCapability(1)) {
/* 250 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes2"));
/*     */     }
/* 252 */     if (paramInt < 0 || paramInt > 2)
/* 253 */       throw new IllegalArgumentException(J3dI18N.getString("PolygonAttributes3")); 
/* 254 */     if (isLive()) {
/* 255 */       ((PolygonAttributesRetained)this.retained).setCullFace(paramInt);
/*     */     } else {
/* 257 */       ((PolygonAttributesRetained)this.retained).initCullFace(paramInt);
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
/*     */   public int getCullFace() {
/* 269 */     if (isLiveOrCompiled() && 
/* 270 */       !getCapability(0)) {
/* 271 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes4"));
/*     */     }
/* 273 */     return ((PolygonAttributesRetained)this.retained).getCullFace();
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
/*     */   public void setBackFaceNormalFlip(boolean paramBoolean) {
/* 288 */     if (isLiveOrCompiled() && 
/* 289 */       !getCapability(7))
/* 290 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes5")); 
/* 291 */     if (isLive()) {
/* 292 */       ((PolygonAttributesRetained)this.retained).setBackFaceNormalFlip(paramBoolean);
/*     */     } else {
/* 294 */       ((PolygonAttributesRetained)this.retained).initBackFaceNormalFlip(paramBoolean);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBackFaceNormalFlip() {
/* 305 */     if (isLiveOrCompiled() && 
/* 306 */       !getCapability(6)) {
/* 307 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes6"));
/*     */     }
/* 309 */     return ((PolygonAttributesRetained)this.retained).getBackFaceNormalFlip();
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
/*     */   public void setPolygonMode(int paramInt) {
/* 321 */     if (isLiveOrCompiled() && 
/* 322 */       !getCapability(3)) {
/* 323 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes7"));
/*     */     }
/* 325 */     if (paramInt < 0 || paramInt > 2)
/* 326 */       throw new IllegalArgumentException(J3dI18N.getString("PolygonAttributes8")); 
/* 327 */     if (isLive()) {
/* 328 */       ((PolygonAttributesRetained)this.retained).setPolygonMode(paramInt);
/*     */     } else {
/* 330 */       ((PolygonAttributesRetained)this.retained).initPolygonMode(paramInt);
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
/*     */   public int getPolygonMode() {
/* 342 */     if (isLiveOrCompiled() && 
/* 343 */       !getCapability(2)) {
/* 344 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes9"));
/*     */     }
/* 346 */     return ((PolygonAttributesRetained)this.retained).getPolygonMode();
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
/*     */   public void setPolygonOffset(float paramFloat) {
/* 359 */     if (isLiveOrCompiled() && 
/* 360 */       !getCapability(5)) {
/* 361 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes10"));
/*     */     }
/* 363 */     if (isLive()) {
/* 364 */       ((PolygonAttributesRetained)this.retained).setPolygonOffset(paramFloat);
/*     */     } else {
/* 366 */       ((PolygonAttributesRetained)this.retained).initPolygonOffset(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPolygonOffset() {
/* 377 */     if (isLiveOrCompiled() && 
/* 378 */       !getCapability(4)) {
/* 379 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes11"));
/*     */     }
/* 381 */     return ((PolygonAttributesRetained)this.retained).getPolygonOffset();
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
/*     */   public void setPolygonOffsetFactor(float paramFloat) {
/* 396 */     if (isLiveOrCompiled() && 
/* 397 */       !getCapability(5)) {
/* 398 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes10"));
/*     */     }
/* 400 */     if (isLive()) {
/* 401 */       ((PolygonAttributesRetained)this.retained).setPolygonOffsetFactor(paramFloat);
/*     */     } else {
/*     */       
/* 404 */       ((PolygonAttributesRetained)this.retained).initPolygonOffsetFactor(paramFloat);
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
/*     */   public float getPolygonOffsetFactor() {
/* 417 */     if (isLiveOrCompiled() && 
/* 418 */       !getCapability(4)) {
/* 419 */       throw new CapabilityNotSetException(J3dI18N.getString("PolygonAttributes11"));
/*     */     }
/* 421 */     return ((PolygonAttributesRetained)this.retained).getPolygonOffsetFactor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 429 */     this.retained = new PolygonAttributesRetained();
/* 430 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 437 */     PolygonAttributes polygonAttributes = new PolygonAttributes();
/* 438 */     polygonAttributes.duplicateNodeComponent(this);
/* 439 */     return polygonAttributes;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 464 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 466 */     PolygonAttributesRetained polygonAttributesRetained1 = (PolygonAttributesRetained)paramNodeComponent.retained;
/*     */ 
/*     */     
/* 469 */     PolygonAttributesRetained polygonAttributesRetained2 = (PolygonAttributesRetained)this.retained;
/*     */     
/* 471 */     polygonAttributesRetained2.initCullFace(polygonAttributesRetained1.getCullFace());
/* 472 */     polygonAttributesRetained2.initBackFaceNormalFlip(polygonAttributesRetained1.getBackFaceNormalFlip());
/* 473 */     polygonAttributesRetained2.initPolygonMode(polygonAttributesRetained1.getPolygonMode());
/* 474 */     polygonAttributesRetained2.initPolygonOffset(polygonAttributesRetained1.getPolygonOffset());
/* 475 */     polygonAttributesRetained2.initPolygonOffsetFactor(polygonAttributesRetained1.getPolygonOffsetFactor());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\PolygonAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */