/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ShaderBin
/*     */   implements ObjectUpdate
/*     */ {
/*     */   static final int SHADER_PROGRAM_DIRTY = 1;
/*     */   static final int SHADER_ATTRIBUTE_SET_DIRTY = 2;
/*     */   RenderBin renderBin;
/*     */   AttributeBin attributeBin;
/*     */   ShaderBin next;
/*     */   ShaderBin prev;
/*     */   TextureBin textureBinList;
/*     */   ArrayList addTextureBins;
/*     */   boolean onUpdateList;
/*     */   int numEditingTextureBins;
/*     */   int componentDirty;
/*     */   ShaderAppearanceRetained shaderAppearance;
/*     */   ShaderProgramRetained shaderProgram;
/*     */   ShaderAttributeSetRetained shaderAttributeSet;
/*     */   
/*     */   ShaderBin(ShaderAppearanceRetained paramShaderAppearanceRetained, RenderBin paramRenderBin) {
/*  37 */     this.renderBin = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.attributeBin = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.next = null;
/*  49 */     this.prev = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     this.textureBinList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.addTextureBins = new ArrayList();
/*     */     
/*  61 */     this.onUpdateList = false;
/*     */     
/*  63 */     this.numEditingTextureBins = 0;
/*     */     
/*  65 */     this.componentDirty = 0;
/*  66 */     this.shaderAppearance = null;
/*  67 */     this.shaderProgram = null;
/*  68 */     this.shaderAttributeSet = new ShaderAttributeSetRetained();
/*     */ 
/*     */     
/*  71 */     reset(paramShaderAppearanceRetained, paramRenderBin);
/*     */   }
/*     */   
/*     */   void reset(ShaderAppearanceRetained paramShaderAppearanceRetained, RenderBin paramRenderBin) {
/*  75 */     this.prev = null;
/*  76 */     this.next = null;
/*  77 */     this.renderBin = paramRenderBin;
/*  78 */     this.attributeBin = null;
/*  79 */     this.textureBinList = null;
/*  80 */     this.onUpdateList = false;
/*  81 */     this.numEditingTextureBins = 0;
/*  82 */     this.addTextureBins.clear();
/*  83 */     if (paramShaderAppearanceRetained != null) {
/*  84 */       this.shaderProgram = paramShaderAppearanceRetained.shaderProgram;
/*  85 */       this.shaderAttributeSet = paramShaderAppearanceRetained.shaderAttributeSet;
/*     */     } else {
/*     */       
/*  88 */       this.shaderProgram = null;
/*  89 */       this.shaderAttributeSet = null;
/*     */     } 
/*  91 */     this.shaderAppearance = paramShaderAppearanceRetained;
/*     */   }
/*     */ 
/*     */   
/*  95 */   void clear() { reset(null, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean equals(ShaderAppearanceRetained paramShaderAppearanceRetained) {
/*     */     ShaderAttributeSetRetained shaderAttributeSetRetained;
/*     */     ShaderProgramRetained shaderProgramRetained;
/* 106 */     if (paramShaderAppearanceRetained == null) {
/* 107 */       shaderProgramRetained = null;
/* 108 */       shaderAttributeSetRetained = null;
/*     */     } else {
/* 110 */       shaderProgramRetained = paramShaderAppearanceRetained.shaderProgram;
/* 111 */       shaderAttributeSetRetained = paramShaderAppearanceRetained.shaderAttributeSet;
/*     */     } 
/*     */     
/* 114 */     if (this.shaderProgram != shaderProgramRetained || this.shaderAttributeSet != shaderAttributeSetRetained) {
/* 115 */       return false;
/*     */     }
/*     */     
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObject() {
/* 126 */     if (this.addTextureBins.size() > 0) {
/* 127 */       TextureBin textureBin = (TextureBin)this.addTextureBins.get(0);
/* 128 */       if (this.textureBinList == null) {
/* 129 */         this.textureBinList = textureBin;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 134 */         insertTextureBin(textureBin);
/*     */       } 
/* 136 */       for (byte b = 1; b < this.addTextureBins.size(); b++) {
/* 137 */         textureBin = (TextureBin)this.addTextureBins.get(b);
/*     */         
/* 139 */         insertTextureBin(textureBin);
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     this.addTextureBins.clear();
/* 144 */     this.onUpdateList = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void insertTextureBin(TextureBin paramTextureBin) {
/* 151 */     TextureRetained textureRetained = null;
/*     */     
/* 153 */     if (paramTextureBin.texUnitState != null && paramTextureBin.texUnitState.length > 0 && 
/* 154 */       paramTextureBin.texUnitState[false] != null) {
/* 155 */       textureRetained = (paramTextureBin.texUnitState[0]).texture;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (textureRetained != null) {
/* 161 */       TextureBin textureBin = this.textureBinList;
/* 162 */       while (textureBin != null) {
/* 163 */         if (textureBin.texUnitState == null || textureBin.texUnitState[false] == null || (textureBin.texUnitState[false]).texture != textureRetained) {
/*     */           
/* 165 */           textureBin = textureBin.next;
/*     */           continue;
/*     */         } 
/* 168 */         paramTextureBin.next = textureBin;
/* 169 */         paramTextureBin.prev = textureBin.prev;
/* 170 */         if (textureBin.prev == null) {
/* 171 */           this.textureBinList = paramTextureBin;
/*     */         } else {
/*     */           
/* 174 */           textureBin.prev.next = paramTextureBin;
/*     */         } 
/* 176 */         textureBin.prev = paramTextureBin;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     paramTextureBin.prev = null;
/* 183 */     paramTextureBin.next = this.textureBinList;
/* 184 */     this.textureBinList.prev = paramTextureBin;
/* 185 */     this.textureBinList = paramTextureBin;
/*     */     
/* 187 */     paramTextureBin.tbFlag &= 0xFFFFFFEF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void reInsertTextureBin(TextureBin paramTextureBin) {
/* 197 */     TextureRetained textureRetained1 = null;
/* 198 */     TextureRetained textureRetained2 = null;
/* 199 */     TextureRetained textureRetained3 = null;
/*     */     
/* 201 */     if (paramTextureBin.texUnitState != null && paramTextureBin.texUnitState[false] != null) {
/* 202 */       textureRetained1 = (paramTextureBin.texUnitState[0]).texture;
/*     */     }
/*     */     
/* 205 */     if (paramTextureBin.prev != null && paramTextureBin.prev.texUnitState != null) {
/* 206 */       textureRetained2 = (paramTextureBin.prev.texUnitState[0]).texture;
/*     */     }
/*     */     
/* 209 */     if (textureRetained1 != textureRetained2) {
/* 210 */       if (paramTextureBin.next != null && paramTextureBin.next.texUnitState != null) {
/* 211 */         textureRetained3 = (paramTextureBin.next.texUnitState[0]).texture;
/*     */       }
/* 213 */       if (textureRetained1 != textureRetained3 && 
/* 214 */         paramTextureBin.prev != null && paramTextureBin.next != null) {
/* 215 */         paramTextureBin.prev.next = paramTextureBin.next;
/* 216 */         paramTextureBin.next.prev = paramTextureBin.prev;
/* 217 */         insertTextureBin(paramTextureBin);
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
/*     */   
/*     */   void addTextureBin(TextureBin paramTextureBin, RenderBin paramRenderBin, RenderAtom paramRenderAtom) {
/* 230 */     paramTextureBin.environmentSet = this.attributeBin.environmentSet;
/* 231 */     paramTextureBin.attributeBin = this.attributeBin;
/* 232 */     paramTextureBin.shaderBin = this;
/*     */     
/* 234 */     this.attributeBin.updateFromShaderBin(paramRenderAtom);
/* 235 */     this.addTextureBins.add(paramTextureBin);
/*     */     
/* 237 */     if (!this.onUpdateList) {
/* 238 */       paramRenderBin.objUpdateList.add(this);
/* 239 */       this.onUpdateList = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeTextureBin(TextureBin paramTextureBin) {
/* 250 */     if (this.addTextureBins.contains(paramTextureBin)) {
/* 251 */       this.addTextureBins.remove(this.addTextureBins.indexOf(paramTextureBin));
/*     */     
/*     */     }
/* 254 */     else if (paramTextureBin.prev == null) {
/* 255 */       this.textureBinList = paramTextureBin.next;
/* 256 */       if (paramTextureBin.next != null) {
/* 257 */         paramTextureBin.next.prev = null;
/*     */       }
/*     */     } else {
/* 260 */       paramTextureBin.prev.next = paramTextureBin.next;
/* 261 */       if (paramTextureBin.next != null) {
/* 262 */         paramTextureBin.next.prev = paramTextureBin.prev;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 267 */     paramTextureBin.shaderBin = null;
/* 268 */     paramTextureBin.prev = null;
/* 269 */     paramTextureBin.next = null;
/*     */     
/* 271 */     paramTextureBin.clear();
/*     */     
/* 273 */     if (this.textureBinList == null && this.addTextureBins.size() == 0)
/*     */     {
/*     */       
/* 276 */       this.attributeBin.removeShaderBin(this);
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
/*     */   void render(Canvas3D paramCanvas3D) {
/* 290 */     paramCanvas3D.setStateToUpdate(6, this);
/*     */     
/* 292 */     TextureBin textureBin = this.textureBinList;
/* 293 */     while (textureBin != null) {
/* 294 */       textureBin.render(paramCanvas3D);
/* 295 */       textureBin = textureBin.next;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateAttributes(Canvas3D paramCanvas3D) {
/* 302 */     if (this.shaderProgram != null) {
/*     */       
/* 304 */       this.shaderProgram.updateNative(paramCanvas3D, true);
/*     */       
/* 306 */       if (this.shaderAttributeSet != null) {
/* 307 */         this.shaderAttributeSet.updateNative(paramCanvas3D, this.shaderProgram);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 312 */     else if (paramCanvas3D.shaderProgram != null) {
/*     */       
/* 314 */       paramCanvas3D.shaderProgram.updateNative(paramCanvas3D, false);
/*     */     } 
/*     */ 
/*     */     
/* 318 */     paramCanvas3D.shaderBin = this;
/* 319 */     paramCanvas3D.shaderProgram = this.shaderProgram;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateNodeComponent() {
/* 328 */     if ((this.componentDirty & true) != 0)
/*     */     {
/*     */       
/* 331 */       this.shaderProgram = this.shaderAppearance.shaderProgram;
/*     */     }
/*     */ 
/*     */     
/* 335 */     if ((this.componentDirty & 0x2) != 0) {
/*     */ 
/*     */       
/* 338 */       HashMap hashMap = (HashMap)this.shaderAttributeSet.getAttrs();
/* 339 */       hashMap.clear();
/* 340 */       if (this.shaderAppearance.shaderAttributeSet != null) {
/* 341 */         hashMap.putAll(this.shaderAppearance.shaderAttributeSet.getAttrs());
/*     */       }
/*     */     } 
/*     */     
/* 345 */     this.componentDirty = 0;
/*     */   }
/*     */ 
/*     */   
/* 349 */   void incrActiveTextureBin() { this.numEditingTextureBins++; }
/*     */ 
/*     */ 
/*     */   
/* 353 */   void decrActiveTextureBin() { this.numEditingTextureBins--; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ShaderBin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */