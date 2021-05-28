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
/*     */ class ScreenViewCache
/*     */ {
/*     */   Screen3D screen;
/*     */   double physicalScreenWidth;
/*     */   double physicalScreenHeight;
/*     */   int screenWidth;
/*     */   int screenHeight;
/*     */   int[] scrvcDirtyMask;
/*     */   Transform3D trackerBaseToImagePlate;
/*     */   Transform3D headTrackerToLeftImagePlate;
/*     */   Transform3D headTrackerToRightImagePlate;
/*     */   double metersPerPixelX;
/*     */   double metersPerPixelY;
/*     */   
/*     */   void snapshot() {
/*  84 */     if (this.screen.offScreen) {
/*  85 */       this.scrvcDirtyMask[0] = this.scrvcDirtyMask[0] | this.screen.scrDirtyMask;
/*  86 */       this.scrvcDirtyMask[1] = this.scrvcDirtyMask[1] | this.screen.scrDirtyMask;
/*     */     } else {
/*  88 */       this.scrvcDirtyMask[0] = this.screen.scrDirtyMask;
/*  89 */       this.scrvcDirtyMask[1] = this.screen.scrDirtyMask;
/*     */     } 
/*  91 */     this.screen.scrDirtyMask = 0;
/*     */     
/*  93 */     this.physicalScreenWidth = this.screen.physicalScreenWidth;
/*  94 */     this.physicalScreenHeight = this.screen.physicalScreenHeight;
/*  95 */     this.screenWidth = this.screen.screenSize.width;
/*  96 */     this.screenHeight = this.screen.screenSize.height;
/*     */     
/*  98 */     this.screen.trackerBaseToImagePlate.getWithLock(this.trackerBaseToImagePlate);
/*     */     
/* 100 */     this.screen.headTrackerToLeftImagePlate.getWithLock(this.headTrackerToLeftImagePlate);
/*     */     
/* 102 */     this.screen.headTrackerToRightImagePlate.getWithLock(this.headTrackerToRightImagePlate);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     this.metersPerPixelX = this.physicalScreenWidth / this.screenWidth;
/* 109 */     this.metersPerPixelY = this.physicalScreenHeight / this.screenHeight;
/*     */   }
/*     */   
/*     */   ScreenViewCache(Screen3D paramScreen3D) {
/*     */     this.scrvcDirtyMask = new int[2];
/*     */     this.trackerBaseToImagePlate = new Transform3D();
/*     */     this.headTrackerToLeftImagePlate = new Transform3D();
/*     */     this.headTrackerToRightImagePlate = new Transform3D();
/* 117 */     this.screen = paramScreen3D;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ScreenViewCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */