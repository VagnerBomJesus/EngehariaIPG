/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.HiResCoord;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfiguredUniverse
/*     */   extends SimpleUniverse
/*     */ {
/* 107 */   private ConfigContainer configContainer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfiguredUniverse() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public ConfiguredUniverse(int paramInt) { super(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public ConfiguredUniverse(Canvas3D paramCanvas3D) { super(paramCanvas3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public ConfiguredUniverse(Canvas3D paramCanvas3D, int paramInt) { super(paramCanvas3D, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public ConfiguredUniverse(ViewingPlatform paramViewingPlatform, Viewer paramViewer) { super(paramViewingPlatform, paramViewer, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public ConfiguredUniverse(ViewingPlatform paramViewingPlatform, Viewer paramViewer, LocaleFactory paramLocaleFactory) { super(paramViewingPlatform, paramViewer, paramLocaleFactory); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public ConfiguredUniverse(Canvas3D[] paramArrayOfCanvas3D) { this(1, paramArrayOfCanvas3D, null, null, null, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public ConfiguredUniverse(Canvas3D[] paramArrayOfCanvas3D, int paramInt) { this(paramInt, paramArrayOfCanvas3D, null, null, null, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public ConfiguredUniverse(Canvas3D[] paramArrayOfCanvas3D, int paramInt, LocaleFactory paramLocaleFactory) { this(paramInt, paramArrayOfCanvas3D, null, paramLocaleFactory, null, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public ConfiguredUniverse(URL paramURL) { this(1, null, paramURL, null, null, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public ConfiguredUniverse(URL paramURL, int paramInt) { this(paramInt, null, paramURL, null, null, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public ConfiguredUniverse(URL paramURL, int paramInt, boolean paramBoolean) { this(paramInt, null, paramURL, null, null, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public ConfiguredUniverse(URL paramURL, LocaleFactory paramLocaleFactory) { this(1, null, paramURL, paramLocaleFactory, null, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 384 */   public ConfiguredUniverse(URL paramURL, LocaleFactory paramLocaleFactory, boolean paramBoolean) { this(1, null, paramURL, paramLocaleFactory, null, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   public ConfiguredUniverse(URL paramURL, LocaleFactory paramLocaleFactory, HiResCoord paramHiResCoord, int paramInt, boolean paramBoolean) { this(paramInt, null, paramURL, paramLocaleFactory, paramHiResCoord, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 436 */   public ConfiguredUniverse(ConfigContainer paramConfigContainer) { this(paramConfigContainer, null, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfiguredUniverse(ConfigContainer paramConfigContainer, LocaleFactory paramLocaleFactory, HiResCoord paramHiResCoord) {
/* 491 */     super(paramHiResCoord, paramLocaleFactory);
/* 492 */     this.configContainer = paramConfigContainer;
/*     */     
/* 494 */     Set set = this.configContainer.getViewers();
/* 495 */     if (set == null || set.size() == 0) {
/* 496 */       throw new IllegalArgumentException("no views defined in configuration file");
/*     */     }
/*     */     
/* 499 */     this.viewer = (Viewer[])set.toArray(new Viewer[1]);
/*     */     
/* 501 */     set = this.configContainer.getViewingPlatforms();
/* 502 */     if (set == null || set.size() == 0) {
/* 503 */       createDefaultViewingPlatform(this.configContainer.getViewPlatformTransformCount());
/*     */     }
/*     */     else {
/*     */       
/* 507 */       Iterator iterator = set.iterator();
/* 508 */       while (iterator.hasNext()) {
/* 509 */         ViewingPlatform viewingPlatform = (ViewingPlatform)iterator.next();
/* 510 */         viewingPlatform.setUniverse(this);
/* 511 */         this.locale.addBranchGraph(viewingPlatform);
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConfiguredUniverse(int paramInt, Canvas3D[] paramArrayOfCanvas3D, URL paramURL, LocaleFactory paramLocaleFactory, HiResCoord paramHiResCoord, boolean paramBoolean) {
/* 547 */     super(paramHiResCoord, paramLocaleFactory);
/*     */     
/* 549 */     if (paramURL == null) {
/* 550 */       this.viewer = new Viewer[1];
/* 551 */       this.viewer[0] = new Viewer(paramArrayOfCanvas3D, null, null, paramBoolean);
/* 552 */       createDefaultViewingPlatform(paramInt);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 557 */       this.configContainer = new ConfigContainer(paramURL, paramBoolean, paramInt, false);
/*     */ 
/*     */       
/* 560 */       Set set = this.configContainer.getViewers();
/* 561 */       if (set == null || set.size() == 0) {
/* 562 */         throw new IllegalArgumentException("no views defined in configuration file");
/*     */       }
/*     */       
/* 565 */       this.viewer = (Viewer[])set.toArray(new Viewer[1]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 571 */       Collection collection = this.configContainer.findConfigObjects("ViewPlatform");
/* 572 */       if (collection == null || collection.size() == 0) {
/* 573 */         createDefaultViewingPlatform(paramInt);
/*     */       } else {
/*     */         
/* 576 */         Iterator iterator = collection.iterator();
/* 577 */         while (iterator.hasNext()) {
/* 578 */           ConfigViewPlatform configViewPlatform = (ConfigViewPlatform)iterator.next();
/* 579 */           ViewingPlatform viewingPlatform = configViewPlatform.viewingPlatform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 585 */           if (viewingPlatform.getViewers() == null && this.viewer.length == 1 && collection.size() == 1) {
/*     */             
/* 587 */             if (configViewPlatform.viewAttachPolicy == -1) {
/* 588 */               setDerivedAttachPolicy(this.viewer[0], viewingPlatform);
/*     */             }
/* 590 */             this.viewer[0].setViewingPlatform(viewingPlatform);
/*     */           } 
/* 592 */           viewingPlatform.setUniverse(this);
/* 593 */           this.locale.addBranchGraph(viewingPlatform);
/*     */ 
/*     */ 
/*     */           
/* 597 */           configViewPlatform.processBehavior();
/*     */         } 
/*     */       } 
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
/*     */   private void createDefaultViewingPlatform(int paramInt) {
/* 611 */     ViewingPlatform viewingPlatform = new ViewingPlatform(paramInt);
/* 612 */     setDerivedAttachPolicy(this.viewer[0], viewingPlatform);
/* 613 */     this.viewer[0].setViewingPlatform(viewingPlatform);
/* 614 */     viewingPlatform.setUniverse(this);
/* 615 */     this.locale.addBranchGraph(viewingPlatform);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDerivedAttachPolicy(Viewer paramViewer, ViewingPlatform paramViewingPlatform) {
/* 625 */     if (paramViewer.getView().getWindowEyepointPolicy() != 2)
/*     */     {
/* 627 */       paramViewingPlatform.getViewPlatform().setViewAttachPolicy(2);
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
/* 640 */   public Viewer getViewer(int paramInt) { return this.viewer[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Viewer[] getViewers() {
/* 649 */     Viewer[] arrayOfViewer = new Viewer[this.viewer.length];
/* 650 */     for (byte b = 0; b < this.viewer.length; b++) {
/* 651 */       arrayOfViewer[b] = this.viewer[b];
/*     */     }
/* 653 */     return arrayOfViewer;
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
/*     */   public void setVisible(boolean paramBoolean) {
/* 665 */     for (byte b = 0; b < this.viewer.length; b++) {
/* 666 */       if (this.viewer[b] != null) {
/* 667 */         this.viewer[b].setVisible(paramBoolean);
/*     */       }
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
/* 685 */   public static URL getConfigURL() { return ConfigContainer.getConfigURL(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 706 */   public static URL getConfigURL(String paramString) { return ConfigContainer.getConfigURL(paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getNamedSensors() {
/* 738 */     if (this.configContainer == null) {
/* 739 */       return null;
/*     */     }
/* 741 */     return this.configContainer.getNamedSensors();
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
/*     */   public Map getNamedBehaviors() {
/* 754 */     if (this.configContainer == null) {
/* 755 */       return null;
/*     */     }
/* 757 */     return this.configContainer.getNamedViewPlatformBehaviors();
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
/* 768 */   public ConfigContainer getConfigContainer() { return this.configContainer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanup() {
/* 776 */     if (this.viewer != null) {
/* 777 */       for (byte b = 0; b < this.viewer.length; b++) {
/* 778 */         this.viewer[b].getView().removeAllCanvas3Ds();
/* 779 */         this.viewer[b].setViewingPlatform(null);
/* 780 */         this.viewer[b] = null;
/*     */       } 
/*     */     }
/*     */     
/* 784 */     this.locale = null;
/* 785 */     removeAllLocales();
/* 786 */     Viewer.clearViewerMap();
/*     */     
/* 788 */     this.configContainer.clear();
/* 789 */     this.configContainer = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\util\\universe\ConfiguredUniverse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */