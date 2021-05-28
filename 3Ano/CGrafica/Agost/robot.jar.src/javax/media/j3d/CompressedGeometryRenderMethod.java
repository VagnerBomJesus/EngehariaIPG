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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class CompressedGeometryRenderMethod
/*    */   implements RenderMethod
/*    */ {
/*    */   public boolean render(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/* 30 */     if (paramRenderMolecule.doInfinite) {
/* 31 */       paramCanvas3D.updateState(paramInt);
/* 32 */       while (paramRenderAtomListInfo != null) {
/* 33 */         renderCompressedGeo(paramRenderAtomListInfo, paramRenderMolecule, paramCanvas3D);
/* 34 */         paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*    */       } 
/* 36 */       return true;
/*    */     } 
/*    */     
/* 39 */     boolean bool = false;
/*    */     
/* 41 */     while (paramRenderAtomListInfo != null) {
/* 42 */       if (paramCanvas3D.ra == paramRenderAtomListInfo.renderAtom) {
/* 43 */         if (paramCanvas3D.raIsVisible) {
/* 44 */           paramCanvas3D.updateState(paramInt);
/* 45 */           renderCompressedGeo(paramRenderAtomListInfo, paramRenderMolecule, paramCanvas3D);
/* 46 */           bool = true;
/*    */         } 
/*    */       } else {
/*    */         
/* 50 */         if (!VirtualUniverse.mc.viewFrustumCulling || paramRenderAtomListInfo.renderAtom.localeVwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/*    */           
/* 52 */           paramCanvas3D.updateState(paramInt);
/* 53 */           paramCanvas3D.raIsVisible = true;
/* 54 */           renderCompressedGeo(paramRenderAtomListInfo, paramRenderMolecule, paramCanvas3D);
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
/*    */     
/* 66 */     return bool;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void renderCompressedGeo(RenderAtomListInfo paramRenderAtomListInfo, RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D) {
/* 74 */     boolean bool = paramRenderMolecule.useAlpha;
/*    */     
/* 76 */     CompressedGeometryRetained compressedGeometryRetained = (CompressedGeometryRetained)paramRenderAtomListInfo.renderAtom.geometryAtom.geometryArray[paramRenderAtomListInfo.index];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 82 */     if (paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors && !paramRenderMolecule.enableLighting && compressedGeometryRetained.mirrorGeometry == null) {
/* 83 */       compressedGeometryRetained.mirrorGeometry = compressedGeometryRetained.getGeometry(true, paramCanvas3D);
/*    */     }
/* 85 */     else if (compressedGeometryRetained.mirrorGeometry == null) {
/*    */ 
/*    */ 
/*    */       
/* 89 */       compressedGeometryRetained.mirrorGeometry = compressedGeometryRetained.getGeometry(false, paramCanvas3D);
/* 90 */       if (compressedGeometryRetained.mirrorGeometry == null) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */     
/* 95 */     compressedGeometryRetained.mirrorGeometry.execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, paramRenderMolecule.isNonUniformScale, (bool && (paramRenderAtomListInfo.geometry()).noAlpha), paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\CompressedGeometryRenderMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */