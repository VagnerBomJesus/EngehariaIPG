/*      */ package javax.media.j3d;
/*      */ class TextureBin implements ObjectUpdate {
/*      */   TextureUnitStateRetained[] texUnitState;
/*      */   private int lastActiveTexUnitIndex;
/*      */   private int numActiveTexUnit;
/*      */   RenderBin renderBin;
/*      */   EnvironmentSet environmentSet;
/*      */   AttributeBin attributeBin;
/*      */   ShaderBin shaderBin;
/*      */   TextureBin next;
/*      */   TextureBin prev;
/*      */   int equivalent;
/*      */   AppearanceRetained app;
/*      */   int soleUserCompDirty;
/*      */   static final int SOLE_USER_DIRTY_REF = 1;
/*      */   static final int SOLE_USER_DIRTY_TA = 2;
/*      */   static final int SOLE_USER_DIRTY_TC = 4;
/*      */   static final int SOLE_USER_DIRTY_TEXTURE = 8;
/*      */   static final int SOLE_USER_DIRTY_TUS = 16;
/*      */   HashMap addOpaqueRMs;
/*      */   HashMap addTransparentRMs;
/*      */   HashMap opaqueRenderMoleculeMap;
/*      */   HashMap transparentRenderMoleculeMap;
/*      */   RenderMolecule opaqueRMList;
/*      */   
/*      */   TextureBin(TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained, AppearanceRetained paramAppearanceRetained, RenderBin paramRenderBin) {
/*   27 */     this.texUnitState = null;
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
/*   38 */     this.renderBin = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   43 */     this.environmentSet = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   48 */     this.attributeBin = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   53 */     this.shaderBin = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   59 */     this.next = null;
/*   60 */     this.prev = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   66 */     this.equivalent = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   75 */     this.app = null;
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
/*  104 */     this.addOpaqueRMs = new HashMap();
/*  105 */     this.addTransparentRMs = new HashMap();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  110 */     this.opaqueRenderMoleculeMap = new HashMap();
/*  111 */     this.transparentRenderMoleculeMap = new HashMap();
/*      */ 
/*      */     
/*  114 */     this.opaqueRMList = null;
/*      */     
/*  116 */     this.transparentRMList = null;
/*      */ 
/*      */     
/*  119 */     this.numRenderMolecules = 0;
/*  120 */     this.numEditingRenderMolecules = 0;
/*      */     
/*  122 */     this.tbFlag = 0;
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
/*  138 */     this.renderBin = paramRenderBin;
/*  139 */     this.tbFlag = 0;
/*  140 */     reset(paramArrayOfTextureUnitStateRetained, paramAppearanceRetained);
/*      */   }
/*      */   
/*      */   RenderMolecule transparentRMList;
/*      */   TransparentRenderingInfo parentTInfo;
/*      */   int numRenderMolecules;
/*      */   int numEditingRenderMolecules;
/*      */   
/*      */   void reset(TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained, AppearanceRetained paramAppearanceRetained) {
/*  149 */     this.prev = null;
/*  150 */     this.next = null;
/*  151 */     this.opaqueRMList = null;
/*  152 */     this.transparentRMList = null;
/*  153 */     this.numEditingRenderMolecules = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  158 */     this.tbFlag &= 0xFFFFFFFB;
/*  159 */     if (VirtualUniverse.mc.allowSoleUser && 
/*  160 */       paramAppearanceRetained != null && (paramAppearanceRetained.changedFrequent & 0x40E) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  166 */       this.tbFlag |= 0x4;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  171 */     if ((this.tbFlag & 0x4) != 0) {
/*  172 */       this.app = paramAppearanceRetained;
/*      */     } else {
/*  174 */       this.app = null;
/*      */     } 
/*      */     
/*  177 */     resetTextureState(paramArrayOfTextureUnitStateRetained);
/*      */     
/*  179 */     if ((this.tbFlag & true) == 0) {
/*  180 */       this.renderBin.addTextureBin(this);
/*  181 */       this.tbFlag |= 0x1;
/*      */     } 
/*      */   }
/*      */   int tbFlag; static final int ON_RENDER_BIN_LIST = 1;
/*      */   static final int ON_UPDATE_LIST = 2;
/*      */   static final int SOLE_USER = 4;
/*      */   
/*      */   void resetTextureState(TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained) {
/*  189 */     boolean bool1 = false;
/*  190 */     this.numActiveTexUnit = 0;
/*  191 */     this.lastActiveTexUnitIndex = 0;
/*  192 */     boolean bool2 = ((this.tbFlag & 0x4) != 0) ? 1 : 0;
/*  193 */     TextureRetained textureRetained = null;
/*      */ 
/*      */     
/*  196 */     this.tbFlag |= 0x8;
/*      */     
/*  198 */     if (paramArrayOfTextureUnitStateRetained != null) {
/*      */       
/*  200 */       bool1 = false;
/*      */       
/*  202 */       if (this.texUnitState == null || this.texUnitState.length != paramArrayOfTextureUnitStateRetained.length) {
/*  203 */         this.texUnitState = new TextureUnitStateRetained[paramArrayOfTextureUnitStateRetained.length];
/*  204 */       } else if (this.texUnitState.length > 0 && this.texUnitState[false] != null) {
/*  205 */         textureRetained = (this.texUnitState[0]).texture;
/*      */       } 
/*      */       
/*  208 */       for (byte b = 0; b < paramArrayOfTextureUnitStateRetained.length; b++) {
/*  209 */         if (paramArrayOfTextureUnitStateRetained[b] == null) {
/*  210 */           this.texUnitState[b] = null;
/*  211 */           bool1 = true;
/*      */         }
/*      */         else {
/*      */           
/*  215 */           if (this.texUnitState[b] == null) {
/*  216 */             this.texUnitState[b] = new TextureUnitStateRetained();
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  223 */           if (bool2 || (paramArrayOfTextureUnitStateRetained[b]).changedFrequent != 0) {
/*  224 */             (this.texUnitState[b]).mirror = paramArrayOfTextureUnitStateRetained[b];
/*      */           }
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
/*  239 */           TextureRetained textureRetained1 = (this.texUnitState[b]).texture;
/*  240 */           if (textureRetained1 != null) {
/*  241 */             textureRetained1.decTextureBinRefCount(this);
/*  242 */             if (bool2 && textureRetained1.getTextureBinRefCount(this) == 0 && textureRetained1 != (paramArrayOfTextureUnitStateRetained[b]).texture)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  248 */               this.renderBin.addTextureResourceFreeList(textureRetained1);
/*      */             }
/*      */           } 
/*      */           
/*  252 */           (this.texUnitState[b]).texture = (paramArrayOfTextureUnitStateRetained[b]).texture;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  257 */           if ((this.texUnitState[b]).texture != null) {
/*  258 */             (this.texUnitState[b]).texture.incTextureBinRefCount(this);
/*      */           }
/*      */           
/*  261 */           if ((paramArrayOfTextureUnitStateRetained[b]).texAttrs != null) {
/*      */             
/*  263 */             if ((paramArrayOfTextureUnitStateRetained[b]).texAttrs.changedFrequent != 0) {
/*  264 */               (this.texUnitState[b]).texAttrs = (paramArrayOfTextureUnitStateRetained[b]).texAttrs;
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */ 
/*      */               
/*  273 */               if ((this.texUnitState[b]).texAttrs == null || (this.texUnitState[b]).texAttrs.source != null)
/*      */               {
/*  275 */                 (this.texUnitState[b]).texAttrs = new TextureAttributesRetained();
/*      */               }
/*      */               
/*  278 */               (this.texUnitState[b]).texAttrs.set((paramArrayOfTextureUnitStateRetained[b]).texAttrs);
/*      */               
/*  280 */               (this.texUnitState[b]).texAttrs.mirrorCompDirty = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  288 */               if (bool2) {
/*  289 */                 (this.texUnitState[b]).texAttrs.mirror = (paramArrayOfTextureUnitStateRetained[b]).texAttrs;
/*      */               } else {
/*      */                 
/*  292 */                 (this.texUnitState[b]).texAttrs.mirror = null;
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/*  297 */             (this.texUnitState[b]).texAttrs = null;
/*      */           } 
/*      */ 
/*      */           
/*  301 */           if ((paramArrayOfTextureUnitStateRetained[b]).texGen != null) {
/*  302 */             if ((paramArrayOfTextureUnitStateRetained[b]).texGen.changedFrequent != 0) {
/*  303 */               (this.texUnitState[b]).texGen = (paramArrayOfTextureUnitStateRetained[b]).texGen;
/*      */ 
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */ 
/*      */               
/*  311 */               if ((this.texUnitState[b]).texGen == null || (this.texUnitState[b]).texGen.source != null)
/*      */               {
/*  313 */                 (this.texUnitState[b]).texGen = new TexCoordGenerationRetained();
/*      */               }
/*      */ 
/*      */               
/*  317 */               (this.texUnitState[b]).texGen.set((paramArrayOfTextureUnitStateRetained[b]).texGen);
/*  318 */               (this.texUnitState[b]).texGen.mirrorCompDirty = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  327 */               if (bool2) {
/*  328 */                 (this.texUnitState[b]).texGen.mirror = (paramArrayOfTextureUnitStateRetained[b]).texGen;
/*      */               } else {
/*  330 */                 (this.texUnitState[b]).texGen.mirror = null;
/*      */               } 
/*      */             } 
/*      */           } else {
/*  334 */             (this.texUnitState[b]).texGen = null;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  342 */           if (this.texUnitState[b].isTextureEnabled()) {
/*  343 */             this.lastActiveTexUnitIndex = b;
/*  344 */             this.numActiveTexUnit = b + true;
/*      */             
/*  346 */             if (bool1)
/*      */             {
/*      */ 
/*      */               
/*  350 */               this.tbFlag &= 0xFFFFFFF7;
/*      */             }
/*      */           } else {
/*  353 */             bool1 = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  362 */       if ((this.texUnitState[false] == null && textureRetained != null) || (this.texUnitState[false] != null && (this.texUnitState[false]).texture != textureRetained))
/*      */       {
/*      */         
/*  365 */         this.tbFlag |= 0x10;
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  374 */       if (this.texUnitState != null && (this.texUnitState[false]).texture != null) {
/*  375 */         this.tbFlag |= 0x10;
/*      */       }
/*  377 */       this.texUnitState = null;
/*      */     } 
/*      */     
/*  380 */     this.soleUserCompDirty = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   static final int CONTIGUOUS_ACTIVE_UNITS = 8;
/*      */   static final int RESORT = 16;
/*      */   static final int ON_UPDATE_CHECK_LIST = 32;
/*      */   static final int USE_DISPLAYLIST = -2;
/*      */   static final int USE_VERTEXARRAY = -1;
/*      */   
/*      */   void clear() {
/*  391 */     this.app = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  396 */     if (this.texUnitState != null)
/*      */     {
/*      */ 
/*      */       
/*  400 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*  401 */         if (this.texUnitState[b] != null) {
/*  402 */           if ((this.texUnitState[b]).texture != null) {
/*  403 */             TextureRetained textureRetained = (this.texUnitState[b]).texture;
/*  404 */             textureRetained.decTextureBinRefCount(this);
/*      */             
/*  406 */             if (textureRetained.getTextureBinRefCount(this) == 0) {
/*  407 */               this.renderBin.addTextureResourceFreeList(textureRetained);
/*      */             }
/*      */             
/*  410 */             (this.texUnitState[b]).texture = null;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  415 */           (this.texUnitState[b]).mirror = null;
/*  416 */           (this.texUnitState[b]).texture = null;
/*  417 */           if ((this.texUnitState[b]).texAttrs != null && (this.texUnitState[b]).texAttrs.source != null)
/*      */           {
/*  419 */             (this.texUnitState[b]).texAttrs = null;
/*      */           }
/*  421 */           if ((this.texUnitState[b]).texGen != null && (this.texUnitState[b]).texGen.source != null)
/*      */           {
/*  423 */             (this.texUnitState[b]).texGen = null;
/*      */           }
/*      */         } 
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
/*      */   boolean equals(TextureUnitStateRetained[] paramArrayOfTextureUnitStateRetained, RenderAtom paramRenderAtom) {
/*  437 */     boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  444 */     if ((this.tbFlag & 0x4) != 0 || (paramRenderAtom.app != null && (paramRenderAtom.app.changedFrequent & 0x40E) != 0)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  452 */       if (this.app == paramRenderAtom.app) {
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
/*  463 */         if (this.numEditingRenderMolecules == 0) {
/*      */ 
/*      */ 
/*      */           
/*  467 */           if (this.soleUserCompDirty == 0) {
/*  468 */             this.renderBin.tbUpdateList.add(this);
/*      */           }
/*  470 */           this.soleUserCompDirty |= 0x1;
/*      */         } 
/*  472 */         return true;
/*      */       } 
/*      */       
/*  475 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  479 */     if (this.texUnitState == null && paramArrayOfTextureUnitStateRetained == null) {
/*  480 */       return true;
/*      */     }
/*  482 */     if (this.texUnitState == null || paramArrayOfTextureUnitStateRetained == null) {
/*  483 */       return false;
/*      */     }
/*  485 */     if (paramArrayOfTextureUnitStateRetained.length != this.texUnitState.length) {
/*  486 */       return false;
/*      */     }
/*  488 */     for (byte b = 0; b < this.texUnitState.length; b++) {
/*      */       
/*  490 */       if (this.texUnitState[b] == null) {
/*  491 */         if (paramArrayOfTextureUnitStateRetained[b] != null) {
/*  492 */           return false;
/*      */         }
/*      */       }
/*  495 */       else if (!this.texUnitState[b].equivalent(paramArrayOfTextureUnitStateRetained[b])) {
/*  496 */         return false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  504 */     if ((this.tbFlag & true) == 0) {
/*  505 */       this.renderBin.addTextureBin(this);
/*  506 */       this.tbFlag |= 0x1;
/*      */     } 
/*      */     
/*  509 */     return true;
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
/*      */   public void updateNodeComponent() {
/*  600 */     if ((this.tbFlag & true) == 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  606 */     if ((this.tbFlag & 0x4) != 0 && (this.soleUserCompDirty & true) != 0) {
/*      */ 
/*      */       
/*  609 */       resetTextureState(this.app.texUnitState);
/*      */       
/*      */       return;
/*      */     } 
/*  613 */     if (this.texUnitState == null) {
/*  614 */       this.soleUserCompDirty = 0;
/*      */       
/*      */       return;
/*      */     } 
/*  618 */     if ((this.soleUserCompDirty & 0x10) != 0) {
/*      */ 
/*      */       
/*  621 */       TextureUnitStateRetained textureUnitStateRetained = null;
/*  622 */       boolean bool = ((this.tbFlag & 0x4) != 0) ? 1 : 0;
/*      */       
/*  624 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*  625 */         TextureUnitStateRetained textureUnitStateRetained1 = this.texUnitState[b];
/*  626 */         if (textureUnitStateRetained1 != null && 
/*  627 */           textureUnitStateRetained1.mirror != null) {
/*      */           
/*  629 */           textureUnitStateRetained = (TextureUnitStateRetained)textureUnitStateRetained1.mirror;
/*      */           
/*  631 */           if (textureUnitStateRetained1.texture != textureUnitStateRetained.texture) {
/*  632 */             if (textureUnitStateRetained1.texture != null) {
/*  633 */               textureUnitStateRetained1.texture.decTextureBinRefCount(this);
/*      */             }
/*  635 */             textureUnitStateRetained1.texture = textureUnitStateRetained.texture;
/*  636 */             if (textureUnitStateRetained1.texture != null) {
/*  637 */               textureUnitStateRetained1.texture.incTextureBinRefCount(this);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  643 */             if (!b) {
/*  644 */               this.tbFlag |= 0x10;
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  649 */           if (textureUnitStateRetained.texAttrs != null) {
/*  650 */             if (textureUnitStateRetained.texAttrs.changedFrequent != 0) {
/*  651 */               textureUnitStateRetained1.texAttrs = textureUnitStateRetained.texAttrs;
/*      */             } else {
/*  653 */               if (textureUnitStateRetained1.texAttrs == null || textureUnitStateRetained1.texAttrs.source != null)
/*      */               {
/*  655 */                 textureUnitStateRetained1.texAttrs = new TextureAttributesRetained();
/*      */               }
/*      */               
/*  658 */               textureUnitStateRetained1.texAttrs.set(textureUnitStateRetained.texAttrs);
/*  659 */               textureUnitStateRetained1.texAttrs.mirrorCompDirty = true;
/*      */               
/*  661 */               if (bool) {
/*  662 */                 textureUnitStateRetained1.texAttrs.mirror = textureUnitStateRetained.texAttrs;
/*      */               } else {
/*  664 */                 textureUnitStateRetained1.texAttrs.mirror = null;
/*      */               } 
/*      */             } 
/*      */           } else {
/*  668 */             textureUnitStateRetained1.texAttrs = null;
/*      */           } 
/*      */           
/*  671 */           if (textureUnitStateRetained.texGen != null) {
/*  672 */             if (textureUnitStateRetained.texGen.changedFrequent != 0) {
/*  673 */               textureUnitStateRetained1.texGen = textureUnitStateRetained.texGen;
/*      */             } else {
/*  675 */               if (textureUnitStateRetained1.texGen == null || textureUnitStateRetained1.texGen.source != null)
/*      */               {
/*  677 */                 textureUnitStateRetained1.texGen = new TexCoordGenerationRetained();
/*      */               }
/*      */               
/*  680 */               textureUnitStateRetained1.texGen.set(textureUnitStateRetained.texGen);
/*  681 */               textureUnitStateRetained1.texGen.mirrorCompDirty = true;
/*      */               
/*  683 */               if (bool) {
/*  684 */                 textureUnitStateRetained1.texGen.mirror = textureUnitStateRetained.texGen;
/*      */               } else {
/*  686 */                 textureUnitStateRetained1.texGen.mirror = null;
/*      */               } 
/*      */             } 
/*      */           } else {
/*  690 */             textureUnitStateRetained1.texGen = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  697 */       this.soleUserCompDirty |= 0x8;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  702 */       this.soleUserCompDirty &= 0xFFFFFFF9;
/*      */     } 
/*      */ 
/*      */     
/*  706 */     if ((this.soleUserCompDirty & 0x8) != 0) {
/*      */ 
/*      */ 
/*      */       
/*  710 */       boolean bool = false;
/*      */       
/*  712 */       this.numActiveTexUnit = 0;
/*  713 */       this.lastActiveTexUnitIndex = 0;
/*  714 */       this.tbFlag |= 0x8;
/*  715 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  721 */         if (this.texUnitState[b] != null && this.texUnitState[b].isTextureEnabled()) {
/*      */           
/*  723 */           this.lastActiveTexUnitIndex = b;
/*  724 */           this.numActiveTexUnit = b + true;
/*      */           
/*  726 */           if (bool)
/*      */           {
/*      */ 
/*      */             
/*  730 */             this.tbFlag &= 0xFFFFFFF7;
/*      */           }
/*      */         } else {
/*  733 */           bool = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  738 */     if ((this.soleUserCompDirty & 0x2) != 0) {
/*  739 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*  740 */         if (this.texUnitState[b] != null && (this.texUnitState[b]).texAttrs != null && (this.texUnitState[b]).texAttrs.mirror != null && (this.texUnitState[b]).texAttrs.mirror.changedFrequent != 0)
/*      */         {
/*      */ 
/*      */           
/*  744 */           (this.texUnitState[b]).texAttrs = (TextureAttributesRetained)(this.texUnitState[b]).texAttrs.mirror;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  750 */     if ((this.soleUserCompDirty & 0x4) != 0) {
/*  751 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/*  752 */         if (this.texUnitState[b] != null && (this.texUnitState[b]).texGen != null && (this.texUnitState[b]).texGen.mirror != null && (this.texUnitState[b]).texGen.mirror.changedFrequent != 0)
/*      */         {
/*      */ 
/*      */           
/*  756 */           (this.texUnitState[b]).texGen = (TexCoordGenerationRetained)(this.texUnitState[b]).texGen.mirror;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  762 */     this.soleUserCompDirty = 0;
/*      */   }
/*      */   
/*      */   public void updateObject() {
/*  766 */     if (!this.addOpaqueRMs.isEmpty()) {
/*  767 */       this.opaqueRMList = addAll(this.opaqueRenderMoleculeMap, this.addOpaqueRMs, this.opaqueRMList, true);
/*      */     }
/*      */     
/*  770 */     if (!this.addTransparentRMs.isEmpty())
/*      */     {
/*      */       
/*  773 */       if (this.transparentRMList == null && (this.renderBin.transpSortMode == 0 || this.environmentSet.lightBin.geometryBackground != null)) {
/*      */ 
/*      */ 
/*      */         
/*  777 */         this.transparentRMList = addAll(this.transparentRenderMoleculeMap, this.addTransparentRMs, this.transparentRMList, false);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  782 */         if (this.transparentRMList != null) {
/*  783 */           this.renderBin.addTransparentObject(this);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  788 */         this.transparentRMList = addAll(this.transparentRenderMoleculeMap, this.addTransparentRMs, this.transparentRMList, false);
/*      */       } 
/*      */     }
/*      */     
/*  792 */     this.tbFlag &= 0xFFFFFFFD;
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
/*      */   RenderMolecule addAll(HashMap paramHashMap1, HashMap paramHashMap2, RenderMolecule paramRenderMolecule, boolean paramBoolean) {
/*  821 */     Collection collection = paramHashMap2.values();
/*  822 */     Iterator iterator = collection.iterator();
/*      */ 
/*      */     
/*  825 */     while (iterator.hasNext()) {
/*  826 */       boolean bool = false;
/*  827 */       ArrayList arrayList = (ArrayList)iterator.next();
/*  828 */       RenderMolecule renderMolecule1 = (RenderMolecule)arrayList.get(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  836 */       if (renderMolecule1.isOpaqueOrInOG != paramBoolean) {
/*      */         continue;
/*      */       }
/*      */       
/*  840 */       RenderMolecule renderMolecule2 = (RenderMolecule)paramHashMap1.get(renderMolecule1.localToVworld);
/*      */       
/*  842 */       if (renderMolecule2 == null) {
/*  843 */         renderMolecule2 = renderMolecule1;
/*  844 */         paramHashMap1.put(renderMolecule1.localToVworld, renderMolecule2);
/*      */         
/*  846 */         if (paramRenderMolecule == null) {
/*  847 */           paramRenderMolecule = renderMolecule1;
/*  848 */           renderMolecule1.nextMap = null;
/*  849 */           renderMolecule1.prevMap = null;
/*  850 */           paramRenderMolecule.dirtyAttrsAcrossRms = 945;
/*      */         } else {
/*      */           
/*  853 */           renderMolecule1.nextMap = paramRenderMolecule;
/*  854 */           paramRenderMolecule.prevMap = renderMolecule1;
/*  855 */           paramRenderMolecule = renderMolecule1;
/*  856 */           paramRenderMolecule.nextMap.checkEquivalenceWithLeftNeighbor(renderMolecule1, 945);
/*      */         } 
/*      */       } else {
/*      */         RenderMolecule renderMolecule;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  864 */         if ((renderMolecule = insertRenderMolecule(renderMolecule1, renderMolecule2)) != null) {
/*  865 */           if (renderMolecule2.prevMap != null) {
/*  866 */             renderMolecule2.prevMap.nextMap = renderMolecule;
/*      */           }
/*  868 */           renderMolecule.prevMap = renderMolecule2.prevMap;
/*  869 */           renderMolecule2.prevMap = null;
/*  870 */           renderMolecule2 = renderMolecule;
/*  871 */           bool = true;
/*      */         } 
/*      */       } 
/*  874 */       for (byte b = 1; b < arrayList.size(); b++) {
/*  875 */         renderMolecule1 = (RenderMolecule)arrayList.get(b);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  883 */         if (renderMolecule1.isOpaqueOrInOG == paramBoolean) {
/*      */           RenderMolecule renderMolecule;
/*  885 */           if ((renderMolecule = insertRenderMolecule(renderMolecule1, renderMolecule2)) != null) {
/*  886 */             if (renderMolecule2.prevMap != null) {
/*  887 */               renderMolecule2.prevMap.nextMap = renderMolecule;
/*      */             }
/*  889 */             renderMolecule.prevMap = renderMolecule2.prevMap;
/*  890 */             renderMolecule2.prevMap = null;
/*  891 */             renderMolecule2 = renderMolecule;
/*  892 */             bool = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*  896 */       if (bool) {
/*  897 */         paramHashMap1.put(renderMolecule1.localToVworld, renderMolecule2);
/*  898 */         if (renderMolecule2.prevMap != null) {
/*  899 */           renderMolecule2.checkEquivalenceWithLeftNeighbor(renderMolecule2.prevMap, 945);
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*  904 */         paramRenderMolecule = renderMolecule2;
/*  905 */         paramRenderMolecule.dirtyAttrsAcrossRms = 945;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  911 */     paramHashMap2.clear();
/*  912 */     return paramRenderMolecule;
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
/*      */   RenderMolecule insertRenderMolecule(RenderMolecule paramRenderMolecule1, RenderMolecule paramRenderMolecule2) {
/*  924 */     RenderMolecule renderMolecule = paramRenderMolecule2;
/*  925 */     while (renderMolecule != null) {
/*  926 */       if (renderMolecule.material == paramRenderMolecule1.material || (renderMolecule.definingMaterial != null && renderMolecule.definingMaterial.equivalent(paramRenderMolecule1.definingMaterial))) {
/*      */         RenderMolecule renderMolecule1;
/*      */ 
/*      */         
/*  930 */         paramRenderMolecule1.next = renderMolecule;
/*  931 */         paramRenderMolecule1.prev = renderMolecule.prev;
/*  932 */         if (renderMolecule.prev == null) {
/*  933 */           paramRenderMolecule2 = paramRenderMolecule1;
/*  934 */           renderMolecule1 = paramRenderMolecule2;
/*      */         } else {
/*  936 */           renderMolecule.prev.next = paramRenderMolecule1;
/*  937 */           renderMolecule1 = null;
/*      */         } 
/*  939 */         renderMolecule.prev = paramRenderMolecule1;
/*  940 */         paramRenderMolecule1.checkEquivalenceWithBothNeighbors(945);
/*  941 */         return renderMolecule1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  946 */       renderMolecule = renderMolecule.next;
/*  947 */       while (renderMolecule != null && (renderMolecule.dirtyAttrsAcrossRms & true) == 0)
/*      */       {
/*  949 */         renderMolecule = renderMolecule.next;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  954 */     paramRenderMolecule1.next = paramRenderMolecule2;
/*  955 */     paramRenderMolecule2.prev = paramRenderMolecule1;
/*  956 */     paramRenderMolecule2 = paramRenderMolecule1;
/*  957 */     paramRenderMolecule1.checkEquivalenceWithBothNeighbors(945);
/*  958 */     return paramRenderMolecule2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addRenderMolecule(RenderMolecule paramRenderMolecule, RenderBin paramRenderBin) {
/*      */     HashMap hashMap;
/*  969 */     paramRenderMolecule.textureBin = this;
/*      */     
/*  971 */     if (paramRenderMolecule.isOpaqueOrInOG) {
/*  972 */       hashMap = this.addOpaqueRMs;
/*      */     } else {
/*  974 */       hashMap = this.addTransparentRMs;
/*      */     }  ArrayList arrayList;
/*  976 */     if ((arrayList = (ArrayList)hashMap.get(paramRenderMolecule.localToVworld)) == null) {
/*  977 */       arrayList = new ArrayList();
/*  978 */       hashMap.put(paramRenderMolecule.localToVworld, arrayList);
/*      */     } 
/*  980 */     arrayList.add(paramRenderMolecule);
/*      */     
/*  982 */     if ((this.tbFlag & 0x2) == 0) {
/*  983 */       this.tbFlag |= 0x2;
/*  984 */       paramRenderBin.objUpdateList.add(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeRenderMolecule(RenderMolecule paramRenderMolecule) {
/*      */     HashMap hashMap2, hashMap1;
/*      */     RenderMolecule renderMolecule;
/*  994 */     boolean bool = false;
/*      */ 
/*      */ 
/*      */     
/*  998 */     paramRenderMolecule.textureBin = null;
/*      */     
/* 1000 */     if (paramRenderMolecule.isOpaqueOrInOG) {
/* 1001 */       renderMolecule = this.opaqueRMList;
/* 1002 */       hashMap2 = this.opaqueRenderMoleculeMap;
/* 1003 */       hashMap1 = this.addOpaqueRMs;
/*      */     } else {
/*      */       
/* 1006 */       renderMolecule = this.transparentRMList;
/* 1007 */       hashMap2 = this.transparentRenderMoleculeMap;
/* 1008 */       hashMap1 = this.addTransparentRMs;
/*      */     } 
/*      */     ArrayList arrayList;
/*      */     int i;
/* 1012 */     if ((arrayList = (ArrayList)hashMap1.get(paramRenderMolecule.localToVworld)) != null && (
/* 1013 */       i = arrayList.indexOf(paramRenderMolecule)) != -1) {
/* 1014 */       arrayList.remove(i);
/*      */ 
/*      */       
/* 1017 */       if (arrayList.isEmpty()) {
/* 1018 */         hashMap1.remove(paramRenderMolecule.localToVworld);
/*      */       }
/*      */       
/* 1021 */       paramRenderMolecule.prev = null;
/* 1022 */       paramRenderMolecule.next = null;
/* 1023 */       bool = true;
/*      */     } 
/*      */     
/* 1026 */     if (!bool) {
/* 1027 */       RenderMolecule renderMolecule1 = removeOneRM(paramRenderMolecule, hashMap2, renderMolecule);
/*      */       
/* 1029 */       paramRenderMolecule.soleUserCompDirty = 0;
/* 1030 */       paramRenderMolecule.onUpdateList = 0;
/* 1031 */       if (paramRenderMolecule.definingPolygonAttributes != null && paramRenderMolecule.definingPolygonAttributes.changedFrequent != 0)
/*      */       {
/* 1033 */         paramRenderMolecule.definingPolygonAttributes = null;
/*      */       }
/* 1035 */       if (paramRenderMolecule.definingLineAttributes != null && paramRenderMolecule.definingLineAttributes.changedFrequent != 0)
/*      */       {
/* 1037 */         paramRenderMolecule.definingLineAttributes = null;
/*      */       }
/* 1039 */       if (paramRenderMolecule.definingPointAttributes != null && paramRenderMolecule.definingPointAttributes.changedFrequent != 0)
/*      */       {
/* 1041 */         paramRenderMolecule.definingPointAttributes = null;
/*      */       }
/* 1043 */       if (paramRenderMolecule.definingMaterial != null && paramRenderMolecule.definingMaterial.changedFrequent != 0)
/*      */       {
/* 1045 */         paramRenderMolecule.definingMaterial = null;
/*      */       }
/* 1047 */       if (paramRenderMolecule.definingColoringAttributes != null && paramRenderMolecule.definingColoringAttributes.changedFrequent != 0)
/*      */       {
/* 1049 */         paramRenderMolecule.definingColoringAttributes = null;
/*      */       }
/* 1051 */       if (paramRenderMolecule.definingTransparency != null && paramRenderMolecule.definingTransparency.changedFrequent != 0)
/*      */       {
/* 1053 */         paramRenderMolecule.definingTransparency = null;
/*      */       }
/* 1055 */       this.renderBin.removeRenderMolecule(paramRenderMolecule);
/* 1056 */       if (paramRenderMolecule.isOpaqueOrInOG) {
/* 1057 */         this.opaqueRMList = renderMolecule1;
/*      */       } else {
/*      */         
/* 1060 */         this.transparentRMList = renderMolecule1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1065 */     if (!paramRenderMolecule.isOpaqueOrInOG && this.transparentRMList == null && (this.renderBin.transpSortMode == 0 || this.environmentSet.lightBin.geometryBackground != null)) {
/*      */       
/* 1067 */       this.renderBin.removeTransparentObject(this);
/*      */ 
/*      */     
/*      */     }
/* 1071 */     else if (this.parentTInfo != null && this.parentTInfo.rm == paramRenderMolecule) {
/* 1072 */       this.parentTInfo.rm = this.transparentRMList;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1078 */     if (this.opaqueRenderMoleculeMap.isEmpty() && this.addOpaqueRMs.isEmpty() && this.transparentRenderMoleculeMap.isEmpty() && this.addTransparentRMs.isEmpty()) {
/*      */       
/* 1080 */       if ((this.tbFlag & true) != 0) {
/* 1081 */         this.tbFlag &= 0xFFFFFFFE;
/* 1082 */         this.renderBin.removeTextureBin(this);
/*      */       } 
/*      */       
/* 1085 */       this.shaderBin.removeTextureBin(this);
/* 1086 */       this.texUnitState = null;
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
/*      */   void updateAttributes(Canvas3D paramCanvas3D) {
/* 1098 */     boolean bool1 = ((paramCanvas3D.canvasDirty & 0xC00) != 0) ? 1 : 0;
/*      */ 
/*      */     
/* 1101 */     if (paramCanvas3D.textureBin == this && !bool1) {
/*      */       return;
/*      */     }
/*      */     
/* 1105 */     paramCanvas3D.textureBin = this;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1110 */     byte b = -1;
/*      */ 
/*      */ 
/*      */     
/* 1114 */     boolean bool2 = (this.shaderBin.shaderProgram != null) ? 1 : 0;
/* 1115 */     int i = bool2 ? paramCanvas3D.maxTextureImageUnits : paramCanvas3D.maxTextureUnits;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1121 */     boolean bool3 = false;
/*      */     
/* 1123 */     if (this.numActiveTexUnit > i) {
/* 1124 */       bool3 = true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1129 */     if (bool3) {
/* 1130 */       paramCanvas3D.setNumActiveTexUnit(0);
/*      */     } else {
/*      */       
/* 1133 */       paramCanvas3D.setNumActiveTexUnit(this.numActiveTexUnit);
/*      */     } 
/*      */ 
/*      */     
/* 1137 */     if (this.numActiveTexUnit <= 0 || bool3) {
/* 1138 */       if (paramCanvas3D.getLastActiveTexUnit() >= 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1143 */         if (paramCanvas3D.multiTexAccelerated) {
/* 1144 */           for (byte b1 = 0; b1 <= paramCanvas3D.getLastActiveTexUnit(); b1++) {
/* 1145 */             paramCanvas3D.resetTexture(paramCanvas3D.ctx, b1);
/*      */           }
/*      */           
/* 1148 */           paramCanvas3D.setNumActiveTexUnit(0);
/* 1149 */           paramCanvas3D.activeTextureUnit(paramCanvas3D.ctx, 0);
/*      */         } else {
/* 1151 */           paramCanvas3D.resetTexture(paramCanvas3D.ctx, -1);
/*      */         } 
/* 1153 */         paramCanvas3D.setLastActiveTexUnit(-1);
/*      */       } 
/*      */     } else {
/*      */       
/* 1157 */       byte b1 = 0;
/*      */       byte b2;
/* 1159 */       for (b2 = 0; b2 < this.texUnitState.length; b2++) {
/*      */         
/* 1161 */         if (b1 >= paramCanvas3D.texUnitState.length) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1169 */         if (this.texUnitState[b2] != null && this.texUnitState[b2].isTextureEnabled()) {
/*      */           
/* 1171 */           if (bool1 || (paramCanvas3D.texUnitState[b1]).mirror == null || (paramCanvas3D.texUnitState[b1]).mirror != (this.texUnitState[b2]).mirror) {
/*      */ 
/*      */ 
/*      */             
/* 1175 */             this.texUnitState[b2].updateNative(b1, paramCanvas3D, false, false);
/* 1176 */             (paramCanvas3D.texUnitState[b1]).mirror = (this.texUnitState[b2]).mirror;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1182 */           b = b1;
/*      */         }
/* 1184 */         else if (b1 <= paramCanvas3D.getLastActiveTexUnit()) {
/* 1185 */           paramCanvas3D.resetTexture(paramCanvas3D.ctx, b1);
/*      */         } 
/*      */ 
/*      */         
/* 1189 */         b1++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1196 */       for (b2 = b1; b2 <= paramCanvas3D.getLastActiveTexUnit(); b2++) {
/* 1197 */         paramCanvas3D.resetTexture(paramCanvas3D.ctx, b2);
/*      */       }
/*      */       
/* 1200 */       paramCanvas3D.setLastActiveTexUnit(b);
/*      */ 
/*      */       
/* 1203 */       paramCanvas3D.activeTextureUnit(paramCanvas3D.ctx, 0);
/*      */     } 
/*      */     
/* 1206 */     paramCanvas3D.canvasDirty &= 0xFFFFFBFF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1214 */   void render(Canvas3D paramCanvas3D) { render(paramCanvas3D, this.opaqueRMList); }
/*      */ 
/*      */ 
/*      */   
/*      */   void render(Canvas3D paramCanvas3D, Object paramObject) {
/* 1219 */     paramCanvas3D.texLinearMode = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1228 */     paramCanvas3D.setStateToUpdate(3, this);
/*      */ 
/*      */ 
/*      */     
/* 1232 */     if (this.texUnitState != null && VirtualUniverse.mc.isD3D())
/*      */     {
/* 1234 */       for (byte b = 0; b < this.texUnitState.length; b++) {
/* 1235 */         TextureUnitStateRetained textureUnitStateRetained = this.texUnitState[b];
/* 1236 */         if (textureUnitStateRetained != null && textureUnitStateRetained.isTextureEnabled() && 
/* 1237 */           textureUnitStateRetained.texGen != null && textureUnitStateRetained.texGen.genMode == 0)
/*      */         {
/* 1239 */           paramCanvas3D.texLinearMode = true;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1245 */     renderList(paramCanvas3D, -2, paramObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderList(Canvas3D paramCanvas3D, int paramInt, Object paramObject) {
/* 1253 */     assert paramInt < 0;
/*      */     
/* 1255 */     if (paramObject instanceof RenderMolecule) {
/* 1256 */       renderList(paramCanvas3D, paramInt, (RenderMolecule)paramObject);
/* 1257 */     } else if (paramObject instanceof TransparentRenderingInfo) {
/* 1258 */       renderList(paramCanvas3D, paramInt, (TransparentRenderingInfo)paramObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderList(Canvas3D paramCanvas3D, int paramInt, RenderMolecule paramRenderMolecule) {
/* 1267 */     assert paramInt < 0;
/*      */ 
/*      */ 
/*      */     
/* 1271 */     int i = 0;
/* 1272 */     boolean bool = true;
/* 1273 */     RenderMolecule renderMolecule = paramRenderMolecule;
/*      */     
/* 1275 */     while (renderMolecule != null) {
/* 1276 */       if (bool) {
/* 1277 */         i = renderMolecule.dirtyAttrsAcrossRms;
/*      */       } else {
/*      */         
/* 1280 */         i |= renderMolecule.dirtyAttrsAcrossRms;
/*      */       } 
/*      */       
/* 1283 */       bool = renderMolecule.render(paramCanvas3D, paramInt, i);
/*      */ 
/*      */ 
/*      */       
/* 1287 */       if (renderMolecule.next == null) {
/* 1288 */         renderMolecule = renderMolecule.nextMap;
/*      */         continue;
/*      */       } 
/* 1291 */       renderMolecule = renderMolecule.next;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void renderList(Canvas3D paramCanvas3D, int paramInt, TransparentRenderingInfo paramTransparentRenderingInfo) {
/* 1301 */     assert paramInt < 0;
/*      */     
/* 1303 */     RenderMolecule renderMolecule = paramTransparentRenderingInfo.rm;
/* 1304 */     if (renderMolecule.isSwitchOn())
/* 1305 */       renderMolecule.transparentSortRender(paramCanvas3D, paramInt, paramTransparentRenderingInfo); 
/*      */   }
/*      */   
/*      */   void changeLists(RenderMolecule paramRenderMolecule) {
/*      */     RenderMolecule renderMolecule3;
/*      */     HashMap hashMap2;
/* 1311 */     RenderMolecule renderMolecule2 = null;
/* 1312 */     HashMap hashMap1 = null;
/*      */ 
/*      */     
/* 1315 */     boolean bool = false;
/*      */ 
/*      */     
/* 1318 */     if (paramRenderMolecule.isOpaqueOrInOG) {
/* 1319 */       if (this.opaqueRMList == null && paramRenderMolecule.prev == null && paramRenderMolecule.prevMap == null && paramRenderMolecule.next == null && paramRenderMolecule.nextMap == null) {
/*      */ 
/*      */         
/* 1322 */         bool = true;
/*      */       } else {
/*      */         
/* 1325 */         renderMolecule2 = this.opaqueRMList;
/* 1326 */         hashMap1 = this.opaqueRenderMoleculeMap;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1331 */     else if (this.transparentRMList == null && paramRenderMolecule.prev == null && paramRenderMolecule.prevMap == null && paramRenderMolecule.next == null && paramRenderMolecule.nextMap == null) {
/*      */ 
/*      */       
/* 1334 */       bool = true;
/*      */     } else {
/*      */       
/* 1337 */       renderMolecule2 = this.transparentRMList;
/* 1338 */       hashMap1 = this.transparentRenderMoleculeMap;
/*      */     } 
/*      */     
/* 1341 */     if (!bool) {
/* 1342 */       RenderMolecule renderMolecule = removeOneRM(paramRenderMolecule, hashMap1, renderMolecule2);
/*      */       
/* 1344 */       if (paramRenderMolecule.isOpaqueOrInOG) {
/* 1345 */         this.opaqueRMList = renderMolecule;
/*      */       } else {
/*      */         
/* 1348 */         this.transparentRMList = renderMolecule;
/* 1349 */         if (this.transparentRMList == null && (this.renderBin.transpSortMode == 0 || this.environmentSet.lightBin.geometryBackground != null))
/*      */         {
/*      */           
/* 1352 */           this.renderBin.removeTransparentObject(this);
/*      */         }
/*      */ 
/*      */         
/* 1356 */         if (this.renderBin.transpSortMode == 1 && this.environmentSet.lightBin.geometryBackground == null)
/*      */         {
/* 1358 */           paramRenderMolecule.addRemoveTransparentObject(this.renderBin, false);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1366 */     paramRenderMolecule.evalAlphaUsage(this.shaderBin.attributeBin.definingRenderingAttributes, this.texUnitState);
/* 1367 */     paramRenderMolecule.isOpaqueOrInOG = (paramRenderMolecule.isOpaque() || paramRenderMolecule.inOrderedGroup);
/* 1368 */     if (paramRenderMolecule.isOpaqueOrInOG) {
/* 1369 */       renderMolecule3 = this.opaqueRMList;
/* 1370 */       hashMap2 = this.opaqueRenderMoleculeMap;
/* 1371 */       markDlistAsDirty(paramRenderMolecule);
/*      */     } else {
/*      */       
/* 1374 */       renderMolecule3 = this.transparentRMList;
/* 1375 */       hashMap2 = this.transparentRenderMoleculeMap;
/* 1376 */       if ((paramRenderMolecule.primaryMoleculeType & RenderMolecule.DLIST_MOLECULE) != 0 && this.renderBin.transpSortMode != 0) {
/*      */         
/* 1378 */         this.renderBin.addDisplayListResourceFreeList(paramRenderMolecule);
/* 1379 */         this.renderBin.removeDirtyRenderMolecule(paramRenderMolecule);
/*      */         
/* 1381 */         paramRenderMolecule.vwcBounds.set(null);
/* 1382 */         paramRenderMolecule.displayListId = 0;
/* 1383 */         paramRenderMolecule.displayListIdObj = null;
/*      */         
/* 1385 */         RenderAtomListInfo renderAtomListInfo = paramRenderMolecule.primaryRenderAtomList;
/* 1386 */         while (renderAtomListInfo != null) {
/* 1387 */           renderAtomListInfo.groupType = RenderAtom.SEPARATE_DLIST_PER_RINFO;
/* 1388 */           if (renderAtomListInfo.renderAtom.dlistIds == null) {
/* 1389 */             renderAtomListInfo.renderAtom.dlistIds = new int[renderAtomListInfo.renderAtom.rListInfo.length];
/*      */             
/* 1391 */             for (byte b = 0; b < renderAtomListInfo.renderAtom.dlistIds.length; b++) {
/* 1392 */               renderAtomListInfo.renderAtom.dlistIds[b] = -1;
/*      */             }
/*      */           } 
/* 1395 */           if (renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] == -1) {
/* 1396 */             renderAtomListInfo.renderAtom.dlistIds[renderAtomListInfo.index] = VirtualUniverse.mc.getDisplayListId().intValue();
/* 1397 */             this.renderBin.addDlistPerRinfo.add(renderAtomListInfo);
/*      */           } 
/* 1399 */           renderAtomListInfo = renderAtomListInfo.next;
/*      */         } 
/* 1401 */         paramRenderMolecule.primaryMoleculeType = RenderMolecule.SEPARATE_DLIST_PER_RINFO_MOLECULE;
/*      */       } else {
/*      */         
/* 1404 */         markDlistAsDirty(paramRenderMolecule);
/*      */       } 
/*      */     } 
/*      */     
/* 1408 */     RenderMolecule renderMolecule1 = (RenderMolecule)hashMap2.get(paramRenderMolecule.localToVworld);
/*      */     
/* 1410 */     if (renderMolecule1 == null) {
/* 1411 */       renderMolecule1 = paramRenderMolecule;
/* 1412 */       hashMap2.put(paramRenderMolecule.localToVworld, renderMolecule1);
/*      */       
/* 1414 */       if (renderMolecule3 == null) {
/* 1415 */         renderMolecule3 = paramRenderMolecule;
/* 1416 */         paramRenderMolecule.nextMap = null;
/* 1417 */         paramRenderMolecule.prevMap = null;
/*      */       } else {
/*      */         
/* 1420 */         paramRenderMolecule.nextMap = renderMolecule3;
/* 1421 */         renderMolecule3.prevMap = paramRenderMolecule;
/* 1422 */         renderMolecule3 = paramRenderMolecule;
/* 1423 */         renderMolecule3.nextMap.checkEquivalenceWithLeftNeighbor(paramRenderMolecule, 945);
/*      */       } 
/*      */ 
/*      */       
/* 1427 */       renderMolecule3.dirtyAttrsAcrossRms = 945;
/*      */     } else {
/*      */       RenderMolecule renderMolecule;
/*      */ 
/*      */       
/* 1432 */       if ((renderMolecule = insertRenderMolecule(paramRenderMolecule, renderMolecule1)) != null) {
/* 1433 */         if (renderMolecule1.prevMap != null) {
/* 1434 */           renderMolecule1.prevMap.nextMap = renderMolecule;
/*      */         }
/* 1436 */         renderMolecule.prevMap = renderMolecule1.prevMap;
/* 1437 */         renderMolecule1.prevMap = null;
/* 1438 */         renderMolecule1 = renderMolecule;
/* 1439 */         hashMap2.put(paramRenderMolecule.localToVworld, renderMolecule1);
/* 1440 */         if (renderMolecule1.prevMap != null) {
/* 1441 */           renderMolecule1.checkEquivalenceWithLeftNeighbor(renderMolecule1.prevMap, 945);
/*      */         }
/*      */         else {
/*      */           
/* 1445 */           renderMolecule3.dirtyAttrsAcrossRms = 945;
/* 1446 */           renderMolecule3 = renderMolecule1;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1451 */     if (paramRenderMolecule.isOpaqueOrInOG) {
/* 1452 */       this.opaqueRMList = renderMolecule3;
/*      */     }
/*      */     else {
/*      */       
/* 1456 */       if (this.transparentRMList == null && (this.renderBin.transpSortMode == 0 || this.environmentSet.lightBin.geometryBackground != null)) {
/*      */ 
/*      */         
/* 1459 */         this.transparentRMList = renderMolecule3;
/* 1460 */         this.renderBin.addTransparentObject(this);
/*      */       } else {
/*      */         
/* 1463 */         this.transparentRMList = renderMolecule3;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1468 */       if (this.renderBin.transpSortMode == 1 && this.environmentSet.lightBin.geometryBackground == null)
/*      */       {
/* 1470 */         paramRenderMolecule.addRemoveTransparentObject(this.renderBin, true);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   RenderMolecule removeOneRM(RenderMolecule paramRenderMolecule1, HashMap paramHashMap, RenderMolecule paramRenderMolecule2) {
/* 1476 */     RenderMolecule renderMolecule = paramRenderMolecule2;
/*      */     
/* 1478 */     if (paramRenderMolecule1.prev != null && paramRenderMolecule1.next != null) {
/* 1479 */       paramRenderMolecule1.prev.next = paramRenderMolecule1.next;
/* 1480 */       paramRenderMolecule1.next.prev = paramRenderMolecule1.prev;
/* 1481 */       paramRenderMolecule1.next.checkEquivalenceWithLeftNeighbor(paramRenderMolecule1.prev, 945);
/*      */     
/*      */     }
/* 1484 */     else if (paramRenderMolecule1.prev != null && paramRenderMolecule1.next == null) {
/* 1485 */       paramRenderMolecule1.prev.next = paramRenderMolecule1.next;
/* 1486 */       paramRenderMolecule1.prev.nextMap = paramRenderMolecule1.nextMap;
/* 1487 */       if (paramRenderMolecule1.nextMap != null) {
/* 1488 */         paramRenderMolecule1.nextMap.prevMap = paramRenderMolecule1.prev;
/* 1489 */         paramRenderMolecule1.nextMap.checkEquivalenceWithLeftNeighbor(paramRenderMolecule1.prev, 945);
/*      */       }
/*      */     
/* 1492 */     } else if (paramRenderMolecule1.prev == null && paramRenderMolecule1.next != null) {
/* 1493 */       paramRenderMolecule1.next.prev = null;
/* 1494 */       paramRenderMolecule1.next.prevMap = paramRenderMolecule1.prevMap;
/* 1495 */       if (paramRenderMolecule1.prevMap != null) {
/* 1496 */         paramRenderMolecule1.prevMap.nextMap = paramRenderMolecule1.next;
/* 1497 */         paramRenderMolecule1.next.checkEquivalenceWithLeftNeighbor(paramRenderMolecule1.prevMap, 945);
/*      */       }
/*      */       else {
/*      */         
/* 1501 */         renderMolecule = paramRenderMolecule1.next;
/* 1502 */         renderMolecule.dirtyAttrsAcrossRms = 945;
/*      */       } 
/* 1504 */       paramHashMap.put(paramRenderMolecule1.localToVworld, paramRenderMolecule1.next);
/*      */     
/*      */     }
/* 1507 */     else if (paramRenderMolecule1.prev == null && paramRenderMolecule1.next == null) {
/* 1508 */       if (paramRenderMolecule1.prevMap != null) {
/* 1509 */         paramRenderMolecule1.prevMap.nextMap = paramRenderMolecule1.nextMap;
/*      */       }
/*      */       else {
/*      */         
/* 1513 */         renderMolecule = paramRenderMolecule1.nextMap;
/* 1514 */         if (paramRenderMolecule1.nextMap != null) {
/* 1515 */           renderMolecule.dirtyAttrsAcrossRms = 945;
/*      */         }
/*      */       } 
/* 1518 */       if (paramRenderMolecule1.nextMap != null) {
/* 1519 */         paramRenderMolecule1.nextMap.prevMap = paramRenderMolecule1.prevMap;
/* 1520 */         if (paramRenderMolecule1.prevMap != null) {
/* 1521 */           paramRenderMolecule1.nextMap.checkEquivalenceWithLeftNeighbor(paramRenderMolecule1.prevMap, 945);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1526 */       paramHashMap.remove(paramRenderMolecule1.localToVworld);
/*      */     } 
/*      */ 
/*      */     
/* 1530 */     paramRenderMolecule1.prev = null;
/* 1531 */     paramRenderMolecule1.next = null;
/* 1532 */     paramRenderMolecule1.prevMap = null;
/* 1533 */     paramRenderMolecule1.nextMap = null;
/* 1534 */     return renderMolecule;
/*      */   }
/*      */ 
/*      */   
/*      */   void markDlistAsDirty(RenderMolecule paramRenderMolecule) {
/* 1539 */     if (paramRenderMolecule.primaryMoleculeType == RenderMolecule.DLIST_MOLECULE) {
/* 1540 */       this.renderBin.addDirtyRenderMolecule(paramRenderMolecule);
/*      */     }
/* 1542 */     else if (paramRenderMolecule.primaryMoleculeType == RenderMolecule.SEPARATE_DLIST_PER_RINFO_MOLECULE) {
/* 1543 */       RenderAtomListInfo renderAtomListInfo = paramRenderMolecule.primaryRenderAtomList;
/* 1544 */       while (renderAtomListInfo != null) {
/* 1545 */         this.renderBin.addDlistPerRinfo.add(renderAtomListInfo);
/* 1546 */         renderAtomListInfo = renderAtomListInfo.next;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void decrActiveRenderMolecule() {
/* 1553 */     this.numEditingRenderMolecules--;
/*      */     
/* 1555 */     if (this.numEditingRenderMolecules == 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1561 */       this.shaderBin.decrActiveTextureBin();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void incrActiveRenderMolecule() {
/* 1567 */     if (this.numEditingRenderMolecules == 0)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1572 */       this.shaderBin.incrActiveTextureBin();
/*      */     }
/*      */     
/* 1575 */     this.numEditingRenderMolecules++;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\TextureBin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */