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
/*    */ class DefaultRenderMethod
/*    */   implements RenderMethod
/*    */ {
/*    */   boolean geometryIsLocked = false;
/*    */   
/*    */   public boolean render(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/* 30 */     boolean bool = false;
/*    */     
/* 32 */     while (paramRenderAtomListInfo != null) {
/* 33 */       if (paramCanvas3D.ra == paramRenderAtomListInfo.renderAtom) {
/* 34 */         if (paramCanvas3D.raIsVisible) {
/* 35 */           paramCanvas3D.updateState(paramInt);
/* 36 */           paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, paramRenderMolecule.isNonUniformScale, paramRenderMolecule.useAlpha, paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 42 */           bool = true;
/*    */         } 
/*    */       } else {
/*    */         
/* 46 */         if (!VirtualUniverse.mc.viewFrustumCulling || paramRenderAtomListInfo.renderAtom.localeVwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/*    */           
/* 48 */           paramCanvas3D.raIsVisible = true;
/* 49 */           paramCanvas3D.updateState(paramInt);
/* 50 */           paramRenderAtomListInfo.geometry().execute(paramCanvas3D, paramRenderAtomListInfo.renderAtom, paramRenderMolecule.isNonUniformScale, paramRenderMolecule.useAlpha, paramRenderMolecule.alpha, paramCanvas3D.screen.screen, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors);
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 55 */           bool = true;
/*    */         } else {
/*    */           
/* 58 */           paramCanvas3D.raIsVisible = false;
/*    */         } 
/* 60 */         paramCanvas3D.ra = paramRenderAtomListInfo.renderAtom;
/*    */       } 
/* 62 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*    */     } 
/*    */     
/* 65 */     return bool;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\DefaultRenderMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */