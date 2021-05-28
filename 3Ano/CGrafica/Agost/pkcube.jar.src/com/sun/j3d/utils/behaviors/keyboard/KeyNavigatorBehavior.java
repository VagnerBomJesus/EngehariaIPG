/*     */ package com.sun.j3d.utils.behaviors.keyboard;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.util.Enumeration;
/*     */ import java.util.LinkedList;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.WakeupCondition;
/*     */ import javax.media.j3d.WakeupCriterion;
/*     */ import javax.media.j3d.WakeupOnAWTEvent;
/*     */ import javax.media.j3d.WakeupOnBehaviorPost;
/*     */ import javax.media.j3d.WakeupOnElapsedFrames;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyNavigatorBehavior
/*     */   extends Behavior
/*     */   implements KeyListener
/*     */ {
/*     */   private WakeupCriterion w1;
/*     */   private WakeupCriterion w2;
/*     */   private WakeupOnElapsedFrames w3;
/*     */   private WakeupCriterion[] warray;
/*     */   private WakeupCondition w;
/*     */   private KeyEvent eventKey;
/*     */   private KeyNavigator keyNavigator;
/*     */   private boolean listener;
/*     */   private LinkedList eventq;
/*     */   
/*     */   public void initialize() {
/*  78 */     if (this.listener) {
/*  79 */       this.w1 = new WakeupOnBehaviorPost(this, 401);
/*  80 */       this.w2 = new WakeupOnBehaviorPost(this, 402);
/*  81 */       this.warray[0] = this.w1;
/*  82 */       this.warray[1] = this.w2;
/*  83 */       this.w = new WakeupOr(this.warray);
/*  84 */       this.eventq = new LinkedList();
/*     */     } 
/*  86 */     wakeupOn(this.w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/*  96 */     boolean bool = false;
/*     */     
/*  98 */     label31: while (paramEnumeration.hasMoreElements()) {
/*  99 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramEnumeration.nextElement();
/* 100 */       if (wakeupCriterion instanceof WakeupOnAWTEvent) {
/* 101 */         WakeupOnAWTEvent wakeupOnAWTEvent = (WakeupOnAWTEvent)wakeupCriterion;
/* 102 */         AWTEvent[] arrayOfAWTEvent = wakeupOnAWTEvent.getAWTEvent();
/* 103 */         processAWTEvent(arrayOfAWTEvent); continue;
/* 104 */       }  if (wakeupCriterion instanceof WakeupOnElapsedFrames && this.eventKey != null) {
/*     */         
/* 106 */         bool = true; continue;
/* 107 */       }  if (wakeupCriterion instanceof WakeupOnBehaviorPost) {
/*     */         while (true) {
/*     */           
/* 110 */           synchronized (this.eventq) {
/* 111 */             if (this.eventq.isEmpty())
/* 112 */               continue label31;  this.eventKey = (KeyEvent)this.eventq.remove(0);
/* 113 */             if (this.eventKey.getID() == 401 || this.eventKey.getID() == 402)
/*     */             {
/* 115 */               this.keyNavigator.processKeyEvent(this.eventKey);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 121 */     if (bool) {
/* 122 */       this.keyNavigator.integrateTransformChanges();
/*     */     }
/*     */     
/* 125 */     wakeupOn(this.w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processAWTEvent(AWTEvent[] paramArrayOfAWTEvent) {
/* 132 */     for (byte b = 0; b < paramArrayOfAWTEvent.length; b++) {
/* 133 */       if (paramArrayOfAWTEvent[b] instanceof KeyEvent) {
/* 134 */         this.eventKey = (KeyEvent)paramArrayOfAWTEvent[b];
/*     */         
/* 136 */         if (this.eventKey.getID() == 401 || this.eventKey.getID() == 402)
/*     */         {
/*     */           
/* 139 */           this.keyNavigator.processKeyEvent(this.eventKey);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(Component paramComponent) {
/* 156 */     if (!this.listener) {
/* 157 */       throw new IllegalStateException(J3dUtilsI18N.getString("Behavior0"));
/*     */     }
/* 159 */     paramComponent.addKeyListener(this);
/*     */   }
/*     */   public KeyNavigatorBehavior(TransformGroup paramTransformGroup) {
/*     */     this.w1 = new WakeupOnAWTEvent(401);
/*     */     this.w2 = new WakeupOnAWTEvent(402);
/*     */     this.w3 = new WakeupOnElapsedFrames(0);
/*     */     this.warray = new WakeupCriterion[] { this.w1, this.w2, this.w3 };
/*     */     this.w = new WakeupOr(this.warray);
/*     */     this.listener = false;
/* 168 */     this.keyNavigator = new KeyNavigator(paramTransformGroup);
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
/*     */   public KeyNavigatorBehavior(Component paramComponent, TransformGroup paramTransformGroup) {
/* 183 */     this(paramTransformGroup);
/* 184 */     if (paramComponent != null) {
/* 185 */       paramComponent.addKeyListener(this);
/*     */     }
/* 187 */     this.listener = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent paramKeyEvent) {
/* 195 */     synchronized (this.eventq) {
/* 196 */       this.eventq.add(paramKeyEvent);
/*     */       
/* 198 */       if (this.eventq.size() == 1) postId(401);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent paramKeyEvent) {
/* 207 */     synchronized (this.eventq) {
/* 208 */       this.eventq.add(paramKeyEvent);
/*     */       
/* 210 */       if (this.eventq.size() == 1) postId(402); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void keyTyped(KeyEvent paramKeyEvent) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\keyboard\KeyNavigatorBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */