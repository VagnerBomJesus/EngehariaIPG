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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineAttributes
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_WIDTH_READ = 0;
/*     */   public static final int ALLOW_WIDTH_WRITE = 1;
/*     */   public static final int ALLOW_PATTERN_READ = 2;
/*     */   public static final int ALLOW_PATTERN_WRITE = 3;
/*     */   public static final int ALLOW_ANTIALIASING_READ = 4;
/*     */   public static final int ALLOW_ANTIALIASING_WRITE = 5;
/*     */   public static final int PATTERN_SOLID = 0;
/*     */   public static final int PATTERN_DASH = 1;
/*     */   public static final int PATTERN_DOT = 2;
/*     */   public static final int PATTERN_DASH_DOT = 3;
/*     */   public static final int PATTERN_USER_DEFINED = 4;
/* 173 */   private static final int[] readCapabilities = { 4, 2, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public LineAttributes() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineAttributes(float paramFloat, int paramInt, boolean paramBoolean) {
/* 205 */     if (paramInt < 0 || paramInt > 3) {
/* 206 */       throw new IllegalArgumentException(J3dI18N.getString("LineAttributes0"));
/*     */     }
/*     */     
/* 209 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 211 */     ((LineAttributesRetained)this.retained).initLineWidth(paramFloat);
/* 212 */     ((LineAttributesRetained)this.retained).initLinePattern(paramInt);
/* 213 */     ((LineAttributesRetained)this.retained).initLineAntialiasingEnable(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineWidth(float paramFloat) {
/* 223 */     if (isLiveOrCompiled() && 
/* 224 */       !getCapability(1))
/* 225 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes1")); 
/* 226 */     if (isLive()) {
/* 227 */       ((LineAttributesRetained)this.retained).setLineWidth(paramFloat);
/*     */     } else {
/* 229 */       ((LineAttributesRetained)this.retained).initLineWidth(paramFloat);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLineWidth() {
/* 240 */     if (isLiveOrCompiled() && 
/* 241 */       !getCapability(0))
/* 242 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes2")); 
/* 243 */     return ((LineAttributesRetained)this.retained).getLineWidth();
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
/*     */   public void setLinePattern(int paramInt) {
/* 255 */     if (isLiveOrCompiled() && 
/* 256 */       !getCapability(3)) {
/* 257 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes3"));
/*     */     }
/* 259 */     if (paramInt < 0 || paramInt > 4) {
/* 260 */       throw new IllegalArgumentException(J3dI18N.getString("LineAttributes4"));
/*     */     }
/* 262 */     if (isLive()) {
/* 263 */       ((LineAttributesRetained)this.retained).setLinePattern(paramInt);
/*     */     } else {
/* 265 */       ((LineAttributesRetained)this.retained).initLinePattern(paramInt);
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
/*     */   public int getLinePattern() {
/* 277 */     if (isLiveOrCompiled() && 
/* 278 */       !getCapability(2)) {
/* 279 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes5"));
/*     */     }
/* 281 */     return ((LineAttributesRetained)this.retained).getLinePattern();
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
/*     */   public void setPatternMask(int paramInt) {
/* 312 */     if (isLiveOrCompiled() && !getCapability(3))
/*     */     {
/* 314 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes8"));
/*     */     }
/* 316 */     if (isLive()) {
/* 317 */       ((LineAttributesRetained)this.retained).setPatternMask(paramInt);
/*     */     } else {
/* 319 */       ((LineAttributesRetained)this.retained).initPatternMask(paramInt);
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
/*     */   public int getPatternMask() {
/* 332 */     if (isLiveOrCompiled() && !getCapability(2))
/*     */     {
/* 334 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes9"));
/*     */     }
/* 336 */     return ((LineAttributesRetained)this.retained).getPatternMask();
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
/*     */   public void setPatternScaleFactor(int paramInt) {
/* 358 */     if (isLiveOrCompiled() && !getCapability(3))
/*     */     {
/* 360 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes10"));
/*     */     }
/* 362 */     if (isLive()) {
/* 363 */       ((LineAttributesRetained)this.retained).setPatternScaleFactor(paramInt);
/*     */     } else {
/* 365 */       ((LineAttributesRetained)this.retained).initPatternScaleFactor(paramInt);
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
/*     */   public int getPatternScaleFactor() {
/* 378 */     if (isLiveOrCompiled() && !getCapability(2))
/*     */     {
/* 380 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes11"));
/*     */     }
/* 382 */     return ((LineAttributesRetained)this.retained).getPatternScaleFactor();
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
/*     */   public void setLineAntialiasingEnable(boolean paramBoolean) {
/* 402 */     if (isLiveOrCompiled() && 
/* 403 */       !getCapability(5))
/* 404 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes6")); 
/* 405 */     if (isLive()) {
/* 406 */       ((LineAttributesRetained)this.retained).setLineAntialiasingEnable(paramBoolean);
/*     */     } else {
/* 408 */       ((LineAttributesRetained)this.retained).initLineAntialiasingEnable(paramBoolean);
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
/*     */   public boolean getLineAntialiasingEnable() {
/* 421 */     if (isLiveOrCompiled() && 
/* 422 */       !getCapability(4)) {
/* 423 */       throw new CapabilityNotSetException(J3dI18N.getString("LineAttributes7"));
/*     */     }
/* 425 */     return ((LineAttributesRetained)this.retained).getLineAntialiasingEnable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 433 */     this.retained = new LineAttributesRetained();
/* 434 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 443 */     LineAttributes lineAttributes = new LineAttributes();
/* 444 */     lineAttributes.duplicateNodeComponent(this);
/* 445 */     return lineAttributes;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 469 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */ 
/*     */     
/* 472 */     LineAttributesRetained lineAttributesRetained1 = (LineAttributesRetained)paramNodeComponent.retained;
/*     */     
/* 474 */     LineAttributesRetained lineAttributesRetained2 = (LineAttributesRetained)this.retained;
/*     */     
/* 476 */     lineAttributesRetained2.initLineWidth(lineAttributesRetained1.getLineWidth());
/* 477 */     lineAttributesRetained2.initLinePattern(lineAttributesRetained1.getLinePattern());
/* 478 */     lineAttributesRetained2.initLineAntialiasingEnable(lineAttributesRetained1.getLineAntialiasingEnable());
/* 479 */     lineAttributesRetained2.initPatternMask(lineAttributesRetained1.getPatternMask());
/* 480 */     lineAttributesRetained2.initPatternScaleFactor(lineAttributesRetained1.getPatternScaleFactor());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\LineAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */