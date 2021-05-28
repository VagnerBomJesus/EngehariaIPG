/*     */ package com.sun.j3d.utils.behaviors.picking;
/*     */ 
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Enumeration;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.Bounds;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.WakeupCriterion;
/*     */ import javax.media.j3d.WakeupOnAWTEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PickMouseBehavior
/*     */   extends Behavior
/*     */ {
/*     */   protected PickObject pickScene;
/*     */   protected WakeupCriterion[] conditions;
/*     */   protected WakeupOr wakeupCondition;
/*     */   protected boolean buttonPress;
/*     */   protected TransformGroup currGrp;
/*     */   protected static final boolean debug = false;
/*     */   protected MouseEvent mevent;
/*     */   
/*     */   public PickMouseBehavior(Canvas3D paramCanvas3D, BranchGroup paramBranchGroup, Bounds paramBounds) {
/*  76 */     this.buttonPress = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.currGrp = new TransformGroup();
/*  89 */     this.currGrp.setCapability(18);
/*  90 */     this.currGrp.setCapability(17);
/*  91 */     paramBranchGroup.addChild(this.currGrp);
/*  92 */     this.pickScene = new PickObject(paramCanvas3D, paramBranchGroup);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  97 */     this.conditions = new WakeupCriterion[2];
/*  98 */     this.conditions[0] = new WakeupOnAWTEvent(503);
/*  99 */     this.conditions[1] = new WakeupOnAWTEvent(501);
/* 100 */     this.wakeupCondition = new WakeupOr(this.conditions);
/*     */     
/* 102 */     wakeupOn(this.wakeupCondition);
/*     */   }
/*     */   
/*     */   private void processMouseEvent(MouseEvent paramMouseEvent) {
/* 106 */     this.buttonPress = false;
/*     */     
/* 108 */     if (((paramMouseEvent.getID() == 501) ? 1 : 0) | ((paramMouseEvent.getID() == 500) ? 1 : 0)) {
/*     */       
/* 110 */       this.buttonPress = true;
/*     */       return;
/*     */     } 
/* 113 */     if (paramMouseEvent.getID() == 503);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 120 */     AWTEvent[] arrayOfAWTEvent = null;
/* 121 */     int i = 0, j = 0;
/*     */     
/* 123 */     while (paramEnumeration.hasMoreElements()) {
/* 124 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramEnumeration.nextElement();
/* 125 */       if (wakeupCriterion instanceof WakeupOnAWTEvent) {
/* 126 */         arrayOfAWTEvent = ((WakeupOnAWTEvent)wakeupCriterion).getAWTEvent();
/*     */       }
/*     */     } 
/* 129 */     if (arrayOfAWTEvent[0] instanceof MouseEvent) {
/* 130 */       this.mevent = (MouseEvent)arrayOfAWTEvent[0];
/*     */ 
/*     */ 
/*     */       
/* 134 */       processMouseEvent((MouseEvent)arrayOfAWTEvent[0]);
/* 135 */       i = (this.mevent.getPoint()).x;
/* 136 */       j = (this.mevent.getPoint()).y;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     if (this.buttonPress) {
/* 143 */       updateScene(i, j);
/*     */     }
/* 145 */     wakeupOn(this.wakeupCondition);
/*     */   }
/*     */   
/*     */   public abstract void updateScene(int paramInt1, int paramInt2);
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\picking\PickMouseBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */