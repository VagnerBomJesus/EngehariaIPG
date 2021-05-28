/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import com.sun.j3d.utils.geometry.Primitive;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.GraphicsConfigTemplate3D;
/*     */ import javax.media.j3d.HiResCoord;
/*     */ import javax.media.j3d.Locale;
/*     */ import javax.media.j3d.View;
/*     */ import javax.media.j3d.VirtualUniverse;
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
/*     */ public class SimpleUniverse
/*     */   extends VirtualUniverse
/*     */ {
/*     */   protected Locale locale;
/*     */   protected Viewer[] viewer;
/*     */   
/*  94 */   public SimpleUniverse() { this(null, 1, null, null); }
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
/* 113 */   public SimpleUniverse(int paramInt) { this(null, paramInt, null, null); }
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
/* 132 */   public SimpleUniverse(Canvas3D paramCanvas3D) { this(null, 1, paramCanvas3D, null); }
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
/* 157 */   public SimpleUniverse(Canvas3D paramCanvas3D, int paramInt) { this(null, paramInt, paramCanvas3D, null); }
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
/* 183 */   public SimpleUniverse(Canvas3D paramCanvas3D, int paramInt, LocaleFactory paramLocaleFactory) { this(null, paramInt, paramCanvas3D, null, paramLocaleFactory); }
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
/* 209 */   public SimpleUniverse(HiResCoord paramHiResCoord, int paramInt, Canvas3D paramCanvas3D, URL paramURL) { this(paramHiResCoord, paramInt, paramCanvas3D, paramURL, null); }
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
/*     */   public SimpleUniverse(HiResCoord paramHiResCoord, int paramInt, Canvas3D paramCanvas3D, URL paramURL, LocaleFactory paramLocaleFactory) {
/*     */     this.viewer = null;
/* 239 */     createLocale(paramHiResCoord, paramLocaleFactory);
/*     */ 
/*     */ 
/*     */     
/* 243 */     ViewingPlatform viewingPlatform = new ViewingPlatform(paramInt);
/* 244 */     viewingPlatform.setUniverse(this);
/* 245 */     this.viewer = new Viewer[1];
/*     */     
/* 247 */     this.viewer[0] = new Viewer(paramCanvas3D);
/* 248 */     this.viewer[0].setViewingPlatform(viewingPlatform);
/*     */ 
/*     */ 
/*     */     
/* 252 */     this.locale.addBranchGraph(viewingPlatform);
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
/* 266 */   public SimpleUniverse(ViewingPlatform paramViewingPlatform, Viewer paramViewer) { this(paramViewingPlatform, paramViewer, null); }
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
/*     */   public SimpleUniverse(ViewingPlatform paramViewingPlatform, Viewer paramViewer, LocaleFactory paramLocaleFactory) {
/*     */     this.viewer = null;
/* 281 */     createLocale(null, paramLocaleFactory);
/* 282 */     paramViewingPlatform.setUniverse(this);
/*     */ 
/*     */     
/* 285 */     this.viewer = new Viewer[1];
/* 286 */     this.viewer[0] = paramViewer;
/*     */ 
/*     */     
/* 289 */     this.viewer[0].setViewingPlatform(paramViewingPlatform);
/*     */ 
/*     */ 
/*     */     
/* 293 */     this.locale.addBranchGraph(paramViewingPlatform);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   SimpleUniverse(HiResCoord paramHiResCoord, LocaleFactory paramLocaleFactory) {
/*     */     this.viewer = null;
/* 300 */     createLocale(paramHiResCoord, paramLocaleFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createLocale(HiResCoord paramHiResCoord, LocaleFactory paramLocaleFactory) {
/* 310 */     if (paramLocaleFactory != null) {
/* 311 */       if (paramHiResCoord != null) {
/* 312 */         this.locale = paramLocaleFactory.createLocale(this, paramHiResCoord);
/*     */       } else {
/* 314 */         this.locale = paramLocaleFactory.createLocale(this);
/*     */       }
/*     */     
/* 317 */     } else if (paramHiResCoord != null) {
/* 318 */       this.locale = new Locale(this, paramHiResCoord);
/*     */     } else {
/* 320 */       this.locale = new Locale(this);
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
/* 331 */   public Locale getLocale() { return this.locale; }
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
/* 342 */   public Viewer getViewer() { return this.viewer[0]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public ViewingPlatform getViewingPlatform() { return this.viewer[0].getViewingPlatform(); }
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
/* 363 */   public Canvas3D getCanvas() { return getCanvas(0); }
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
/* 377 */   public Canvas3D getCanvas(int paramInt) { return this.viewer[0].getCanvas3D(paramInt); }
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
/* 388 */   public void addBranchGraph(BranchGroup paramBranchGroup) { this.locale.addBranchGraph(paramBranchGroup); }
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
/*     */   public static GraphicsConfiguration getPreferredConfiguration() {
/* 400 */     GraphicsConfigTemplate3D graphicsConfigTemplate3D = new GraphicsConfigTemplate3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 407 */     String str = (String)AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run() {
/* 410 */             return System.getProperty("j3d.stereo");
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 415 */     if (str != null) {
/* 416 */       if (str.equals("REQUIRED")) {
/* 417 */         graphicsConfigTemplate3D; graphicsConfigTemplate3D.setStereo(1);
/* 418 */       } else if (str.equals("PREFERRED")) {
/* 419 */         graphicsConfigTemplate3D; graphicsConfigTemplate3D.setStereo(2);
/*     */       } 
/*     */     }
/*     */     
/* 423 */     return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getBestConfiguration(graphicsConfigTemplate3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanup() {
/* 433 */     View view = this.viewer[0].getView();
/*     */ 
/*     */     
/* 436 */     for (int i = view.numCanvas3Ds() - 1; i >= 0; i--) {
/* 437 */       Canvas3D canvas3D = view.getCanvas3D(i);
/* 438 */       if (canvas3D.isOffScreen()) {
/* 439 */         canvas3D.setOffScreenBuffer(null);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 445 */     view.removeAllCanvas3Ds();
/* 446 */     this.viewer[0].setViewingPlatform(null);
/* 447 */     removeAllLocales();
/*     */ 
/*     */     
/* 450 */     Viewer.clearViewerMap();
/* 451 */     Primitive.clearGeometryCache();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\util\\universe\SimpleUniverse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */