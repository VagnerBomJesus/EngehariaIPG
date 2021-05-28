/*     */ package com.sun.j3d.utils.pickfast;
/*     */ 
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.Locale;
/*     */ import javax.media.j3d.PickConeRay;
/*     */ import javax.media.j3d.PickCylinderRay;
/*     */ import javax.media.j3d.PickRay;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Point3d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PickCanvas
/*     */   extends PickTool
/*     */ {
/*     */   Canvas3D canvas;
/*  91 */   float tolerance = 2.0F;
/*     */   
/*     */   int save_xpos;
/*     */   
/*     */   int save_ypos;
/*     */   
/*     */   public PickCanvas(Canvas3D paramCanvas3D, BranchGroup paramBranchGroup) {
/*  98 */     super(paramBranchGroup);
/*  99 */     this.canvas = paramCanvas3D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PickCanvas(Canvas3D paramCanvas3D, Locale paramLocale) {
/* 105 */     super(paramLocale);
/* 106 */     this.canvas = paramCanvas3D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public Canvas3D getCanvas() { return this.canvas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTolerance(float paramFloat) {
/* 123 */     if (paramFloat < 0.0F) {
/* 124 */       throw new IllegalArgumentException();
/*     */     }
/* 126 */     this.tolerance = paramFloat;
/*     */     
/* 128 */     if (this.pickShape != null && !this.userDefineShape) {
/*     */       
/* 130 */       this.pickShape = null;
/* 131 */       setShapeLocation(this.save_xpos, this.save_ypos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public float getTolerance() { return this.tolerance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public void setShapeLocation(MouseEvent paramMouseEvent) { setShapeLocation(paramMouseEvent.getX(), paramMouseEvent.getY()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeLocation(int paramInt1, int paramInt2) {
/* 154 */     Transform3D transform3D = new Transform3D();
/* 155 */     Point3d point3d1 = new Point3d();
/* 156 */     Point3d point3d2 = new Point3d();
/* 157 */     Vector3d vector3d1 = new Vector3d();
/* 158 */     boolean bool = false;
/* 159 */     double d1 = 0.0D;
/* 160 */     double d2 = 0.0D;
/*     */     
/* 162 */     this.save_xpos = paramInt1;
/* 163 */     this.save_ypos = paramInt2;
/* 164 */     this.canvas.getCenterEyeInImagePlate(point3d1);
/* 165 */     this.canvas.getPixelLocationInImagePlate(paramInt1, paramInt2, point3d2);
/*     */     
/* 167 */     if (this.canvas.getView() != null && this.canvas.getView().getProjectionPolicy() == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 173 */       point3d1.x = point3d2.x;
/* 174 */       point3d1.y = point3d2.y;
/* 175 */       bool = true;
/*     */     } 
/*     */ 
/*     */     
/* 179 */     Vector3d vector3d2 = new Vector3d();
/* 180 */     vector3d2.sub(point3d2, point3d1);
/* 181 */     double d3 = vector3d2.length();
/*     */     
/* 183 */     Point3d point3d3 = new Point3d();
/* 184 */     this.canvas.getPixelLocationInImagePlate(paramInt1 + 1, paramInt2, point3d3);
/*     */     
/* 186 */     Vector3d vector3d3 = new Vector3d();
/* 187 */     vector3d3.sub(point3d2, point3d3);
/* 188 */     double d4 = vector3d3.length();
/* 189 */     d4 *= this.tolerance;
/*     */     
/* 191 */     this.canvas.getImagePlateToVworld(transform3D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     transform3D.transform(point3d1);
/* 199 */     this.start = new Point3d(point3d1);
/* 200 */     transform3D.transform(point3d2);
/* 201 */     vector3d1.sub(point3d2, point3d1);
/* 202 */     vector3d1.normalize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     if (this.tolerance == 0.0D) {
/* 211 */       if (this.pickShape != null && this.pickShape instanceof PickRay) {
/* 212 */         ((PickRay)this.pickShape).set(point3d1, vector3d1);
/*     */       } else {
/* 214 */         this.pickShape = new PickRay(point3d1, vector3d1);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 219 */     else if (bool) {
/*     */       
/* 221 */       d4 *= transform3D.getScale();
/* 222 */       if (this.pickShape != null && this.pickShape instanceof PickCylinderRay) {
/*     */         
/* 224 */         ((PickCylinderRay)this.pickShape).set(point3d1, vector3d1, d4);
/*     */       } else {
/*     */         
/* 227 */         this.pickShape = new PickCylinderRay(point3d1, vector3d1, d4);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 234 */       d2 = Math.atan(d4 / d3);
/*     */       
/* 236 */       if (this.pickShape != null && this.pickShape instanceof PickConeRay) {
/*     */         
/* 238 */         ((PickConeRay)this.pickShape).set(point3d1, vector3d1, d2);
/*     */       } else {
/*     */         
/* 241 */         this.pickShape = new PickConeRay(point3d1, vector3d1, d2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\pickfast\PickCanvas.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */