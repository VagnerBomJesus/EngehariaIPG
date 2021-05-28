/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColoringAttributes
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_COLOR_READ = 0;
/*     */   public static final int ALLOW_COLOR_WRITE = 1;
/*     */   public static final int ALLOW_SHADE_MODEL_READ = 2;
/*     */   public static final int ALLOW_SHADE_MODEL_WRITE = 3;
/*     */   public static final int FASTEST = 0;
/*     */   public static final int NICEST = 1;
/*     */   public static final int SHADE_FLAT = 2;
/*     */   public static final int SHADE_GOURAUD = 3;
/* 119 */   private static final int[] readCapabilities = { 0, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public ColoringAttributes() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColoringAttributes(Color3f paramColor3f, int paramInt) {
/* 146 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 148 */     ((ColoringAttributesRetained)this.retained).initColor(paramColor3f);
/* 149 */     ((ColoringAttributesRetained)this.retained).initShadeModel(paramInt);
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
/*     */   public ColoringAttributes(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt) {
/* 164 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 166 */     ((ColoringAttributesRetained)this.retained).initColor(paramFloat1, paramFloat2, paramFloat3);
/* 167 */     ((ColoringAttributesRetained)this.retained).initShadeModel(paramInt);
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
/*     */   public void setColor(Color3f paramColor3f) {
/* 186 */     if (isLiveOrCompiled() && 
/* 187 */       !getCapability(1)) {
/* 188 */       throw new CapabilityNotSetException(J3dI18N.getString("ColoringAttributes0"));
/*     */     }
/* 190 */     if (isLive()) {
/* 191 */       ((ColoringAttributesRetained)this.retained).setColor(paramColor3f);
/*     */     } else {
/* 193 */       ((ColoringAttributesRetained)this.retained).initColor(paramColor3f);
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
/*     */   public void setColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 214 */     if (isLiveOrCompiled() && 
/* 215 */       !getCapability(1)) {
/* 216 */       throw new CapabilityNotSetException(J3dI18N.getString("ColoringAttributes0"));
/*     */     }
/* 218 */     if (isLive()) {
/* 219 */       ((ColoringAttributesRetained)this.retained).setColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } else {
/* 221 */       ((ColoringAttributesRetained)this.retained).initColor(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getColor(Color3f paramColor3f) {
/* 232 */     if (isLiveOrCompiled() && 
/* 233 */       !getCapability(0)) {
/* 234 */       throw new CapabilityNotSetException(J3dI18N.getString("ColoringAttributes2"));
/*     */     }
/* 236 */     ((ColoringAttributesRetained)this.retained).getColor(paramColor3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShadeModel(int paramInt) {
/* 247 */     if (isLiveOrCompiled() && 
/* 248 */       !getCapability(3)) {
/* 249 */       throw new CapabilityNotSetException(J3dI18N.getString("ColoringAttributes3"));
/*     */     }
/* 251 */     if (isLive()) {
/* 252 */       ((ColoringAttributesRetained)this.retained).setShadeModel(paramInt);
/*     */     } else {
/* 254 */       ((ColoringAttributesRetained)this.retained).initShadeModel(paramInt);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getShadeModel() {
/* 264 */     if (isLiveOrCompiled() && 
/* 265 */       !getCapability(2)) {
/* 266 */       throw new CapabilityNotSetException(J3dI18N.getString("ColoringAttributes4"));
/*     */     }
/* 268 */     return ((ColoringAttributesRetained)this.retained).getShadeModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 276 */     this.retained = new ColoringAttributesRetained();
/* 277 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 286 */     ColoringAttributes coloringAttributes = new ColoringAttributes();
/* 287 */     coloringAttributes.duplicateNodeComponent(this);
/* 288 */     return coloringAttributes;
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
/* 312 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */ 
/*     */     
/* 315 */     ColoringAttributesRetained coloringAttributesRetained1 = (ColoringAttributesRetained)paramNodeComponent.retained;
/*     */ 
/*     */     
/* 318 */     ColoringAttributesRetained coloringAttributesRetained2 = (ColoringAttributesRetained)this.retained;
/* 319 */     Color3f color3f = new Color3f();
/* 320 */     coloringAttributesRetained1.getColor(color3f);
/*     */     
/* 322 */     coloringAttributesRetained2.initColor(color3f);
/* 323 */     coloringAttributesRetained2.initShadeModel(coloringAttributesRetained1.getShadeModel());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 332 */     StringBuffer stringBuffer = new StringBuffer(getNamePrefix());
/* 333 */     stringBuffer.append("javax.media.j3d.ColoringAttributes: ");
/* 334 */     String[] arrayOfString = { "FASTEST", "NICEST", "SHADE_FLAT", "SHADE_GOURAUD" };
/*     */ 
/*     */     
/*     */     try {
/* 338 */       Color3f color3f = new Color3f();
/* 339 */       getColor(color3f);
/* 340 */       stringBuffer.append("Color=" + color3f);
/*     */     } catch (CapabilityNotSetException capabilityNotSetException) {
/* 342 */       stringBuffer.append("Color=N/A");
/*     */     } 
/*     */     try {
/* 345 */       stringBuffer.append(" ShadeModel=" + arrayOfString[getShadeModel()]);
/*     */     } catch (CapabilityNotSetException capabilityNotSetException) {
/* 347 */       stringBuffer.append("ShadeModel=N/A");
/*     */     } 
/* 349 */     return new String(stringBuffer);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ColoringAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */