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
/*     */ class RenderAtom
/*     */   implements ObjectUpdate
/*     */ {
/*  24 */   GeometryAtom geometryAtom = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   RenderMolecule renderMolecule = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   LightRetained[] lights = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   FogRetained fog = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   ModelClipRetained modelClip = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   AppearanceRetained app = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   static int IN_RENDERBIN = 1;
/*     */ 
/*     */ 
/*     */   
/*  65 */   static int HAS_SEPARATE_LOCALE_VWC_BOUNDS = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   static int NEED_SEPARATE_LOCALE_VWC_BOUNDS = 4;
/*     */   
/*  77 */   static int ON_UPDATELIST = 8;
/*  78 */   static int ON_LOCALE_VWC_BOUNDS_UPDATELIST = 16;
/*     */   
/*  80 */   static int IS_ORIENTED = 32;
/*     */   
/*  82 */   static int IN_DIRTY_ORIENTED_RAs = 64;
/*     */ 
/*     */   
/*  85 */   static int IN_SORTED_POS_DIRTY_TRANSP_LIST = 128;
/*     */ 
/*     */   
/*  88 */   int dirtyMask = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EnvironmentSet envSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   BoundingBox localeVwcBounds = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   long lastVisibleTime = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RenderAtomListInfo[] rListInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   TransparentRenderingInfo[] parentTInfo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   int[] dlistIds = null;
/*     */ 
/*     */   
/* 130 */   static int TEXT3D = 1;
/* 131 */   static int DLIST = 2;
/* 132 */   static int CG = 4;
/* 133 */   static int OTHER = 8;
/* 134 */   static int SEPARATE_DLIST_PER_GEO = 16;
/* 135 */   static int VARRAY = 32;
/* 136 */   static int SEPARATE_DLIST_PER_RINFO = 64;
/* 137 */   static int PRIMARY = TEXT3D | DLIST | CG | OTHER | SEPARATE_DLIST_PER_RINFO;
/*     */ 
/*     */   
/* 140 */   RenderMolecule added = null;
/*     */ 
/*     */   
/* 143 */   RenderMolecule removed = null;
/*     */ 
/*     */   
/* 146 */   RenderAtom nextAdd = null;
/* 147 */   RenderAtom prevAdd = null;
/*     */ 
/*     */   
/* 150 */   RenderAtom nextRemove = null;
/* 151 */   RenderAtom prevRemove = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setRenderBin(boolean paramBoolean) {
/* 160 */     if (!paramBoolean) {
/* 161 */       this.app = null;
/* 162 */       this.dirtyMask &= (IN_RENDERBIN ^ 0xFFFFFFFF);
/* 163 */       this.dirtyMask &= (ON_LOCALE_VWC_BOUNDS_UPDATELIST ^ 0xFFFFFFFF);
/* 164 */       this.dirtyMask &= (ON_UPDATELIST ^ 0xFFFFFFFF);
/*     */     } else {
/*     */       
/* 167 */       this.dirtyMask |= IN_RENDERBIN;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isOpaque() {
/* 177 */     AppearanceRetained appearanceRetained = this.geometryAtom.source.appearance;
/*     */     
/* 179 */     if (appearanceRetained == null) {
/* 180 */       return true;
/*     */     }
/*     */     
/* 183 */     TransparencyAttributesRetained transparencyAttributesRetained = appearanceRetained.transparencyAttributes;
/*     */     
/* 185 */     if (!VirtualUniverse.mc.isD3D()) {
/*     */       
/* 187 */       switch (this.geometryAtom.geoType) {
/*     */         case 3:
/*     */         case 10:
/* 190 */           if (appearanceRetained.pointAttributes != null && appearanceRetained.pointAttributes.pointAntialiasing)
/*     */           {
/* 192 */             return false;
/*     */           }
/*     */           break;
/*     */         case 4:
/*     */         case 7:
/*     */         case 11:
/*     */         case 14:
/* 199 */           if (appearanceRetained.lineAttributes != null && appearanceRetained.lineAttributes.lineAntialiasing)
/*     */           {
/* 201 */             return false;
/*     */           }
/*     */           break;
/*     */         case 15:
/*     */         case 17:
/*     */           break;
/*     */         default:
/* 208 */           if (appearanceRetained.polygonAttributes != null) {
/* 209 */             if (appearanceRetained.polygonAttributes.polygonMode == 0 && appearanceRetained.pointAttributes != null && appearanceRetained.pointAttributes.pointAntialiasing)
/*     */             {
/*     */ 
/*     */               
/* 213 */               return false; } 
/* 214 */             if (appearanceRetained.polygonAttributes.polygonMode == 1 && appearanceRetained.lineAttributes != null && appearanceRetained.lineAttributes.lineAntialiasing)
/*     */             {
/*     */ 
/*     */               
/* 218 */               return false;
/*     */             }
/*     */           } 
/*     */           break;
/*     */       } 
/*     */       
/* 224 */       return (transparencyAttributesRetained == null || transparencyAttributesRetained.transparencyMode == 4 || transparencyAttributesRetained.transparencyMode == 3);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     return (transparencyAttributesRetained == null || transparencyAttributesRetained.transparencyMode == 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   boolean inRenderBin() { return ((this.dirtyMask & IN_RENDERBIN) != 0); }
/*     */ 
/*     */ 
/*     */   
/* 241 */   boolean hasSeparateLocaleVwcBounds() { return ((this.dirtyMask & HAS_SEPARATE_LOCALE_VWC_BOUNDS) != 0); }
/*     */ 
/*     */ 
/*     */   
/* 245 */   boolean needSeparateLocaleVwcBounds() { return ((this.dirtyMask & NEED_SEPARATE_LOCALE_VWC_BOUNDS) != 0); }
/*     */ 
/*     */ 
/*     */   
/* 249 */   boolean onUpdateList() { return ((this.dirtyMask & ON_UPDATELIST) != 0); }
/*     */ 
/*     */ 
/*     */   
/* 253 */   boolean onLocaleVwcBoundsUpdateList() { return ((this.dirtyMask & ON_LOCALE_VWC_BOUNDS_UPDATELIST) != 0); }
/*     */ 
/*     */ 
/*     */   
/* 257 */   boolean isOriented() { return ((this.dirtyMask & IS_ORIENTED) != 0); }
/*     */ 
/*     */ 
/*     */   
/* 261 */   boolean inDepthSortList() { return ((this.dirtyMask & IN_SORTED_POS_DIRTY_TRANSP_LIST) != 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   boolean inDirtyOrientedRAs() { return ((this.dirtyMask & IN_DIRTY_ORIENTED_RAs) != 0); }
/*     */ 
/*     */   
/*     */   public void updateObject() {
/* 270 */     if (inRenderBin()) {
/* 271 */       int i = this.renderMolecule.localToVworldIndex[0];
/*     */ 
/*     */       
/* 274 */       for (byte b = 0; b < this.rListInfo.length; b++) {
/* 275 */         if (this.rListInfo[b].geometry() != null)
/*     */         {
/*     */           
/* 278 */           if (this.geometryAtom.source.inBackgroundGroup) {
/* 279 */             if ((this.rListInfo[b]).infLocalToVworld == null) {
/* 280 */               (this.rListInfo[b]).infLocalToVworld = new Transform3D();
/*     */             }
/*     */             
/* 283 */             this.renderMolecule.localToVworld[i].getRotation((this.rListInfo[b]).infLocalToVworld);
/*     */             
/* 285 */             (this.rListInfo[b]).infLocalToVworld.mul(this.geometryAtom.lastLocalTransformArray[b]);
/*     */           } else {
/*     */             
/* 288 */             (this.rListInfo[b]).localToVworld.mul(this.renderMolecule.localeLocalToVworld[i], this.geometryAtom.lastLocalTransformArray[b]);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 294 */     this.dirtyMask &= (ON_UPDATELIST ^ 0xFFFFFFFF);
/*     */   }
/*     */   
/*     */   void updateOrientedTransform() {
/* 298 */     int i = this.renderMolecule.localToVworldIndex[0];
/*     */     
/* 300 */     Transform3D transform3D = ((OrientedShape3DRetained)this.geometryAtom.source).getOrientedTransform(this.renderMolecule.renderBin.view.viewIndex);
/*     */ 
/*     */     
/* 303 */     for (byte b = 0; b < this.rListInfo.length; b++) {
/*     */       
/* 305 */       if (this.geometryAtom.geoType == 16 && this.geometryAtom.lastLocalTransformArray[b] != null) {
/*     */         
/* 307 */         if (this.geometryAtom.source.inBackgroundGroup) {
/* 308 */           if ((this.rListInfo[b]).infLocalToVworld == null) {
/* 309 */             (this.rListInfo[b]).infLocalToVworld = new Transform3D();
/*     */           }
/* 311 */           (this.rListInfo[b]).infLocalToVworld.mul(this.renderMolecule.infLocalToVworld[i], transform3D);
/*     */ 
/*     */           
/* 314 */           (this.rListInfo[b]).infLocalToVworld.mul(this.geometryAtom.lastLocalTransformArray[b]);
/*     */         } else {
/*     */           
/* 317 */           (this.rListInfo[b]).localToVworld.mul(this.renderMolecule.localeLocalToVworld[i], transform3D);
/*     */ 
/*     */           
/* 320 */           (this.rListInfo[b]).localToVworld.mul(this.geometryAtom.lastLocalTransformArray[b]);
/*     */         }
/*     */       
/*     */       }
/* 324 */       else if (this.geometryAtom.source.inBackgroundGroup) {
/* 325 */         if ((this.rListInfo[b]).infLocalToVworld == null) {
/* 326 */           (this.rListInfo[b]).infLocalToVworld = new Transform3D();
/*     */         }
/* 328 */         (this.rListInfo[b]).infLocalToVworld.mul(this.renderMolecule.infLocalToVworld[i], transform3D);
/*     */       }
/*     */       else {
/*     */         
/* 332 */         (this.rListInfo[b]).localToVworld.mul(this.renderMolecule.localeLocalToVworld[i], transform3D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateLocaleVwcBounds() {
/* 349 */     if (inRenderBin()) {
/*     */ 
/*     */ 
/*     */       
/* 353 */       if (this.renderMolecule.renderBin.locale != this.geometryAtom.source.locale) {
/*     */         
/* 355 */         this.geometryAtom.source.locale.hiRes.difference(this.renderMolecule.renderBin.locale.hiRes, this.renderMolecule.renderBin.localeTranslation);
/*     */ 
/*     */         
/* 358 */         this.localeVwcBounds.translate(this.geometryAtom.source.vwcBounds, this.renderMolecule.renderBin.localeTranslation);
/*     */       } else {
/*     */         
/* 361 */         this.localeVwcBounds.set(this.geometryAtom.source.vwcBounds);
/*     */       } 
/* 363 */       this.dirtyMask &= (ON_LOCALE_VWC_BOUNDS_UPDATELIST ^ 0xFFFFFFFF);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\RenderAtom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */