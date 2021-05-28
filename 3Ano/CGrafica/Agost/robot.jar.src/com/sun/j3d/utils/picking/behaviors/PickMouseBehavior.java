/*     */ package com.sun.j3d.utils.picking.behaviors;
/*     */ 
/*     */ import com.sun.j3d.utils.picking.PickCanvas;
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
/*     */ public abstract class PickMouseBehavior
/*     */   extends Behavior
/*     */ {
/*     */   protected PickCanvas pickCanvas;
/*     */   protected WakeupCriterion[] conditions;
/*     */   protected WakeupOr wakeupCondition;
/*     */   protected boolean buttonPress;
/*     */   protected TransformGroup currGrp;
/*     */   protected static final boolean debug = false;
/*     */   protected MouseEvent mevent;
/*     */   
/*     */   public PickMouseBehavior(Canvas3D paramCanvas3D, BranchGroup paramBranchGroup, Bounds paramBounds) {
/*  68 */     this.buttonPress = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     this.currGrp = new TransformGroup();
/*  81 */     this.currGrp.setCapability(18);
/*  82 */     this.currGrp.setCapability(17);
/*  83 */     paramBranchGroup.addChild(this.currGrp);
/*  84 */     this.pickCanvas = new PickCanvas(paramCanvas3D, paramBranchGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setMode(int paramInt) { this.pickCanvas.setMode(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public int getMode() { return this.pickCanvas.getMode(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setTolerance(float paramFloat) { this.pickCanvas.setTolerance(paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public float getTolerance() { return this.pickCanvas.getTolerance(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 122 */     this.conditions = new WakeupCriterion[2];
/* 123 */     this.conditions[0] = new WakeupOnAWTEvent(503);
/* 124 */     this.conditions[1] = new WakeupOnAWTEvent(501);
/* 125 */     this.wakeupCondition = new WakeupOr(this.conditions);
/*     */     
/* 127 */     wakeupOn(this.wakeupCondition);
/*     */   }
/*     */   
/*     */   private void processMouseEvent(MouseEvent paramMouseEvent) {
/* 131 */     this.buttonPress = false;
/*     */     
/* 133 */     if (((paramMouseEvent.getID() == 501) ? 1 : 0) | ((paramMouseEvent.getID() == 500) ? 1 : 0)) {
/*     */       
/* 135 */       this.buttonPress = true;
/*     */       return;
/*     */     } 
/* 138 */     if (paramMouseEvent.getID() == 503);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 145 */     AWTEvent[] arrayOfAWTEvent = null;
/* 146 */     int i = 0, j = 0;
/*     */     
/* 148 */     while (paramEnumeration.hasMoreElements()) {
/* 149 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramEnumeration.nextElement();
/* 150 */       if (wakeupCriterion instanceof WakeupOnAWTEvent) {
/* 151 */         arrayOfAWTEvent = ((WakeupOnAWTEvent)wakeupCriterion).getAWTEvent();
/*     */       }
/*     */     } 
/* 154 */     if (arrayOfAWTEvent[0] instanceof MouseEvent) {
/* 155 */       this.mevent = (MouseEvent)arrayOfAWTEvent[0];
/*     */ 
/*     */ 
/*     */       
/* 159 */       processMouseEvent((MouseEvent)arrayOfAWTEvent[0]);
/* 160 */       i = (this.mevent.getPoint()).x;
/* 161 */       j = (this.mevent.getPoint()).y;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     if (this.buttonPress) {
/* 168 */       updateScene(i, j);
/*     */     }
/* 170 */     wakeupOn(this.wakeupCondition);
/*     */   }
/*     */   
/*     */   public abstract void updateScene(int paramInt1, int paramInt2);
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\picking\behaviors\PickMouseBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */