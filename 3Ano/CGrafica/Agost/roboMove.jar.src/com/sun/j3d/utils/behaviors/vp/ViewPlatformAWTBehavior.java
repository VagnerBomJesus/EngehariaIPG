/*     */ package com.sun.j3d.utils.behaviors.vp;
/*     */ 
/*     */ import com.sun.j3d.utils.universe.Viewer;
/*     */ import com.sun.j3d.utils.universe.ViewingPlatform;
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.WakeupCondition;
/*     */ import javax.media.j3d.WakeupOnBehaviorPost;
/*     */ import javax.media.j3d.WakeupOnElapsedFrames;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ViewPlatformAWTBehavior
/*     */   extends ViewPlatformBehavior
/*     */   implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   protected static final int POST_ID = 9998;
/*     */   protected WakeupOnElapsedFrames frameWakeup;
/*     */   protected WakeupOnBehaviorPost postWakeup;
/* 108 */   protected Transform3D targetTransform = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean motion = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MOUSE_LISTENER = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MOUSE_MOTION_LISTENER = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int KEY_LISTENER = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MOUSE_WHEEL_LISTENER = 8;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Canvas3D[] canvases;
/*     */ 
/*     */ 
/*     */   
/* 140 */   private ArrayList eventQueue = new ArrayList();
/* 141 */   private int listenerFlags = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean firstEvent = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ViewPlatformAWTBehavior() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   protected ViewPlatformAWTBehavior(int paramInt) { setListenerFlags(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ViewPlatformAWTBehavior(Canvas3D paramCanvas3D, int paramInt) {
/* 179 */     if (paramCanvas3D == null) {
/* 180 */       throw new NullPointerException();
/*     */     }
/* 182 */     this.canvases = new Canvas3D[] { paramCanvas3D };
/* 183 */     setListenerFlags(paramInt);
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
/* 194 */   protected void setListenerFlags(int paramInt) { this.listenerFlags = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 203 */     this.frameWakeup = new WakeupOnElapsedFrames(0);
/* 204 */     this.postWakeup = new WakeupOnBehaviorPost(this, 9998);
/*     */     
/* 206 */     wakeupOn(this.postWakeup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 215 */     boolean bool = false;
/*     */     
/* 217 */     while (paramEnumeration.hasMoreElements()) {
/* 218 */       WakeupCondition wakeupCondition = (WakeupCondition)paramEnumeration.nextElement();
/* 219 */       if (wakeupCondition instanceof WakeupOnBehaviorPost) {
/* 220 */         bool = true; continue;
/* 221 */       }  if (wakeupCondition instanceof WakeupOnElapsedFrames) {
/* 222 */         AWTEvent[] arrayOfAWTEvent = null;
/*     */         
/* 224 */         synchronized (this.eventQueue) {
/* 225 */           arrayOfAWTEvent = (AWTEvent[])this.eventQueue.toArray(new AWTEvent[this.eventQueue.size()]);
/* 226 */           this.eventQueue.clear();
/*     */         } 
/* 228 */         processAWTEvents(arrayOfAWTEvent);
/*     */         
/* 230 */         if (this.motion) {
/* 231 */           integrateTransforms();
/*     */         }
/*     */       } 
/*     */     } 
/* 235 */     if (this.motion || bool) {
/*     */       
/* 237 */       wakeupOn(this.frameWakeup);
/*     */     } else {
/*     */       
/* 240 */       wakeupOn(this.postWakeup);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnable(boolean paramBoolean) {
/* 251 */     if (paramBoolean == getEnable()) {
/*     */       return;
/*     */     }
/* 254 */     super.setEnable(paramBoolean);
/*     */     
/* 256 */     if (this.canvases != null) {
/* 257 */       enableListeners(paramBoolean);
/*     */     }
/*     */   }
/*     */   
/*     */   private void enableListeners(boolean paramBoolean) {
/* 262 */     if (paramBoolean) {
/* 263 */       this.firstEvent = true;
/* 264 */       if ((this.listenerFlags & true) != 0)
/* 265 */         for (byte b = 0; b < this.canvases.length; b++) {
/* 266 */           this.canvases[b].addMouseListener(this);
/*     */         } 
/* 268 */       if ((this.listenerFlags & 0x2) != 0)
/* 269 */         for (byte b = 0; b < this.canvases.length; b++) {
/* 270 */           this.canvases[b].addMouseMotionListener(this);
/*     */         } 
/* 272 */       if ((this.listenerFlags & 0x8) != 0)
/* 273 */         for (byte b = 0; b < this.canvases.length; b++) {
/* 274 */           this.canvases[b].addMouseWheelListener(this);
/*     */         } 
/* 276 */       if ((this.listenerFlags & 0x4) != 0)
/* 277 */         for (byte b = 0; b < this.canvases.length; b++)
/* 278 */           this.canvases[b].addKeyListener(this);  
/*     */     } else {
/* 280 */       if ((this.listenerFlags & true) != 0)
/* 281 */         for (byte b = 0; b < this.canvases.length; b++) {
/* 282 */           this.canvases[b].removeMouseListener(this);
/*     */         } 
/* 284 */       if ((this.listenerFlags & 0x2) != 0)
/* 285 */         for (byte b = 0; b < this.canvases.length; b++) {
/* 286 */           this.canvases[b].removeMouseMotionListener(this);
/*     */         } 
/* 288 */       if ((this.listenerFlags & 0x8) != 0)
/* 289 */         for (byte b = 0; b < this.canvases.length; b++) {
/* 290 */           this.canvases[b].removeMouseWheelListener(this);
/*     */         } 
/* 292 */       if ((this.listenerFlags & 0x4) != 0) {
/* 293 */         for (byte b = 0; b < this.canvases.length; b++) {
/* 294 */           this.canvases[b].removeKeyListener(this);
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
/*     */   public void setViewingPlatform(ViewingPlatform paramViewingPlatform) {
/* 306 */     super.setViewingPlatform(paramViewingPlatform);
/*     */     
/* 308 */     if (paramViewingPlatform == null) {
/* 309 */       enableListeners(false);
/*     */     } else {
/* 311 */       if (this.canvases != null)
/*     */       {
/* 313 */         enableListeners(false);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 318 */       Viewer[] arrayOfViewer = paramViewingPlatform.getViewers();
/*     */       
/* 320 */       if (arrayOfViewer != null && arrayOfViewer[false] != null) {
/* 321 */         this.canvases = arrayOfViewer[0].getCanvas3Ds();
/*     */       }
/* 323 */       if (this.canvases == null || this.canvases[false] == null) {
/* 324 */         throw new IllegalStateException("No canvases available");
/*     */       }
/* 326 */       if (getEnable()) {
/* 327 */         enableListeners(true);
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
/*     */   protected abstract void processAWTEvents(AWTEvent[] paramArrayOfAWTEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void integrateTransforms();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void queueAWTEvent(AWTEvent paramAWTEvent) {
/* 360 */     synchronized (this.eventQueue) {
/* 361 */       this.eventQueue.add(paramAWTEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 366 */       if (this.firstEvent || this.eventQueue.size() == 1) {
/* 367 */         this.firstEvent = false;
/* 368 */         postId(9998);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 374 */   public void mouseClicked(MouseEvent paramMouseEvent) { queueAWTEvent(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */   
/* 378 */   public void mouseEntered(MouseEvent paramMouseEvent) { queueAWTEvent(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */   
/* 382 */   public void mouseExited(MouseEvent paramMouseEvent) { queueAWTEvent(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */   
/* 386 */   public void mousePressed(MouseEvent paramMouseEvent) { queueAWTEvent(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */   
/* 390 */   public void mouseReleased(MouseEvent paramMouseEvent) { queueAWTEvent(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */   
/* 394 */   public void mouseDragged(MouseEvent paramMouseEvent) { queueAWTEvent(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */   
/* 398 */   public void mouseMoved(MouseEvent paramMouseEvent) { queueAWTEvent(paramMouseEvent); }
/*     */ 
/*     */ 
/*     */   
/* 402 */   public void keyReleased(KeyEvent paramKeyEvent) { queueAWTEvent(paramKeyEvent); }
/*     */ 
/*     */ 
/*     */   
/* 406 */   public void keyPressed(KeyEvent paramKeyEvent) { queueAWTEvent(paramKeyEvent); }
/*     */ 
/*     */ 
/*     */   
/* 410 */   public void keyTyped(KeyEvent paramKeyEvent) { queueAWTEvent(paramKeyEvent); }
/*     */ 
/*     */ 
/*     */   
/* 414 */   public void mouseWheelMoved(MouseWheelEvent paramMouseWheelEvent) { queueAWTEvent(paramMouseWheelEvent); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\behaviors\vp\ViewPlatformAWTBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */