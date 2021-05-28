/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class AppearanceRetained
/*      */   extends NodeComponentRetained
/*      */ {
/*   32 */   MaterialRetained material = null;
/*      */ 
/*      */   
/*   35 */   TextureRetained texture = null;
/*      */ 
/*      */   
/*   38 */   TexCoordGenerationRetained texCoordGeneration = null;
/*      */ 
/*      */   
/*   41 */   TextureAttributesRetained textureAttributes = null;
/*      */   
/*   43 */   TextureUnitStateRetained[] texUnitState = null;
/*      */ 
/*      */   
/*   46 */   ColoringAttributesRetained coloringAttributes = null;
/*      */ 
/*      */   
/*   49 */   TransparencyAttributesRetained transparencyAttributes = null;
/*      */ 
/*      */   
/*   52 */   RenderingAttributesRetained renderingAttributes = null;
/*      */ 
/*      */   
/*   55 */   PolygonAttributesRetained polygonAttributes = null;
/*      */ 
/*      */   
/*   58 */   LineAttributesRetained lineAttributes = null;
/*      */ 
/*      */   
/*   61 */   PointAttributesRetained pointAttributes = null;
/*      */ 
/*      */ 
/*      */   
/*   65 */   Object liveStateLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   71 */   CompileState map = null;
/*   72 */   AppearanceRetained mapAppearance = null;
/*      */   
/*      */   static final int MATERIAL = 1;
/*      */   
/*      */   static final int TEXTURE = 2;
/*      */   
/*      */   static final int TEXCOORD_GEN = 4;
/*      */   
/*      */   static final int TEXTURE_ATTR = 8;
/*      */   static final int COLOR = 16;
/*      */   static final int TRANSPARENCY = 32;
/*      */   static final int RENDERING = 64;
/*      */   static final int POLYGON = 128;
/*      */   static final int LINE = 256;
/*      */   static final int POINT = 512;
/*      */   static final int TEXTURE_UNIT_STATE = 1024;
/*      */   static final int ALL_SOLE_USERS = 0;
/*   89 */   AppearanceRetained sgApp = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMaterial(Material paramMaterial) {
/*  102 */     synchronized (this.liveStateLock) {
/*  103 */       if (this.source.isLive()) {
/*      */         
/*  105 */         if (this.material != null) {
/*  106 */           this.material.clearLive(this.refCount);
/*  107 */           this.material.removeMirrorUsers(this);
/*      */         } 
/*  109 */         if (paramMaterial != null) {
/*  110 */           ((MaterialRetained)paramMaterial.retained).setLive(this.inBackgroundGroup, this.refCount);
/*      */ 
/*      */           
/*  113 */           ((MaterialRetained)paramMaterial.retained).copyMirrorUsers(this);
/*      */         } 
/*  115 */         sendMessage(1, (paramMaterial != null) ? ((MaterialRetained)paramMaterial.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */       
/*  119 */       if (paramMaterial == null) {
/*  120 */         this.material = null;
/*      */       } else {
/*  122 */         this.material = (MaterialRetained)paramMaterial.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  132 */   Material getMaterial() { return (this.material == null) ? null : (Material)this.material.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTexture(Texture paramTexture) {
/*  141 */     synchronized (this.liveStateLock) {
/*  142 */       if (this.source.isLive()) {
/*      */         
/*  144 */         if (this.texture != null) {
/*  145 */           this.texture.clearLive(this.refCount);
/*  146 */           this.texture.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  149 */         if (paramTexture != null) {
/*  150 */           ((TextureRetained)paramTexture.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  151 */           ((TextureRetained)paramTexture.retained).copyMirrorUsers(this);
/*      */         } 
/*  153 */         sendMessage(2, (paramTexture != null) ? ((TextureRetained)paramTexture.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  160 */       if (paramTexture == null) {
/*  161 */         this.texture = null;
/*      */       } else {
/*  163 */         this.texture = (TextureRetained)paramTexture.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  173 */   Texture getTexture() { return (this.texture == null) ? null : (Texture)this.texture.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTextureAttributes(TextureAttributes paramTextureAttributes) {
/*  183 */     synchronized (this.liveStateLock) {
/*  184 */       if (this.source.isLive()) {
/*      */         
/*  186 */         if (this.textureAttributes != null) {
/*  187 */           this.textureAttributes.clearLive(this.refCount);
/*  188 */           this.textureAttributes.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  191 */         if (paramTextureAttributes != null) {
/*  192 */           ((TextureAttributesRetained)paramTextureAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  193 */           ((TextureAttributesRetained)paramTextureAttributes.retained).copyMirrorUsers(this);
/*      */         } 
/*  195 */         sendMessage(8, (paramTextureAttributes != null) ? ((TextureAttributesRetained)paramTextureAttributes.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  203 */       if (paramTextureAttributes == null) {
/*  204 */         this.textureAttributes = null;
/*      */       } else {
/*  206 */         this.textureAttributes = (TextureAttributesRetained)paramTextureAttributes.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   TextureAttributes getTextureAttributes() { return (this.textureAttributes == null) ? null : (TextureAttributes)this.textureAttributes.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setColoringAttributes(ColoringAttributes paramColoringAttributes) {
/*  227 */     synchronized (this.liveStateLock) {
/*  228 */       if (this.source.isLive()) {
/*      */         
/*  230 */         if (this.coloringAttributes != null) {
/*  231 */           this.coloringAttributes.clearLive(this.refCount);
/*  232 */           this.coloringAttributes.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  235 */         if (paramColoringAttributes != null) {
/*  236 */           ((ColoringAttributesRetained)paramColoringAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  237 */           ((ColoringAttributesRetained)paramColoringAttributes.retained).copyMirrorUsers(this);
/*      */         } 
/*  239 */         sendMessage(16, (paramColoringAttributes != null) ? ((ColoringAttributesRetained)paramColoringAttributes.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  246 */       if (paramColoringAttributes == null) {
/*  247 */         this.coloringAttributes = null;
/*      */       } else {
/*  249 */         this.coloringAttributes = (ColoringAttributesRetained)paramColoringAttributes.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  259 */   ColoringAttributes getColoringAttributes() { return (this.coloringAttributes == null) ? null : (ColoringAttributes)this.coloringAttributes.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTransparencyAttributes(TransparencyAttributes paramTransparencyAttributes) {
/*  270 */     synchronized (this.liveStateLock) {
/*  271 */       if (this.source.isLive()) {
/*      */         
/*  273 */         if (this.transparencyAttributes != null) {
/*  274 */           this.transparencyAttributes.clearLive(this.refCount);
/*  275 */           this.transparencyAttributes.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  278 */         if (paramTransparencyAttributes != null) {
/*  279 */           ((TransparencyAttributesRetained)paramTransparencyAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  280 */           ((TransparencyAttributesRetained)paramTransparencyAttributes.retained).copyMirrorUsers(this);
/*      */         } 
/*      */         
/*  283 */         sendMessage(32, (paramTransparencyAttributes != null) ? ((TransparencyAttributesRetained)paramTransparencyAttributes.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  289 */       if (paramTransparencyAttributes == null) {
/*  290 */         this.transparencyAttributes = null;
/*      */       } else {
/*  292 */         this.transparencyAttributes = (TransparencyAttributesRetained)paramTransparencyAttributes.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  303 */   TransparencyAttributes getTransparencyAttributes() { return (this.transparencyAttributes == null) ? null : (TransparencyAttributes)this.transparencyAttributes.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setRenderingAttributes(RenderingAttributes paramRenderingAttributes) {
/*  314 */     synchronized (this.liveStateLock) {
/*  315 */       if (this.source.isLive()) {
/*  316 */         if (this.renderingAttributes != null) {
/*  317 */           this.renderingAttributes.clearLive(this.refCount);
/*  318 */           this.renderingAttributes.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  321 */         if (paramRenderingAttributes != null) {
/*  322 */           ((RenderingAttributesRetained)paramRenderingAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  323 */           ((RenderingAttributesRetained)paramRenderingAttributes.retained).copyMirrorUsers(this);
/*      */         } 
/*  325 */         NodeComponentRetained nodeComponentRetained = null;
/*  326 */         boolean bool = true;
/*  327 */         if (paramRenderingAttributes != null) {
/*  328 */           nodeComponentRetained = ((RenderingAttributesRetained)paramRenderingAttributes.retained).mirror;
/*  329 */           bool = ((RenderingAttributesRetained)paramRenderingAttributes.retained).visible;
/*      */         } 
/*  331 */         sendMessage(64, nodeComponentRetained, bool);
/*      */         
/*  333 */         sendRenderingAttributesChangedMessage(bool);
/*      */       } 
/*  335 */       if (paramRenderingAttributes == null) {
/*  336 */         this.renderingAttributes = null;
/*      */       } else {
/*  338 */         this.renderingAttributes = (RenderingAttributesRetained)paramRenderingAttributes.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderingAttributes getRenderingAttributes() {
/*  349 */     if (this.renderingAttributes == null) {
/*  350 */       return null;
/*      */     }
/*  352 */     return (RenderingAttributes)this.renderingAttributes.source;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPolygonAttributes(PolygonAttributes paramPolygonAttributes) {
/*  362 */     synchronized (this.liveStateLock) {
/*  363 */       if (this.source.isLive()) {
/*  364 */         if (this.polygonAttributes != null) {
/*  365 */           this.polygonAttributes.clearLive(this.refCount);
/*  366 */           this.polygonAttributes.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  369 */         if (paramPolygonAttributes != null) {
/*  370 */           ((PolygonAttributesRetained)paramPolygonAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  371 */           ((PolygonAttributesRetained)paramPolygonAttributes.retained).copyMirrorUsers(this);
/*      */         } 
/*  373 */         sendMessage(128, (paramPolygonAttributes != null) ? ((PolygonAttributesRetained)paramPolygonAttributes.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  380 */       if (paramPolygonAttributes == null) {
/*  381 */         this.polygonAttributes = null;
/*      */       } else {
/*  383 */         this.polygonAttributes = (PolygonAttributesRetained)paramPolygonAttributes.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  393 */   PolygonAttributes getPolygonAttributes() { return (this.polygonAttributes == null) ? null : (PolygonAttributes)this.polygonAttributes.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLineAttributes(LineAttributes paramLineAttributes) {
/*  404 */     synchronized (this.liveStateLock) {
/*  405 */       if (this.source.isLive()) {
/*      */         
/*  407 */         if (this.lineAttributes != null) {
/*  408 */           this.lineAttributes.clearLive(this.refCount);
/*  409 */           this.lineAttributes.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  412 */         if (paramLineAttributes != null) {
/*  413 */           ((LineAttributesRetained)paramLineAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  414 */           ((LineAttributesRetained)paramLineAttributes.retained).copyMirrorUsers(this);
/*      */         } 
/*  416 */         sendMessage(256, (paramLineAttributes != null) ? ((LineAttributesRetained)paramLineAttributes.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  422 */       if (paramLineAttributes == null) {
/*  423 */         this.lineAttributes = null;
/*      */       } else {
/*  425 */         this.lineAttributes = (LineAttributesRetained)paramLineAttributes.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  435 */   LineAttributes getLineAttributes() { return (this.lineAttributes == null) ? null : (LineAttributes)this.lineAttributes.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPointAttributes(PointAttributes paramPointAttributes) {
/*  446 */     synchronized (this.liveStateLock) {
/*  447 */       if (this.source.isLive()) {
/*      */         
/*  449 */         if (this.pointAttributes != null) {
/*  450 */           this.pointAttributes.clearLive(this.refCount);
/*  451 */           this.pointAttributes.removeMirrorUsers(this);
/*      */         } 
/*  453 */         if (paramPointAttributes != null) {
/*  454 */           ((PointAttributesRetained)paramPointAttributes.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  455 */           ((PointAttributesRetained)paramPointAttributes.retained).copyMirrorUsers(this);
/*      */         } 
/*  457 */         sendMessage(512, (paramPointAttributes != null) ? ((PointAttributesRetained)paramPointAttributes.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  464 */       if (paramPointAttributes == null) {
/*  465 */         this.pointAttributes = null;
/*      */       } else {
/*  467 */         this.pointAttributes = (PointAttributesRetained)paramPointAttributes.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  477 */   PointAttributes getPointAttributes() { return (this.pointAttributes == null) ? null : (PointAttributes)this.pointAttributes.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTexCoordGeneration(TexCoordGeneration paramTexCoordGeneration) {
/*  487 */     synchronized (this.liveStateLock) {
/*  488 */       if (this.source.isLive()) {
/*      */         
/*  490 */         if (this.texCoordGeneration != null) {
/*  491 */           this.texCoordGeneration.clearLive(this.refCount);
/*  492 */           this.texCoordGeneration.removeMirrorUsers(this);
/*      */         } 
/*      */         
/*  495 */         if (paramTexCoordGeneration != null) {
/*  496 */           ((TexCoordGenerationRetained)paramTexCoordGeneration.retained).setLive(this.inBackgroundGroup, this.refCount);
/*  497 */           ((TexCoordGenerationRetained)paramTexCoordGeneration.retained).copyMirrorUsers(this);
/*      */         } 
/*  499 */         sendMessage(4, (paramTexCoordGeneration != null) ? ((TexCoordGenerationRetained)paramTexCoordGeneration.retained).mirror : null, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  504 */       if (paramTexCoordGeneration == null) {
/*  505 */         this.texCoordGeneration = null;
/*      */       } else {
/*  507 */         this.texCoordGeneration = (TexCoordGenerationRetained)paramTexCoordGeneration.retained;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  517 */   TexCoordGeneration getTexCoordGeneration() { return (this.texCoordGeneration == null) ? null : (TexCoordGeneration)this.texCoordGeneration.source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTextureUnitState(TextureUnitState[] paramArrayOfTextureUnitState) {
/*  530 */     synchronized (this.liveStateLock) {
/*  531 */       if (this.source.isLive()) {
/*      */ 
/*      */         
/*  534 */         if (this.texUnitState != null) {
/*  535 */           for (byte b = 0; b < this.texUnitState.length; b++) {
/*  536 */             if (this.texUnitState[b] != null) {
/*  537 */               this.texUnitState[b].clearLive(this.refCount);
/*  538 */               this.texUnitState[b].removeMirrorUsers(this);
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  545 */         if (paramArrayOfTextureUnitState != null && paramArrayOfTextureUnitState.length > 0) {
/*      */           
/*  547 */           Object[] arrayOfObject = new Object[2];
/*      */ 
/*      */           
/*  550 */           arrayOfObject[0] = new Integer(-1);
/*      */ 
/*      */           
/*  553 */           TextureUnitStateRetained[] arrayOfTextureUnitStateRetained = new TextureUnitStateRetained[paramArrayOfTextureUnitState.length];
/*      */ 
/*      */           
/*  556 */           arrayOfObject[1] = arrayOfTextureUnitStateRetained;
/*      */           
/*  558 */           for (byte b = 0; b < paramArrayOfTextureUnitState.length; b++) {
/*  559 */             TextureUnitState textureUnitState = paramArrayOfTextureUnitState[b];
/*  560 */             if (textureUnitState != null) {
/*  561 */               ((TextureUnitStateRetained)textureUnitState.retained).setLive(this.inBackgroundGroup, this.refCount);
/*      */               
/*  563 */               ((TextureUnitStateRetained)textureUnitState.retained).copyMirrorUsers(this);
/*      */               
/*  565 */               arrayOfTextureUnitStateRetained[b] = (TextureUnitStateRetained)((TextureUnitStateRetained)textureUnitState.retained).mirror;
/*      */             } else {
/*      */               
/*  568 */               arrayOfTextureUnitStateRetained[b] = null;
/*      */             } 
/*      */           } 
/*  571 */           sendMessage(1024, arrayOfObject, true);
/*      */         } else {
/*      */           
/*  574 */           sendMessage(1024, null, true);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  580 */       if (paramArrayOfTextureUnitState == null) {
/*  581 */         this.texUnitState = null;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  587 */         if (this.texUnitState == null || this.texUnitState.length != paramArrayOfTextureUnitState.length)
/*      */         {
/*  589 */           this.texUnitState = new TextureUnitStateRetained[paramArrayOfTextureUnitState.length];
/*      */         }
/*      */         
/*  592 */         for (byte b = 0; b < paramArrayOfTextureUnitState.length; b++) {
/*  593 */           if (paramArrayOfTextureUnitState[b] != null) {
/*  594 */             this.texUnitState[b] = (TextureUnitStateRetained)(paramArrayOfTextureUnitState[b]).retained;
/*      */           } else {
/*      */             
/*  597 */             this.texUnitState[b] = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void setTextureUnitState(int paramInt, TextureUnitState paramTextureUnitState) {
/*  606 */     synchronized (this.liveStateLock) {
/*  607 */       if (this.source.isLive()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  612 */         if (this.texUnitState[paramInt] != null) {
/*  613 */           this.texUnitState[paramInt].clearLive(this.refCount);
/*  614 */           this.texUnitState[paramInt].removeMirrorUsers(this);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  619 */         Object[] arrayOfObject = new Object[2];
/*  620 */         arrayOfObject[0] = new Integer(paramInt);
/*      */         
/*  622 */         if (paramTextureUnitState != null) {
/*  623 */           ((TextureUnitStateRetained)paramTextureUnitState.retained).setLive(this.inBackgroundGroup, this.refCount);
/*      */           
/*  625 */           ((TextureUnitStateRetained)paramTextureUnitState.retained).copyMirrorUsers(this);
/*  626 */           arrayOfObject[1] = ((TextureUnitStateRetained)paramTextureUnitState.retained).mirror;
/*  627 */           sendMessage(1024, arrayOfObject, true);
/*      */         } else {
/*  629 */           arrayOfObject[1] = null;
/*  630 */           sendMessage(1024, arrayOfObject, true);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  636 */       if (paramTextureUnitState != null) {
/*  637 */         this.texUnitState[paramInt] = (TextureUnitStateRetained)paramTextureUnitState.retained;
/*      */       } else {
/*  639 */         this.texUnitState[paramInt] = null;
/*      */       } 
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
/*      */   TextureUnitState[] getTextureUnitState() {
/*  653 */     if (this.texUnitState == null) {
/*  654 */       return null;
/*      */     }
/*  656 */     TextureUnitState[] arrayOfTextureUnitState = new TextureUnitState[this.texUnitState.length];
/*      */     
/*  658 */     for (byte b = 0; b < this.texUnitState.length; b++) {
/*  659 */       if (this.texUnitState[b] != null) {
/*  660 */         arrayOfTextureUnitState[b] = (TextureUnitState)(this.texUnitState[b]).source;
/*      */       } else {
/*  662 */         arrayOfTextureUnitState[b] = null;
/*      */       } 
/*      */     } 
/*  665 */     return arrayOfTextureUnitState;
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
/*      */   TextureUnitState getTextureUnitState(int paramInt) {
/*  677 */     if (this.texUnitState[paramInt] != null) {
/*  678 */       return (TextureUnitState)(this.texUnitState[paramInt]).source;
/*      */     }
/*  680 */     return null;
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
/*      */   int getTextureUnitCount() {
/*  693 */     if (this.texUnitState == null) {
/*  694 */       return 0;
/*      */     }
/*  696 */     return this.texUnitState.length;
/*      */   }
/*      */ 
/*      */   
/*      */   void createMirrorObject() {
/*  701 */     if (this.mirror == null)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  706 */       this.mirror = new AppearanceRetained();
/*      */     }
/*  708 */     initMirrorObject();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initMirrorObject() {
/*  718 */     AppearanceRetained appearanceRetained = (AppearanceRetained)this.mirror;
/*      */     
/*  720 */     appearanceRetained.source = this.source;
/*  721 */     appearanceRetained.sgApp = this;
/*      */ 
/*      */     
/*  724 */     appearanceRetained.changedFrequent = this.changedFrequent;
/*      */     
/*  726 */     if (this.material != null) {
/*  727 */       appearanceRetained.material = (MaterialRetained)this.material.mirror;
/*      */     } else {
/*  729 */       appearanceRetained.material = null;
/*      */     } 
/*      */     
/*  732 */     if (this.texture != null) {
/*  733 */       appearanceRetained.texture = (TextureRetained)this.texture.mirror;
/*      */     } else {
/*  735 */       appearanceRetained.texture = null;
/*      */     } 
/*  737 */     if (this.texCoordGeneration != null) {
/*  738 */       appearanceRetained.texCoordGeneration = (TexCoordGenerationRetained)this.texCoordGeneration.mirror;
/*      */     } else {
/*  740 */       appearanceRetained.texCoordGeneration = null;
/*      */     } 
/*      */     
/*  743 */     if (this.textureAttributes != null) {
/*  744 */       appearanceRetained.textureAttributes = (TextureAttributesRetained)this.textureAttributes.mirror;
/*      */     } else {
/*  746 */       appearanceRetained.textureAttributes = null;
/*      */     } 
/*      */ 
/*      */     
/*  750 */     if (this.texUnitState != null && this.texUnitState.length > 0) {
/*  751 */       appearanceRetained.texUnitState = new TextureUnitStateRetained[this.texUnitState.length];
/*      */       
/*  753 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*  754 */         if (this.texUnitState[b] != null) {
/*  755 */           appearanceRetained.texUnitState[b] = (TextureUnitStateRetained)(this.texUnitState[b]).mirror;
/*      */         }
/*      */       }
/*      */     
/*  759 */     } else if (appearanceRetained.texture != null || appearanceRetained.textureAttributes != null || appearanceRetained.texCoordGeneration != null) {
/*      */ 
/*      */ 
/*      */       
/*  763 */       appearanceRetained.texUnitState = new TextureUnitStateRetained[1];
/*  764 */       appearanceRetained.texUnitState[0] = new TextureUnitStateRetained();
/*  765 */       appearanceRetained.texUnitState[0].set(appearanceRetained.texture, appearanceRetained.textureAttributes, appearanceRetained.texCoordGeneration);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  771 */     if (this.coloringAttributes != null) {
/*  772 */       appearanceRetained.coloringAttributes = (ColoringAttributesRetained)this.coloringAttributes.mirror;
/*      */     } else {
/*  774 */       appearanceRetained.coloringAttributes = null;
/*      */     } 
/*  776 */     if (this.transparencyAttributes != null) {
/*  777 */       appearanceRetained.transparencyAttributes = (TransparencyAttributesRetained)this.transparencyAttributes.mirror;
/*      */     } else {
/*  779 */       appearanceRetained.transparencyAttributes = null;
/*      */     } 
/*      */     
/*  782 */     if (this.renderingAttributes != null) {
/*  783 */       appearanceRetained.renderingAttributes = (RenderingAttributesRetained)this.renderingAttributes.mirror;
/*      */     } else {
/*  785 */       appearanceRetained.renderingAttributes = null;
/*      */     } 
/*      */     
/*  788 */     if (this.polygonAttributes != null) {
/*  789 */       appearanceRetained.polygonAttributes = (PolygonAttributesRetained)this.polygonAttributes.mirror;
/*      */     } else {
/*  791 */       appearanceRetained.polygonAttributes = null;
/*      */     } 
/*      */     
/*  794 */     if (this.lineAttributes != null) {
/*  795 */       appearanceRetained.lineAttributes = (LineAttributesRetained)this.lineAttributes.mirror;
/*      */     } else {
/*  797 */       appearanceRetained.lineAttributes = null;
/*      */     } 
/*      */     
/*  800 */     if (this.pointAttributes != null) {
/*  801 */       appearanceRetained.pointAttributes = (PointAttributesRetained)this.pointAttributes.mirror;
/*      */     } else {
/*  803 */       appearanceRetained.pointAttributes = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMirrorObject(int paramInt, Object paramObject) {
/*  812 */     AppearanceRetained appearanceRetained = (AppearanceRetained)this.mirror;
/*  813 */     if ((paramInt & true) != 0) {
/*  814 */       appearanceRetained.material = (MaterialRetained)paramObject;
/*      */     }
/*  816 */     else if ((paramInt & 0x2) != 0) {
/*      */       
/*  818 */       appearanceRetained.texture = (TextureRetained)paramObject;
/*  819 */       if (appearanceRetained.texUnitState == null) {
/*  820 */         appearanceRetained.texUnitState = new TextureUnitStateRetained[1];
/*  821 */         appearanceRetained.texUnitState[0] = new TextureUnitStateRetained();
/*      */       } 
/*  823 */       (appearanceRetained.texUnitState[0]).texture = (TextureRetained)paramObject;
/*      */     }
/*  825 */     else if ((paramInt & 0x4) != 0) {
/*  826 */       if (appearanceRetained.texUnitState == null) {
/*  827 */         appearanceRetained.texUnitState = new TextureUnitStateRetained[1];
/*  828 */         appearanceRetained.texUnitState[0] = new TextureUnitStateRetained();
/*      */       } 
/*  830 */       (appearanceRetained.texUnitState[0]).texGen = (TexCoordGenerationRetained)paramObject;
/*      */     }
/*  832 */     else if ((paramInt & 0x8) != 0) {
/*  833 */       if (appearanceRetained.texUnitState == null) {
/*  834 */         appearanceRetained.texUnitState = new TextureUnitStateRetained[1];
/*  835 */         appearanceRetained.texUnitState[0] = new TextureUnitStateRetained();
/*      */       } 
/*  837 */       (appearanceRetained.texUnitState[0]).texAttrs = (TextureAttributesRetained)paramObject;
/*      */     }
/*  839 */     else if ((paramInt & 0x400) != 0) {
/*  840 */       Object[] arrayOfObject = (Object[])paramObject;
/*      */       
/*  842 */       if (arrayOfObject == null) {
/*  843 */         appearanceRetained.texUnitState = null;
/*      */       } else {
/*  845 */         int i = ((Integer)arrayOfObject[0]).intValue();
/*  846 */         if (i == -1) {
/*  847 */           appearanceRetained.texUnitState = (TextureUnitStateRetained[])arrayOfObject[1];
/*      */         } else {
/*      */           
/*  850 */           appearanceRetained.texUnitState[i] = (TextureUnitStateRetained)arrayOfObject[1];
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  855 */     } else if ((paramInt & 0x10) != 0) {
/*  856 */       appearanceRetained.coloringAttributes = (ColoringAttributesRetained)paramObject;
/*      */     }
/*  858 */     else if ((paramInt & 0x20) != 0) {
/*  859 */       appearanceRetained.transparencyAttributes = (TransparencyAttributesRetained)paramObject;
/*      */     }
/*  861 */     else if ((paramInt & 0x40) != 0) {
/*  862 */       appearanceRetained.renderingAttributes = (RenderingAttributesRetained)paramObject;
/*      */     }
/*  864 */     else if ((paramInt & 0x80) != 0) {
/*  865 */       appearanceRetained.polygonAttributes = (PolygonAttributesRetained)paramObject;
/*      */     }
/*  867 */     else if ((paramInt & 0x100) != 0) {
/*  868 */       appearanceRetained.lineAttributes = (LineAttributesRetained)paramObject;
/*      */     }
/*  870 */     else if ((paramInt & 0x200) != 0) {
/*  871 */       appearanceRetained.pointAttributes = (PointAttributesRetained)paramObject;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(boolean paramBoolean, int paramInt) {
/*  878 */     doSetLive(paramBoolean, paramInt);
/*  879 */     markAsLive();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doSetLive(boolean paramBoolean, int paramInt) {
/*  889 */     if (this.material != null)
/*      */     {
/*  891 */       this.material.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  894 */     if (this.texture != null)
/*      */     {
/*  896 */       this.texture.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  899 */     if (this.texCoordGeneration != null)
/*      */     {
/*  901 */       this.texCoordGeneration.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  904 */     if (this.textureAttributes != null)
/*      */     {
/*  906 */       this.textureAttributes.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  909 */     if (this.texUnitState != null) {
/*  910 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*  911 */         if (this.texUnitState[b] != null) {
/*  912 */           this.texUnitState[b].setLive(paramBoolean, paramInt);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  917 */     if (this.coloringAttributes != null) {
/*  918 */       this.coloringAttributes.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  921 */     if (this.transparencyAttributes != null) {
/*  922 */       this.transparencyAttributes.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  925 */     if (this.renderingAttributes != null) {
/*  926 */       this.renderingAttributes.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  929 */     if (this.polygonAttributes != null) {
/*  930 */       this.polygonAttributes.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  933 */     if (this.lineAttributes != null) {
/*  934 */       this.lineAttributes.setLive(paramBoolean, paramInt);
/*      */     }
/*      */     
/*  937 */     if (this.pointAttributes != null) {
/*  938 */       this.pointAttributes.setLive(paramBoolean, paramInt);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  944 */     super.doSetLive(paramBoolean, paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearLive(int paramInt) {
/*  952 */     super.clearLive(paramInt);
/*      */     
/*  954 */     if (this.texture != null) {
/*  955 */       this.texture.clearLive(paramInt);
/*      */     }
/*      */     
/*  958 */     if (this.texCoordGeneration != null) {
/*  959 */       this.texCoordGeneration.clearLive(paramInt);
/*      */     }
/*      */     
/*  962 */     if (this.textureAttributes != null) {
/*  963 */       this.textureAttributes.clearLive(paramInt);
/*      */     }
/*      */     
/*  966 */     if (this.texUnitState != null) {
/*  967 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*  968 */         if (this.texUnitState[b] != null) {
/*  969 */           this.texUnitState[b].clearLive(paramInt);
/*      */         }
/*      */       } 
/*      */     }
/*  973 */     if (this.coloringAttributes != null) {
/*  974 */       this.coloringAttributes.clearLive(paramInt);
/*      */     }
/*      */     
/*  977 */     if (this.transparencyAttributes != null) {
/*  978 */       this.transparencyAttributes.clearLive(paramInt);
/*      */     }
/*      */     
/*  981 */     if (this.renderingAttributes != null) {
/*  982 */       this.renderingAttributes.clearLive(paramInt);
/*      */     }
/*      */     
/*  985 */     if (this.polygonAttributes != null) {
/*  986 */       this.polygonAttributes.clearLive(paramInt);
/*      */     }
/*      */     
/*  989 */     if (this.lineAttributes != null) {
/*  990 */       this.lineAttributes.clearLive(paramInt);
/*      */     }
/*      */     
/*  993 */     if (this.pointAttributes != null) {
/*  994 */       this.pointAttributes.clearLive(paramInt);
/*      */     }
/*      */     
/*  997 */     if (this.material != null) {
/*  998 */       this.material.clearLive(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isStatic() {
/* 1006 */     boolean bool = (this.source.capabilityBitsEmpty() && (this.texture == null || this.texture.source.capabilityBitsEmpty()) && (this.texCoordGeneration == null || this.texCoordGeneration.source.capabilityBitsEmpty()) && (this.textureAttributes == null || this.textureAttributes.source.capabilityBitsEmpty()) && (this.coloringAttributes == null || this.coloringAttributes.source.capabilityBitsEmpty()) && (this.transparencyAttributes == null || this.transparencyAttributes.source.capabilityBitsEmpty()) && (this.renderingAttributes == null || this.renderingAttributes.source.capabilityBitsEmpty()) && (this.polygonAttributes == null || this.polygonAttributes.source.capabilityBitsEmpty()) && (this.lineAttributes == null || this.lineAttributes.source.capabilityBitsEmpty()) && (this.pointAttributes == null || this.pointAttributes.source.capabilityBitsEmpty()) && (this.material == null || this.material.source.capabilityBitsEmpty()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1028 */     if (!bool) {
/* 1029 */       return bool;
/*      */     }
/* 1031 */     if (this.texUnitState != null) {
/* 1032 */       for (byte b = 0; b < this.texUnitState.length && bool; b++) {
/* 1033 */         if (this.texUnitState[b] != null) {
/* 1034 */           bool = (bool && this.texUnitState[b].isStatic());
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1039 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void compile(CompileState paramCompileState) {
/* 1045 */     setCompiled();
/*      */     
/* 1047 */     if (this.texture != null) {
/* 1048 */       this.texture.compile(paramCompileState);
/*      */     }
/*      */     
/* 1051 */     if (this.texCoordGeneration != null) {
/* 1052 */       this.texCoordGeneration.compile(paramCompileState);
/*      */     }
/*      */     
/* 1055 */     if (this.textureAttributes != null) {
/* 1056 */       this.textureAttributes.compile(paramCompileState);
/*      */     }
/*      */     
/* 1059 */     if (this.texUnitState != null) {
/* 1060 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/* 1061 */         if (this.texUnitState[b] != null) {
/* 1062 */           this.texUnitState[b].compile(paramCompileState);
/*      */         }
/*      */       } 
/*      */     }
/* 1066 */     if (this.coloringAttributes != null) {
/* 1067 */       this.coloringAttributes.compile(paramCompileState);
/*      */     }
/*      */     
/* 1070 */     if (this.transparencyAttributes != null) {
/* 1071 */       this.transparencyAttributes.compile(paramCompileState);
/*      */     }
/*      */     
/* 1074 */     if (this.renderingAttributes != null) {
/* 1075 */       this.renderingAttributes.compile(paramCompileState);
/*      */     }
/*      */     
/* 1078 */     if (this.polygonAttributes != null) {
/* 1079 */       this.polygonAttributes.compile(paramCompileState);
/*      */     }
/*      */     
/* 1082 */     if (this.lineAttributes != null) {
/* 1083 */       this.lineAttributes.compile(paramCompileState);
/*      */     }
/*      */     
/* 1086 */     if (this.pointAttributes != null) {
/* 1087 */       this.pointAttributes.compile(paramCompileState);
/*      */     }
/*      */     
/* 1090 */     if (this.material != null) {
/* 1091 */       this.material.compile(paramCompileState);
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
/* 1107 */   public int hashCode() { return getClass().hashCode(); }
/*      */ 
/*      */ 
/*      */   
/* 1111 */   public boolean equals(Object paramObject) { return (paramObject instanceof AppearanceRetained && equals((AppearanceRetained)paramObject)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean equals(AppearanceRetained paramAppearanceRetained) {
/* 1118 */     boolean bool = (paramAppearanceRetained == this || (paramAppearanceRetained != null && (this.material == paramAppearanceRetained.material || (this.material != null && this.material.equivalent(paramAppearanceRetained.material))) && (this.texture == paramAppearanceRetained.texture || (this.texture != null && this.texture.equals(paramAppearanceRetained.texture))) && (this.renderingAttributes == paramAppearanceRetained.renderingAttributes || (this.renderingAttributes != null && this.renderingAttributes.equivalent(paramAppearanceRetained.renderingAttributes))) && (this.polygonAttributes == paramAppearanceRetained.polygonAttributes || (this.polygonAttributes != null && this.polygonAttributes.equivalent(paramAppearanceRetained.polygonAttributes))) && (this.texCoordGeneration == paramAppearanceRetained.texCoordGeneration || (this.texCoordGeneration != null && this.texCoordGeneration.equivalent(paramAppearanceRetained.texCoordGeneration))) && (this.textureAttributes == paramAppearanceRetained.textureAttributes || (this.textureAttributes != null && this.textureAttributes.equivalent(paramAppearanceRetained.textureAttributes))) && (this.coloringAttributes == paramAppearanceRetained.coloringAttributes || (this.coloringAttributes != null && this.coloringAttributes.equivalent(paramAppearanceRetained.coloringAttributes))) && (this.transparencyAttributes == paramAppearanceRetained.transparencyAttributes || (this.transparencyAttributes != null && this.transparencyAttributes.equivalent(paramAppearanceRetained.transparencyAttributes))) && (this.lineAttributes == paramAppearanceRetained.lineAttributes || (this.lineAttributes != null && this.lineAttributes.equivalent(paramAppearanceRetained.lineAttributes))) && (this.pointAttributes == paramAppearanceRetained.pointAttributes || (this.pointAttributes != null && this.pointAttributes.equivalent(paramAppearanceRetained.pointAttributes)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1151 */     if (!bool) {
/* 1152 */       return bool;
/*      */     }
/* 1154 */     if (this.texUnitState == paramAppearanceRetained.texUnitState) {
/* 1155 */       return bool;
/*      */     }
/* 1157 */     if (this.texUnitState == null || paramAppearanceRetained.texUnitState == null || this.texUnitState.length != paramAppearanceRetained.texUnitState.length)
/*      */     {
/* 1159 */       return false;
/*      */     }
/* 1161 */     for (byte b = 0; b < this.texUnitState.length; b++) {
/* 1162 */       if (this.texUnitState[b] != paramAppearanceRetained.texUnitState[b])
/*      */       {
/*      */         
/* 1165 */         if (this.texUnitState[b] == null || paramAppearanceRetained.texUnitState[b] == null || !this.texUnitState[b].equals(paramAppearanceRetained.texUnitState[b]))
/*      */         {
/* 1167 */           return false; }  } 
/*      */     } 
/* 1169 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 1177 */     super.addAMirrorUser(paramShape3DRetained);
/* 1178 */     if (this.material != null) {
/* 1179 */       this.material.addAMirrorUser(paramShape3DRetained);
/*      */     }
/* 1181 */     if (this.texture != null)
/* 1182 */       this.texture.addAMirrorUser(paramShape3DRetained); 
/* 1183 */     if (this.texCoordGeneration != null)
/* 1184 */       this.texCoordGeneration.addAMirrorUser(paramShape3DRetained); 
/* 1185 */     if (this.textureAttributes != null) {
/* 1186 */       this.textureAttributes.addAMirrorUser(paramShape3DRetained);
/*      */     }
/* 1188 */     if (this.texUnitState != null) {
/* 1189 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/* 1190 */         if (this.texUnitState[b] != null) {
/* 1191 */           this.texUnitState[b].addAMirrorUser(paramShape3DRetained);
/*      */         }
/*      */       } 
/*      */     }
/* 1195 */     if (this.coloringAttributes != null)
/* 1196 */       this.coloringAttributes.addAMirrorUser(paramShape3DRetained); 
/* 1197 */     if (this.transparencyAttributes != null)
/* 1198 */       this.transparencyAttributes.addAMirrorUser(paramShape3DRetained); 
/* 1199 */     if (this.renderingAttributes != null)
/* 1200 */       this.renderingAttributes.addAMirrorUser(paramShape3DRetained); 
/* 1201 */     if (this.polygonAttributes != null)
/* 1202 */       this.polygonAttributes.addAMirrorUser(paramShape3DRetained); 
/* 1203 */     if (this.lineAttributes != null)
/* 1204 */       this.lineAttributes.addAMirrorUser(paramShape3DRetained); 
/* 1205 */     if (this.pointAttributes != null)
/* 1206 */       this.pointAttributes.addAMirrorUser(paramShape3DRetained); 
/*      */   }
/*      */   
/*      */   void removeAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 1210 */     super.removeAMirrorUser(paramShape3DRetained);
/* 1211 */     if (this.material != null)
/* 1212 */       this.material.removeAMirrorUser(paramShape3DRetained); 
/* 1213 */     if (this.texture != null)
/* 1214 */       this.texture.removeAMirrorUser(paramShape3DRetained); 
/* 1215 */     if (this.texCoordGeneration != null)
/* 1216 */       this.texCoordGeneration.removeAMirrorUser(paramShape3DRetained); 
/* 1217 */     if (this.textureAttributes != null) {
/* 1218 */       this.textureAttributes.removeAMirrorUser(paramShape3DRetained);
/*      */     }
/* 1220 */     if (this.texUnitState != null) {
/* 1221 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/* 1222 */         if (this.texUnitState[b] != null) {
/* 1223 */           this.texUnitState[b].removeAMirrorUser(paramShape3DRetained);
/*      */         }
/*      */       } 
/*      */     }
/* 1227 */     if (this.coloringAttributes != null)
/* 1228 */       this.coloringAttributes.removeAMirrorUser(paramShape3DRetained); 
/* 1229 */     if (this.transparencyAttributes != null)
/* 1230 */       this.transparencyAttributes.removeAMirrorUser(paramShape3DRetained); 
/* 1231 */     if (this.renderingAttributes != null)
/* 1232 */       this.renderingAttributes.removeAMirrorUser(paramShape3DRetained); 
/* 1233 */     if (this.polygonAttributes != null)
/* 1234 */       this.polygonAttributes.removeAMirrorUser(paramShape3DRetained); 
/* 1235 */     if (this.lineAttributes != null)
/* 1236 */       this.lineAttributes.removeAMirrorUser(paramShape3DRetained); 
/* 1237 */     if (this.pointAttributes != null) {
/* 1238 */       this.pointAttributes.removeAMirrorUser(paramShape3DRetained);
/*      */     }
/*      */   }
/*      */   
/*      */   final void sendMessage(int paramInt, Object paramObject, boolean paramBoolean) {
/* 1243 */     ArrayList arrayList1 = new ArrayList();
/* 1244 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*      */ 
/*      */     
/* 1247 */     J3dMessage j3dMessage = new J3dMessage();
/* 1248 */     j3dMessage.threads = 1024;
/* 1249 */     j3dMessage.type = 18;
/* 1250 */     j3dMessage.universe = null;
/* 1251 */     j3dMessage.args[0] = this;
/* 1252 */     j3dMessage.args[1] = new Integer(paramInt);
/* 1253 */     j3dMessage.args[2] = paramObject;
/* 1254 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/*      */     
/* 1256 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */ 
/*      */ 
/*      */     
/* 1260 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 1261 */       j3dMessage = new J3dMessage();
/* 1262 */       j3dMessage.threads = 128;
/* 1263 */       j3dMessage.type = 18;
/*      */       
/* 1265 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 1266 */       j3dMessage.args[0] = this;
/* 1267 */       j3dMessage.args[1] = new Integer(paramInt);
/* 1268 */       j3dMessage.args[2] = paramObject;
/*      */       
/* 1270 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 1271 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 1272 */       arrayList.toArray(arrayOfGeometryAtom);
/* 1273 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*      */ 
/*      */       
/* 1276 */       if (paramInt == 64) {
/* 1277 */         if (paramObject != null) {
/* 1278 */           j3dMessage.args[4] = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
/*      */         } else {
/*      */           
/* 1281 */           j3dMessage.args[4] = Boolean.TRUE;
/*      */         } 
/*      */       }
/* 1284 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void sendRenderingAttributesChangedMessage(boolean paramBoolean) {
/* 1292 */     ArrayList arrayList1 = new ArrayList();
/* 1293 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*      */ 
/*      */     
/* 1296 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 1297 */       J3dMessage j3dMessage = new J3dMessage();
/* 1298 */       j3dMessage.threads = 64;
/* 1299 */       j3dMessage.type = 10;
/*      */       
/* 1301 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 1302 */       j3dMessage.args[0] = this;
/* 1303 */       j3dMessage.args[1] = null;
/* 1304 */       j3dMessage.args[2] = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
/*      */       
/* 1306 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 1307 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 1308 */       arrayList.toArray(arrayOfGeometryAtom);
/* 1309 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/* 1310 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isOpaque(int paramInt) {
/* 1318 */     TransparencyAttributesRetained transparencyAttributesRetained = this.transparencyAttributes;
/* 1319 */     if (transparencyAttributesRetained != null && transparencyAttributesRetained.transparencyMode != 4 && (VirtualUniverse.mc.isD3D() || (!VirtualUniverse.mc.isD3D() && transparencyAttributesRetained.transparencyMode != 3)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1325 */       return false;
/*      */     }
/*      */     
/* 1328 */     switch (paramInt) {
/*      */       case 3:
/*      */       case 10:
/* 1331 */         if (this.pointAttributes != null && this.pointAttributes.pointAntialiasing)
/*      */         {
/* 1333 */           return false;
/*      */         }
/*      */       
/*      */       case 4:
/*      */       case 7:
/*      */       case 11:
/*      */       case 14:
/* 1340 */         if (this.lineAttributes != null && this.lineAttributes.lineAntialiasing)
/*      */         {
/* 1342 */           return false;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 15:
/*      */       case 17:
/* 1365 */         return true;
/*      */     }  if (this.polygonAttributes != null) { if (this.polygonAttributes.polygonMode == 0 && this.pointAttributes != null && this.pointAttributes.pointAntialiasing)
/*      */         return false;  if (this.polygonAttributes.polygonMode == 1 && this.lineAttributes != null && this.lineAttributes.lineAntialiasing)
/*      */         return false;  }
/* 1369 */      } void handleFrequencyChange(int paramInt) { char c = Character.MIN_VALUE;
/* 1370 */     if (paramInt == 9) {
/* 1371 */       c = '\020';
/* 1372 */     } else if (paramInt == 11) {
/* 1373 */       c = ' ';
/* 1374 */     } else if (paramInt == 13) {
/* 1375 */       c = '@';
/* 1376 */     } else if (paramInt == 15) {
/* 1377 */       c = '';
/* 1378 */     } else if (paramInt == 17) {
/* 1379 */       c = 'Ā';
/* 1380 */     } else if (paramInt == 19) {
/* 1381 */       c = 'Ȁ';
/* 1382 */     } else if (paramInt == 1) {
/* 1383 */       c = '\001';
/* 1384 */     } else if (paramInt == 3) {
/* 1385 */       c = '\002';
/* 1386 */     } else if (paramInt == 7) {
/* 1387 */       c = '\b';
/* 1388 */     } else if (paramInt == 5) {
/* 1389 */       c = '\004';
/* 1390 */     } else if (paramInt == 21) {
/* 1391 */       c = 'Ѐ';
/*      */     } 
/* 1393 */     if (c != '\000')
/* 1394 */       setFrequencyChangeMask(paramInt, c);  }
/*      */ 
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\AppearanceRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */