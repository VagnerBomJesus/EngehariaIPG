/*    */ package javax.media.j3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class VertexArrayRenderMethod
/*    */   implements RenderMethod
/*    */ {
/*    */   public boolean render(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/* 26 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramRenderAtomListInfo.geometry();
/* 27 */     geometryArrayRetained.setVertexFormat((paramRenderMolecule.useAlpha && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors, paramCanvas3D.ctx);
/*    */ 
/*    */ 
/*    */     
/* 31 */     if (paramRenderMolecule.doInfinite) {
/* 32 */       paramCanvas3D.updateState(paramInt);
/* 33 */       while (paramRenderAtomListInfo != null) {
/* 34 */         renderGeo(paramRenderAtomListInfo, paramRenderMolecule, paramCanvas3D);
/* 35 */         paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*    */       } 
/* 37 */       return true;
/*    */     } 
/*    */     
/* 40 */     boolean bool = false;
/* 41 */     while (paramRenderAtomListInfo != null) {
/* 42 */       if (paramCanvas3D.ra == paramRenderAtomListInfo.renderAtom) {
/* 43 */         if (paramCanvas3D.raIsVisible) {
/* 44 */           paramCanvas3D.updateState(paramInt);
/* 45 */           renderGeo(paramRenderAtomListInfo, paramRenderMolecule, paramCanvas3D);
/* 46 */           bool = true;
/*    */         } 
/*    */       } else {
/*    */         
/* 50 */         if (!VirtualUniverse.mc.viewFrustumCulling || paramRenderAtomListInfo.renderAtom.localeVwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/*    */           
/* 52 */           paramCanvas3D.updateState(paramInt);
/* 53 */           paramCanvas3D.raIsVisible = true;
/* 54 */           renderGeo(paramRenderAtomListInfo, paramRenderMolecule, paramCanvas3D);
/* 55 */           bool = true;
/*    */         } else {
/*    */           
/* 58 */           paramCanvas3D.raIsVisible = false;
/*    */         } 
/* 60 */         paramCanvas3D.ra = paramRenderAtomListInfo.renderAtom;
/*    */       } 
/*    */       
/* 63 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*    */     } 
/* 65 */     geometryArrayRetained.disableGlobalAlpha(paramCanvas3D.ctx, (paramRenderMolecule.useAlpha && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*    */ 
/*    */ 
/*    */     
/* 69 */     return bool;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void renderGeo(RenderAtomListInfo paramRenderAtomListInfo, RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D) {
/* 76 */     boolean bool = paramRenderMolecule.useAlpha;
/*    */     
/* 78 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramRenderAtomListInfo.geometry();
/*    */ 
/*    */     
/* 81 */     geometryArrayRetained.execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, paramRenderMolecule.isNonUniformScale, (bool && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\VertexArrayRenderMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */