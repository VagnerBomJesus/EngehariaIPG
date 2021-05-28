/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
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
/*     */ public class Screen3D
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   private static final double METERS_PER_PIXEL = 2.8222222222222223E-4D;
/*     */   GraphicsDevice graphicsDevice;
/*     */   boolean offScreen;
/*     */   long display;
/*     */   int screen;
/*     */   double physicalScreenWidth;
/*     */   double physicalScreenHeight;
/*     */   Dimension screenSize;
/*     */   Transform3D trackerBaseToImagePlate;
/*     */   Transform3D headTrackerToLeftImagePlate;
/*     */   Transform3D headTrackerToRightImagePlate;
/*     */   static final int PHYSICAL_SCREEN_SIZE_DIRTY = 1;
/*     */   static final int SCREEN_SIZE_DIRTY_DIRTY = 2;
/*     */   static final int TRACKER_BASE_TO_IMAGE_PLATE_DIRTY = 4;
/*     */   static final int HEAD_TRACKER_TO_IMAGE_PLATE_DIRTY = 8;
/*     */   int scrDirtyMask;
/*     */   ScreenViewCache screenViewCache;
/*     */   Renderer renderer;
/* 154 */   static Hashtable deviceRendererMap = new Hashtable();
/*     */ 
/*     */   
/*     */   int canvasCount;
/*     */ 
/*     */   
/*     */   UnorderList activeViews;
/*     */ 
/*     */   
/*     */   ArrayList users;
/*     */ 
/*     */   
/* 166 */   void addActiveView(View paramView) { this.activeViews.addUnique(paramView); }
/*     */ 
/*     */ 
/*     */   
/* 170 */   void removeActiveView(View paramView) { this.activeViews.remove(paramView); }
/*     */ 
/*     */ 
/*     */   
/* 174 */   boolean activeViewEmpty() { return this.activeViews.isEmpty(); }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeUser(Canvas3D paramCanvas3D) {
/* 179 */     int i = this.users.indexOf(paramCanvas3D);
/* 180 */     if (i >= 0) {
/* 181 */       this.users.remove(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void addUser(Canvas3D paramCanvas3D) {
/* 187 */     int i = this.users.indexOf(paramCanvas3D);
/* 188 */     if (i < 0) {
/* 189 */       this.users.add(paramCanvas3D);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void notifyUsers() {
/* 198 */     for (byte b = 0; b < this.users.size(); b++) {
/* 199 */       Canvas3D canvas3D = (Canvas3D)this.users.get(b);
/* 200 */       canvas3D.redraw();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public Dimension getSize() { return new Dimension(this.screenSize); }
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
/*     */   public Dimension getSize(Dimension paramDimension) {
/* 227 */     if (paramDimension == null) {
/* 228 */       return new Dimension(this.screenSize);
/*     */     }
/*     */     
/* 231 */     paramDimension.setSize(this.screenSize);
/* 232 */     return paramDimension;
/*     */   }
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
/*     */   public void setSize(int paramInt1, int paramInt2) {
/* 254 */     if (!this.offScreen) {
/* 255 */       throw new IllegalStateException(J3dI18N.getString("Screen3D1"));
/*     */     }
/* 257 */     synchronized (this) {
/* 258 */       this.screenSize.width = paramInt1;
/* 259 */       this.screenSize.height = paramInt2;
/* 260 */       this.scrDirtyMask |= 0x2;
/*     */     } 
/*     */   }
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
/*     */   public void setSize(Dimension paramDimension) {
/* 280 */     if (!this.offScreen) {
/* 281 */       throw new IllegalStateException(J3dI18N.getString("Screen3D1"));
/*     */     }
/* 283 */     synchronized (this) {
/* 284 */       this.screenSize.width = paramDimension.width;
/* 285 */       this.screenSize.height = paramDimension.height;
/* 286 */       this.scrDirtyMask |= 0x2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPhysicalScreenWidth(double paramDouble) {
/* 297 */     synchronized (this) {
/* 298 */       this.physicalScreenWidth = paramDouble;
/* 299 */       this.scrDirtyMask |= 0x1;
/*     */     } 
/* 301 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   public double getPhysicalScreenWidth() { return this.physicalScreenWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPhysicalScreenHeight(double paramDouble) {
/* 319 */     synchronized (this) {
/* 320 */       this.physicalScreenHeight = paramDouble;
/* 321 */       this.scrDirtyMask |= 0x1;
/*     */     } 
/* 323 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public double getPhysicalScreenHeight() { return this.physicalScreenHeight; }
/*     */ 
/*     */ 
/*     */   
/* 335 */   public String toString() { return "Screen3D: size = (" + (getSize()).width + " x " + (getSize()).height + ")" + ", physical size = " + "(" + getPhysicalScreenWidth() + "m x " + getPhysicalScreenHeight() + "m)"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/* 344 */     VirtualUniverse.loadLibraries();
/*     */   }
/*     */   
/*     */   Screen3D(GraphicsConfiguration paramGraphicsConfiguration, boolean paramBoolean) {
/*     */     this.screenSize = new Dimension(0, 0);
/*     */     this.trackerBaseToImagePlate = new Transform3D();
/*     */     this.headTrackerToLeftImagePlate = new Transform3D();
/*     */     this.headTrackerToRightImagePlate = new Transform3D();
/*     */     this.scrDirtyMask = 15;
/*     */     this.screenViewCache = null;
/*     */     this.renderer = null;
/*     */     this.canvasCount = 0;
/*     */     this.activeViews = new UnorderList(1, View.class);
/*     */     this.users = new ArrayList();
/* 358 */     this.offScreen = paramBoolean;
/* 359 */     this.graphicsDevice = paramGraphicsConfiguration.getDevice();
/*     */     
/* 361 */     this.screenViewCache = new ScreenViewCache(this);
/*     */ 
/*     */     
/* 364 */     this.display = Pipeline.getPipeline().getDisplay();
/* 365 */     this.screen = Pipeline.getPipeline().getScreen(this.graphicsDevice);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (!paramBoolean) {
/*     */       
/* 374 */       Rectangle rectangle = paramGraphicsConfiguration.getBounds();
/* 375 */       this.screenSize.width = rectangle.width;
/* 376 */       this.screenSize.height = rectangle.height;
/*     */     } 
/*     */ 
/*     */     
/* 380 */     this.physicalScreenWidth = this.screenSize.width * 2.8222222222222223E-4D;
/* 381 */     this.physicalScreenHeight = this.screenSize.height * 2.8222222222222223E-4D;
/*     */   }
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
/*     */   public void setTrackerBaseToImagePlate(Transform3D paramTransform3D) {
/* 394 */     synchronized (this) {
/* 395 */       if (!paramTransform3D.isRigid()) {
/* 396 */         throw new BadTransformException(J3dI18N.getString("Screen3D0"));
/*     */       }
/* 398 */       this.trackerBaseToImagePlate.setWithLock(paramTransform3D);
/* 399 */       this.scrDirtyMask |= 0x4;
/*     */     } 
/* 401 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 411 */   public void getTrackerBaseToImagePlate(Transform3D paramTransform3D) { paramTransform3D.set(this.trackerBaseToImagePlate); }
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
/*     */   public void setHeadTrackerToLeftImagePlate(Transform3D paramTransform3D) {
/* 423 */     synchronized (this) {
/* 424 */       if (!paramTransform3D.isRigid()) {
/* 425 */         throw new BadTransformException(J3dI18N.getString("Screen3D0"));
/*     */       }
/* 427 */       this.headTrackerToLeftImagePlate.setWithLock(paramTransform3D);
/* 428 */       this.scrDirtyMask |= 0x8;
/*     */     } 
/* 430 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 440 */   public void getHeadTrackerToLeftImagePlate(Transform3D paramTransform3D) { paramTransform3D.set(this.headTrackerToLeftImagePlate); }
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
/*     */   public void setHeadTrackerToRightImagePlate(Transform3D paramTransform3D) {
/* 452 */     synchronized (this) {
/* 453 */       if (!paramTransform3D.isRigid()) {
/* 454 */         throw new BadTransformException(J3dI18N.getString("Screen3D0"));
/*     */       }
/* 456 */       this.headTrackerToRightImagePlate.setWithLock(paramTransform3D);
/* 457 */       this.scrDirtyMask |= 0x8;
/*     */     } 
/* 459 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public void getHeadTrackerToRightImagePlate(Transform3D paramTransform3D) { paramTransform3D.set(this.headTrackerToRightImagePlate); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateViewCache() {
/* 478 */     synchronized (this) {
/* 479 */       this.screenViewCache.snapshot();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 487 */   void incCanvasCount() { this.canvasCount++; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 494 */   void decCanvasCount() { this.canvasCount--; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Screen3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */