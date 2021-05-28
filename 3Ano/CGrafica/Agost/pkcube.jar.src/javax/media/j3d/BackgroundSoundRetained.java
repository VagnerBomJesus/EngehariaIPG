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
/*    */ class BackgroundSoundRetained
/*    */   extends SoundRetained
/*    */ {
/*    */   BackgroundSoundRetained() {
/* 23 */     this.nodeType = 12;
/* 24 */     this.localBounds = new BoundingBox();
/* 25 */     ((BoundingBox)this.localBounds).setLower(1.0D, 1.0D, 1.0D);
/* 26 */     ((BoundingBox)this.localBounds).setUpper(-1.0D, -1.0D, -1.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\BackgroundSoundRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */