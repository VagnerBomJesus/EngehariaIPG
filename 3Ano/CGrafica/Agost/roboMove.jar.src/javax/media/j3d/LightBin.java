/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LightBin
/*     */   implements ObjectUpdate
/*     */ {
/*     */   int maxLights;
/*     */   LightRetained[] lights;
/*     */   int[] lightsRef;
/*     */   int numEmptySlots;
/*     */   RenderBin renderBin;
/*     */   LightBin next;
/*     */   LightBin prev;
/*     */   EnvironmentSet environmentSetList;
/*     */   ArrayList insertEnvSet;
/*     */   int canvasDirty;
/*     */   int lightDirtyMaskCache;
/*     */   int lightDirtyMask;
/*     */   ArrayList pointLts;
/*     */   int[] pointLtsSlotIndex;
/*     */   OrderedCollection orderedCollection;
/*     */   boolean onUpdateList;
/*     */   BackgroundRetained geometryBackground;
/*     */   
/*     */   LightBin(int paramInt, RenderBin paramRenderBin, boolean paramBoolean) {
/*  30 */     this.maxLights = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     this.lights = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.lightsRef = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.numEmptySlots = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.renderBin = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.next = null;
/*  59 */     this.prev = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.environmentSetList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.insertEnvSet = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     this.canvasDirty = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     this.lightDirtyMaskCache = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.lightDirtyMask = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     this.pointLts = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.orderedCollection = null;
/*     */     
/* 100 */     this.onUpdateList = false;
/*     */ 
/*     */     
/* 103 */     this.geometryBackground = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     this.maxLights = paramInt;
/* 109 */     this.numEmptySlots = paramInt;
/* 110 */     this.lights = new LightRetained[paramInt];
/* 111 */     this.lightsRef = new int[paramInt];
/* 112 */     this.renderBin = paramRenderBin;
/*     */   }
/*     */   
/*     */   void reset(boolean paramBoolean) {
/* 116 */     this.prev = null;
/* 117 */     this.next = null;
/* 118 */     this.orderedCollection = null;
/* 119 */     this.environmentSetList = null;
/* 120 */     this.onUpdateList = false;
/* 121 */     this.geometryBackground = null;
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
/* 132 */   void setOrderedInfo(OrderedCollection paramOrderedCollection) { this.orderedCollection = paramOrderedCollection; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean willEnvironmentSetFit(EnvironmentSet paramEnvironmentSet) {
/* 143 */     int i = paramEnvironmentSet.lights.size();
/* 144 */     int j = i;
/* 145 */     for (byte b = 0; b < i; b++) {
/* 146 */       LightRetained lightRetained = (LightRetained)paramEnvironmentSet.lights.get(b);
/* 147 */       if (!(lightRetained instanceof AmbientLightRetained))
/*     */       {
/*     */         
/* 150 */         for (byte b1 = 0; b1 < this.maxLights; b1++) {
/* 151 */           if (this.lights[b1] == lightRetained) {
/* 152 */             j--;
/*     */             break;
/*     */           } 
/*     */         }  } 
/*     */     } 
/* 157 */     if (j > this.numEmptySlots) {
/* 158 */       return false;
/*     */     }
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addEnvironmentSet(EnvironmentSet paramEnvironmentSet, RenderBin paramRenderBin) {
/* 171 */     int i = paramEnvironmentSet.lights.size();
/* 172 */     for (byte b = 0; b < i; b++) {
/* 173 */       LightRetained lightRetained = (LightRetained)paramEnvironmentSet.lights.get(b);
/* 174 */       if (!(lightRetained instanceof AmbientLightRetained)) {
/*     */         byte b1;
/*     */         
/* 177 */         for (b1 = 0; b1 < this.maxLights; b1++) {
/* 178 */           if (this.lights[b1] == lightRetained) {
/* 179 */             if (lightRetained.lightOn) {
/* 180 */               paramEnvironmentSet.enableMask |= (true << b1);
/*     */             }
/* 182 */             this.lightsRef[b1] = this.lightsRef[b1] + 1;
/*     */ 
/*     */ 
/*     */             
/* 186 */             paramEnvironmentSet.ltPos[b] = b1;
/*     */             break;
/*     */           } 
/*     */         } 
/* 190 */         if (b1 == this.maxLights)
/*     */         {
/* 192 */           for (b1 = 0; b1 < this.maxLights; b1++) {
/* 193 */             if (this.lights[b1] == null) {
/* 194 */               this.lights[b1] = lightRetained;
/* 195 */               this.lightsRef[b1] = 1;
/* 196 */               if (lightRetained instanceof PointLightRetained) {
/* 197 */                 this.pointLts.add(lightRetained);
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 202 */                 int j = 0;
/* 203 */                 if (this.pointLtsSlotIndex != null)
/* 204 */                   j = this.pointLtsSlotIndex.length; 
/* 205 */                 if (j < this.pointLts.size()) {
/*     */                   
/* 207 */                   int[] arrayOfInt = new int[j + 8];
/*     */                   
/* 209 */                   for (byte b2 = 0; b2 < j; b2++) {
/* 210 */                     arrayOfInt[b2] = this.pointLtsSlotIndex[b2];
/*     */                   }
/* 212 */                   this.pointLtsSlotIndex = arrayOfInt;
/*     */                 } 
/* 214 */                 this.pointLtsSlotIndex[this.pointLts.size() - 1] = b1;
/*     */               } 
/* 216 */               if (lightRetained.lightOn) {
/* 217 */                 paramEnvironmentSet.enableMask |= (1 << b1);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 222 */               paramEnvironmentSet.ltPos[b] = b1;
/* 223 */               this.numEmptySlots--;
/*     */               break;
/*     */             } 
/*     */           }  } 
/*     */       } 
/*     */     } 
/* 229 */     paramEnvironmentSet.lightBin = this;
/* 230 */     paramEnvironmentSet.enableMaskCache = paramEnvironmentSet.enableMask;
/* 231 */     this.insertEnvSet.add(paramEnvironmentSet);
/* 232 */     if (!this.onUpdateList) {
/* 233 */       paramRenderBin.objUpdateList.add(this);
/* 234 */       this.onUpdateList = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObject() {
/* 245 */     if (this.insertEnvSet.size() > 0) {
/* 246 */       EnvironmentSet environmentSet = (EnvironmentSet)this.insertEnvSet.get(0);
/* 247 */       if (this.environmentSetList == null) {
/* 248 */         this.environmentSetList = environmentSet;
/*     */       } else {
/*     */         
/* 251 */         environmentSet.next = this.environmentSetList;
/* 252 */         this.environmentSetList.prev = environmentSet;
/* 253 */         this.environmentSetList = environmentSet;
/*     */       } 
/* 255 */       for (byte b = 1; b < this.insertEnvSet.size(); b++) {
/* 256 */         environmentSet = (EnvironmentSet)this.insertEnvSet.get(b);
/* 257 */         environmentSet.next = this.environmentSetList;
/* 258 */         this.environmentSetList.prev = environmentSet;
/* 259 */         this.environmentSetList = environmentSet;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 264 */     this.insertEnvSet.clear();
/* 265 */     if (this.canvasDirty != 0) {
/*     */       
/* 267 */       Canvas3D[] arrayOfCanvas3D = this.renderBin.view.getCanvases();
/* 268 */       for (byte b = 0; b < arrayOfCanvas3D.length; b++) {
/* 269 */         (arrayOfCanvas3D[b]).canvasDirty |= this.canvasDirty;
/*     */       }
/* 271 */       this.lightDirtyMask = this.lightDirtyMaskCache;
/* 272 */       this.canvasDirty = 0;
/* 273 */       this.lightDirtyMaskCache = 0;
/*     */     } 
/* 275 */     this.onUpdateList = false;
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
/*     */   void removeEnvironmentSet(EnvironmentSet paramEnvironmentSet) {
/* 287 */     paramEnvironmentSet.lightBin = null;
/*     */ 
/*     */     
/* 290 */     if (this.insertEnvSet.contains(paramEnvironmentSet)) {
/* 291 */       this.insertEnvSet.remove(this.insertEnvSet.indexOf(paramEnvironmentSet));
/*     */     } else {
/*     */       
/* 294 */       int i = paramEnvironmentSet.lights.size(); byte b;
/* 295 */       for (b = 0; b < i; b++) {
/* 296 */         LightRetained lightRetained = (LightRetained)paramEnvironmentSet.lights.get(b);
/* 297 */         for (byte b1 = 0; b1 < this.maxLights; b1++) {
/* 298 */           if (this.lights[b1] == lightRetained) {
/* 299 */             this.lightsRef[b1] = this.lightsRef[b1] - 1;
/* 300 */             if (this.lightsRef[b1] == 0) {
/* 301 */               if (lightRetained instanceof PointLightRetained)
/* 302 */                 this.pointLts.remove(this.pointLts.indexOf(lightRetained)); 
/* 303 */               this.lights[b1] = null;
/*     */               
/* 305 */               this.lightDirtyMaskCache &= (true << b1 ^ 0xFFFFFFFF);
/*     */               
/* 307 */               this.lightDirtyMask &= (true << b1 ^ 0xFFFFFFFF);
/* 308 */               this.numEmptySlots++;
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 315 */       if (paramEnvironmentSet.prev == null) {
/* 316 */         this.environmentSetList = paramEnvironmentSet.next;
/* 317 */         if (paramEnvironmentSet.next != null) {
/* 318 */           paramEnvironmentSet.next.prev = null;
/*     */         }
/*     */       } else {
/* 321 */         paramEnvironmentSet.prev.next = paramEnvironmentSet.next;
/* 322 */         if (paramEnvironmentSet.next != null) {
/* 323 */           paramEnvironmentSet.next.prev = paramEnvironmentSet.prev;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 328 */       Canvas3D[] arrayOfCanvas3D = this.renderBin.view.getCanvases();
/* 329 */       for (b = 0; b < arrayOfCanvas3D.length; b++)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 335 */         (arrayOfCanvas3D[b]).environmentSet = null;
/*     */       }
/*     */     } 
/*     */     
/* 339 */     paramEnvironmentSet.prev = null;
/* 340 */     paramEnvironmentSet.next = null;
/*     */     
/* 342 */     if (this.environmentSetList == null && this.insertEnvSet.size() == 0) {
/* 343 */       this.renderBin.removeLightBin(this);
/* 344 */       this.geometryBackground = null;
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
/*     */   void render(Canvas3D paramCanvas3D) {
/* 356 */     paramCanvas3D.setStateToUpdate(0, this);
/*     */     
/* 358 */     EnvironmentSet environmentSet = this.environmentSetList;
/* 359 */     while (environmentSet != null) {
/* 360 */       environmentSet.render(paramCanvas3D);
/* 361 */       environmentSet = environmentSet.next;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateAttributes(Canvas3D paramCanvas3D) {
/* 369 */     int i = VirtualUniverse.mc.frameCount;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 379 */     if (paramCanvas3D.lightBin != this) {
/*     */       double d;
/* 381 */       if (this.geometryBackground == null) {
/* 382 */         d = paramCanvas3D.canvasViewCache.getVworldToCoexistenceScale();
/* 383 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vpcToEc.mat, this.renderBin.vworldToVpc);
/*     */       } else {
/*     */         
/* 386 */         d = paramCanvas3D.canvasViewCache.getInfVworldToCoexistenceScale();
/* 387 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vpcToEc.mat, this.renderBin.infVworldToVpc);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 392 */       for (byte b = 0; b < this.maxLights; b++) {
/* 393 */         if (this.lights[b] != null && (
/* 394 */           paramCanvas3D.lights[b] != this.lights[b] || paramCanvas3D.frameCount[b] != i)) {
/*     */           
/* 396 */           paramCanvas3D.lights[b] = this.lights[b];
/* 397 */           paramCanvas3D.frameCount[b] = i;
/* 398 */           this.lights[b].update(paramCanvas3D.ctx, b, d);
/*     */         } 
/*     */       } 
/*     */       
/* 402 */       paramCanvas3D.lightBin = this;
/* 403 */       paramCanvas3D.canvasDirty &= 0xFFFFFFBF;
/*     */       
/* 405 */       paramCanvas3D.enableMask = -1L;
/*     */     
/*     */     }
/* 408 */     else if ((paramCanvas3D.canvasDirty & 0x40) != 0) {
/*     */       double d;
/* 410 */       if (this.geometryBackground == null) {
/* 411 */         d = paramCanvas3D.canvasViewCache.getVworldToCoexistenceScale();
/* 412 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vpcToEc.mat, this.renderBin.vworldToVpc);
/*     */       } else {
/*     */         
/* 415 */         d = paramCanvas3D.canvasViewCache.getInfVworldToCoexistenceScale();
/* 416 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vpcToEc.mat, this.renderBin.infVworldToVpc);
/*     */       } 
/*     */       
/* 419 */       byte b = 0;
/* 420 */       int j = this.lightDirtyMask;
/* 421 */       while (j != 0) {
/* 422 */         if ((j & true) != 0) {
/* 423 */           this.lights[b].update(paramCanvas3D.ctx, b, d);
/* 424 */           paramCanvas3D.lights[b] = this.lights[b];
/* 425 */           paramCanvas3D.frameCount[b] = i;
/*     */         } 
/* 427 */         j >>= 1;
/* 428 */         b++;
/*     */       } 
/*     */       
/* 431 */       paramCanvas3D.canvasDirty &= 0xFFFFFFBF;
/*     */     }
/* 433 */     else if (this.pointLts.size() > 0 && (paramCanvas3D.canvasDirty & 0x8000) != 0) {
/* 434 */       double d; if (this.geometryBackground == null) {
/* 435 */         d = paramCanvas3D.canvasViewCache.getVworldToCoexistenceScale();
/* 436 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vpcToEc.mat, this.renderBin.vworldToVpc);
/*     */       } else {
/*     */         
/* 439 */         d = paramCanvas3D.canvasViewCache.getInfVworldToCoexistenceScale();
/* 440 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vpcToEc.mat, this.renderBin.infVworldToVpc);
/*     */       } 
/*     */       
/* 443 */       for (byte b = 0; b < this.pointLts.size(); b++) {
/* 444 */         LightRetained lightRetained = (LightRetained)this.pointLts.get(b);
/* 445 */         lightRetained.update(paramCanvas3D.ctx, this.pointLtsSlotIndex[b], d);
/* 446 */         paramCanvas3D.lights[this.pointLtsSlotIndex[b]] = lightRetained;
/* 447 */         paramCanvas3D.frameCount[this.pointLtsSlotIndex[b]] = i;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\LightBin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */