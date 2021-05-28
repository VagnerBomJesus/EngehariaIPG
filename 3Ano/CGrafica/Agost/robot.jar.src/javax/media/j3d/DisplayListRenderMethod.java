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
/*     */ class DisplayListRenderMethod
/*     */   implements RenderMethod
/*     */ {
/*  25 */   final int bufferSize = 128;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   int[] buffer = new int[128];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean render(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/*  39 */     if (paramRenderMolecule.doInfinite || !VirtualUniverse.mc.viewFrustumCulling || paramRenderMolecule.vwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/*     */ 
/*     */       
/*  42 */       paramCanvas3D.updateState(paramInt);
/*  43 */       paramCanvas3D.callDisplayList(paramCanvas3D.ctx, paramRenderMolecule.displayListId, paramRenderMolecule.isNonUniformScale);
/*     */       
/*  45 */       return true;
/*     */     } 
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderSeparateDlists(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/*  54 */     if (paramRenderMolecule.doInfinite) {
/*  55 */       paramCanvas3D.updateState(paramInt);
/*  56 */       while (paramRenderAtomListInfo != null) {
/*  57 */         paramCanvas3D.callDisplayList(paramCanvas3D.ctx, ((GeometryArrayRetained)paramRenderAtomListInfo.geometry()).dlistId, paramRenderMolecule.isNonUniformScale);
/*     */ 
/*     */         
/*  60 */         paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */       } 
/*     */       
/*  63 */       return true;
/*     */     } 
/*     */     
/*  66 */     boolean bool = false;
/*  67 */     while (paramRenderAtomListInfo != null) {
/*  68 */       if (paramCanvas3D.ra == paramRenderAtomListInfo.renderAtom) {
/*  69 */         if (paramCanvas3D.raIsVisible) {
/*  70 */           paramCanvas3D.updateState(paramInt);
/*  71 */           paramCanvas3D.callDisplayList(paramCanvas3D.ctx, ((GeometryArrayRetained)paramRenderAtomListInfo.geometry()).dlistId, paramRenderMolecule.isNonUniformScale);
/*     */ 
/*     */           
/*  74 */           bool = true;
/*     */         } 
/*     */       } else {
/*     */         
/*  78 */         if (paramRenderAtomListInfo.renderAtom.localeVwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/*  79 */           paramCanvas3D.updateState(paramInt);
/*  80 */           paramCanvas3D.raIsVisible = true;
/*  81 */           paramCanvas3D.callDisplayList(paramCanvas3D.ctx, ((GeometryArrayRetained)paramRenderAtomListInfo.geometry()).dlistId, paramRenderMolecule.isNonUniformScale);
/*     */ 
/*     */           
/*  84 */           bool = true;
/*     */         } else {
/*     */           
/*  87 */           paramCanvas3D.raIsVisible = false;
/*     */         } 
/*  89 */         paramCanvas3D.ra = paramRenderAtomListInfo.renderAtom;
/*     */       } 
/*  91 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */     } 
/*     */     
/*  94 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderSeparateDlistPerRinfo(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D, RenderAtomListInfo paramRenderAtomListInfo, int paramInt) {
/* 104 */     if (paramRenderMolecule.doInfinite) {
/* 105 */       paramCanvas3D.updateState(paramInt);
/* 106 */       while (paramRenderAtomListInfo != null) {
/* 107 */         paramCanvas3D.callDisplayList(paramCanvas3D.ctx, paramRenderAtomListInfo.renderAtom.dlistIds[paramRenderAtomListInfo.index], paramRenderMolecule.isNonUniformScale);
/*     */         
/* 109 */         paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */       } 
/* 111 */       return true;
/*     */     } 
/* 113 */     boolean bool = false;
/* 114 */     while (paramRenderAtomListInfo != null) {
/* 115 */       if (paramCanvas3D.ra == paramRenderAtomListInfo.renderAtom) {
/* 116 */         if (paramCanvas3D.raIsVisible) {
/* 117 */           paramCanvas3D.updateState(paramInt);
/* 118 */           paramCanvas3D.callDisplayList(paramCanvas3D.ctx, paramRenderAtomListInfo.renderAtom.dlistIds[paramRenderAtomListInfo.index], paramRenderMolecule.isNonUniformScale);
/*     */           
/* 120 */           bool = true;
/*     */         } 
/*     */       } else {
/*     */         
/* 124 */         if (paramRenderAtomListInfo.renderAtom.localeVwcBounds.intersect(paramCanvas3D.viewFrustum)) {
/* 125 */           paramCanvas3D.updateState(paramInt);
/* 126 */           paramCanvas3D.raIsVisible = true;
/* 127 */           paramCanvas3D.callDisplayList(paramCanvas3D.ctx, paramRenderAtomListInfo.renderAtom.dlistIds[paramRenderAtomListInfo.index], paramRenderMolecule.isNonUniformScale);
/*     */           
/* 129 */           bool = true;
/*     */         } else {
/*     */           
/* 132 */           paramCanvas3D.raIsVisible = false;
/*     */         } 
/* 134 */         paramCanvas3D.ra = paramRenderAtomListInfo.renderAtom;
/*     */       } 
/* 136 */       paramRenderAtomListInfo = paramRenderAtomListInfo.next;
/*     */     } 
/* 138 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void buildDisplayList(RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D) {
/* 149 */     boolean bool = paramRenderMolecule.useAlpha;
/*     */ 
/*     */ 
/*     */     
/* 153 */     if (paramRenderMolecule.primaryRenderAtomList != null && paramRenderMolecule.texCoordSetMapLen <= paramCanvas3D.maxTexCoordSets) {
/*     */ 
/*     */       
/* 156 */       paramCanvas3D.newDisplayList(paramCanvas3D.ctx, paramRenderMolecule.displayListId);
/*     */       
/* 158 */       RenderAtomListInfo renderAtomListInfo = paramRenderMolecule.primaryRenderAtomList;
/*     */       
/* 160 */       while (renderAtomListInfo != null) {
/* 161 */         Transform3D transform3D2, transform3D1; GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)renderAtomListInfo.geometry();
/* 162 */         if (renderAtomListInfo.renderAtom.geometryAtom.source.staticTransform == null) {
/* 163 */           transform3D1 = null;
/* 164 */           transform3D2 = null;
/*     */         } else {
/* 166 */           transform3D1 = renderAtomListInfo.renderAtom.geometryAtom.source.staticTransform.transform;
/*     */           
/* 168 */           if ((geometryArrayRetained.vertexFormat & 0x2) != 0) {
/* 169 */             transform3D2 = renderAtomListInfo.renderAtom.geometryAtom.source.staticTransform.getNormalTransform();
/*     */           } else {
/*     */             
/* 172 */             transform3D2 = null;
/*     */           } 
/*     */         } 
/* 175 */         geometryArrayRetained.buildGA(paramCanvas3D, renderAtomListInfo.renderAtom, false, (bool && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.alpha, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors, transform3D1, transform3D2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 182 */         renderAtomListInfo = renderAtomListInfo.next;
/*     */       } 
/* 184 */       paramCanvas3D.endDisplayList(paramCanvas3D.ctx);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void buildIndividualDisplayList(RenderAtomListInfo paramRenderAtomListInfo, Canvas3D paramCanvas3D, Context paramContext) {
/* 192 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramRenderAtomListInfo.geometry();
/* 193 */     if (geometryArrayRetained.texCoordSetMap != null && geometryArrayRetained.texCoordSetMap.length > paramCanvas3D.maxTexCoordSets) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 199 */     paramCanvas3D.newDisplayList(paramContext, geometryArrayRetained.dlistId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     geometryArrayRetained.buildGA(paramCanvas3D, paramRenderAtomListInfo.renderAtom, false, false, 1.0F, false, null, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     paramCanvas3D.endDisplayList(paramContext);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void buildDlistPerRinfo(RenderAtomListInfo paramRenderAtomListInfo, RenderMolecule paramRenderMolecule, Canvas3D paramCanvas3D) {
/* 216 */     boolean bool = paramRenderMolecule.useAlpha;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)paramRenderAtomListInfo.geometry();
/* 222 */     if (paramRenderMolecule.primaryRenderAtomList != null && paramRenderMolecule.texCoordSetMapLen <= paramCanvas3D.maxTexCoordSets) {
/*     */       Transform3D transform3D2, transform3D1;
/*     */       
/* 225 */       int i = paramRenderAtomListInfo.renderAtom.dlistIds[paramRenderAtomListInfo.index];
/* 226 */       paramCanvas3D.newDisplayList(paramCanvas3D.ctx, i);
/* 227 */       geometryArrayRetained = (GeometryArrayRetained)paramRenderAtomListInfo.geometry();
/* 228 */       if (paramRenderAtomListInfo.renderAtom.geometryAtom.source.staticTransform == null) {
/* 229 */         transform3D1 = null;
/* 230 */         transform3D2 = null;
/*     */       } else {
/* 232 */         transform3D1 = paramRenderAtomListInfo.renderAtom.geometryAtom.source.staticTransform.transform;
/*     */         
/* 234 */         if ((geometryArrayRetained.vertexFormat & 0x2) != 0) {
/* 235 */           transform3D2 = paramRenderAtomListInfo.renderAtom.geometryAtom.source.staticTransform.getNormalTransform();
/*     */         } else {
/*     */           
/* 238 */           transform3D2 = null;
/*     */         } 
/*     */       } 
/*     */       
/* 242 */       geometryArrayRetained.buildGA(paramCanvas3D, paramRenderAtomListInfo.renderAtom, false, (bool && (geometryArrayRetained.vertexFormat & 0x4) != 0), paramRenderMolecule.alpha, paramRenderMolecule.textureBin.attributeBin.ignoreVertexColors, transform3D1, transform3D2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 249 */       paramCanvas3D.endDisplayList(paramCanvas3D.ctx);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\DisplayListRenderMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */