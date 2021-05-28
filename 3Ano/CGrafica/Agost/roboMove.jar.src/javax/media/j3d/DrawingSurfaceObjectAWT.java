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
/*     */ class DrawingSurfaceObjectAWT
/*     */   extends DrawingSurfaceObject
/*     */ {
/*  24 */   private long nativeDS = 0L;
/*  25 */   private long dsi = 0L;
/*     */   
/*     */   private boolean doLastUnlock = false;
/*     */   
/*     */   private boolean xineramaDisabled = false;
/*  30 */   private long display = 0L;
/*  31 */   private int screenID = 0;
/*     */   
/*  33 */   private static long nativeAWT = 0L;
/*     */ 
/*     */ 
/*     */   
/*     */   private native boolean lockAWT(long paramLong);
/*     */ 
/*     */ 
/*     */   
/*     */   private native void unlockAWT(long paramLong);
/*     */ 
/*     */   
/*     */   private static native void lockGlobal(long paramLong);
/*     */ 
/*     */   
/*     */   private static native void unlockGlobal(long paramLong);
/*     */ 
/*     */   
/*     */   DrawingSurfaceObjectAWT(Canvas3D paramCanvas3D, long paramLong1, long paramLong2, int paramInt, boolean paramBoolean) {
/*  51 */     super(paramCanvas3D);
/*  52 */     nativeAWT = paramLong1;
/*     */     
/*  54 */     this.display = paramLong2;
/*  55 */     this.screenID = paramInt;
/*  56 */     this.xineramaDisabled = paramBoolean;
/*     */   } private native long getDrawingSurfaceAWT(Canvas3D paramCanvas3D, long paramLong); private native long getDrawingSurfaceInfo(long paramLong);
/*     */   private static native void freeResource(long paramLong1, long paramLong2, long paramLong3);
/*     */   private native int getDrawingSurfaceWindowIdAWT(Canvas3D paramCanvas3D, long paramLong1, long paramLong2, long paramLong3, int paramInt, boolean paramBoolean);
/*     */   boolean renderLock() {
/*  61 */     if (this.onScreen) {
/*  62 */       if (this.nativeDS == 0L) {
/*  63 */         return false;
/*     */       }
/*  65 */       if (lockAWT(this.nativeDS)) {
/*  66 */         this.gotDsiLock = true;
/*  67 */         return true;
/*     */       } 
/*  69 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  73 */     this.gotDsiLock = true;
/*  74 */     lockGlobal(nativeAWT);
/*     */     
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   void unLock() {
/*  81 */     if (this.gotDsiLock) {
/*  82 */       if (this.onScreen) {
/*  83 */         if (this.nativeDS != 0L) {
/*  84 */           unlockAWT(this.nativeDS);
/*  85 */           this.gotDsiLock = false;
/*  86 */           if (this.doLastUnlock) {
/*  87 */             this.nativeDS = 0L;
/*  88 */             this.dsi = 0L;
/*  89 */             this.doLastUnlock = false;
/*     */           } 
/*     */         } 
/*     */       } else {
/*  93 */         unlockGlobal(nativeAWT);
/*  94 */         this.gotDsiLock = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void getDrawingSurfaceObjectInfo() {
/* 102 */     if (this.nativeDS != 0L && this.dsi != 0L) {
/* 103 */       freeResource(nativeAWT, this.nativeDS, this.dsi);
/* 104 */       this.nativeDS = 0L;
/* 105 */       this.dsi = 0L;
/*     */     } 
/*     */ 
/*     */     
/* 109 */     this.nativeDS = getDrawingSurfaceAWT(this.canvas, nativeAWT);
/*     */ 
/*     */     
/* 112 */     if (this.nativeDS != 0L) {
/* 113 */       this.dsi = getDrawingSurfaceInfo(this.nativeDS);
/* 114 */       if (this.dsi != 0L) {
/* 115 */         long l = getDrawingSurfaceWindowIdAWT(this.canvas, this.nativeDS, this.dsi, this.display, this.screenID, this.xineramaDisabled);
/*     */ 
/*     */         
/* 118 */         this.canvas.drawable = new NativeDrawable(l);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void invalidate() {
/* 125 */     if (this.gotDsiLock && this.nativeDS != 0L) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       this.doLastUnlock = true;
/*     */     } else {
/* 133 */       this.nativeDS = 0L;
/* 134 */       this.dsi = 0L;
/*     */     } 
/*     */   }
/*     */   
/*     */   static void freeDrawingSurface(Object paramObject) {
/* 139 */     long[] arrayOfLong = (long[])paramObject;
/* 140 */     freeResource(nativeAWT, arrayOfLong[0], arrayOfLong[1]);
/*     */   }
/*     */ 
/*     */   
/* 144 */   long getDSI() { return this.dsi; }
/*     */ 
/*     */ 
/*     */   
/* 148 */   long getDS() { return this.nativeDS; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\DrawingSurfaceObjectAWT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */