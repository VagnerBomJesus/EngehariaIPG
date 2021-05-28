/*     */ package com.sun.j3d.exp.swing;
/*     */ 
/*     */ import com.sun.j3d.exp.swing.impl.AutoOffScreenCanvas3D;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.InputMethodEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.GraphicsConfigTemplate3D;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.event.AncestorEvent;
/*     */ import javax.swing.event.AncestorListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JCanvas3D
/*     */   extends JPanel
/*     */   implements AncestorListener
/*     */ {
/*     */   public static final int RESIZE_IMMEDIATELY = 0;
/*     */   public static final int RESIZE_DELAYED = 1;
/* 125 */   private static double METERS_PER_PIXEL = 2.8222222222222223E-4D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GraphicsConfigTemplate3D template;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GraphicsConfiguration graphicsConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private InternalCanvas3D canvas;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasBeenAdded;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int resizeMode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int resizeValidationDelay;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GraphicsDevice device;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public JCanvas3D() { this(null, GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public JCanvas3D(GraphicsDevice paramGraphicsDevice) { this(null, paramGraphicsDevice); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public JCanvas3D(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D) { this(paramGraphicsConfigTemplate3D, GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JCanvas3D(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsDevice paramGraphicsDevice) {
/*     */     this.hasBeenAdded = false;
/* 209 */     this.device = paramGraphicsDevice;
/* 210 */     this.template = new GraphicsConfigTemplate3D();
/*     */     
/* 212 */     if (paramGraphicsConfigTemplate3D != null) {
/*     */       
/* 214 */       this.template.setRedSize(paramGraphicsConfigTemplate3D.getRedSize());
/* 215 */       this.template.setGreenSize(paramGraphicsConfigTemplate3D.getGreenSize());
/* 216 */       this.template.setBlueSize(paramGraphicsConfigTemplate3D.getBlueSize());
/* 217 */       this.template.setDepthSize(paramGraphicsConfigTemplate3D.getDepthSize());
/* 218 */       this.template.setSceneAntialiasing(paramGraphicsConfigTemplate3D.getSceneAntialiasing());
/* 219 */       this.template.setStencilSize(paramGraphicsConfigTemplate3D.getStencilSize());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     this.template.setStereo(3);
/* 226 */     this.template.setDoubleBuffer(3);
/*     */     
/* 228 */     this.graphicsConfig = this.device.getBestConfiguration(this.template);
/*     */     
/* 230 */     addAncestorListener(this);
/* 231 */     setDoubleBuffered(false);
/* 232 */     setResizeMode(0);
/* 233 */     setResizeValidationDelay(100);
/*     */ 
/*     */     
/* 236 */     setFocusable(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ancestorAdded(AncestorEvent paramAncestorEvent) {
/* 247 */     Dimension dimension = getSize();
/*     */     
/* 249 */     if (0 == dimension.width) {
/* 250 */       dimension.width = 100;
/*     */     }
/*     */     
/* 253 */     if (0 == dimension.height) {
/* 254 */       dimension.height = 100;
/*     */     }
/*     */     
/* 257 */     createCanvas(dimension.width, dimension.height);
/* 258 */     this.canvas.addNotifyFlag = true;
/* 259 */     this.canvas.addNotify();
/* 260 */     this.hasBeenAdded = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ancestorMoved(AncestorEvent paramAncestorEvent) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ancestorRemoved(AncestorEvent paramAncestorEvent) {
/* 278 */     this.hasBeenAdded = false;
/* 279 */     this.canvas.removeNotify();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computePhysicalDimensions() {
/* 287 */     Rectangle rectangle = this.graphicsConfig.getBounds();
/* 288 */     int i = (int)rectangle.getWidth();
/* 289 */     int j = (int)rectangle.getHeight();
/* 290 */     this.canvas.getScreen3D().setSize(i, j);
/* 291 */     this.canvas.getScreen3D().setPhysicalScreenWidth(i * METERS_PER_PIXEL);
/*     */     
/* 293 */     this.canvas.getScreen3D().setPhysicalScreenHeight(j * METERS_PER_PIXEL);
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
/*     */   void createCanvas(int paramInt1, int paramInt2) {
/* 308 */     if (getParent() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 312 */     if (null != this.canvas) {
/*     */       
/* 314 */       if (paramInt1 != this.canvas.getWidth() || paramInt2 != this.canvas.getHeight()) {
/* 315 */         if (null != this.canvas.getOffScreenBuffer() && null != this.canvas.getOffScreenBuffer().getImage())
/*     */         {
/* 317 */           this.canvas.getOffScreenBuffer().getImage().flush();
/*     */         }
/*     */       } else {
/*     */         
/*     */         return;
/*     */       } 
/*     */     } else {
/* 324 */       this.canvas = new InternalCanvas3D(this.graphicsConfig, this);
/*     */     } 
/*     */     
/* 327 */     createOffScreenBuffer(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createOffScreenBuffer(int paramInt1, int paramInt2) {
/* 338 */     computePhysicalDimensions();
/*     */ 
/*     */     
/* 341 */     BufferedImage bufferedImage = new BufferedImage(paramInt1, paramInt2, 2);
/*     */     
/* 343 */     ImageComponent2D imageComponent2D = new ImageComponent2D(2, bufferedImage, true, false);
/*     */     
/* 345 */     imageComponent2D; imageComponent2D.setCapability(2);
/* 346 */     imageComponent2D; imageComponent2D.setCapability(3);
/*     */     
/* 348 */     this.canvas.stopRenderer();
/*     */ 
/*     */ 
/*     */     
/* 352 */     this.canvas.waitForOffScreenRendering();
/*     */     
/* 354 */     this.canvas.setOffScreenBuffer(imageComponent2D);
/* 355 */     this.canvas.startRenderer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Canvas3D getOffscreenCanvas3D() {
/* 366 */     if (null == this.canvas) {
/* 367 */       createCanvas(getWidth(), getHeight());
/*     */     }
/*     */     
/* 370 */     return this.canvas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public int getResizeMode() { return this.resizeMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public int getResizeValidationDelay() { return this.resizeValidationDelay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics paramGraphics) {
/* 402 */     super.paintComponent(paramGraphics);
/*     */ 
/*     */     
/* 405 */     if (this.hasBeenAdded) {
/* 406 */       if (false == this.canvas.canvasCrashed && true == this.canvas.isRendererRunning())
/*     */       {
/*     */         
/* 409 */         this.canvas.waitForSwap();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 414 */       if (null != this.canvas.bi)
/*     */       {
/*     */         
/* 417 */         paramGraphics.drawImage(this.canvas.bi, 0, 0, getWidth(), getHeight(), null);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processComponentKeyEvent(KeyEvent paramKeyEvent) {
/* 428 */     super.processComponentKeyEvent(paramKeyEvent);
/*     */     
/* 430 */     Object object = paramKeyEvent.getSource();
/* 431 */     paramKeyEvent.setSource(this.canvas);
/* 432 */     this.canvas.processComponentEvent(paramKeyEvent);
/* 433 */     paramKeyEvent.setSource(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processFocusEvent(FocusEvent paramFocusEvent) {
/* 442 */     super.processFocusEvent(paramFocusEvent);
/*     */     
/* 444 */     Object object = paramFocusEvent.getSource();
/* 445 */     paramFocusEvent.setSource(this.canvas);
/* 446 */     this.canvas.processFocusEvent(paramFocusEvent);
/* 447 */     paramFocusEvent.setSource(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processInputMethodEvent(InputMethodEvent paramInputMethodEvent) {
/* 456 */     super.processInputMethodEvent(paramInputMethodEvent);
/*     */     
/* 458 */     Object object = paramInputMethodEvent.getSource();
/* 459 */     paramInputMethodEvent.setSource(this.canvas);
/* 460 */     this.canvas.processInputMethodEvent(paramInputMethodEvent);
/* 461 */     paramInputMethodEvent.setSource(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processKeyEvent(KeyEvent paramKeyEvent) {
/* 470 */     super.processKeyEvent(paramKeyEvent);
/*     */     
/* 472 */     Object object = paramKeyEvent.getSource();
/* 473 */     paramKeyEvent.setSource(this.canvas);
/* 474 */     this.canvas.processKeyEvent(paramKeyEvent);
/* 475 */     paramKeyEvent.setSource(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processMouseEvent(MouseEvent paramMouseEvent) {
/* 484 */     super.processMouseEvent(paramMouseEvent);
/*     */     
/* 486 */     Object object = paramMouseEvent.getSource();
/* 487 */     paramMouseEvent.setSource(this.canvas);
/* 488 */     this.canvas.processMouseEvent(paramMouseEvent);
/* 489 */     paramMouseEvent.setSource(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processMouseMotionEvent(MouseEvent paramMouseEvent) {
/* 498 */     super.processMouseMotionEvent(paramMouseEvent);
/*     */     
/* 500 */     Object object = paramMouseEvent.getSource();
/* 501 */     paramMouseEvent.setSource(this.canvas);
/* 502 */     this.canvas.processMouseMotionEvent(paramMouseEvent);
/* 503 */     paramMouseEvent.setSource(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processMouseWheelEvent(MouseWheelEvent paramMouseWheelEvent) {
/* 512 */     super.processMouseWheelEvent(paramMouseWheelEvent);
/*     */     
/* 514 */     Object object = paramMouseWheelEvent.getSource();
/* 515 */     paramMouseWheelEvent.setSource(this.canvas);
/* 516 */     this.canvas.processMouseWheelEvent(paramMouseWheelEvent);
/* 517 */     paramMouseWheelEvent.setSource(object);
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
/*     */   public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 529 */     super.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
/*     */     
/* 531 */     if (null == this.canvas || null == this.canvas.getOffScreenBuffer() || 0 == getResizeMode()) {
/*     */ 
/*     */       
/* 534 */       createCanvas(paramInt3, paramInt4);
/* 535 */     } else if (1 == getResizeMode() && null != this.canvas.getParent() && true == this.canvas.getParent().isVisible()) {
/*     */ 
/*     */       
/* 538 */       if (null == this.canvas.resizeThread || false == this.canvas.resizeThread.isAlive()) {
/*     */         
/* 540 */         this.canvas.resizeThread = new ResizeThread(paramInt3, paramInt4, getResizeValidationDelay(), this);
/*     */         
/* 542 */         this.canvas.resizeThread.start();
/*     */       } else {
/* 544 */         this.canvas.resizeThread.setWidth(paramInt3);
/* 545 */         this.canvas.resizeThread.setHeight(paramInt4);
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
/* 572 */   public void setResizeMode(int paramInt) { this.resizeMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 588 */   public void setResizeValidationDelay(int paramInt) { this.resizeValidationDelay = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class InternalCanvas3D
/*     */     extends Canvas3D
/*     */     implements AutoOffScreenCanvas3D
/*     */   {
/*     */     private static final int MAX_WAIT_LOOPS = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final long MAX_WAIT_TIME = 100L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 609 */     BufferedImage bi = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     JCanvas3D lwCanvas;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     JCanvas3D.ResizeThread resizeThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean addNotifyFlag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canvasCrashed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean imageReadyBis;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean waitingForSwap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InternalCanvas3D(GraphicsConfiguration param1GraphicsConfiguration, JCanvas3D param1JCanvas3D) {
/* 664 */       super(param1GraphicsConfiguration, true);
/* 665 */       this.lwCanvas = param1JCanvas3D;
/* 666 */       this.imageReadyBis = false;
/* 667 */       this.waitingForSwap = false;
/* 668 */       this.addNotifyFlag = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addNotify() {
/* 675 */       if (false == this.addNotifyFlag) {
/* 676 */         throw new UnsupportedOperationException("CHANGE ME");
/*     */       }
/* 678 */       this.addNotifyFlag = false;
/* 679 */       super.addNotify();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Container getParent() {
/* 694 */       if (null == this.lwCanvas) {
/* 695 */         return null;
/*     */       }
/*     */       
/* 698 */       return this.lwCanvas.getParent();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 705 */     public void postRender() { this.imageReadyBis = false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void postSwap() {
/* 713 */       if (true == isRendererRunning()) {
/* 714 */         this.bi = getOffScreenBuffer().getImage();
/* 715 */         this.imageReadyBis = true;
/*     */         
/* 717 */         if (false == this.waitingForSwap) {
/*     */           
/* 719 */           this.lwCanvas.repaint();
/*     */         } else {
/* 721 */           notify();
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 734 */     protected void processComponentEvent(ComponentEvent param1ComponentEvent) { super.processComponentEvent(param1ComponentEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 743 */     protected void processFocusEvent(FocusEvent param1FocusEvent) { super.processFocusEvent(param1FocusEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 753 */     protected void processInputMethodEvent(InputMethodEvent param1InputMethodEvent) { super.processInputMethodEvent(param1InputMethodEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 762 */     protected void processKeyEvent(KeyEvent param1KeyEvent) { super.processKeyEvent(param1KeyEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 771 */     protected void processMouseEvent(MouseEvent param1MouseEvent) { super.processMouseEvent(param1MouseEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 780 */     protected void processMouseMotionEvent(MouseEvent param1MouseEvent) { super.processMouseMotionEvent(param1MouseEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 789 */     protected void processMouseWheelEvent(MouseWheelEvent param1MouseWheelEvent) { super.processMouseWheelEvent(param1MouseWheelEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void waitForSwap() {
/* 797 */       byte b = 5;
/* 798 */       while (false == this.imageReadyBis) {
/*     */         try {
/* 800 */           this.waitingForSwap = true;
/* 801 */           wait(100L);
/* 802 */           this.waitingForSwap = false;
/*     */           
/* 804 */           if (!this.imageReadyBis && --b <= 0) {
/*     */             
/* 806 */             System.err.println("CANVAS CRASHED!!!");
/* 807 */             this.canvasCrashed = true;
/*     */             return;
/*     */           } 
/* 810 */         } catch (InterruptedException interruptedException) {
/* 811 */           System.err.println(interruptedException);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ResizeSwingRunnable
/*     */     implements Runnable
/*     */   {
/*     */     JCanvas3D canvas;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int height;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int width;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private ResizeSwingRunnable() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ResizeSwingRunnable(JCanvas3D param1JCanvas3D, int param1Int1, int param1Int2) {
/* 846 */       this.canvas = param1JCanvas3D;
/* 847 */       this.width = param1Int1;
/* 848 */       this.height = param1Int2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 855 */     public void run() { this.canvas.createCanvas(this.width, this.height); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ResizeThread
/*     */     extends Thread
/*     */   {
/*     */     JCanvas3D canvas;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean sizeChanged;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int delay;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int height;
/*     */ 
/*     */ 
/*     */     
/*     */     int width;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private ResizeThread() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ResizeThread(int param1Int1, int param1Int2, int param1Int3, JCanvas3D param1JCanvas3D) {
/* 895 */       this.width = param1Int1;
/* 896 */       this.height = param1Int2;
/* 897 */       this.delay = param1Int3;
/* 898 */       this.sizeChanged = true;
/* 899 */       this.canvas = param1JCanvas3D;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 908 */     public int getHeight() { return this.height; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 917 */     public int getWidth() { return this.width; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       try {
/* 925 */         while (true == this.sizeChanged)
/*     */         {
/*     */           
/* 928 */           while (true == this.sizeChanged) {
/* 929 */             this.sizeChanged = false;
/* 930 */             Thread.sleep(this.delay);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 935 */           try { EventQueue.invokeAndWait(new JCanvas3D.ResizeSwingRunnable(this.canvas, this.width, this.height)); }
/*     */           
/* 937 */           catch (InterruptedException interruptedException) {  }
/* 938 */           catch (InvocationTargetException invocationTargetException) {}
/*     */         }
/*     */       
/* 941 */       } catch (InterruptedException interruptedException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setHeight(int param1Int) {
/* 954 */       if (isAlive()) {
/* 955 */         this.height = param1Int;
/* 956 */         this.sizeChanged = true;
/*     */       } else {
/* 958 */         throw new RuntimeException("Resizing order arrived to a dead resizing thread. Spawn a new one.");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setWidth(int param1Int) {
/* 971 */       if (isAlive()) {
/* 972 */         this.width = param1Int;
/* 973 */         this.sizeChanged = true;
/*     */       } else {
/* 975 */         throw new RuntimeException("Resizing order arrived to a dead resizing thread. Spawn a new one.");
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\exp\swing\JCanvas3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */