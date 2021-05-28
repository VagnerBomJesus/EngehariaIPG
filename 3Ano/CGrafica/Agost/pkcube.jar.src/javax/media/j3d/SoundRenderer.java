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
/*    */ class SoundRenderer
/*    */ {
/*    */   void activate(SoundRetained paramSoundRetained, SoundscapeRetained paramSoundscapeRetained) {
/* 24 */     AuralAttributesRetained auralAttributesRetained = paramSoundscapeRetained.attributes.mirrorAa;
/*    */     
/* 26 */     if (paramSoundRetained instanceof BackgroundSoundRetained) {
/* 27 */       System.err.println("Activating BackgroundSoundRetained");
/* 28 */     } else if (paramSoundRetained instanceof ConeSoundRetained) {
/* 29 */       System.err.println("Activating ConeSoundRetained");
/* 30 */     } else if (paramSoundRetained instanceof PointSoundRetained) {
/* 31 */       System.err.println("Activating PointSoundRetained");
/*    */     } 
/* 33 */     if (paramSoundscapeRetained != null) {
/* 34 */       System.err.println("Soundscape is " + paramSoundscapeRetained);
/*    */     } else {
/* 36 */       System.err.println("Soundscape is null");
/*    */     } 
/* 38 */     if (auralAttributesRetained != null) {
/* 39 */       System.err.println("AuralAttributes is " + auralAttributesRetained);
/*    */     } else {
/* 41 */       System.err.println("AuralAttributes is null");
/*    */     } 
/*    */   }
/*    */   
/* 45 */   void update(SoundRetained paramSoundRetained, SoundscapeRetained paramSoundscapeRetained) { AuralAttributesRetained auralAttributesRetained = paramSoundscapeRetained.attributes.mirrorAa; }
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
/*    */   void deactivate(SoundRetained paramSoundRetained) {}
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
/* 72 */   public String toString() { return ""; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\SoundRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */