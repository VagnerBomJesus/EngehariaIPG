/*     */ package com.sun.j3d.utils.behaviors.mouse;
/*     */ 
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.util.Enumeration;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.WakeupCriterion;
/*     */ import javax.media.j3d.WakeupOnAWTEvent;
/*     */ import javax.vecmath.Vector3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MouseWheelZoom
/*     */   extends MouseBehavior
/*     */ {
/*  62 */   double z_factor = 0.1D;
/*  63 */   Vector3d translation = new Vector3d();
/*     */   
/*  65 */   private MouseBehaviorCallback callback = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public MouseWheelZoom(TransformGroup paramTransformGroup) { super(paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public MouseWheelZoom() { super(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public MouseWheelZoom(int paramInt) { super(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public MouseWheelZoom(Component paramComponent) { super(paramComponent, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public MouseWheelZoom(Component paramComponent, TransformGroup paramTransformGroup) { super(paramComponent, paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public MouseWheelZoom(Component paramComponent, int paramInt) { super(paramComponent, paramInt); }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 139 */     super.initialize();
/* 140 */     if ((this.flags & 0x2) == 2) {
/* 141 */       this.z_factor *= -1.0D;
/* 142 */       this.invert = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public double getFactor() { return this.z_factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public void setFactor(double paramDouble) { this.z_factor = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 166 */     label30: while (paramEnumeration.hasMoreElements()) {
/* 167 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramEnumeration.nextElement();
/* 168 */       if (wakeupCriterion instanceof WakeupOnAWTEvent) {
/* 169 */         AWTEvent[] arrayOfAWTEvent = ((WakeupOnAWTEvent)wakeupCriterion).getAWTEvent();
/* 170 */         if (arrayOfAWTEvent.length > 0) {
/* 171 */           MouseEvent mouseEvent = (MouseEvent)arrayOfAWTEvent[arrayOfAWTEvent.length - 1];
/* 172 */           doProcess(mouseEvent);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 176 */       if (wakeupCriterion instanceof javax.media.j3d.WakeupOnBehaviorPost) {
/*     */         while (true) {
/* 178 */           MouseEvent mouseEvent; synchronized (this.mouseq) {
/* 179 */             if (this.mouseq.isEmpty())
/* 180 */               continue label30;  mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */ 
/*     */             
/* 183 */             while (mouseEvent.getID() == 507 && !this.mouseq.isEmpty() && ((MouseEvent)this.mouseq.get(0)).getID() == 507)
/*     */             {
/*     */               
/* 186 */               mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */             }
/*     */           } 
/* 189 */           doProcess(mouseEvent);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 194 */     wakeupOn(this.mouseCriterion);
/*     */   }
/*     */   
/*     */   void doProcess(MouseEvent paramMouseEvent) {
/* 198 */     int i = 0;
/*     */     
/* 200 */     processMouseEvent(paramMouseEvent);
/*     */     
/* 202 */     if (paramMouseEvent.getID() == 507) {
/* 203 */       MouseWheelEvent mouseWheelEvent = (MouseWheelEvent)paramMouseEvent;
/* 204 */       mouseWheelEvent; if (mouseWheelEvent.getScrollType() == 0) {
/* 205 */         i = mouseWheelEvent.getUnitsToScroll();
/*     */       }
/*     */       
/* 208 */       if (!this.reset) {
/* 209 */         this.transformGroup.getTransform(this.currXform);
/*     */         
/* 211 */         this.translation.z = i * this.z_factor;
/*     */         
/* 213 */         this.transformX.set(this.translation);
/*     */         
/* 215 */         if (this.invert) {
/* 216 */           this.currXform.mul(this.currXform, this.transformX);
/*     */         } else {
/* 218 */           this.currXform.mul(this.transformX, this.currXform);
/*     */         } 
/*     */         
/* 221 */         this.transformGroup.setTransform(this.currXform);
/*     */         
/* 223 */         transformChanged(this.currXform);
/*     */         
/* 225 */         if (this.callback != null) {
/* 226 */           this.callback.transformChanged(2, this.currXform);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 231 */         this.reset = false;
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
/*     */   public void transformChanged(Transform3D paramTransform3D) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   public void setupCallback(MouseBehaviorCallback paramMouseBehaviorCallback) { this.callback = paramMouseBehaviorCallback; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\behaviors\mouse\MouseWheelZoom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */