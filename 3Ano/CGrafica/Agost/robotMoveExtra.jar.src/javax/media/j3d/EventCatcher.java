/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ 
/*     */ class EventCatcher
/*     */   implements ComponentListener, FocusListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, WindowListener {
/*     */   private Canvas3D canvas;
/*     */   private static final boolean DEBUG = false;
/*     */   private boolean stopped;
/*     */   private boolean focusEvents;
/*     */   private boolean keyEvents;
/*     */   private boolean mouseEvents;
/*     */   private boolean mouseMotionEvents;
/*     */   private boolean mouseWheelEvents;
/*     */   private boolean mouseListenerAdded;
/*     */   
/*     */   EventCatcher(Canvas3D paramCanvas3D) {
/*  30 */     this.stopped = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     this.focusEvents = false;
/*  36 */     this.keyEvents = false;
/*  37 */     this.mouseEvents = false;
/*  38 */     this.mouseMotionEvents = false;
/*  39 */     this.mouseWheelEvents = false;
/*  40 */     this.mouseListenerAdded = false;
/*     */ 
/*     */     
/*  43 */     this.canvas = paramCanvas3D;
/*     */     
/*  45 */     if (VirtualUniverse.mc.isD3D()) {
/*  46 */       enableKeyEvents();
/*     */     }
/*     */   }
/*     */   
/*     */   void enableFocusEvents() {
/*  51 */     if (!this.focusEvents) {
/*  52 */       this.canvas.addFocusListener(this);
/*  53 */       this.focusEvents = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void disableFocusEvents() {
/*  59 */     if (this.focusEvents) {
/*  60 */       this.canvas.removeFocusListener(this);
/*  61 */       this.focusEvents = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   void enableKeyEvents() {
/*  66 */     if (!this.keyEvents) {
/*  67 */       this.canvas.addKeyListener(this);
/*  68 */       this.keyEvents = true;
/*     */       
/*  70 */       if (!this.mouseListenerAdded) {
/*  71 */         this.canvas.addMouseListener(this);
/*  72 */         this.mouseListenerAdded = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void disableKeyEvents() {
/*  78 */     if (this.keyEvents) {
/*  79 */       this.canvas.removeKeyListener(this);
/*  80 */       this.keyEvents = false;
/*     */       
/*  82 */       if (!this.mouseEvents && 
/*  83 */         this.mouseListenerAdded) {
/*  84 */         this.canvas.removeMouseListener(this);
/*  85 */         this.mouseListenerAdded = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void enableMouseEvents() {
/*  94 */     if (!this.mouseEvents) {
/*  95 */       this.mouseEvents = true;
/*  96 */       if (!this.mouseListenerAdded) {
/*  97 */         this.canvas.addMouseListener(this);
/*  98 */         this.mouseListenerAdded = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void disableMouseEvents() {
/* 104 */     if (this.mouseEvents) {
/* 105 */       this.mouseEvents = false;
/* 106 */       if (!this.keyEvents && 
/* 107 */         this.mouseListenerAdded) {
/* 108 */         this.canvas.removeMouseListener(this);
/* 109 */         this.mouseListenerAdded = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void enableMouseMotionEvents() {
/* 116 */     if (!this.mouseMotionEvents) {
/* 117 */       this.canvas.addMouseMotionListener(this);
/* 118 */       this.mouseMotionEvents = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void disableMouseMotionEvents() {
/* 124 */     if (this.mouseMotionEvents) {
/* 125 */       this.canvas.removeMouseMotionListener(this);
/* 126 */       this.mouseMotionEvents = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   void enableMouseWheelEvents() {
/* 131 */     if (!this.mouseWheelEvents) {
/* 132 */       this.canvas.addMouseWheelListener(this);
/* 133 */       this.mouseWheelEvents = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void disableMouseWheelEvents() {
/* 139 */     if (this.mouseWheelEvents) {
/* 140 */       this.canvas.removeMouseWheelListener(this);
/* 141 */       this.mouseWheelEvents = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent paramComponentEvent) {
/* 147 */     if (paramComponentEvent.getSource() == this.canvas) {
/*     */ 
/*     */ 
/*     */       
/* 151 */       this.canvas.sendEventToBehaviorScheduler(paramComponentEvent);
/* 152 */       if (VirtualUniverse.mc.isD3D()) {
/* 153 */         this.canvas.notifyD3DPeer(1);
/*     */       }
/* 155 */       this.canvas.evaluateVisiblilty();
/* 156 */       this.canvas.redraw();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void componentMoved(ComponentEvent paramComponentEvent) {
/* 161 */     if (paramComponentEvent.getSource() == this.canvas)
/*     */     {
/*     */ 
/*     */       
/* 165 */       this.canvas.sendEventToBehaviorScheduler(paramComponentEvent);
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
/*     */   public void componentHidden(ComponentEvent paramComponentEvent) {
/* 179 */     if (paramComponentEvent.getSource() == this.canvas) {
/* 180 */       this.canvas.sendEventToBehaviorScheduler(paramComponentEvent);
/*     */     }
/* 182 */     this.canvas.evaluateVisiblilty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent paramComponentEvent) {
/* 189 */     if (paramComponentEvent.getSource() == this.canvas) {
/* 190 */       this.canvas.sendEventToBehaviorScheduler(paramComponentEvent);
/*     */     }
/* 192 */     this.canvas.evaluateVisiblilty();
/*     */   }
/*     */ 
/*     */   
/* 196 */   public void focusGained(FocusEvent paramFocusEvent) { this.canvas.sendEventToBehaviorScheduler(paramFocusEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public void focusLost(FocusEvent paramFocusEvent) { this.canvas.sendEventToBehaviorScheduler(paramFocusEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public void keyTyped(KeyEvent paramKeyEvent) { this.canvas.sendEventToBehaviorScheduler(paramKeyEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent paramKeyEvent) {
/* 217 */     this.canvas.sendEventToBehaviorScheduler(paramKeyEvent);
/*     */     
/* 219 */     if (VirtualUniverse.mc.isD3D() && paramKeyEvent.isAltDown() && paramKeyEvent.getKeyCode() == 10)
/*     */     {
/*     */       
/* 222 */       this.canvas.notifyD3DPeer(2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent paramKeyEvent) {
/* 231 */     this.canvas.sendEventToBehaviorScheduler(paramKeyEvent);
/* 232 */     if (this.stopped) {
/* 233 */       this.stopped = false;
/*     */     } else {
/* 235 */       this.stopped = true;
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
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) {
/* 250 */     if (this.mouseEvents) {
/* 251 */       this.canvas.sendEventToBehaviorScheduler(paramMouseEvent);
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
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) {
/* 265 */     if (this.mouseEvents) {
/* 266 */       this.canvas.sendEventToBehaviorScheduler(paramMouseEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent paramMouseEvent) {
/* 274 */     if (this.mouseEvents) {
/* 275 */       this.canvas.sendEventToBehaviorScheduler(paramMouseEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent paramMouseEvent) {
/* 282 */     if (this.mouseEvents) {
/* 283 */       this.canvas.sendEventToBehaviorScheduler(paramMouseEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent paramMouseEvent) {
/* 290 */     if (this.mouseEvents) {
/* 291 */       this.canvas.sendEventToBehaviorScheduler(paramMouseEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public void mouseDragged(MouseEvent paramMouseEvent) { this.canvas.sendEventToBehaviorScheduler(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   public void mouseMoved(MouseEvent paramMouseEvent) { this.canvas.sendEventToBehaviorScheduler(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public void mouseWheelMoved(MouseWheelEvent paramMouseWheelEvent) { this.canvas.sendEventToBehaviorScheduler(paramMouseWheelEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public void windowClosed(WindowEvent paramWindowEvent) { this.canvas.sendEventToBehaviorScheduler(paramWindowEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   public void windowClosing(WindowEvent paramWindowEvent) { this.canvas.sendEventToBehaviorScheduler(paramWindowEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 347 */   public void windowActivated(WindowEvent paramWindowEvent) { this.canvas.sendEventToBehaviorScheduler(paramWindowEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public void windowDeactivated(WindowEvent paramWindowEvent) { this.canvas.sendEventToBehaviorScheduler(paramWindowEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowDeiconified(WindowEvent paramWindowEvent) {
/* 361 */     this.canvas.sendEventToBehaviorScheduler(paramWindowEvent);
/* 362 */     if (this.canvas.view != null) {
/* 363 */       this.canvas.view.sendEventToSoundScheduler(paramWindowEvent);
/*     */     }
/* 365 */     this.canvas.evaluateVisiblilty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowIconified(WindowEvent paramWindowEvent) {
/* 372 */     this.canvas.sendEventToBehaviorScheduler(paramWindowEvent);
/* 373 */     if (this.canvas.view != null) {
/* 374 */       this.canvas.view.sendEventToSoundScheduler(paramWindowEvent);
/*     */     }
/* 376 */     this.canvas.evaluateVisiblilty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void windowOpened(WindowEvent paramWindowEvent) {
/* 383 */     this.canvas.sendEventToBehaviorScheduler(paramWindowEvent);
/* 384 */     this.canvas.evaluateVisiblilty();
/*     */   }
/*     */   
/*     */   void reset() {
/* 388 */     this.focusEvents = false;
/* 389 */     this.keyEvents = false;
/* 390 */     this.mouseEvents = false;
/* 391 */     this.mouseMotionEvents = false;
/* 392 */     this.mouseWheelEvents = false;
/* 393 */     this.mouseListenerAdded = false;
/* 394 */     this.stopped = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\EventCatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */