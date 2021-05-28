/*     */ package javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.transparency.TransparencySortGeom;
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
/*     */ class TransparentRenderingInfo
/*     */   implements TransparencySortGeom
/*     */ {
/*     */   RenderMolecule rm;
/*     */   RenderAtomListInfo rInfo;
/*     */   TransparentRenderingInfo prev;
/*     */   TransparentRenderingInfo next;
/*     */   GeometryAtom geometryAtom;
/*     */   double zVal;
/*     */   
/*     */   boolean updateState(Canvas3D paramCanvas3D) {
/*  35 */     TextureBin textureBin = this.rm.textureBin;
/*  36 */     AttributeBin attributeBin = textureBin.attributeBin;
/*  37 */     ShaderBin shaderBin = textureBin.shaderBin;
/*     */ 
/*     */ 
/*     */     
/*  41 */     RenderMolecule renderMolecule = textureBin.transparentRMList;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  46 */     while (renderMolecule != null && 
/*  47 */       !renderMolecule.isSwitchOn()) {
/*     */ 
/*     */       
/*  50 */       if (renderMolecule.next != null) {
/*  51 */         renderMolecule = renderMolecule.next; continue;
/*     */       } 
/*  53 */       renderMolecule = renderMolecule.nextMap;
/*     */     } 
/*     */ 
/*     */     
/*  57 */     if (renderMolecule == null) {
/*  58 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  63 */     if (paramCanvas3D.environmentSet != attributeBin.environmentSet) {
/*     */       
/*  65 */       boolean bool = (attributeBin.definingRenderingAttributes == null || attributeBin.definingRenderingAttributes.visible) ? 1 : 0;
/*     */ 
/*     */       
/*  68 */       if ((attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy == 0 && !bool) || (attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy == 1 && bool))
/*     */       {
/*     */ 
/*     */         
/*  72 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  77 */       paramCanvas3D.setStateToUpdate(0, attributeBin.environmentSet.lightBin);
/*  78 */       paramCanvas3D.setStateToUpdate(1, attributeBin.environmentSet);
/*  79 */       paramCanvas3D.setStateToUpdate(2, attributeBin);
/*  80 */       paramCanvas3D.setStateToUpdate(6, shaderBin);
/*  81 */       paramCanvas3D.updateEnvState();
/*     */     }
/*  83 */     else if (paramCanvas3D.attributeBin != attributeBin) {
/*  84 */       boolean bool = (attributeBin.definingRenderingAttributes == null || attributeBin.definingRenderingAttributes.visible) ? 1 : 0;
/*     */ 
/*     */       
/*  87 */       if ((attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy == 0 && !bool) || (attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy == 1 && bool))
/*     */       {
/*     */ 
/*     */         
/*  91 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  96 */       paramCanvas3D.setStateToUpdate(2, attributeBin);
/*  97 */       paramCanvas3D.setStateToUpdate(6, shaderBin);
/*  98 */       paramCanvas3D.updateEnvState();
/*     */     }
/* 100 */     else if (paramCanvas3D.shaderBin != shaderBin) {
/*     */ 
/*     */ 
/*     */       
/* 104 */       paramCanvas3D.setStateToUpdate(6, shaderBin);
/* 105 */       paramCanvas3D.updateEnvState();
/*     */     } 
/*     */ 
/*     */     
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   void render(Canvas3D paramCanvas3D) {
/* 113 */     if (updateState(paramCanvas3D)) {
/* 114 */       this.rm.textureBin.render(paramCanvas3D, this.rm.textureBin.transparentRMList);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void sortRender(Canvas3D paramCanvas3D) {
/* 120 */     if (updateState(paramCanvas3D)) {
/* 121 */       this.rm.textureBin.render(paramCanvas3D, this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 126 */   public double getDistanceSquared() { return this.zVal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Geometry getGeometry() {
/* 133 */     if (this.geometryAtom.geometryArray[false] == null)
/* 134 */       return null; 
/* 135 */     return (Geometry)(this.geometryAtom.geometryArray[0]).source;
/*     */   }
/*     */ 
/*     */   
/* 139 */   public void getLocalToVWorld(Transform3D paramTransform3D) { paramTransform3D.set(this.rm.localToVworld[0]); }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public Shape3D getShape3D() { return (Shape3D)this.geometryAtom.source.source; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\TransparentRenderingInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */