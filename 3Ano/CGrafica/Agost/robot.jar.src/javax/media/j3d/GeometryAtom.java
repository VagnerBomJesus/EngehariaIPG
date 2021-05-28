/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
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
/*     */ class GeometryAtom
/*     */   implements BHLeafInterface, NnuId
/*     */ {
/*  29 */   GeometryRetained[] geometryArray = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   Transform3D[] lastLocalTransformArray = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   Locale locale = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   Shape3DRetained source = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   BHLeafNode bhLeafNode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean visible = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   int geoType = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   RenderAtom[] renderAtoms = new RenderAtom[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   Point3d[] centroid = null;
/*     */   boolean centroidIsDirty = true;
/*  74 */   Object lockObj = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   int nnuId = NnuIdManager.getId();
/*     */   
/*     */   boolean alphaEditable;
/*     */   
/*  83 */   public int getId() { return this.nnuId; }
/*     */ 
/*     */   
/*     */   public int equal(NnuId paramNnuId) {
/*  87 */     int i = paramNnuId.getId();
/*  88 */     if (this.nnuId < i) {
/*  89 */       return -1;
/*     */     }
/*  91 */     if (this.nnuId > i) {
/*  92 */       return 1;
/*     */     }
/*     */     
/*  95 */     return 0;
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
/* 108 */   public BoundingBox computeBoundingHull() { return this.source.vwcBounds; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public boolean isEnable() { return (this.source.vwcBounds != null && !this.source.vwcBounds.isEmpty() && this.source.switchState.currentSwitchOn); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnable(int paramInt) {
/* 120 */     if (this.source.vwcBounds != null && !this.source.vwcBounds.isEmpty() && this.source.switchState.currentSwitchOn)
/*     */     {
/* 122 */       switch (paramInt) {
/*     */         case 0:
/* 124 */           return this.visible;
/*     */         case 1:
/* 126 */           return !this.visible;
/*     */         case 2:
/* 128 */           return true;
/*     */       } 
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 135 */   public Locale getLocale2() { return this.locale; }
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
/*     */   RenderAtom getRenderAtom(View paramView) {
/*     */     int i;
/* 149 */     synchronized (this.renderAtoms) {
/* 150 */       i = paramView.viewIndex;
/* 151 */       if (i >= this.renderAtoms.length) {
/*     */ 
/*     */ 
/*     */         
/* 155 */         if (this.source.viewList != null && !this.source.viewList.contains(paramView))
/*     */         {
/* 157 */           return null; } 
/* 158 */         RenderAtom[] arrayOfRenderAtom = new RenderAtom[i + 1]; byte b;
/* 159 */         for (b = 0; b < this.renderAtoms.length; b++) {
/* 160 */           arrayOfRenderAtom[b] = this.renderAtoms[b];
/*     */         }
/* 162 */         RenderAtom renderAtom = new RenderAtom();
/* 163 */         arrayOfRenderAtom[i] = renderAtom;
/* 164 */         (arrayOfRenderAtom[i]).geometryAtom = this;
/*     */ 
/*     */         
/* 167 */         renderAtom.rListInfo = new RenderAtomListInfo[this.geometryArray.length];
/* 168 */         if (this.geoType != 16) {
/* 169 */           for (b = 0; b < renderAtom.rListInfo.length; b++) {
/* 170 */             renderAtom.rListInfo[b] = new RenderAtomListInfo();
/* 171 */             (renderAtom.rListInfo[b]).renderAtom = renderAtom;
/* 172 */             (renderAtom.rListInfo[b]).index = b;
/*     */           } 
/*     */         } else {
/*     */           
/* 176 */           for (b = 0; b < renderAtom.rListInfo.length; b++) {
/* 177 */             renderAtom.rListInfo[b] = new RenderAtomListInfo();
/* 178 */             (renderAtom.rListInfo[b]).renderAtom = renderAtom;
/* 179 */             (renderAtom.rListInfo[b]).index = b;
/* 180 */             (renderAtom.rListInfo[b]).localToVworld = new Transform3D();
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 190 */         this.renderAtoms = arrayOfRenderAtom;
/*     */       }
/* 192 */       else if (this.renderAtoms[i] == null) {
/*     */ 
/*     */         
/* 195 */         if (this.source.viewList != null && !this.source.viewList.contains(paramView))
/*     */         {
/* 197 */           return null;
/*     */         }
/* 199 */         RenderAtom renderAtom = new RenderAtom();
/* 200 */         this.renderAtoms[i] = renderAtom;
/* 201 */         (this.renderAtoms[i]).geometryAtom = this;
/*     */         
/* 203 */         renderAtom.rListInfo = new RenderAtomListInfo[this.geometryArray.length];
/* 204 */         if (this.geoType != 16) {
/* 205 */           for (byte b = 0; b < renderAtom.rListInfo.length; b++) {
/* 206 */             renderAtom.rListInfo[b] = new RenderAtomListInfo();
/* 207 */             (renderAtom.rListInfo[b]).renderAtom = renderAtom;
/* 208 */             (renderAtom.rListInfo[b]).index = b;
/*     */           } 
/*     */         } else {
/*     */           
/* 212 */           for (byte b = 0; b < renderAtom.rListInfo.length; b++) {
/* 213 */             renderAtom.rListInfo[b] = new RenderAtomListInfo();
/* 214 */             (renderAtom.rListInfo[b]).renderAtom = renderAtom;
/* 215 */             (renderAtom.rListInfo[b]).index = b;
/* 216 */             (renderAtom.rListInfo[b]).localToVworld = new Transform3D();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 223 */     return this.renderAtoms[i];
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
/*     */   void updateCentroid() {
/* 235 */     if (VirtualUniverse.mc.sortShape3DBounds && !this.source.boundsAutoCompute) {
/*     */ 
/*     */       
/* 238 */       synchronized (this.lockObj) {
/* 239 */         if (this.centroid == null) {
/* 240 */           this.centroid = new Point3d[this.geometryArray.length];
/* 241 */           for (byte b = 0; b < this.centroid.length; b++) {
/* 242 */             this.centroid[b] = new Point3d(this.source.localBounds.getCenter());
/* 243 */             this.source.getCurrentLocalToVworld(0).transform(this.centroid[b]);
/*     */           } 
/*     */         } else {
/*     */           
/* 247 */           for (byte b = 0; b < this.centroid.length; b++) {
/* 248 */             this.centroid[b].set(this.source.localBounds.getCenter());
/* 249 */             this.source.getCurrentLocalToVworld(0).transform(this.centroid[b]);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 258 */     synchronized (this.lockObj) {
/* 259 */       byte b; for (b = 0; b < this.geometryArray.length; b++) {
/* 260 */         if (this.geometryArray[b] != null)
/*     */         {
/* 262 */           synchronized ((this.geometryArray[b]).centroid) {
/* 263 */             if ((this.geometryArray[b]).recompCentroid) {
/* 264 */               this.geometryArray[b].computeCentroid();
/* 265 */               (this.geometryArray[b]).recompCentroid = false;
/*     */             } 
/*     */           }  } 
/*     */       } 
/* 269 */       if (this.centroidIsDirty) {
/* 270 */         if (this.centroid == null) {
/* 271 */           this.centroid = new Point3d[this.geometryArray.length];
/* 272 */           for (b = 0; b < this.centroid.length; b++) {
/* 273 */             if (this.geometryArray[b] != null) {
/*     */               
/* 275 */               this.centroid[b] = new Point3d((this.geometryArray[b]).centroid);
/* 276 */               this.source.getCurrentLocalToVworld(0).transform(this.centroid[b]);
/*     */             } 
/*     */           } 
/*     */         } else {
/* 280 */           for (b = 0; b < this.centroid.length; b++) {
/* 281 */             if (this.geometryArray[b] != null) {
/*     */               
/* 283 */               this.centroid[b].set((this.geometryArray[b]).centroid);
/* 284 */               this.source.getCurrentLocalToVworld(0).transform(this.centroid[b]);
/*     */             } 
/*     */           } 
/* 287 */         }  this.centroidIsDirty = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\GeometryAtom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */