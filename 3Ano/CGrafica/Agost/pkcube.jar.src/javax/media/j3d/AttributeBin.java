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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class AttributeBin
/*     */   implements ObjectUpdate
/*     */ {
/*     */   RenderingAttributesRetained definingRenderingAttributes;
/*     */   RenderBin renderBin;
/*     */   EnvironmentSet environmentSet;
/*     */   AttributeBin next;
/*     */   AttributeBin prev;
/*     */   ShaderBin shaderBinList;
/*     */   ArrayList addShaderBins;
/*     */   boolean soleUser;
/*     */   AppearanceRetained app;
/*     */   int onUpdateList;
/*  65 */   static int ON_OBJ_UPDATE_LIST = 1;
/*  66 */   static int ON_CHANGED_FREQUENT_UPDATE_LIST = 2; boolean ignoreVertexColors; AttributeBin(AppearanceRetained paramAppearanceRetained, RenderingAttributesRetained paramRenderingAttributesRetained, RenderBin paramRenderBin) { this.definingRenderingAttributes = null; this.renderBin = null; this.environmentSet = null; this.next = null; this.prev = null; this.shaderBinList = null; this.addShaderBins = new ArrayList();
/*     */     this.soleUser = false;
/*     */     this.app = null;
/*     */     this.onUpdateList = 0;
/*  70 */     this.ignoreVertexColors = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     this.numEditingShaderBins = 0;
/*     */ 
/*     */ 
/*     */     
/*  80 */     reset(paramAppearanceRetained, paramRenderingAttributesRetained, paramRenderBin); }
/*     */   
/*     */   RenderingAttributesRetained renderingAttrs; int numEditingShaderBins;
/*     */   void reset(AppearanceRetained paramAppearanceRetained, RenderingAttributesRetained paramRenderingAttributesRetained, RenderBin paramRenderBin) {
/*  84 */     this.prev = null;
/*  85 */     this.next = null;
/*  86 */     this.shaderBinList = null;
/*  87 */     this.onUpdateList = 0;
/*  88 */     this.numEditingShaderBins = 0;
/*  89 */     this.renderingAttrs = paramRenderingAttributesRetained;
/*     */     
/*  91 */     this.renderBin = paramRenderBin;
/*     */ 
/*     */     
/*  94 */     this.soleUser = false;
/*  95 */     if (VirtualUniverse.mc.allowSoleUser && 
/*  96 */       paramAppearanceRetained != null) {
/*  97 */       this.soleUser = ((paramAppearanceRetained.changedFrequent & 0x40) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (this.soleUser) {
/* 104 */       this.app = paramAppearanceRetained;
/*     */     } else {
/* 106 */       paramAppearanceRetained = null;
/*     */     } 
/* 108 */     if (paramRenderingAttributesRetained != null) {
/* 109 */       if (paramRenderingAttributesRetained.changedFrequent != 0) {
/* 110 */         this.definingRenderingAttributes = paramRenderingAttributesRetained;
/* 111 */         if ((this.onUpdateList & ON_CHANGED_FREQUENT_UPDATE_LIST) == 0) {
/* 112 */           this.renderBin.aBinUpdateList.add(this);
/* 113 */           this.onUpdateList |= ON_CHANGED_FREQUENT_UPDATE_LIST;
/*     */         }
/*     */       
/*     */       }
/* 117 */       else if (this.definingRenderingAttributes != null) {
/* 118 */         this.definingRenderingAttributes.set(paramRenderingAttributesRetained);
/*     */       } else {
/*     */         
/* 121 */         this.definingRenderingAttributes = (RenderingAttributesRetained)paramRenderingAttributesRetained.clone();
/*     */       } 
/*     */       
/* 124 */       this.ignoreVertexColors = this.definingRenderingAttributes.ignoreVertexColors;
/*     */     } else {
/* 126 */       this.definingRenderingAttributes = null;
/* 127 */       this.ignoreVertexColors = false;
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
/*     */   boolean equals(RenderingAttributesRetained paramRenderingAttributesRetained, RenderAtom paramRenderAtom) {
/* 139 */     if (this.soleUser || (paramRenderAtom.geometryAtom.source.appearance != null && (paramRenderAtom.geometryAtom.source.appearance.changedFrequent & 0x40) != 0)) {
/*     */ 
/*     */       
/* 142 */       if (this.app == paramRenderAtom.geometryAtom.source.appearance) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 154 */         if (this.numEditingShaderBins == 0 && (
/* 155 */           this.onUpdateList & ON_CHANGED_FREQUENT_UPDATE_LIST) == 0) {
/* 156 */           this.renderBin.aBinUpdateList.add(this);
/* 157 */           this.onUpdateList |= ON_CHANGED_FREQUENT_UPDATE_LIST;
/*     */         } 
/*     */ 
/*     */         
/* 161 */         return true;
/*     */       } 
/*     */       
/* 164 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     if (this.definingRenderingAttributes != null) {
/* 173 */       if (this.definingRenderingAttributes.changedFrequent != 0 || (paramRenderingAttributesRetained != null && paramRenderingAttributesRetained.changedFrequent != 0)) {
/*     */         
/* 175 */         if (this.definingRenderingAttributes == paramRenderingAttributesRetained) {
/* 176 */           if (this.definingRenderingAttributes.compChanged != 0 && (
/* 177 */             this.onUpdateList & ON_CHANGED_FREQUENT_UPDATE_LIST) == 0) {
/* 178 */             this.renderBin.aBinUpdateList.add(this);
/* 179 */             this.onUpdateList |= ON_CHANGED_FREQUENT_UPDATE_LIST;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 184 */           return false;
/*     */         } 
/* 186 */       } else if (!this.definingRenderingAttributes.equivalent(paramRenderingAttributesRetained)) {
/* 187 */         return false;
/*     */       }
/*     */     
/* 190 */     } else if (paramRenderingAttributesRetained != null) {
/* 191 */       return false;
/*     */     } 
/*     */     
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObject() {
/* 202 */     int i = this.addShaderBins.size();
/* 203 */     if (i > 0) {
/* 204 */       ShaderBin shaderBin = (ShaderBin)this.addShaderBins.get(0);
/* 205 */       if (this.shaderBinList == null) {
/* 206 */         this.shaderBinList = shaderBin;
/*     */       } else {
/*     */         
/* 209 */         shaderBin.next = this.shaderBinList;
/* 210 */         this.shaderBinList.prev = shaderBin;
/* 211 */         this.shaderBinList = shaderBin;
/*     */       } 
/*     */       
/* 214 */       for (byte b = 1; b < i; b++) {
/* 215 */         shaderBin = (ShaderBin)this.addShaderBins.get(b);
/* 216 */         shaderBin.next = this.shaderBinList;
/* 217 */         this.shaderBinList.prev = shaderBin;
/* 218 */         this.shaderBinList = shaderBin;
/*     */       } 
/*     */     } 
/* 221 */     this.addShaderBins.clear();
/* 222 */     this.onUpdateList &= (ON_OBJ_UPDATE_LIST ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addShaderBin(ShaderBin paramShaderBin, RenderBin paramRenderBin, ShaderAppearanceRetained paramShaderAppearanceRetained) {
/* 231 */     paramShaderBin.attributeBin = this;
/*     */     
/* 233 */     if (paramShaderAppearanceRetained != null) {
/*     */ 
/*     */       
/* 236 */       assert paramShaderAppearanceRetained.isMirror;
/* 237 */       paramShaderBin.shaderProgram = paramShaderAppearanceRetained.shaderProgram;
/* 238 */       paramShaderBin.shaderAttributeSet = paramShaderAppearanceRetained.shaderAttributeSet;
/*     */     } 
/* 240 */     paramShaderBin.shaderAppearance = paramShaderAppearanceRetained;
/*     */ 
/*     */     
/* 243 */     this.addShaderBins.add(paramShaderBin);
/* 244 */     if ((this.onUpdateList & ON_OBJ_UPDATE_LIST) == 0) {
/* 245 */       this.onUpdateList |= ON_OBJ_UPDATE_LIST;
/* 246 */       paramRenderBin.objUpdateList.add(this);
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
/*     */   void removeShaderBin(ShaderBin paramShaderBin) {
/* 259 */     if (this.addShaderBins.contains(paramShaderBin)) {
/* 260 */       this.addShaderBins.remove(this.addShaderBins.indexOf(paramShaderBin));
/*     */     
/*     */     }
/* 263 */     else if (paramShaderBin.prev == null) {
/* 264 */       this.shaderBinList = paramShaderBin.next;
/* 265 */       if (paramShaderBin.next != null) {
/* 266 */         paramShaderBin.next.prev = null;
/*     */       }
/*     */     } else {
/* 269 */       paramShaderBin.prev.next = paramShaderBin.next;
/* 270 */       if (paramShaderBin.next != null) {
/* 271 */         paramShaderBin.next.prev = paramShaderBin.prev;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 276 */     paramShaderBin.clear();
/*     */     
/* 278 */     if (this.shaderBinList == null && this.addShaderBins.size() == 0)
/*     */     {
/*     */       
/* 281 */       this.environmentSet.removeAttributeBin(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void render(Canvas3D paramCanvas3D) {
/* 292 */     boolean bool = (this.definingRenderingAttributes == null || this.definingRenderingAttributes.visible) ? 1 : 0;
/*     */ 
/*     */     
/* 295 */     if ((this.renderBin.view.viewCache.visibilityPolicy == 0 && !bool) || (this.renderBin.view.viewCache.visibilityPolicy == 1 && bool)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 304 */     paramCanvas3D.setStateToUpdate(2, this);
/*     */     
/* 306 */     ShaderBin shaderBin = this.shaderBinList;
/* 307 */     while (shaderBin != null) {
/* 308 */       shaderBin.render(paramCanvas3D);
/* 309 */       shaderBin = shaderBin.next;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateAttributes(Canvas3D paramCanvas3D) {
/* 316 */     if ((paramCanvas3D.canvasDirty & 0x200) != 0) {
/*     */       
/* 318 */       if (this.definingRenderingAttributes == null) {
/* 319 */         paramCanvas3D.resetRenderingAttributes(paramCanvas3D.ctx, paramCanvas3D.depthBufferWriteEnableOverride, paramCanvas3D.depthBufferEnableOverride);
/*     */       }
/*     */       else {
/*     */         
/* 323 */         this.definingRenderingAttributes.updateNative(paramCanvas3D, paramCanvas3D.depthBufferWriteEnableOverride, paramCanvas3D.depthBufferEnableOverride);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 328 */       paramCanvas3D.renderingAttrs = this.renderingAttrs;
/*     */     
/*     */     }
/* 331 */     else if (paramCanvas3D.renderingAttrs != this.renderingAttrs && paramCanvas3D.attributeBin != this) {
/*     */ 
/*     */       
/* 334 */       if (this.definingRenderingAttributes == null) {
/* 335 */         paramCanvas3D.resetRenderingAttributes(paramCanvas3D.ctx, paramCanvas3D.depthBufferWriteEnableOverride, paramCanvas3D.depthBufferEnableOverride);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 340 */         this.definingRenderingAttributes.updateNative(paramCanvas3D, paramCanvas3D.depthBufferWriteEnableOverride, paramCanvas3D.depthBufferEnableOverride);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 345 */       paramCanvas3D.renderingAttrs = this.renderingAttrs;
/*     */     } 
/* 347 */     paramCanvas3D.attributeBin = this;
/* 348 */     paramCanvas3D.canvasDirty &= 0xFFFFFDFF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateNodeComponent() {
/* 354 */     if ((this.onUpdateList & ON_CHANGED_FREQUENT_UPDATE_LIST) != 0) {
/* 355 */       if (this.soleUser) {
/* 356 */         boolean bool = (this.definingRenderingAttributes != null && this.definingRenderingAttributes != this.renderingAttrs) ? 1 : 0;
/* 357 */         this.renderingAttrs = this.app.renderingAttributes;
/*     */         
/* 359 */         if (this.renderingAttrs == null) {
/* 360 */           this.definingRenderingAttributes = null;
/* 361 */           this.ignoreVertexColors = false;
/*     */         } else {
/*     */           
/* 364 */           if (this.renderingAttrs.changedFrequent != 0) {
/* 365 */             this.definingRenderingAttributes = this.renderingAttrs;
/*     */           
/*     */           }
/* 368 */           else if (bool) {
/* 369 */             this.definingRenderingAttributes.set(this.renderingAttrs);
/*     */           } else {
/*     */             
/* 372 */             this.definingRenderingAttributes = (RenderingAttributesRetained)this.renderingAttrs.clone();
/*     */           } 
/*     */           
/* 375 */           this.ignoreVertexColors = this.definingRenderingAttributes.ignoreVertexColors;
/*     */         } 
/*     */       } else {
/*     */         
/* 379 */         this.ignoreVertexColors = this.definingRenderingAttributes.ignoreVertexColors;
/*     */       } 
/*     */     }
/*     */     
/* 383 */     this.onUpdateList &= (ON_CHANGED_FREQUENT_UPDATE_LIST ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   
/* 387 */   void incrActiveShaderBin() { this.numEditingShaderBins++; }
/*     */ 
/*     */ 
/*     */   
/* 391 */   void decrActiveShaderBin() { this.numEditingShaderBins--; }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateFromShaderBin(RenderAtom paramRenderAtom) {
/* 396 */     AppearanceRetained appearanceRetained = paramRenderAtom.geometryAtom.source.appearance;
/* 397 */     RenderingAttributesRetained renderingAttributesRetained = (appearanceRetained == null) ? null : appearanceRetained.renderingAttributes;
/*     */ 
/*     */     
/* 400 */     if (!this.soleUser && this.renderingAttrs != renderingAttributesRetained)
/*     */     {
/* 402 */       this.renderingAttrs = this.definingRenderingAttributes;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\AttributeBin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */