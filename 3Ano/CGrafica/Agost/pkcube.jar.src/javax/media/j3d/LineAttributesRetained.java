/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LineAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int LINE_WIDTH_CHANGED = 1;
/*     */   static final int LINE_PATTERN_CHANGED = 2;
/*     */   static final int LINE_AA_CHANGED = 4;
/*     */   static final int LINE_PATTERN_MASK_CHANGED = 8;
/*     */   static final int LINE_PATTERN_SCALEFACTOR_CHANGED = 16;
/*  31 */   float lineWidth = 1.0F;
/*     */ 
/*     */   
/*  34 */   int linePattern = 0;
/*     */ 
/*     */   
/*     */   boolean lineAntialiasing = false;
/*     */ 
/*     */   
/*  40 */   int linePatternMask = 65535;
/*     */ 
/*     */   
/*  43 */   int linePatternScaleFactor = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   final void initLineWidth(float paramFloat) { this.lineWidth = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setLineWidth(float paramFloat) {
/*  59 */     initLineWidth(paramFloat);
/*  60 */     sendMessage(1, new Float(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   final float getLineWidth() { return this.lineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   final void initLinePattern(int paramInt) { this.linePattern = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setLinePattern(int paramInt) {
/*  86 */     initLinePattern(paramInt);
/*  87 */     sendMessage(2, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   final int getLinePattern() { return this.linePattern; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   final void initLineAntialiasingEnable(boolean paramBoolean) { this.lineAntialiasing = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setLineAntialiasingEnable(boolean paramBoolean) {
/* 114 */     initLineAntialiasingEnable(paramBoolean);
/* 115 */     sendMessage(4, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   final boolean getLineAntialiasingEnable() { return this.lineAntialiasing; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   final void initPatternMask(int paramInt) { this.linePatternMask = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPatternMask(int paramInt) {
/* 147 */     initPatternMask(paramInt);
/* 148 */     sendMessage(8, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   final int getPatternMask() { return this.linePatternMask; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initPatternScaleFactor(int paramInt) {
/* 166 */     if (paramInt < 1) {
/* 167 */       paramInt = 1;
/* 168 */     } else if (paramInt > 15) {
/* 169 */       paramInt = 15;
/*     */     } 
/* 171 */     this.linePatternScaleFactor = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPatternScaleFactor(int paramInt) {
/* 182 */     initPatternScaleFactor(paramInt);
/* 183 */     sendMessage(16, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   final int getPatternScaleFactor() { return this.linePatternScaleFactor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 200 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 203 */       if (isStatic()) {
/* 204 */         this.mirror = this;
/*     */       } else {
/* 206 */         LineAttributesRetained lineAttributesRetained = new LineAttributesRetained();
/* 207 */         lineAttributesRetained.source = this.source;
/* 208 */         lineAttributesRetained.set(this);
/* 209 */         this.mirror = lineAttributesRetained;
/*     */       } 
/*     */     } else {
/* 212 */       ((LineAttributesRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   void updateNative(Context paramContext) { Pipeline.getPipeline().updateLineAttributes(paramContext, this.lineWidth, this.linePattern, this.linePatternMask, this.linePatternScaleFactor, this.lineAntialiasing); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   void initMirrorObject() { ((LineAttributesRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 240 */     LineAttributesRetained lineAttributesRetained = (LineAttributesRetained)this.mirror;
/*     */     
/* 242 */     if ((paramInt & true) != 0) {
/* 243 */       lineAttributesRetained.lineWidth = ((Float)paramObject).floatValue();
/*     */     }
/* 245 */     else if ((paramInt & 0x2) != 0) {
/* 246 */       lineAttributesRetained.linePattern = ((Integer)paramObject).intValue();
/*     */     }
/* 248 */     else if ((paramInt & 0x4) != 0) {
/* 249 */       lineAttributesRetained.lineAntialiasing = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 251 */     else if ((paramInt & 0x8) != 0) {
/* 252 */       lineAttributesRetained.linePatternMask = ((Integer)paramObject).intValue();
/*     */     }
/* 254 */     else if ((paramInt & 0x10) != 0) {
/*     */       
/* 256 */       lineAttributesRetained.linePatternScaleFactor = ((Integer)paramObject).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 262 */   boolean equivalent(LineAttributesRetained paramLineAttributesRetained) { return (paramLineAttributesRetained != null && this.lineWidth == paramLineAttributesRetained.lineWidth && this.linePattern == paramLineAttributesRetained.linePattern && this.lineAntialiasing == paramLineAttributesRetained.lineAntialiasing && this.linePatternMask == paramLineAttributesRetained.linePatternMask && this.linePatternScaleFactor == paramLineAttributesRetained.linePatternScaleFactor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(LineAttributesRetained paramLineAttributesRetained) {
/* 272 */     set(paramLineAttributesRetained);
/* 273 */     this.lineWidth = paramLineAttributesRetained.lineWidth;
/* 274 */     this.linePattern = paramLineAttributesRetained.linePattern;
/* 275 */     this.linePatternScaleFactor = paramLineAttributesRetained.linePatternScaleFactor;
/* 276 */     this.linePatternMask = paramLineAttributesRetained.linePatternMask;
/* 277 */     this.lineAntialiasing = paramLineAttributesRetained.lineAntialiasing;
/*     */   }
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 281 */     ArrayList arrayList1 = new ArrayList();
/* 282 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 286 */     J3dMessage j3dMessage = new J3dMessage();
/* 287 */     j3dMessage.threads = 1024;
/* 288 */     j3dMessage.type = 7;
/* 289 */     j3dMessage.universe = null;
/* 290 */     j3dMessage.args[0] = this;
/* 291 */     j3dMessage.args[1] = new Integer(paramInt);
/* 292 */     j3dMessage.args[2] = paramObject;
/* 293 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 294 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */ 
/*     */     
/* 298 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 299 */       j3dMessage = new J3dMessage();
/* 300 */       j3dMessage.threads = 128;
/* 301 */       j3dMessage.type = 7;
/*     */       
/* 303 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 304 */       j3dMessage.args[0] = this;
/* 305 */       j3dMessage.args[1] = new Integer(paramInt);
/* 306 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 308 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 309 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 310 */       arrayList.toArray(arrayOfGeometryAtom);
/* 311 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 313 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 319 */     if (paramInt == 1 || paramInt == 3 || paramInt == 5)
/*     */     {
/*     */       
/* 322 */       setFrequencyChangeMask(paramInt, 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\LineAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */