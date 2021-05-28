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
/*     */ public class MouseTranslate
/*     */   extends MouseBehavior
/*     */ {
/*  61 */   double x_factor = 0.02D;
/*  62 */   double y_factor = 0.02D;
/*  63 */   Vector3d translation = new Vector3d();
/*     */   
/*  65 */   private MouseBehaviorCallback callback = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public MouseTranslate(TransformGroup paramTransformGroup) { super(paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public MouseTranslate() { super(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public MouseTranslate(int paramInt) { super(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public MouseTranslate(Component paramComponent) { super(paramComponent, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public MouseTranslate(Component paramComponent, TransformGroup paramTransformGroup) { super(paramComponent, paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public MouseTranslate(Component paramComponent, int paramInt) { super(paramComponent, paramInt); }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 140 */     super.initialize();
/* 141 */     if ((this.flags & 0x2) == 2) {
/* 142 */       this.invert = true;
/* 143 */       this.x_factor *= -1.0D;
/* 144 */       this.y_factor *= -1.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public double getXFactor() { return this.x_factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public double getYFactor() { return this.y_factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setFactor(double paramDouble) { this.x_factor = this.y_factor = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFactor(double paramDouble1, double paramDouble2) {
/* 174 */     this.x_factor = paramDouble1;
/* 175 */     this.y_factor = paramDouble2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 185 */     label30: while (paramEnumeration.hasMoreElements()) {
/* 186 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramEnumeration.nextElement();
/*     */       
/* 188 */       if (wakeupCriterion instanceof WakeupOnAWTEvent) {
/* 189 */         AWTEvent[] arrayOfAWTEvent = ((WakeupOnAWTEvent)wakeupCriterion).getAWTEvent();
/* 190 */         if (arrayOfAWTEvent.length > 0) {
/* 191 */           MouseEvent mouseEvent = (MouseEvent)arrayOfAWTEvent[arrayOfAWTEvent.length - 1];
/* 192 */           doProcess(mouseEvent);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 196 */       if (wakeupCriterion instanceof javax.media.j3d.WakeupOnBehaviorPost) {
/*     */         while (true) {
/*     */           MouseEvent mouseEvent;
/* 199 */           synchronized (this.mouseq) {
/* 200 */             if (this.mouseq.isEmpty())
/* 201 */               continue label30;  mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */ 
/*     */             
/* 204 */             while (mouseEvent.getID() == 506 && !this.mouseq.isEmpty() && ((MouseEvent)this.mouseq.get(0)).getID() == 506)
/*     */             {
/*     */               
/* 207 */               mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */             }
/*     */           } 
/* 210 */           doProcess(mouseEvent);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 215 */     wakeupOn(this.mouseCriterion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void doProcess(MouseEvent paramMouseEvent) {
/* 222 */     processMouseEvent(paramMouseEvent);
/*     */     
/* 224 */     if ((this.buttonPress && (this.flags & true) == 0) || (this.wakeUp && (this.flags & true) != 0)) {
/*     */       
/* 226 */       int i = paramMouseEvent.getID();
/* 227 */       if (i == 506 && !paramMouseEvent.isAltDown() && paramMouseEvent.isMetaDown()) {
/*     */ 
/*     */         
/* 230 */         this.x = paramMouseEvent.getX();
/* 231 */         this.y = paramMouseEvent.getY();
/*     */         
/* 233 */         int j = this.x - this.x_last;
/* 234 */         int k = this.y - this.y_last;
/*     */         
/* 236 */         if (!this.reset && Math.abs(k) < 50 && Math.abs(j) < 50) {
/*     */           
/* 238 */           this.transformGroup.getTransform(this.currXform);
/*     */           
/* 240 */           this.translation.x = j * this.x_factor;
/* 241 */           this.translation.y = -k * this.y_factor;
/*     */           
/* 243 */           this.transformX.set(this.translation);
/*     */           
/* 245 */           if (this.invert) {
/* 246 */             this.currXform.mul(this.currXform, this.transformX);
/*     */           } else {
/* 248 */             this.currXform.mul(this.transformX, this.currXform);
/*     */           } 
/*     */           
/* 251 */           this.transformGroup.setTransform(this.currXform);
/*     */           
/* 253 */           transformChanged(this.currXform);
/*     */           
/* 255 */           if (this.callback != null) {
/* 256 */             this.callback.transformChanged(1, this.currXform);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 261 */           this.reset = false;
/*     */         } 
/* 263 */         this.x_last = this.x;
/* 264 */         this.y_last = this.y;
/*     */       }
/* 266 */       else if (i == 501) {
/* 267 */         this.x_last = paramMouseEvent.getX();
/* 268 */         this.y_last = paramMouseEvent.getY();
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
/*     */   public void transformChanged(Transform3D paramTransform3D) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void setupCallback(MouseBehaviorCallback paramMouseBehaviorCallback) { this.callback = paramMouseBehaviorCallback; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\mouse\MouseTranslate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */