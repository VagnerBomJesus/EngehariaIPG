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
/*    */ class LightSet
/*    */ {
/*    */   LightRetained[] lights;
/*    */   int nlights;
/*    */   LightSet next;
/*    */   LightSet prev;
/*    */   boolean lightingOn;
/*    */   boolean isDirty;
/*    */   
/*    */   LightSet(RenderBin paramRenderBin, RenderAtom paramRenderAtom, LightRetained[] paramArrayOfLightRetained, int paramInt, boolean paramBoolean) {
/* 22 */     this.lights = null;
/*    */ 
/*    */     
/* 25 */     this.nlights = 0;
/*    */ 
/*    */     
/* 28 */     this.next = null;
/*    */ 
/*    */     
/* 31 */     this.prev = null;
/*    */ 
/*    */     
/* 34 */     this.lightingOn = true;
/*    */ 
/*    */     
/* 37 */     this.isDirty = true;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     reset(paramRenderBin, paramRenderAtom, paramArrayOfLightRetained, paramInt, paramBoolean);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void reset(RenderBin paramRenderBin, RenderAtom paramRenderAtom, LightRetained[] paramArrayOfLightRetained, int paramInt, boolean paramBoolean) {
/* 51 */     this.isDirty = true;
/* 52 */     this.lightingOn = paramBoolean;
/* 53 */     if (this.lights == null || this.lights.length < paramInt) {
/* 54 */       this.lights = new LightRetained[paramInt];
/*    */     }
/*    */     
/* 57 */     for (byte b = 0; b < paramInt; b++) {
/* 58 */       this.lights[b] = paramArrayOfLightRetained[b];
/*    */     }
/*    */     
/* 61 */     this.nlights = paramInt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   boolean equals(RenderBin paramRenderBin, LightRetained[] paramArrayOfLightRetained, int paramInt, boolean paramBoolean) {
/* 72 */     if (this.nlights != paramInt) {
/* 73 */       return false;
/*    */     }
/* 75 */     if (this.lightingOn != paramBoolean) {
/* 76 */       return false;
/*    */     }
/* 78 */     for (byte b = 0; b < paramInt; b++) {
/* 79 */       byte b1; for (b1 = 0; b1 < this.nlights && 
/* 80 */         this.lights[b1] != paramArrayOfLightRetained[b]; b1++);
/*    */ 
/*    */ 
/*    */       
/* 84 */       if (b1 == this.nlights) {
/* 85 */         return false;
/*    */       }
/*    */     } 
/* 88 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\LightSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */