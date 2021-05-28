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
/*     */ import javax.vecmath.Matrix4d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MouseRotate
/*     */   extends MouseBehavior
/*     */ {
/*     */   double x_angle;
/*     */   double y_angle;
/*  73 */   double x_factor = 0.03D;
/*  74 */   double y_factor = 0.03D;
/*     */   
/*  76 */   private MouseBehaviorCallback callback = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public MouseRotate(TransformGroup paramTransformGroup) { super(paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public MouseRotate() { super(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public MouseRotate(int paramInt) { super(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public MouseRotate(Component paramComponent) { super(paramComponent, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public MouseRotate(Component paramComponent, TransformGroup paramTransformGroup) { super(paramComponent, paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public MouseRotate(Component paramComponent, int paramInt) { super(paramComponent, paramInt); }
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 151 */     super.initialize();
/* 152 */     this.x_angle = 0.0D;
/* 153 */     this.y_angle = 0.0D;
/* 154 */     if ((this.flags & 0x2) == 2) {
/* 155 */       this.invert = true;
/* 156 */       this.x_factor *= -1.0D;
/* 157 */       this.y_factor *= -1.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public double getXFactor() { return this.x_factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public double getYFactor() { return this.y_factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public void setFactor(double paramDouble) { this.x_factor = this.y_factor = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFactor(double paramDouble1, double paramDouble2) {
/* 188 */     this.x_factor = paramDouble1;
/* 189 */     this.y_factor = paramDouble2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 199 */     label30: while (paramEnumeration.hasMoreElements()) {
/* 200 */       WakeupCriterion wakeupCriterion = (WakeupCriterion)paramEnumeration.nextElement();
/* 201 */       if (wakeupCriterion instanceof WakeupOnAWTEvent) {
/* 202 */         AWTEvent[] arrayOfAWTEvent = ((WakeupOnAWTEvent)wakeupCriterion).getAWTEvent();
/* 203 */         if (arrayOfAWTEvent.length > 0) {
/* 204 */           MouseEvent mouseEvent = (MouseEvent)arrayOfAWTEvent[arrayOfAWTEvent.length - 1];
/* 205 */           doProcess(mouseEvent);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 209 */       if (wakeupCriterion instanceof javax.media.j3d.WakeupOnBehaviorPost) {
/*     */         while (true) {
/*     */           MouseEvent mouseEvent;
/* 212 */           synchronized (this.mouseq) {
/* 213 */             if (this.mouseq.isEmpty())
/* 214 */               continue label30;  mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */ 
/*     */             
/* 217 */             while (mouseEvent.getID() == 506 && !this.mouseq.isEmpty() && ((MouseEvent)this.mouseq.get(0)).getID() == 506)
/*     */             {
/*     */               
/* 220 */               mouseEvent = (MouseEvent)this.mouseq.remove(0);
/*     */             }
/*     */           } 
/* 223 */           doProcess(mouseEvent);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 228 */     wakeupOn(this.mouseCriterion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void doProcess(MouseEvent paramMouseEvent) {
/* 235 */     processMouseEvent(paramMouseEvent);
/* 236 */     if ((this.buttonPress && (this.flags & true) == 0) || (this.wakeUp && (this.flags & true) != 0)) {
/*     */       
/* 238 */       int i = paramMouseEvent.getID();
/* 239 */       if (i == 506 && !paramMouseEvent.isMetaDown() && !paramMouseEvent.isAltDown()) {
/*     */         
/* 241 */         this.x = paramMouseEvent.getX();
/* 242 */         this.y = paramMouseEvent.getY();
/*     */         
/* 244 */         int j = this.x - this.x_last;
/* 245 */         int k = this.y - this.y_last;
/*     */         
/* 247 */         if (!this.reset) {
/* 248 */           this.x_angle = k * this.y_factor;
/* 249 */           this.y_angle = j * this.x_factor;
/*     */           
/* 251 */           this.transformX.rotX(this.x_angle);
/* 252 */           this.transformY.rotY(this.y_angle);
/*     */           
/* 254 */           this.transformGroup.getTransform(this.currXform);
/*     */           
/* 256 */           Matrix4d matrix4d = new Matrix4d();
/*     */           
/* 258 */           this.currXform.get(matrix4d);
/*     */ 
/*     */           
/* 261 */           this.currXform.setTranslation(new Vector3d(0.0D, 0.0D, 0.0D));
/* 262 */           if (this.invert) {
/* 263 */             this.currXform.mul(this.currXform, this.transformX);
/* 264 */             this.currXform.mul(this.currXform, this.transformY);
/*     */           } else {
/* 266 */             this.currXform.mul(this.transformX, this.currXform);
/* 267 */             this.currXform.mul(this.transformY, this.currXform);
/*     */           } 
/*     */ 
/*     */           
/* 271 */           Vector3d vector3d = new Vector3d(matrix4d.m03, matrix4d.m13, matrix4d.m23);
/*     */           
/* 273 */           this.currXform.setTranslation(vector3d);
/*     */ 
/*     */           
/* 276 */           this.transformGroup.setTransform(this.currXform);
/*     */           
/* 278 */           transformChanged(this.currXform);
/*     */           
/* 280 */           if (this.callback != null) {
/* 281 */             this.callback.transformChanged(0, this.currXform);
/*     */           }
/*     */         } else {
/*     */           
/* 285 */           this.reset = false;
/*     */         } 
/*     */         
/* 288 */         this.x_last = this.x;
/* 289 */         this.y_last = this.y;
/*     */       }
/* 291 */       else if (i == 501) {
/* 292 */         this.x_last = paramMouseEvent.getX();
/* 293 */         this.y_last = paramMouseEvent.getY();
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
/* 313 */   public void setupCallback(MouseBehaviorCallback paramMouseBehaviorCallback) { this.callback = paramMouseBehaviorCallback; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\mouse\MouseRotate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */