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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Text3DRenderMethod
/*     */   implements RenderMethod
/*     */ {
/*     */   public boolean render(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/*  29 */     Transform3D transform3D = null;
/*     */     
/*  31 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramRenderAtomListInfo.geometry();
/*  32 */     geometryArrayRetained.setVertexFormat((paramRenderMolecule.useAlpha && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors, paramCanvas3D.ctx);
/*     */ 
/*     */ 
/*     */     
/*  36 */     if (paramRenderMolecule.doInfinite) {
/*  37 */       paramCanvas3D.updateState(paramInt);
/*  38 */       while (paramRenderAtomListInfo != null) {
/*  39 */         transform3D = paramRenderAtomListInfo.infLocalToVworld;
/*  40 */         boolean bool1 = !transform3D.isCongruent();
/*  41 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, transform3D);
/*     */         
/*  43 */         paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, bool1, (paramRenderMolecule.useAlpha && (paramRenderAtomListInfo.geometry()).noAlpha), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  48 */         paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */       } 
/*  50 */       return true;
/*     */     } 
/*     */     
/*  53 */     boolean bool = false;
/*  54 */     while (paramRenderAtomListInfo != null) {
/*  55 */       if (paramCanvas3D.ra == paramRenderAtomListInfo.renderAtom) {
/*  56 */         if (paramCanvas3D.raIsVisible) {
/*  57 */           paramCanvas3D.updateState(paramInt);
/*  58 */           transform3D = paramRenderAtomListInfo.localToVworld;
/*  59 */           boolean bool1 = !transform3D.isCongruent();
/*     */           
/*  61 */           paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, transform3D);
/*  62 */           paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, bool1, (paramRenderMolecule.useAlpha && (paramRenderAtomListInfo.geometry()).noAlpha), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  68 */           bool = true;
/*     */         } 
/*     */       } else {
/*     */         
/*  72 */         if (!VirtualUniverse.mc.viewFrustumCulling || paramRenderAtomListInfo.renderAtom.localeVwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/*     */           
/*  74 */           paramCanvas3D.updateState(paramInt);
/*  75 */           paramCanvas3D.raIsVisible = true;
/*  76 */           transform3D = paramRenderAtomListInfo.localToVworld;
/*  77 */           boolean bool1 = !transform3D.isCongruent();
/*     */           
/*  79 */           paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, transform3D);
/*  80 */           paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, bool1, (paramRenderMolecule.useAlpha && (paramRenderAtomListInfo.geometry()).noAlpha), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  86 */           bool = true;
/*     */         } else {
/*     */           
/*  89 */           paramCanvas3D.raIsVisible = false;
/*     */         } 
/*  91 */         paramCanvas3D.ra = paramRenderAtomListInfo.renderAtom;
/*     */       } 
/*     */       
/*  94 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     geometryArrayRetained.disableGlobalAlpha(paramCanvas3D.ctx, (paramRenderMolecule.useAlpha && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     return bool;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Text3DRenderMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */