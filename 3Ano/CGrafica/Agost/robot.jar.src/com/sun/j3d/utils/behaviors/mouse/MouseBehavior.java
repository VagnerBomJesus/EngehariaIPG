/*     */ package com.sun.j3d.utils.behaviors.mouse;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.util.Enumeration;
/*     */ import java.util.LinkedList;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.WakeupCriterion;
/*     */ import javax.media.j3d.WakeupOnAWTEvent;
/*     */ import javax.media.j3d.WakeupOnBehaviorPost;
/*     */ import javax.media.j3d.WakeupOr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MouseBehavior
/*     */   extends Behavior
/*     */   implements MouseListener, MouseMotionListener, MouseWheelListener
/*     */ {
/*     */   private boolean listener;
/*     */   protected WakeupCriterion[] mouseEvents;
/*     */   protected WakeupOr mouseCriterion;
/*     */   protected int x;
/*     */   protected int y;
/*     */   protected int x_last;
/*     */   protected int y_last;
/*     */   protected TransformGroup transformGroup;
/*     */   protected Transform3D transformX;
/*     */   protected Transform3D transformY;
/*     */   protected Transform3D currXform;
/*     */   protected boolean buttonPress;
/*     */   protected boolean reset;
/*     */   protected boolean invert;
/*     */   protected boolean wakeUp;
/*     */   protected int flags;
/*     */   protected LinkedList mouseq;
/*     */   protected boolean enable;
/*     */   public static final int MANUAL_WAKEUP = 1;
/*     */   public static final int INVERT_INPUT = 2;
/*     */   
/*     */   public MouseBehavior(TransformGroup paramTransformGroup) {
/*  64 */     this.listener = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     this.buttonPress = false;
/*  75 */     this.reset = false;
/*  76 */     this.invert = false;
/*  77 */     this.wakeUp = false;
/*  78 */     this.flags = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     this.enable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.transformGroup = paramTransformGroup;
/* 106 */     this.currXform = new Transform3D();
/* 107 */     this.transformX = new Transform3D();
/* 108 */     this.transformY = new Transform3D();
/* 109 */     this.reset = true;
/*     */   }
/*     */   
/*     */   public MouseBehavior(int paramInt) {
/*     */     this.listener = false;
/*     */     this.buttonPress = false;
/*     */     this.reset = false;
/*     */     this.invert = false;
/*     */     this.wakeUp = false;
/*     */     this.flags = 0;
/*     */     this.enable = true;
/* 120 */     this.flags = paramInt;
/* 121 */     this.currXform = new Transform3D();
/* 122 */     this.transformX = new Transform3D();
/* 123 */     this.transformY = new Transform3D();
/* 124 */     this.reset = true;
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
/*     */   public MouseBehavior(Component paramComponent, TransformGroup paramTransformGroup) {
/* 140 */     this(paramTransformGroup);
/* 141 */     if (paramComponent != null) {
/* 142 */       paramComponent.addMouseListener(this);
/* 143 */       paramComponent.addMouseMotionListener(this);
/* 144 */       paramComponent.addMouseWheelListener(this);
/*     */     } 
/* 146 */     this.listener = true;
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
/*     */   public MouseBehavior(Component paramComponent, int paramInt) {
/* 162 */     this(paramInt);
/* 163 */     if (paramComponent != null) {
/* 164 */       paramComponent.addMouseListener(this);
/* 165 */       paramComponent.addMouseMotionListener(this);
/* 166 */       paramComponent.addMouseWheelListener(this);
/*     */     } 
/* 168 */     this.listener = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransformGroup(TransformGroup paramTransformGroup) {
/* 179 */     this.transformGroup = paramTransformGroup;
/* 180 */     this.currXform = new Transform3D();
/* 181 */     this.transformX = new Transform3D();
/* 182 */     this.transformY = new Transform3D();
/* 183 */     this.reset = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public TransformGroup getTransformGroup() { return this.transformGroup; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 197 */     this.mouseEvents = new WakeupCriterion[4];
/*     */     
/* 199 */     if (!this.listener) {
/* 200 */       this.mouseEvents[0] = new WakeupOnAWTEvent(506);
/* 201 */       this.mouseEvents[1] = new WakeupOnAWTEvent(501);
/* 202 */       this.mouseEvents[2] = new WakeupOnAWTEvent(502);
/* 203 */       this.mouseEvents[3] = new WakeupOnAWTEvent(507);
/*     */     } else {
/*     */       
/* 206 */       this.mouseEvents[0] = new WakeupOnBehaviorPost(this, 506);
/*     */       
/* 208 */       this.mouseEvents[1] = new WakeupOnBehaviorPost(this, 501);
/*     */       
/* 210 */       this.mouseEvents[2] = new WakeupOnBehaviorPost(this, 502);
/*     */       
/* 212 */       this.mouseEvents[3] = new WakeupOnBehaviorPost(this, 507);
/*     */       
/* 214 */       this.mouseq = new LinkedList();
/*     */     } 
/* 216 */     this.mouseCriterion = new WakeupOr(this.mouseEvents);
/* 217 */     wakeupOn(this.mouseCriterion);
/* 218 */     this.x = 0;
/* 219 */     this.y = 0;
/* 220 */     this.x_last = 0;
/* 221 */     this.y_last = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public void wakeup() { this.wakeUp = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processMouseEvent(MouseEvent paramMouseEvent) {
/* 238 */     if (paramMouseEvent.getID() == 501) {
/* 239 */       this.buttonPress = true;
/*     */       return;
/*     */     } 
/* 242 */     if (paramMouseEvent.getID() == 502) {
/* 243 */       this.buttonPress = false;
/* 244 */       this.wakeUp = false;
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
/*     */   public abstract void processStimulus(Enumeration paramEnumeration);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(Component paramComponent) {
/* 273 */     if (!this.listener) {
/* 274 */       throw new IllegalStateException(J3dUtilsI18N.getString("Behavior0"));
/*     */     }
/* 276 */     paramComponent.addMouseListener(this);
/* 277 */     paramComponent.addMouseMotionListener(this);
/* 278 */     paramComponent.addMouseWheelListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) {}
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) {}
/*     */   
/*     */   public void mouseExited(MouseEvent paramMouseEvent) {}
/*     */   
/*     */   public void mousePressed(MouseEvent paramMouseEvent) {
/* 290 */     if (this.enable) {
/* 291 */       synchronized (this.mouseq) {
/* 292 */         this.mouseq.add(paramMouseEvent);
/*     */         
/* 294 */         if (this.mouseq.size() == 1) {
/* 295 */           postId(501);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent paramMouseEvent) {
/* 305 */     if (this.enable) {
/* 306 */       synchronized (this.mouseq) {
/* 307 */         this.mouseq.add(paramMouseEvent);
/*     */         
/* 309 */         if (this.mouseq.size() == 1) {
/* 310 */           postId(502);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseDragged(MouseEvent paramMouseEvent) {
/* 320 */     if (this.enable)
/* 321 */       synchronized (this.mouseq) {
/* 322 */         this.mouseq.add(paramMouseEvent);
/*     */         
/* 324 */         if (this.mouseq.size() == 1) {
/* 325 */           postId(506);
/*     */         }
/*     */       }  
/*     */   }
/*     */   
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {}
/*     */   
/*     */   public void setEnable(boolean paramBoolean) {
/* 333 */     super.setEnable(paramBoolean);
/* 334 */     this.enable = paramBoolean;
/* 335 */     if (!this.enable && this.mouseq != null) {
/* 336 */       this.mouseq.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseWheelMoved(MouseWheelEvent paramMouseWheelEvent) {
/* 341 */     System.out.println("MouseBehavior : mouseWheel enable = " + this.enable);
/*     */ 
/*     */ 
/*     */     
/* 345 */     if (this.enable)
/* 346 */       synchronized (this.mouseq) {
/* 347 */         this.mouseq.add(paramMouseWheelEvent);
/*     */         
/* 349 */         if (this.mouseq.size() == 1)
/* 350 */           postId(507); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\mouse\MouseBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */