/*     */ package com.sun.j3d.utils.behaviors.mouse;
/*     */ 
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.MouseEvent;
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
/*     */ 
/*     */ public class MouseZoom
/*     */   extends MouseBehavior
/*     */ {
/*  62 */   double z_factor = 0.04D;
/*  63 */   Vector3d translation = new Vector3d();
/*     */   
/*  65 */   private MouseBehaviorCallback callback = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public MouseZoom(TransformGroup paramTransformGroup) { super(paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public MouseZoom() { super(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public MouseZoom(int paramInt) { super(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public MouseZoom(Component paramComponent) { super(paramComponent, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public MouseZoom(Component paramComponent, TransformGroup paramTransformGroup) { super(paramComponent, paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public MouseZoom(Component paramComponent, int paramInt) { super(paramComponent, paramInt); }
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
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 168 */     label30: while (paramEnumeration.hasMoreElements()) {
/* 169 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramEnumeration.nextElement();
/* 170 */       if (wakeupCriterion instanceof WakeupOnAWTEvent) {
/* 171 */         AWTEvent[] arrayOfAWTEvent = ((WakeupOnAWTEvent)wakeupCriterion).getAWTEvent();
/* 172 */         if (arrayOfAWTEvent.length > 0) {
/* 173 */           MouseEvent mouseEvent = (MouseEvent)arrayOfAWTEvent[arrayOfAWTEvent.length - 1];
/* 174 */           doProcess(mouseEvent);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 178 */       if (wakeupCriterion instanceof javax.media.j3d.WakeupOnBehaviorPost) {
/*     */         while (true) {
/* 180 */           MouseEvent mouseEvent; synchronized (this.mouseq) {
/* 181 */             if (this.mouseq.isEmpty())
/* 182 */               continue label30;  mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */ 
/*     */             
/* 185 */             while (mouseEvent.getID() == 506 && !this.mouseq.isEmpty() && ((MouseEvent)this.mouseq.get(0)).getID() == 506)
/*     */             {
/*     */               
/* 188 */               mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */             }
/*     */           } 
/* 191 */           doProcess(mouseEvent);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 196 */     wakeupOn(this.mouseCriterion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void doProcess(MouseEvent paramMouseEvent) {
/* 203 */     processMouseEvent(paramMouseEvent);
/*     */     
/* 205 */     if ((this.buttonPress && (this.flags & true) == 0) || (this.wakeUp && (this.flags & true) != 0)) {
/*     */       
/* 207 */       int i = paramMouseEvent.getID();
/* 208 */       if (i == 506 && paramMouseEvent.isAltDown() && !paramMouseEvent.isMetaDown()) {
/*     */ 
/*     */         
/* 211 */         this.x = paramMouseEvent.getX();
/* 212 */         this.y = paramMouseEvent.getY();
/*     */         
/* 214 */         int j = this.x - this.x_last;
/* 215 */         int k = this.y - this.y_last;
/*     */         
/* 217 */         if (!this.reset) {
/* 218 */           this.transformGroup.getTransform(this.currXform);
/*     */           
/* 220 */           this.translation.z = k * this.z_factor;
/*     */           
/* 222 */           this.transformX.set(this.translation);
/*     */           
/* 224 */           if (this.invert) {
/* 225 */             this.currXform.mul(this.currXform, this.transformX);
/*     */           } else {
/* 227 */             this.currXform.mul(this.transformX, this.currXform);
/*     */           } 
/*     */           
/* 230 */           this.transformGroup.setTransform(this.currXform);
/*     */           
/* 232 */           transformChanged(this.currXform);
/*     */           
/* 234 */           if (this.callback != null) {
/* 235 */             this.callback.transformChanged(2, this.currXform);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 240 */           this.reset = false;
/*     */         } 
/*     */         
/* 243 */         this.x_last = this.x;
/* 244 */         this.y_last = this.y;
/*     */       }
/* 246 */       else if (i == 501) {
/* 247 */         this.x_last = paramMouseEvent.getX();
/* 248 */         this.y_last = paramMouseEvent.getY();
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
/* 268 */   public void setupCallback(MouseBehaviorCallback paramMouseBehaviorCallback) { this.callback = paramMouseBehaviorCallback; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\mouse\MouseZoom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */