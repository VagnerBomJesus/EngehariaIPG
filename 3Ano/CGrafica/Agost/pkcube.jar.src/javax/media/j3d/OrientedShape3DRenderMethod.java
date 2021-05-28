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
/*     */ class OrientedShape3DRenderMethod
/*     */   implements RenderMethod
/*     */ {
/*     */   public boolean render(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/*  28 */     Transform3D transform3D = null;
/*     */     
/*  30 */     boolean bool1 = paramRenderMolecule.useAlpha;
/*     */     
/*  32 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramRenderAtomListInfo.geometry();
/*  33 */     geometryArrayRetained.setVertexFormat((paramRenderMolecule.useAlpha && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors, paramCanvas3D.ctx);
/*     */ 
/*     */ 
/*     */     
/*  37 */     if (paramRenderMolecule.doInfinite) {
/*  38 */       paramCanvas3D.updateState(paramInt);
/*  39 */       while (paramRenderAtomListInfo != null) {
/*  40 */         transform3D = paramRenderAtomListInfo.infLocalToVworld;
/*  41 */         boolean bool = !transform3D.isCongruent();
/*     */         
/*  43 */         paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, transform3D);
/*  44 */         paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, bool, (bool1 && (paramRenderAtomListInfo.geometry()).noAlpha), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  49 */         paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */       } 
/*  51 */       return true;
/*     */     } 
/*     */     
/*  54 */     boolean bool2 = false;
/*  55 */     while (paramRenderAtomListInfo != null) {
/*  56 */       if (paramCanvas3D.ra == paramRenderAtomListInfo.renderAtom) {
/*  57 */         if (paramCanvas3D.raIsVisible) {
/*  58 */           paramCanvas3D.updateState(paramInt);
/*  59 */           transform3D = paramRenderAtomListInfo.localToVworld;
/*  60 */           boolean bool = !transform3D.isCongruent();
/*     */           
/*  62 */           paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, transform3D);
/*  63 */           paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, bool, (bool1 && (paramRenderAtomListInfo.geometry()).noAlpha), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  69 */           bool2 = true;
/*     */         } 
/*     */       } else {
/*     */         
/*  73 */         if (!VirtualUniverse.mc.viewFrustumCulling || paramRenderAtomListInfo.renderAtom.localeVwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/*     */           
/*  75 */           paramCanvas3D.updateState(paramInt);
/*  76 */           paramCanvas3D.raIsVisible = true;
/*  77 */           transform3D = paramRenderAtomListInfo.localToVworld;
/*  78 */           boolean bool = !transform3D.isCongruent();
/*     */           
/*  80 */           paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, paramCanvas3D.vworldToEc.mat, transform3D);
/*  81 */           paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, bool, (bool1 && (paramRenderAtomListInfo.geometry()).noAlpha), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  87 */           bool2 = true;
/*     */         } else {
/*     */           
/*  90 */           paramCanvas3D.raIsVisible = false;
/*     */         } 
/*  92 */         paramCanvas3D.ra = paramRenderAtomListInfo.renderAtom;
/*     */       } 
/*     */       
/*  95 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 100 */     geometryArrayRetained.disableGlobalAlpha(paramCanvas3D.ctx, (paramRenderMolecule.useAlpha && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*     */ 
/*     */ 
/*     */     
/* 104 */     return bool2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\OrientedShape3DRenderMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */