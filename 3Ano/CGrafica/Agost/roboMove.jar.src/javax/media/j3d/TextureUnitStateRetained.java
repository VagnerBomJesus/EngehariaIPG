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
/*     */ class TextureUnitStateRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int TEXTURE_CHANGED = 1;
/*     */   static final int TEXTURE_ATTRS_CHANGED = 2;
/*     */   static final int TEXCOORD_GEN_CHANGED = 4;
/*     */   static final int ALL_STATE_CHANGED = 8;
/*  25 */   TextureRetained texture = null;
/*  26 */   TextureAttributesRetained texAttrs = null;
/*  27 */   TexCoordGenerationRetained texGen = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setTextureUnitStateComponent(NodeComponent paramNodeComponent, NodeComponentRetained paramNodeComponentRetained, int paramInt) {
/*  35 */     if (this.source.isLive()) {
/*     */       
/*  37 */       if ((paramNodeComponent == null && paramNodeComponentRetained == null) || (paramNodeComponent != null && paramNodeComponent.retained == paramNodeComponentRetained)) {
/*     */         return;
/*     */       }
/*     */       
/*  41 */       if (paramNodeComponentRetained != null) {
/*  42 */         paramNodeComponentRetained.clearLive(this.refCount);
/*  43 */         paramNodeComponentRetained.removeMirrorUsers(this);
/*     */       } 
/*     */       
/*  46 */       if (paramNodeComponent != null) {
/*  47 */         ((NodeComponentRetained)paramNodeComponent.retained).setLive(this.inBackgroundGroup, this.refCount);
/*     */ 
/*     */         
/*  50 */         ((NodeComponentRetained)paramNodeComponent.retained).copyMirrorUsers(this);
/*     */       } 
/*     */       
/*  53 */       if (paramInt != -1) {
/*  54 */         sendMessage(paramInt, (paramNodeComponent == null) ? null : ((NodeComponentRetained)paramNodeComponent.retained).mirror);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initTextureUnitState(Texture paramTexture, TextureAttributes paramTextureAttributes, TexCoordGeneration paramTexCoordGeneration) {
/*  66 */     initTexture(paramTexture);
/*  67 */     initTextureAttributes(paramTextureAttributes);
/*  68 */     initTexCoordGeneration(paramTexCoordGeneration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setTextureUnitState(Texture paramTexture, TextureAttributes paramTextureAttributes, TexCoordGeneration paramTexCoordGeneration) {
/*  75 */     setTextureUnitStateComponent(paramTexture, this.texture, -1);
/*  76 */     setTextureUnitStateComponent(paramTextureAttributes, this.texAttrs, -1);
/*  77 */     setTextureUnitStateComponent(paramTexCoordGeneration, this.texGen, -1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     Object[] arrayOfObject = new Object[3];
/*  84 */     arrayOfObject[0] = (paramTexture == null) ? null : ((TextureRetained)paramTexture.retained).mirror;
/*     */     
/*  86 */     arrayOfObject[1] = (paramTextureAttributes == null) ? null : ((TextureAttributesRetained)paramTextureAttributes.retained).mirror;
/*     */     
/*  88 */     arrayOfObject[2] = (paramTexCoordGeneration == null) ? null : ((TexCoordGenerationRetained)paramTexCoordGeneration.retained).mirror;
/*     */ 
/*     */     
/*  91 */     sendMessage(8, arrayOfObject);
/*     */     
/*  93 */     initTextureUnitState(paramTexture, paramTextureAttributes, paramTexCoordGeneration);
/*     */   }
/*     */   
/*     */   final void initTexture(Texture paramTexture) {
/*  97 */     if (paramTexture == null) {
/*  98 */       this.texture = null;
/*     */     } else {
/* 100 */       this.texture = (TextureRetained)paramTexture.retained;
/*     */     } 
/*     */   }
/*     */   final void setTexture(Texture paramTexture) {
/* 104 */     setTextureUnitStateComponent(paramTexture, this.texture, 1);
/* 105 */     initTexture(paramTexture);
/*     */   }
/*     */   
/*     */   final void initTextureAttributes(TextureAttributes paramTextureAttributes) {
/* 109 */     if (paramTextureAttributes == null) {
/* 110 */       this.texAttrs = null;
/*     */     } else {
/* 112 */       this.texAttrs = (TextureAttributesRetained)paramTextureAttributes.retained;
/*     */     } 
/*     */   }
/*     */   final void setTextureAttributes(TextureAttributes paramTextureAttributes) {
/* 116 */     setTextureUnitStateComponent(paramTextureAttributes, this.texAttrs, 2);
/*     */     
/* 118 */     initTextureAttributes(paramTextureAttributes);
/*     */   }
/*     */   
/*     */   final void initTexCoordGeneration(TexCoordGeneration paramTexCoordGeneration) {
/* 122 */     if (paramTexCoordGeneration == null) {
/* 123 */       this.texGen = null;
/*     */     } else {
/* 125 */       this.texGen = (TexCoordGenerationRetained)paramTexCoordGeneration.retained;
/*     */     } 
/*     */   }
/*     */   final void setTexCoordGeneration(TexCoordGeneration paramTexCoordGeneration) {
/* 129 */     setTextureUnitStateComponent(paramTexCoordGeneration, this.texGen, 4);
/* 130 */     initTexCoordGeneration(paramTexCoordGeneration);
/*     */   }
/*     */ 
/*     */   
/* 134 */   Texture getTexture() { return (this.texture == null) ? null : (Texture)this.texture.source; }
/*     */ 
/*     */ 
/*     */   
/* 138 */   TextureAttributes getTextureAttributes() { return (this.texAttrs == null) ? null : (TextureAttributes)this.texAttrs.source; }
/*     */ 
/*     */ 
/*     */   
/* 142 */   TexCoordGeneration getTexCoordGeneration() { return (this.texGen == null) ? null : (TexCoordGeneration)this.texGen.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateNative(int paramInt, Canvas3D paramCanvas3D, boolean paramBoolean1, boolean paramBoolean2) {
/* 154 */     int i = paramInt;
/*     */     
/* 156 */     if (i < 0) {
/* 157 */       i = 0;
/*     */     }
/*     */     
/* 160 */     boolean bool = ((paramCanvas3D.canvasDirty & 0xC00) != 0) ? 1 : 0;
/*     */     
/* 162 */     if (this.texture == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       if ((paramCanvas3D.texUnitState[i]).texture != null) {
/* 170 */         paramCanvas3D.resetTexture(paramCanvas3D.ctx, paramInt);
/* 171 */         (paramCanvas3D.texUnitState[i]).texture = null;
/*     */       } 
/* 173 */       paramCanvas3D.canvasDirty &= 0xFFFFF7FF;
/*     */       
/*     */       return;
/*     */     } 
/* 177 */     Pipeline.getPipeline().updateTextureUnitState(paramCanvas3D.ctx, paramInt, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (paramBoolean1 || bool || (paramCanvas3D.texUnitState[i]).texture != this.texture) {
/*     */ 
/*     */ 
/*     */       
/* 187 */       this.texture.updateNative(paramCanvas3D);
/*     */       
/* 189 */       (paramCanvas3D.texUnitState[i]).texture = this.texture;
/*     */     } 
/*     */ 
/*     */     
/* 193 */     if (this.texAttrs == null) {
/* 194 */       if (paramBoolean1 || bool || (paramCanvas3D.texUnitState[i]).texAttrs != null) {
/* 195 */         paramCanvas3D.resetTextureAttributes(paramCanvas3D.ctx);
/* 196 */         if (VirtualUniverse.mc.isD3D() && this.texGen != null && (this.texGen.genMode == 1 || this.texGen.genMode == 2))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 203 */           bool = true;
/*     */         }
/* 205 */         paramCanvas3D.setBlendFunc(paramCanvas3D.ctx, 1, 0);
/*     */         
/* 207 */         (paramCanvas3D.texUnitState[i]).texAttrs = null;
/*     */       } 
/*     */     } else {
/*     */       TextureAttributesRetained textureAttributesRetained;
/*     */       
/* 212 */       if (this.texAttrs.mirror == null) {
/* 213 */         textureAttributesRetained = this.texAttrs;
/*     */       } else {
/* 215 */         textureAttributesRetained = (TextureAttributesRetained)this.texAttrs.mirror;
/*     */       } 
/*     */ 
/*     */       
/* 219 */       if (textureAttributesRetained.mirrorCompDirty) {
/*     */ 
/*     */ 
/*     */         
/* 223 */         (paramCanvas3D.texUnitState[i]).texAttrs = null;
/* 224 */         textureAttributesRetained.mirrorCompDirty = false;
/*     */       } 
/*     */       
/* 227 */       if (paramBoolean1 || bool || (paramCanvas3D.texUnitState[i]).texAttrs != textureAttributesRetained) {
/* 228 */         this.texAttrs.updateNative(paramCanvas3D, paramBoolean2, this.texture.format);
/* 229 */         if (VirtualUniverse.mc.isD3D() && this.texGen != null && (this.texGen.genMode == 1 || this.texGen.genMode == 2))
/*     */         {
/*     */ 
/*     */           
/* 233 */           bool = true;
/*     */         }
/* 235 */         (paramCanvas3D.texUnitState[i]).texAttrs = textureAttributesRetained;
/*     */       } 
/*     */     } 
/*     */     
/* 239 */     if (this.texGen == null) {
/* 240 */       if (paramBoolean1 || bool || (paramCanvas3D.texUnitState[i]).texGen != null) {
/* 241 */         paramCanvas3D.resetTexCoordGeneration(paramCanvas3D.ctx);
/* 242 */         (paramCanvas3D.texUnitState[i]).texGen = null;
/*     */       } 
/*     */     } else {
/*     */       TexCoordGenerationRetained texCoordGenerationRetained;
/*     */       
/* 247 */       if (this.texGen.mirror == null) {
/* 248 */         texCoordGenerationRetained = this.texGen;
/*     */       } else {
/* 250 */         texCoordGenerationRetained = (TexCoordGenerationRetained)this.texGen.mirror;
/*     */       } 
/*     */       
/* 253 */       if (texCoordGenerationRetained.mirrorCompDirty) {
/*     */ 
/*     */ 
/*     */         
/* 257 */         (paramCanvas3D.texUnitState[i]).texGen = null;
/* 258 */         texCoordGenerationRetained.mirrorCompDirty = false;
/*     */       } 
/*     */       
/* 261 */       if (paramBoolean1 || bool || (paramCanvas3D.texUnitState[i]).texGen != texCoordGenerationRetained) {
/* 262 */         this.texGen.updateNative(paramCanvas3D);
/* 263 */         (paramCanvas3D.texUnitState[i]).texGen = texCoordGenerationRetained;
/*     */       } 
/*     */     } 
/* 266 */     paramCanvas3D.canvasDirty &= 0xFFFFF7FF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 276 */     if (this.mirror == null) {
/* 277 */       TextureUnitStateRetained textureUnitStateRetained = new TextureUnitStateRetained();
/*     */       
/* 279 */       this.mirror = textureUnitStateRetained;
/*     */     } 
/* 281 */     this.mirror.source = this.source;
/* 282 */     initMirrorObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void initMirrorObject() {
/* 288 */     TextureUnitStateRetained textureUnitStateRetained = (TextureUnitStateRetained)this.mirror;
/*     */ 
/*     */     
/* 291 */     if (this.texture != null) {
/* 292 */       textureUnitStateRetained.texture = (TextureRetained)this.texture.mirror;
/*     */     } else {
/* 294 */       textureUnitStateRetained.texture = null;
/*     */     } 
/* 296 */     if (this.texAttrs != null) {
/* 297 */       textureUnitStateRetained.texAttrs = (TextureAttributesRetained)this.texAttrs.mirror;
/*     */     } else {
/*     */       
/* 300 */       textureUnitStateRetained.texAttrs = null;
/*     */     } 
/* 302 */     if (this.texGen != null) {
/* 303 */       textureUnitStateRetained.texGen = (TexCoordGenerationRetained)this.texGen.mirror;
/*     */     } else {
/* 305 */       textureUnitStateRetained.texGen = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 314 */     TextureUnitStateRetained textureUnitStateRetained = (TextureUnitStateRetained)this.mirror;
/*     */     
/* 316 */     if ((paramInt & true) != 0) {
/* 317 */       textureUnitStateRetained.texture = (TextureRetained)paramObject;
/*     */     }
/* 319 */     else if ((paramInt & 0x2) != 0) {
/* 320 */       textureUnitStateRetained.texAttrs = (TextureAttributesRetained)paramObject;
/*     */     }
/* 322 */     else if ((paramInt & 0x4) != 0) {
/* 323 */       textureUnitStateRetained.texGen = (TexCoordGenerationRetained)paramObject;
/*     */     }
/* 325 */     else if ((paramInt & 0x8) != 0) {
/* 326 */       Object[] arrayOfObject = (Object[])paramObject;
/* 327 */       textureUnitStateRetained.texture = (TextureRetained)arrayOfObject[0];
/* 328 */       textureUnitStateRetained.texAttrs = (TextureAttributesRetained)arrayOfObject[1];
/* 329 */       textureUnitStateRetained.texGen = (TexCoordGenerationRetained)arrayOfObject[2];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean equivalent(TextureUnitStateRetained paramTextureUnitStateRetained) {
/* 336 */     if (paramTextureUnitStateRetained == null) {
/* 337 */       return false;
/*     */     }
/* 339 */     if (this.changedFrequent != 0 || paramTextureUnitStateRetained.changedFrequent != 0) {
/* 340 */       return (this.mirror == paramTextureUnitStateRetained);
/*     */     }
/*     */ 
/*     */     
/* 344 */     if (this.texture != paramTextureUnitStateRetained.texture) {
/* 345 */       return false;
/*     */     }
/*     */     
/* 348 */     if (this.texAttrs != null && !this.texAttrs.equivalent(paramTextureUnitStateRetained.texAttrs))
/*     */     {
/* 350 */       return false;
/*     */     }
/*     */     
/* 353 */     if (this.texGen != null && !this.texGen.equivalent(paramTextureUnitStateRetained.texGen))
/*     */     {
/* 355 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 359 */     return true;
/*     */   }
/*     */   
/*     */   protected Object clone() {
/* 363 */     TextureUnitStateRetained textureUnitStateRetained = (TextureUnitStateRetained)super.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 370 */     if (this.texAttrs != null) {
/* 371 */       textureUnitStateRetained.texAttrs = (TextureAttributesRetained)this.texAttrs.clone();
/*     */     }
/* 373 */     if (this.texGen != null) {
/* 374 */       textureUnitStateRetained.texGen = (TexCoordGenerationRetained)this.texGen.clone();
/*     */     }
/* 376 */     return textureUnitStateRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(TextureUnitStateRetained paramTextureUnitStateRetained) {
/* 385 */     set(paramTextureUnitStateRetained);
/* 386 */     this.texture = paramTextureUnitStateRetained.texture;
/*     */     
/* 388 */     if (paramTextureUnitStateRetained.texAttrs == null) {
/* 389 */       this.texAttrs = null;
/*     */     }
/* 391 */     else if (this.texAttrs == null) {
/* 392 */       this.texAttrs = (TextureAttributesRetained)paramTextureUnitStateRetained.texAttrs.clone();
/*     */     } else {
/* 394 */       this.texAttrs.set(paramTextureUnitStateRetained.texAttrs);
/*     */     } 
/*     */ 
/*     */     
/* 398 */     if (paramTextureUnitStateRetained.texGen == null) {
/* 399 */       this.texGen = null;
/*     */     }
/* 401 */     else if (this.texGen == null) {
/* 402 */       this.texGen = (TexCoordGenerationRetained)paramTextureUnitStateRetained.texGen.clone();
/*     */     } else {
/* 404 */       this.texGen.set(paramTextureUnitStateRetained.texGen);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(TextureRetained paramTextureRetained, TextureAttributesRetained paramTextureAttributesRetained, TexCoordGenerationRetained paramTexCoordGenerationRetained) {
/* 412 */     this.texture = paramTextureRetained;
/* 413 */     this.texAttrs = paramTextureAttributesRetained;
/* 414 */     this.texGen = paramTexCoordGenerationRetained;
/*     */   }
/*     */ 
/*     */   
/*     */   void addAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 419 */     super.addAMirrorUser(paramShape3DRetained);
/*     */     
/* 421 */     if (this.texture != null)
/* 422 */       this.texture.addAMirrorUser(paramShape3DRetained); 
/* 423 */     if (this.texAttrs != null)
/* 424 */       this.texAttrs.addAMirrorUser(paramShape3DRetained); 
/* 425 */     if (this.texGen != null)
/* 426 */       this.texGen.addAMirrorUser(paramShape3DRetained); 
/*     */   }
/*     */   
/*     */   void removeAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 430 */     super.removeAMirrorUser(paramShape3DRetained);
/*     */     
/* 432 */     if (this.texture != null)
/* 433 */       this.texture.removeAMirrorUser(paramShape3DRetained); 
/* 434 */     if (this.texAttrs != null)
/* 435 */       this.texAttrs.removeAMirrorUser(paramShape3DRetained); 
/* 436 */     if (this.texGen != null)
/* 437 */       this.texGen.removeAMirrorUser(paramShape3DRetained); 
/*     */   }
/*     */   
/*     */   void removeMirrorUsers(NodeComponentRetained paramNodeComponentRetained) {
/* 441 */     super.removeMirrorUsers(paramNodeComponentRetained);
/*     */     
/* 443 */     if (this.texture != null)
/* 444 */       this.texture.removeMirrorUsers(paramNodeComponentRetained); 
/* 445 */     if (this.texAttrs != null)
/* 446 */       this.texAttrs.removeMirrorUsers(paramNodeComponentRetained); 
/* 447 */     if (this.texGen != null)
/* 448 */       this.texGen.removeMirrorUsers(paramNodeComponentRetained); 
/*     */   }
/*     */   
/*     */   void copyMirrorUsers(NodeComponentRetained paramNodeComponentRetained) {
/* 452 */     super.copyMirrorUsers(paramNodeComponentRetained);
/*     */     
/* 454 */     if (this.texture != null)
/* 455 */       this.texture.copyMirrorUsers(paramNodeComponentRetained); 
/* 456 */     if (this.texAttrs != null)
/* 457 */       this.texAttrs.copyMirrorUsers(paramNodeComponentRetained); 
/* 458 */     if (this.texGen != null) {
/* 459 */       this.texGen.copyMirrorUsers(paramNodeComponentRetained);
/*     */     }
/*     */   }
/*     */   
/*     */   void setLive(boolean paramBoolean, int paramInt) {
/* 464 */     if (this.texture != null) {
/* 465 */       this.texture.setLive(paramBoolean, paramInt);
/*     */     }
/* 467 */     if (this.texAttrs != null) {
/* 468 */       this.texAttrs.setLive(paramBoolean, paramInt);
/*     */     }
/* 470 */     if (this.texGen != null) {
/* 471 */       this.texGen.setLive(paramBoolean, paramInt);
/*     */     }
/*     */ 
/*     */     
/* 475 */     doSetLive(paramBoolean, paramInt);
/* 476 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(int paramInt) {
/* 482 */     super.clearLive(paramInt);
/*     */     
/* 484 */     if (this.texture != null)
/* 485 */       this.texture.clearLive(paramInt); 
/* 486 */     if (this.texAttrs != null)
/* 487 */       this.texAttrs.clearLive(paramInt); 
/* 488 */     if (this.texGen != null) {
/* 489 */       this.texGen.clearLive(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 494 */   boolean isStatic() { return (this.source.capabilityBitsEmpty() && (this.texture == null || this.texture.isStatic()) && (this.texAttrs == null || this.texAttrs.isStatic()) && (this.texGen == null || this.texGen.isStatic())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void compile(CompileState paramCompileState) {
/* 503 */     setCompiled();
/*     */     
/* 505 */     if (this.texture != null)
/* 506 */       this.texture.compile(paramCompileState); 
/* 507 */     if (this.texAttrs != null)
/* 508 */       this.texAttrs.compile(paramCompileState); 
/* 509 */     if (this.texGen != null) {
/* 510 */       this.texGen.compile(paramCompileState);
/*     */     }
/*     */   }
/*     */   
/* 514 */   boolean equals(TextureUnitStateRetained paramTextureUnitStateRetained) { return (paramTextureUnitStateRetained == this || (paramTextureUnitStateRetained != null && (this.texture == paramTextureUnitStateRetained.texture || (this.texture != null && this.texture.equals(paramTextureUnitStateRetained.texture))) && (this.texAttrs == paramTextureUnitStateRetained.texAttrs || (this.texAttrs != null && this.texAttrs.equals(paramTextureUnitStateRetained.texAttrs))) && (this.texGen == paramTextureUnitStateRetained.texGen || (this.texGen != null && this.texGen.equals(paramTextureUnitStateRetained.texGen))))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInImmCtx(boolean paramBoolean) {
/* 526 */     super.setInImmCtx(paramBoolean);
/* 527 */     if (this.texture != null)
/* 528 */       this.texture.setInImmCtx(paramBoolean); 
/* 529 */     if (this.texAttrs != null)
/* 530 */       this.texAttrs.setInImmCtx(paramBoolean); 
/* 531 */     if (this.texGen != null) {
/* 532 */       this.texGen.setInImmCtx(paramBoolean);
/*     */     }
/*     */   }
/*     */   
/* 536 */   boolean getInImmCtx() { return (super.getInImmCtx() || (this.texture != null && this.texture.getInImmCtx()) || (this.texAttrs != null && this.texAttrs.getInImmCtx()) || (this.texGen != null && this.texGen.getInImmCtx())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 544 */   boolean isLive() { return (this.source.isLive() || (this.texture != null && this.texture.source.isLive()) || (this.texAttrs != null && this.texAttrs.source.isLive()) || (this.texGen != null && this.texGen.source.isLive())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 551 */     ArrayList arrayList1 = new ArrayList();
/* 552 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 556 */     J3dMessage j3dMessage = new J3dMessage();
/* 557 */     j3dMessage.threads = 1024;
/* 558 */     j3dMessage.type = 47;
/* 559 */     j3dMessage.universe = null;
/* 560 */     j3dMessage.args[0] = this;
/* 561 */     j3dMessage.args[1] = new Integer(paramInt);
/* 562 */     j3dMessage.args[2] = paramObject;
/* 563 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 564 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */     
/* 567 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 568 */       j3dMessage = new J3dMessage();
/* 569 */       j3dMessage.threads = 128;
/* 570 */       j3dMessage.type = 47;
/*     */       
/* 572 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 573 */       j3dMessage.args[0] = this;
/* 574 */       j3dMessage.args[1] = new Integer(paramInt);
/* 575 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 577 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 578 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 579 */       arrayList.toArray(arrayOfGeometryAtom);
/* 580 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 582 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 589 */   boolean isTextureEnabled() { return (this.texture != null && this.texture.enable); }
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 593 */     switch (paramInt) {
/*     */       case 1:
/* 595 */         setFrequencyChangeMask(paramInt, paramInt);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TextureUnitStateRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */