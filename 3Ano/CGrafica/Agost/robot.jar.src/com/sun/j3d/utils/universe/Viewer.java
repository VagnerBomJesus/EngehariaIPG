/*      */ package com.sun.j3d.utils.universe;
/*      */ 
/*      */ import com.sun.j3d.audioengines.AudioEngine3DL2;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Container;
/*      */ import java.awt.Frame;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.Panel;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.net.URL;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.HashMap;
/*      */ import javax.media.j3d.AudioDevice;
/*      */ import javax.media.j3d.Canvas3D;
/*      */ import javax.media.j3d.GraphicsConfigTemplate3D;
/*      */ import javax.media.j3d.PhysicalBody;
/*      */ import javax.media.j3d.PhysicalEnvironment;
/*      */ import javax.media.j3d.Screen3D;
/*      */ import javax.media.j3d.Transform3D;
/*      */ import javax.media.j3d.View;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JWindow;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Viewer
/*      */ {
/*      */   private static final boolean debug = false;
/*  105 */   private static PhysicalBody physicalBody = null;
/*  106 */   private static PhysicalEnvironment physicalEnvironment = null;
/*  107 */   private View view = null;
/*  108 */   private ViewerAvatar avatar = null;
/*  109 */   private Canvas3D[] canvases = null;
/*  110 */   private JFrame[] j3dJFrames = null;
/*  111 */   private JPanel[] j3dJPanels = null;
/*  112 */   private Window[] j3dWindows = null;
/*  113 */   private ViewingPlatform viewingPlatform = null;
/*      */ 
/*      */   
/*  116 */   static HashMap viewerMap = new HashMap(5);
/*  117 */   private float dvrFactor = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean doDvr = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean doDvrResizeCompensation = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Viewer getViewer(View paramView) {
/*  135 */     Viewer viewer = null;
/*  136 */     synchronized (viewerMap) {
/*      */       
/*  138 */       viewer = (Viewer)viewerMap.get(paramView);
/*      */     } 
/*  140 */     return viewer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Viewer removeViewerMapEntry(View paramView) {
/*  157 */     Viewer viewer = null;
/*  158 */     synchronized (viewerMap) {
/*      */       
/*  160 */       viewer = (Viewer)viewerMap.remove(paramView);
/*      */     } 
/*      */ 
/*      */     
/*  164 */     return viewer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void clearViewerMap() {
/*  178 */     synchronized (viewerMap) {
/*  179 */       viewerMap.clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  197 */   public boolean isDvrEnabled() { return this.doDvr; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDvrEnable(boolean paramBoolean) {
/*  212 */     this.doDvr = paramBoolean;
/*  213 */     this.view.repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  228 */   public float getDvrFactor() { return this.dvrFactor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDvrFactor(float paramFloat) {
/*  244 */     this.dvrFactor = paramFloat;
/*  245 */     this.view.repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDvrResizeCompensationEnable(boolean paramBoolean) {
/*  261 */     this.doDvrResizeCompensation = paramBoolean;
/*  262 */     this.view.repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  276 */   public boolean getDvrResizeCompensationEnable() { return this.doDvrResizeCompensation; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  287 */   public Viewer() { this(null, null, null, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  302 */   public Viewer(Canvas3D paramCanvas3D) { this((paramCanvas3D == null) ? null : new Canvas3D[1], null, null, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  318 */   public Viewer(Canvas3D[] paramArrayOfCanvas3D) { this(paramArrayOfCanvas3D, null, null, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Viewer(Canvas3D[] paramArrayOfCanvas3D, PhysicalBody paramPhysicalBody, PhysicalEnvironment paramPhysicalEnvironment, boolean paramBoolean) {
/*  338 */     if (paramPhysicalBody == null) {
/*  339 */       physicalBody = new PhysicalBody();
/*      */     } else {
/*  341 */       physicalBody = paramPhysicalBody;
/*      */     } 
/*      */     
/*  344 */     if (paramPhysicalEnvironment == null) {
/*  345 */       physicalEnvironment = new PhysicalEnvironment();
/*      */     } else {
/*  347 */       physicalEnvironment = paramPhysicalEnvironment;
/*      */     } 
/*      */ 
/*      */     
/*  351 */     if (paramArrayOfCanvas3D == null) {
/*  352 */       GraphicsConfiguration graphicsConfiguration = ConfiguredUniverse.getPreferredConfiguration();
/*      */ 
/*      */       
/*  355 */       this.canvases = new Canvas3D[1];
/*  356 */       this.canvases[0] = new Canvas3D(graphicsConfiguration);
/*      */       try {
/*  358 */         this.canvases[0].setFocusable(true);
/*  359 */       } catch (NoSuchMethodError noSuchMethodError) {}
/*  360 */       createFramesAndPanels(paramBoolean);
/*      */     } else {
/*      */       
/*  363 */       this.canvases = new Canvas3D[paramArrayOfCanvas3D.length];
/*  364 */       for (byte b1 = 0; b1 < paramArrayOfCanvas3D.length; b1++) {
/*  365 */         this.canvases[b1] = paramArrayOfCanvas3D[b1];
/*      */         try {
/*  367 */           this.canvases[b1].setFocusable(true);
/*  368 */         } catch (NoSuchMethodError noSuchMethodError) {}
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  374 */     this.view = new View();
/*      */ 
/*      */     
/*  377 */     this.view.setUserHeadToVworldEnable(true);
/*      */ 
/*      */     
/*  380 */     synchronized (viewerMap) {
/*  381 */       viewerMap.put(this.view, this);
/*      */     } 
/*  383 */     for (byte b = 0; b < this.canvases.length; b++) {
/*  384 */       this.view.addCanvas3D(this.canvases[b]);
/*      */     }
/*  386 */     this.view.setPhysicalBody(physicalBody);
/*  387 */     this.view.setPhysicalEnvironment(physicalEnvironment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  403 */   public Viewer(URL paramURL) { this(null, paramURL); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Viewer(Canvas3D paramCanvas3D, URL paramURL) {
/*  421 */     if (physicalBody == null) {
/*  422 */       physicalBody = new PhysicalBody();
/*      */     }
/*      */ 
/*      */     
/*  426 */     if (physicalEnvironment == null) {
/*  427 */       physicalEnvironment = new PhysicalEnvironment();
/*      */     }
/*      */ 
/*      */     
/*  431 */     if (paramCanvas3D == null) {
/*  432 */       GraphicsConfiguration graphicsConfiguration = SimpleUniverse.getPreferredConfiguration();
/*      */ 
/*      */       
/*  435 */       this.canvases = new Canvas3D[1];
/*  436 */       this.canvases[0] = new Canvas3D(graphicsConfiguration);
/*  437 */       createFramesAndPanels(true);
/*      */     } else {
/*      */       
/*  440 */       this.canvases = new Canvas3D[1];
/*  441 */       this.canvases[0] = paramCanvas3D;
/*      */     } 
/*      */     
/*      */     try {
/*  445 */       this.canvases[0].setFocusable(true);
/*  446 */     } catch (NoSuchMethodError noSuchMethodError) {}
/*      */ 
/*      */ 
/*      */     
/*  450 */     this.view = new View();
/*      */ 
/*      */     
/*  453 */     this.view.setUserHeadToVworldEnable(true);
/*      */ 
/*      */     
/*  456 */     synchronized (viewerMap) {
/*  457 */       viewerMap.put(this.view, this);
/*      */     } 
/*  459 */     this.view.addCanvas3D(this.canvases[0]);
/*  460 */     this.view.setPhysicalBody(physicalBody);
/*  461 */     this.view.setPhysicalEnvironment(physicalEnvironment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Viewer(ConfigScreen[] paramArrayOfConfigScreen, ConfigView paramConfigView, boolean paramBoolean) {
/*  479 */     this.view = paramConfigView.j3dView;
/*      */     
/*  481 */     synchronized (viewerMap) {
/*  482 */       viewerMap.put(this.view, this);
/*      */     } 
/*      */ 
/*      */     
/*  486 */     physicalBody = paramConfigView.physicalBody;
/*  487 */     physicalEnvironment = paramConfigView.physicalEnvironment;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  504 */     GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*  505 */     GraphicsDevice[] arrayOfGraphicsDevice = graphicsEnvironment.getScreenDevices();
/*      */     
/*  507 */     if (arrayOfGraphicsDevice == null) {
/*  508 */       throw new RuntimeException("No screen devices available in local environment");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  525 */     this.canvases = new Canvas3D[paramArrayOfConfigScreen.length];
/*  526 */     this.j3dJFrames = new JFrame[paramArrayOfConfigScreen.length];
/*  527 */     this.j3dJPanels = new JPanel[paramArrayOfConfigScreen.length];
/*  528 */     this.j3dWindows = new Window[paramArrayOfConfigScreen.length];
/*      */ 
/*      */     
/*  531 */     GraphicsConfigTemplate3D graphicsConfigTemplate3D = new GraphicsConfigTemplate3D();
/*  532 */     if (paramConfigView.stereoEnable) {
/*  533 */       graphicsConfigTemplate3D; graphicsConfigTemplate3D.setStereo(2);
/*      */     } 
/*  535 */     if (paramConfigView.antialiasingEnable) {
/*  536 */       graphicsConfigTemplate3D; graphicsConfigTemplate3D.setSceneAntialiasing(2);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  542 */     for (byte b = 0; b < paramArrayOfConfigScreen.length; b++) {
/*  543 */       Container container; if ((paramArrayOfConfigScreen[b]).frameBufferNumber >= arrayOfGraphicsDevice.length) {
/*  544 */         throw new ArrayIndexOutOfBoundsException(paramArrayOfConfigScreen[b].errorMessage((paramArrayOfConfigScreen[b]).creatingCommand, "Screen " + (paramArrayOfConfigScreen[b]).frameBufferNumber + " is invalid; " + (arrayOfGraphicsDevice.length - 1) + " is the maximum local index."));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  551 */       GraphicsConfiguration graphicsConfiguration1 = arrayOfGraphicsDevice[(paramArrayOfConfigScreen[b]).frameBufferNumber].getBestConfiguration(graphicsConfigTemplate3D);
/*      */ 
/*      */       
/*  554 */       if (graphicsConfiguration1 == null) {
/*  555 */         throw new RuntimeException("No GraphicsConfiguration on screen " + (paramArrayOfConfigScreen[b]).frameBufferNumber + " conforms to template");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  560 */       GraphicsConfiguration graphicsConfiguration2 = graphicsConfiguration1.getDevice().getDefaultConfiguration();
/*  561 */       Rectangle rectangle = graphicsConfiguration2.getBounds();
/*  562 */       (paramArrayOfConfigScreen[b]).j3dJFrame = this.j3dJFrames[b] = new JFrame((paramArrayOfConfigScreen[b]).instanceName, graphicsConfiguration2);
/*      */ 
/*      */       
/*  565 */       if ((paramArrayOfConfigScreen[b]).noBorderFullScreen) {
/*      */         
/*      */         try {
/*  568 */           this.j3dJFrames[b].setUndecorated(true);
/*      */           
/*  570 */           (paramArrayOfConfigScreen[b]).j3dWindow = this.j3dWindows[b] = this.j3dJFrames[b];
/*  571 */           container = this.j3dJFrames[b].getContentPane();
/*      */         }
/*  573 */         catch (NoSuchMethodError noSuchMethodError) {
/*      */           
/*  575 */           JWindow jWindow = new JWindow(this.j3dJFrames[b], graphicsConfiguration1);
/*      */           
/*  577 */           (paramArrayOfConfigScreen[b]).j3dWindow = this.j3dWindows[b] = jWindow;
/*  578 */           container = jWindow.getContentPane();
/*      */         } 
/*      */         
/*  581 */         container.setLayout(new BorderLayout());
/*  582 */         this.j3dWindows[b].setSize(rectangle.width, rectangle.height);
/*  583 */         this.j3dWindows[b].setLocation(rectangle.x, rectangle.y);
/*      */       } else {
/*      */         
/*  586 */         (paramArrayOfConfigScreen[b]).j3dWindow = this.j3dWindows[b] = this.j3dJFrames[b];
/*      */         
/*  588 */         container = this.j3dJFrames[b].getContentPane();
/*  589 */         container.setLayout(new BorderLayout());
/*      */         
/*  591 */         if ((paramArrayOfConfigScreen[b]).fullScreen) {
/*  592 */           this.j3dWindows[b].setSize(rectangle.width, rectangle.height);
/*  593 */           this.j3dWindows[b].setLocation(rectangle.x, rectangle.y);
/*      */         } else {
/*      */           
/*  596 */           this.j3dWindows[b].setSize((paramArrayOfConfigScreen[b]).windowWidthInPixels, (paramArrayOfConfigScreen[b]).windowHeightInPixels);
/*      */           
/*  598 */           this.j3dWindows[b].setLocation(rectangle.x + (paramArrayOfConfigScreen[b]).windowX, rectangle.y + (paramArrayOfConfigScreen[b]).windowY);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  604 */       (paramArrayOfConfigScreen[b]).j3dCanvas = this.canvases[b] = new Canvas3D(graphicsConfiguration1);
/*  605 */       this.canvases[b].setStereoEnable(paramConfigView.stereoEnable);
/*  606 */       this.canvases[b].setMonoscopicViewPolicy((paramArrayOfConfigScreen[b]).monoscopicViewPolicy);
/*      */ 
/*      */       
/*  609 */       Screen3D screen3D = this.canvases[b].getScreen3D();
/*      */       
/*  611 */       if ((paramArrayOfConfigScreen[b]).physicalScreenWidth != 0.0D) {
/*  612 */         screen3D.setPhysicalScreenWidth((paramArrayOfConfigScreen[b]).physicalScreenWidth);
/*      */       }
/*  614 */       if ((paramArrayOfConfigScreen[b]).physicalScreenHeight != 0.0D) {
/*  615 */         screen3D.setPhysicalScreenHeight((paramArrayOfConfigScreen[b]).physicalScreenHeight);
/*      */       }
/*  617 */       if ((paramArrayOfConfigScreen[b]).trackerBaseToImagePlate != null) {
/*  618 */         screen3D.setTrackerBaseToImagePlate(new Transform3D((paramArrayOfConfigScreen[b]).trackerBaseToImagePlate));
/*      */       }
/*      */       
/*  621 */       if ((paramArrayOfConfigScreen[b]).headTrackerToLeftImagePlate != null) {
/*  622 */         screen3D.setHeadTrackerToLeftImagePlate(new Transform3D((paramArrayOfConfigScreen[b]).headTrackerToLeftImagePlate));
/*      */       }
/*      */       
/*  625 */       if ((paramArrayOfConfigScreen[b]).headTrackerToRightImagePlate != null) {
/*  626 */         screen3D.setHeadTrackerToRightImagePlate(new Transform3D((paramArrayOfConfigScreen[b]).headTrackerToRightImagePlate));
/*      */       }
/*      */ 
/*      */       
/*  630 */       (paramArrayOfConfigScreen[b]).j3dJPanel = this.j3dJPanels[b] = new JPanel();
/*  631 */       this.j3dJPanels[b].setLayout(new BorderLayout());
/*  632 */       this.j3dJPanels[b].add("Center", this.canvases[b]);
/*      */ 
/*      */       
/*  635 */       container.add("Center", this.j3dJPanels[b]);
/*      */ 
/*      */       
/*  638 */       this.view.addCanvas3D(this.canvases[b]);
/*      */ 
/*      */       
/*  641 */       addWindowCloseListener(this.j3dWindows[b]);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  647 */         this.canvases[b].setFocusable(true);
/*      */       }
/*  649 */       catch (NoSuchMethodError noSuchMethodError) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  666 */     if (paramBoolean)
/*      */     {
/*  668 */       setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void createFramesAndPanels(boolean paramBoolean) {
/*  674 */     this.j3dJFrames = new JFrame[this.canvases.length];
/*  675 */     this.j3dJPanels = new JPanel[this.canvases.length];
/*  676 */     this.j3dWindows = new Window[this.canvases.length];
/*      */     
/*  678 */     for (byte b = 0; b < this.canvases.length; b++) {
/*  679 */       this.j3dJFrames[b] = new JFrame(); this.j3dWindows[b] = new JFrame();
/*  680 */       this.j3dJFrames[b].getContentPane().setLayout(new BorderLayout());
/*  681 */       this.j3dJFrames[b].setSize(256, 256);
/*      */ 
/*      */       
/*  684 */       this.j3dJPanels[b] = new JPanel();
/*  685 */       this.j3dJPanels[b].setLayout(new BorderLayout());
/*  686 */       this.j3dJPanels[b].add("Center", this.canvases[b]);
/*  687 */       this.j3dJFrames[b].getContentPane().add("Center", this.j3dJPanels[b]);
/*  688 */       if (paramBoolean) {
/*  689 */         this.j3dJFrames[b].setVisible(true);
/*      */       }
/*  691 */       addWindowCloseListener(this.j3dJFrames[b]);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVisible(boolean paramBoolean) {
/*  703 */     for (byte b = 0; b < this.j3dWindows.length; b++) {
/*  704 */       this.j3dWindows[b].setVisible(paramBoolean);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  714 */   public View getView() { return this.view; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setViewingPlatform(ViewingPlatform paramViewingPlatform) {
/*  725 */     if (this.viewingPlatform != null) {
/*  726 */       this.viewingPlatform.removeViewer(this);
/*      */     }
/*      */     
/*  729 */     this.viewingPlatform = paramViewingPlatform;
/*      */     
/*  731 */     if (paramViewingPlatform != null) {
/*  732 */       this.view.attachViewPlatform(paramViewingPlatform.getViewPlatform());
/*  733 */       paramViewingPlatform.addViewer(this);
/*      */       
/*  735 */       if (this.avatar != null) {
/*  736 */         this.viewingPlatform.setAvatar(this, this.avatar);
/*      */       }
/*      */     } else {
/*  739 */       this.view.attachViewPlatform(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  748 */   public ViewingPlatform getViewingPlatform() { return this.viewingPlatform; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAvatar(ViewerAvatar paramViewerAvatar) {
/*  762 */     if (this.avatar == paramViewerAvatar) {
/*      */       return;
/*      */     }
/*  765 */     this.avatar = paramViewerAvatar;
/*  766 */     if (this.viewingPlatform != null) {
/*  767 */       this.viewingPlatform.setAvatar(this, this.avatar);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  779 */   public ViewerAvatar getAvatar() { return this.avatar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  788 */   public PhysicalBody getPhysicalBody() { return physicalBody; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  798 */   public PhysicalEnvironment getPhysicalEnvironment() { return physicalEnvironment; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  809 */   public Canvas3D getCanvas3D() { return this.canvases[0]; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Canvas3D getCanvas3D(int paramInt) {
/*  823 */     if (paramInt > this.canvases.length) {
/*  824 */       return null;
/*      */     }
/*  826 */     return this.canvases[paramInt];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Canvas3D[] getCanvas3Ds() {
/*  837 */     Canvas3D[] arrayOfCanvas3D = new Canvas3D[this.canvases.length];
/*  838 */     for (byte b = 0; b < this.canvases.length; b++) {
/*  839 */       arrayOfCanvas3D[b] = this.canvases[b];
/*      */     }
/*  841 */     return arrayOfCanvas3D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  849 */   public Canvas3D getCanvases() { return getCanvas3D(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  859 */   public Frame getFrame() { throw new UnsupportedOperationException("AWT Frame components are not created by the Viewer class"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JFrame getJFrame(int paramInt) {
/*  881 */     if (this.j3dJFrames == null || paramInt > this.j3dJFrames.length) {
/*  882 */       return null;
/*      */     }
/*  884 */     return this.j3dJFrames[paramInt];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JFrame[] getJFrames() {
/*  904 */     if (this.j3dJFrames == null) {
/*  905 */       return null;
/*      */     }
/*  907 */     JFrame[] arrayOfJFrame = new JFrame[this.j3dJFrames.length];
/*  908 */     for (byte b = 0; b < this.j3dJFrames.length; b++) {
/*  909 */       arrayOfJFrame[b] = this.j3dJFrames[b];
/*      */     }
/*  911 */     return arrayOfJFrame;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  921 */   public Panel getPanel() { throw new UnsupportedOperationException("AWT Panel components are not created by the Viewer class"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JPanel getJPanel(int paramInt) {
/*  937 */     if (this.j3dJPanels == null || paramInt > this.j3dJPanels.length) {
/*  938 */       return null;
/*      */     }
/*  940 */     return this.j3dJPanels[paramInt];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JPanel[] getJPanels() {
/*  954 */     if (this.j3dJPanels == null) {
/*  955 */       return null;
/*      */     }
/*  957 */     JPanel[] arrayOfJPanel = new JPanel[this.j3dJPanels.length];
/*  958 */     for (byte b = 0; b < this.j3dJPanels.length; b++) {
/*  959 */       arrayOfJPanel[b] = this.j3dJPanels[b];
/*      */     }
/*  961 */     return arrayOfJPanel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudioDevice createAudioDevice() {
/*  971 */     if (physicalEnvironment == null) {
/*  972 */       System.err.println("Java 3D: createAudioDevice: physicalEnvironment is null");
/*  973 */       return null;
/*      */     } 
/*      */     
/*      */     try {
/*  977 */       String str = (String)AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run()
/*      */             {
/*  981 */               return System.getProperty("j3d.audiodevice");
/*      */             }
/*      */           });
/*      */       
/*  985 */       if (str == null) {
/*  986 */         throw new UnsupportedOperationException("No AudioDevice specified");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  991 */       Class clazz1 = null;
/*      */       try {
/*  993 */         clazz1 = Class.forName(str);
/*  994 */       } catch (ClassNotFoundException classNotFoundException) {}
/*      */ 
/*      */ 
/*      */       
/*  998 */       if (clazz1 == null) {
/*  999 */         ClassLoader classLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/* 1003 */                 return ClassLoader.getSystemClassLoader();
/*      */               }
/*      */             });
/*      */         
/* 1007 */         if (classLoader == null) {
/* 1008 */           throw new IllegalStateException("System ClassLoader is null");
/*      */         }
/*      */         
/* 1011 */         clazz1 = Class.forName(str, true, classLoader);
/*      */       } 
/*      */       
/* 1014 */       Class clazz2 = PhysicalEnvironment.class;
/* 1015 */       Constructor constructor = clazz1.getConstructor(new Class[] { clazz2 });
/*      */       
/* 1017 */       PhysicalEnvironment[] arrayOfPhysicalEnvironment = { physicalEnvironment };
/* 1018 */       AudioEngine3DL2 audioEngine3DL2 = (AudioEngine3DL2)constructor.newInstance((Object[])arrayOfPhysicalEnvironment);
/*      */       
/* 1020 */       audioEngine3DL2.initialize();
/* 1021 */       return audioEngine3DL2;
/*      */     }
/* 1023 */     catch (Throwable throwable) {
/* 1024 */       throwable.printStackTrace();
/* 1025 */       physicalEnvironment.setAudioDevice(null);
/* 1026 */       System.err.println("Java 3D: audio is disabled");
/* 1027 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1038 */   public SimpleUniverse getUniverse() { return getViewingPlatform().getUniverse(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addWindowCloseListener(Window paramWindow) {
/* 1046 */     SecurityManager securityManager = System.getSecurityManager();
/* 1047 */     boolean bool = true;
/*      */     
/* 1049 */     if (securityManager != null) {
/*      */       try {
/* 1051 */         securityManager.checkExit(0);
/* 1052 */       } catch (SecurityException securityException) {
/* 1053 */         bool = false;
/*      */       } 
/*      */     }
/* 1056 */     final boolean _doExit = bool;
/*      */     
/* 1058 */     paramWindow.addWindowListener(new WindowAdapter() {
/*      */           public void windowClosing(WindowEvent param1WindowEvent) {
/* 1060 */             Window window = param1WindowEvent.getWindow();
/* 1061 */             window.setVisible(false);
/*      */             try {
/* 1063 */               window.dispose();
/* 1064 */             } catch (IllegalStateException illegalStateException) {}
/* 1065 */             if (_doExit)
/* 1066 */               System.exit(0); 
/*      */           }
/*      */         });
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\util\\universe\Viewer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */