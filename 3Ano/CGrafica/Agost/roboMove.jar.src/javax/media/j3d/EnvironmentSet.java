/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class EnvironmentSet
/*     */   implements ObjectUpdate
/*     */ {
/*     */   static final int LIGHTENABLE_CHANGED = 1;
/*     */   static final int AMBIENT_CHANGED = 2;
/*     */   static final int FOG_CHANGED = 4;
/*     */   static final int MODELCLIP_CHANGED = 8;
/*     */   ArrayList lights;
/*     */   int[] ltPos;
/*     */   ArrayList ambLights;
/*     */   LightBin lightBin;
/*     */   long enableMask;
/*     */   Color3f sceneAmbient;
/*     */   RenderBin renderBin;
/*     */   FogRetained fog;
/*     */   ModelClipRetained modelClip;
/*     */   int enableMCMask;
/*     */   int enableMCMaskCache;
/*     */   EnvironmentSet next;
/*     */   EnvironmentSet prev;
/*     */   ArrayList addAttributeBins;
/*     */   int canvasDirty;
/*     */   long enableMaskCache;
/*     */   boolean onUpdateList;
/*     */   AttributeBin attributeBinList;
/*     */   
/*     */   EnvironmentSet(RenderAtom paramRenderAtom, LightRetained[] paramArrayOfLightRetained, FogRetained paramFogRetained, ModelClipRetained paramModelClipRetained, RenderBin paramRenderBin) {
/*  36 */     this.lights = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.ltPos = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.ambLights = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.lightBin = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.enableMask = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     this.sceneAmbient = new Color3f();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     this.renderBin = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.fog = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     this.modelClip = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     this.enableMCMask = 0;
/*  85 */     this.enableMCMaskCache = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     this.next = null;
/*  93 */     this.prev = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.addAttributeBins = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     this.canvasDirty = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     this.enableMaskCache = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.onUpdateList = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     this.attributeBinList = null;
/*     */ 
/*     */ 
/*     */     
/* 123 */     this.renderBin = paramRenderBin;
/* 124 */     reset(paramRenderAtom, paramArrayOfLightRetained, paramFogRetained, paramModelClipRetained);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void reset(RenderAtom paramRenderAtom, LightRetained[] paramArrayOfLightRetained, FogRetained paramFogRetained, ModelClipRetained paramModelClipRetained) {
/* 132 */     this.prev = null;
/* 133 */     this.next = null;
/* 134 */     this.onUpdateList = false;
/* 135 */     this.attributeBinList = null;
/* 136 */     this.lights.clear();
/* 137 */     this.ambLights.clear();
/* 138 */     this.sceneAmbient.x = 0.0F;
/* 139 */     this.sceneAmbient.y = 0.0F;
/* 140 */     this.sceneAmbient.z = 0.0F;
/* 141 */     if (paramArrayOfLightRetained != null) {
/* 142 */       for (byte b = 0; b < paramArrayOfLightRetained.length; b++) {
/* 143 */         LightRetained lightRetained = paramArrayOfLightRetained[b];
/* 144 */         if (lightRetained.nodeType == 5) {
/* 145 */           this.ambLights.add(lightRetained);
/* 146 */           this.sceneAmbient.x += lightRetained.color.x;
/* 147 */           this.sceneAmbient.y += lightRetained.color.y;
/* 148 */           this.sceneAmbient.z += lightRetained.color.z;
/*     */         } else {
/*     */           
/* 151 */           this.lights.add(lightRetained);
/*     */         } 
/*     */       } 
/* 154 */       if (this.sceneAmbient.x > 1.0F) {
/* 155 */         this.sceneAmbient.x = 1.0F;
/*     */       }
/* 157 */       if (this.sceneAmbient.y > 1.0F) {
/* 158 */         this.sceneAmbient.y = 1.0F;
/*     */       }
/* 160 */       if (this.sceneAmbient.z > 1.0F) {
/* 161 */         this.sceneAmbient.z = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/* 165 */     this.fog = paramFogRetained;
/*     */     
/* 167 */     this.modelClip = paramModelClipRetained;
/* 168 */     this.enableMCMaskCache = 0;
/* 169 */     if (paramModelClipRetained != null) {
/* 170 */       for (byte b = 0; b < 6; b++) {
/* 171 */         if (paramModelClipRetained.enables[b])
/* 172 */           this.enableMCMaskCache |= true << b; 
/*     */       } 
/* 174 */       this.enableMCMask = this.enableMCMaskCache;
/*     */     } 
/*     */ 
/*     */     
/* 178 */     this.ltPos = new int[this.lights.size()];
/* 179 */     this.enableMask = 0L;
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (paramArrayOfLightRetained != null) {
/* 184 */       for (byte b = 0; b < paramArrayOfLightRetained.length; b++) {
/* 185 */         (paramArrayOfLightRetained[b]).environmentSets.add(this);
/*     */       }
/*     */     }
/*     */     
/* 189 */     if (paramFogRetained != null) {
/* 190 */       paramFogRetained.environmentSets.add(this);
/*     */     }
/*     */     
/* 193 */     if (paramModelClipRetained != null) {
/* 194 */       paramModelClipRetained.environmentSets.add(this);
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
/*     */   boolean equals(RenderAtom paramRenderAtom, LightRetained[] paramArrayOfLightRetained, FogRetained paramFogRetained, ModelClipRetained paramModelClipRetained) {
/* 207 */     if (paramArrayOfLightRetained == null && this.ambLights == null) {
/* 208 */       if (this.lights.size() == 0) {
/* 209 */         if (this.fog == paramFogRetained) {
/* 210 */           return true;
/*     */         }
/* 212 */         return false;
/*     */       } 
/*     */       
/* 215 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 219 */     if (this.lights.size() + this.ambLights.size() != paramArrayOfLightRetained.length) {
/* 220 */       return false;
/*     */     }
/*     */     
/* 223 */     for (byte b = 0; b < paramArrayOfLightRetained.length; b++) {
/* 224 */       if ((paramArrayOfLightRetained[b]).nodeType == 5) {
/* 225 */         if (!this.ambLights.contains(paramArrayOfLightRetained[b])) {
/* 226 */           return false;
/*     */         
/*     */         }
/*     */       }
/* 230 */       else if (!this.lights.contains(paramArrayOfLightRetained[b])) {
/* 231 */         return false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (this.fog != paramFogRetained) {
/* 238 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 242 */     if (this.modelClip != paramModelClipRetained) {
/* 243 */       return false;
/*     */     }
/*     */     
/* 246 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean equalLights(LightRetained[] paramArrayOfLightRetained) {
/* 257 */     if (paramArrayOfLightRetained == null && this.ambLights == null && 
/* 258 */       this.lights.size() == 0) {
/* 259 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 263 */     if (this.lights.size() + this.ambLights.size() != paramArrayOfLightRetained.length) {
/* 264 */       return false;
/*     */     }
/*     */     
/* 267 */     for (byte b = 0; b < paramArrayOfLightRetained.length; b++) {
/* 268 */       if ((paramArrayOfLightRetained[b]).nodeType == 5) {
/* 269 */         if (!this.ambLights.contains(paramArrayOfLightRetained[b])) {
/* 270 */           return false;
/*     */         
/*     */         }
/*     */       }
/* 274 */       else if (!this.lights.contains(paramArrayOfLightRetained[b])) {
/* 275 */         return false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObject() {
/* 288 */     if (this.addAttributeBins.size() > 0) {
/* 289 */       AttributeBin attributeBin = (AttributeBin)this.addAttributeBins.get(0);
/* 290 */       if (this.attributeBinList == null) {
/* 291 */         this.attributeBinList = attributeBin;
/*     */       }
/*     */       else {
/*     */         
/* 295 */         attributeBin.next = this.attributeBinList;
/* 296 */         this.attributeBinList.prev = attributeBin;
/* 297 */         this.attributeBinList = attributeBin;
/*     */       } 
/* 299 */       for (byte b = 1; b < this.addAttributeBins.size(); b++) {
/* 300 */         attributeBin = (AttributeBin)this.addAttributeBins.get(b);
/* 301 */         attributeBin.next = this.attributeBinList;
/* 302 */         this.attributeBinList.prev = attributeBin;
/* 303 */         this.attributeBinList = attributeBin;
/*     */       } 
/*     */     } 
/*     */     
/* 307 */     this.addAttributeBins.clear();
/*     */     
/* 309 */     if (this.canvasDirty != 0) {
/* 310 */       Canvas3D[] arrayOfCanvas3D = this.renderBin.view.getCanvases();
/*     */       
/* 312 */       for (byte b = 0; b < arrayOfCanvas3D.length; b++) {
/* 313 */         (arrayOfCanvas3D[b]).canvasDirty |= this.canvasDirty;
/*     */       }
/*     */       
/* 316 */       if ((this.canvasDirty & 0x100) != 0) {
/* 317 */         updateSceneAmbient();
/*     */       }
/*     */       
/* 320 */       if ((this.canvasDirty & 0x80) != 0) {
/* 321 */         this.enableMask = this.enableMaskCache;
/*     */       }
/*     */       
/* 324 */       if ((this.canvasDirty & 0x4000) != 0) {
/* 325 */         this.enableMCMask = this.enableMCMaskCache;
/*     */       }
/*     */       
/* 328 */       this.canvasDirty = 0;
/*     */     } 
/* 330 */     this.onUpdateList = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addAttributeBin(AttributeBin paramAttributeBin, RenderBin paramRenderBin) {
/* 337 */     paramAttributeBin.environmentSet = this;
/* 338 */     this.addAttributeBins.add(paramAttributeBin);
/* 339 */     if (!this.onUpdateList) {
/* 340 */       paramRenderBin.objUpdateList.add(this);
/* 341 */       this.onUpdateList = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeAttributeBin(AttributeBin paramAttributeBin) {
/* 352 */     paramAttributeBin.environmentSet = null;
/*     */ 
/*     */     
/* 355 */     if (this.addAttributeBins.contains(paramAttributeBin)) {
/* 356 */       this.addAttributeBins.remove(this.addAttributeBins.indexOf(paramAttributeBin));
/*     */     
/*     */     }
/* 359 */     else if (paramAttributeBin.prev == null) {
/* 360 */       this.attributeBinList = paramAttributeBin.next;
/* 361 */       if (paramAttributeBin.next != null) {
/* 362 */         paramAttributeBin.next.prev = null;
/*     */       }
/*     */     } else {
/* 365 */       paramAttributeBin.prev.next = paramAttributeBin.next;
/* 366 */       if (paramAttributeBin.next != null) {
/* 367 */         paramAttributeBin.next.prev = paramAttributeBin.prev;
/*     */       }
/*     */     } 
/*     */     
/* 371 */     paramAttributeBin.prev = null;
/* 372 */     paramAttributeBin.next = null;
/*     */     
/* 374 */     if (paramAttributeBin.definingRenderingAttributes != null && paramAttributeBin.definingRenderingAttributes.changedFrequent != 0)
/*     */     {
/* 376 */       paramAttributeBin.definingRenderingAttributes = null; } 
/* 377 */     paramAttributeBin.onUpdateList &= (AttributeBin.ON_CHANGED_FREQUENT_UPDATE_LIST ^ 0xFFFFFFFF);
/*     */     
/* 379 */     if (this.attributeBinList == null && this.addAttributeBins.size() == 0) {
/*     */ 
/*     */       
/* 382 */       int i = this.lights.size(); byte b;
/* 383 */       for (b = 0; b < i; b++) {
/* 384 */         ((LightRetained)this.lights.get(b)).environmentSets.remove(this);
/*     */       }
/* 386 */       i = this.ambLights.size();
/* 387 */       for (b = 0; b < i; b++) {
/* 388 */         ((LightRetained)this.ambLights.get(b)).environmentSets.remove(this);
/*     */       }
/* 390 */       if (this.fog != null) {
/* 391 */         this.fog.environmentSets.remove(this);
/*     */       }
/* 393 */       this.lightBin.removeEnvironmentSet(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateSceneAmbient() {
/* 400 */     this.sceneAmbient.x = 0.0F;
/* 401 */     this.sceneAmbient.y = 0.0F;
/* 402 */     this.sceneAmbient.z = 0.0F;
/* 403 */     for (byte b = 0; b < this.ambLights.size(); b++) {
/* 404 */       LightRetained lightRetained = (LightRetained)this.ambLights.get(b);
/* 405 */       if (lightRetained.lightOn) {
/* 406 */         this.sceneAmbient.x += lightRetained.color.x;
/* 407 */         this.sceneAmbient.y += lightRetained.color.y;
/* 408 */         this.sceneAmbient.z += lightRetained.color.z;
/*     */       } 
/*     */     } 
/* 411 */     if (this.sceneAmbient.x > 1.0F) {
/* 412 */       this.sceneAmbient.x = 1.0F;
/*     */     }
/* 414 */     if (this.sceneAmbient.y > 1.0F) {
/* 415 */       this.sceneAmbient.y = 1.0F;
/*     */     }
/* 417 */     if (this.sceneAmbient.z > 1.0F) {
/* 418 */       this.sceneAmbient.z = 1.0F;
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
/* 429 */     paramCanvas3D.setStateToUpdate(1, this);
/*     */     
/* 431 */     AttributeBin attributeBin = this.attributeBinList;
/* 432 */     while (attributeBin != null) {
/* 433 */       attributeBin.render(paramCanvas3D);
/* 434 */       attributeBin = attributeBin.next;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateAttributes(Canvas3D paramCanvas3D) {
/* 444 */     boolean bool1 = false, bool2 = false;
/* 445 */     boolean bool3 = false, bool4 = false;
/*     */     
/* 447 */     if (paramCanvas3D.environmentSet != this) {
/* 448 */       if (paramCanvas3D.enableMask != this.enableMask) {
/* 449 */         bool2 = true;
/*     */       }
/*     */       
/* 452 */       if (paramCanvas3D.sceneAmbient.x != this.sceneAmbient.x || paramCanvas3D.sceneAmbient.y != this.sceneAmbient.y || paramCanvas3D.sceneAmbient.z != this.sceneAmbient.z)
/*     */       {
/*     */         
/* 455 */         bool1 = true;
/*     */       }
/*     */       
/* 458 */       if (paramCanvas3D.fog != this.fog) {
/* 459 */         bool4 = true;
/*     */       }
/*     */       
/* 462 */       if (paramCanvas3D.modelClip != this.modelClip) {
/* 463 */         bool3 = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 468 */     if ((paramCanvas3D.canvasDirty & 0xE180) != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 474 */       if ((paramCanvas3D.canvasDirty & 0x80) != 0) {
/* 475 */         bool2 = true;
/*     */       }
/*     */       
/* 478 */       if ((paramCanvas3D.canvasDirty & 0x100) != 0) {
/* 479 */         bool1 = true;
/*     */       }
/*     */       
/* 482 */       if ((paramCanvas3D.canvasDirty & 0x2000) != 0) {
/* 483 */         bool4 = true;
/*     */       }
/*     */       
/* 486 */       if ((paramCanvas3D.canvasDirty & 0x4000) != 0) {
/* 487 */         bool3 = true;
/*     */       }
/*     */       
/* 490 */       if ((paramCanvas3D.canvasDirty & 0x8000) != 0) {
/* 491 */         bool4 = true;
/* 492 */         bool3 = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 497 */     if (bool2) {
/* 498 */       paramCanvas3D.setLightEnables(paramCanvas3D.ctx, this.enableMask, this.renderBin.maxLights);
/* 499 */       paramCanvas3D.enableMask = this.enableMask;
/*     */     } 
/*     */     
/* 502 */     if (bool1) {
/* 503 */       paramCanvas3D.setSceneAmbient(paramCanvas3D.ctx, this.sceneAmbient.x, this.sceneAmbient.y, this.sceneAmbient.z);
/*     */       
/* 505 */       paramCanvas3D.sceneAmbient.set(this.sceneAmbient);
/*     */     } 
/*     */     
/* 508 */     if (bool4) {
/* 509 */       if (this.fog != null) {
/* 510 */         double d = (this.lightBin.geometryBackground == null) ? paramCanvas3D.canvasViewCache.getVworldToCoexistenceScale() : paramCanvas3D.canvasViewCache.getInfVworldToCoexistenceScale();
/*     */ 
/*     */         
/* 513 */         this.fog.update(paramCanvas3D.ctx, d);
/*     */       } else {
/* 515 */         paramCanvas3D.disableFog(paramCanvas3D.ctx);
/*     */       } 
/* 517 */       paramCanvas3D.fog = this.fog;
/*     */     } 
/*     */     
/* 520 */     if (bool3) {
/* 521 */       if (this.modelClip != null) {
/* 522 */         this.modelClip.update(paramCanvas3D, this.enableMCMask);
/*     */       } else {
/* 524 */         paramCanvas3D.disableModelClip(paramCanvas3D.ctx);
/*     */       } 
/* 526 */       paramCanvas3D.modelClip = this.modelClip;
/*     */     } 
/*     */     
/* 529 */     paramCanvas3D.canvasDirty &= 0xFFFF1E7F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 535 */     paramCanvas3D.environmentSet = this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\EnvironmentSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */