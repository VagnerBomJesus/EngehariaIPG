/*     */ package com.sun.j3d.utils.picking;
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
/*     */ public class PickCanvas
/*     */   extends PickTool
/*     */ {
/*     */   Canvas3D canvas;
/*  90 */   float tolerance = 2.0F;
/*     */   
/*     */   int save_xpos;
/*     */   
/*     */   int save_ypos;
/*     */   
/*     */   public PickCanvas(Canvas3D paramCanvas3D, BranchGroup paramBranchGroup) {
/*  97 */     super(paramBranchGroup);
/*  98 */     this.canvas = paramCanvas3D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PickCanvas(Canvas3D paramCanvas3D, Locale paramLocale) {
/* 104 */     super(paramLocale);
/* 105 */     this.canvas = paramCanvas3D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public Canvas3D getCanvas() { return this.canvas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTolerance(float paramFloat) {
/* 122 */     if (paramFloat < 0.0F) {
/* 123 */       throw new IllegalArgumentException();
/*     */     }
/* 125 */     this.tolerance = paramFloat;
/*     */     
/* 127 */     if (this.pickShape != null && !this.userDefineShape) {
/*     */       
/* 129 */       this.pickShape = null;
/* 130 */       setShapeLocation(this.save_xpos, this.save_ypos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public float getTolerance() { return this.tolerance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void setShapeLocation(MouseEvent paramMouseEvent) { setShapeLocation(paramMouseEvent.getX(), paramMouseEvent.getY()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapeLocation(int paramInt1, int paramInt2) {
/* 153 */     Transform3D transform3D = new Transform3D();
/* 154 */     Point3d point3d1 = new Point3d();
/* 155 */     Point3d point3d2 = new Point3d();
/* 156 */     Vector3d vector3d1 = new Vector3d();
/* 157 */     boolean bool = false;
/* 158 */     double d1 = 0.0D;
/* 159 */     double d2 = 0.0D;
/*     */     
/* 161 */     this.save_xpos = paramInt1;
/* 162 */     this.save_ypos = paramInt2;
/* 163 */     this.canvas.getCenterEyeInImagePlate(point3d1);
/* 164 */     this.canvas.getPixelLocationInImagePlate(paramInt1, paramInt2, point3d2);
/*     */     
/* 166 */     if (this.canvas.getView() != null && this.canvas.getView().getProjectionPolicy() == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       point3d1.x = point3d2.x;
/* 173 */       point3d1.y = point3d2.y;
/* 174 */       bool = true;
/*     */     } 
/*     */ 
/*     */     
/* 178 */     Vector3d vector3d2 = new Vector3d();
/* 179 */     vector3d2.sub(point3d2, point3d1);
/* 180 */     double d3 = vector3d2.length();
/*     */     
/* 182 */     Point3d point3d3 = new Point3d();
/* 183 */     this.canvas.getPixelLocationInImagePlate(paramInt1 + 1, paramInt2, point3d3);
/*     */     
/* 185 */     Vector3d vector3d3 = new Vector3d();
/* 186 */     vector3d3.sub(point3d2, point3d3);
/* 187 */     double d4 = vector3d3.length();
/* 188 */     d4 *= this.tolerance;
/*     */     
/* 190 */     this.canvas.getImagePlateToVworld(transform3D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     transform3D.transform(point3d1);
/* 198 */     this.start = new Point3d(point3d1);
/* 199 */     transform3D.transform(point3d2);
/* 200 */     vector3d1.sub(point3d2, point3d1);
/* 201 */     vector3d1.normalize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     if (this.tolerance == 0.0D) {
/* 210 */       if (this.pickShape != null && this.pickShape instanceof PickRay) {
/* 211 */         ((PickRay)this.pickShape).set(point3d1, vector3d1);
/*     */       } else {
/* 213 */         this.pickShape = new PickRay(point3d1, vector3d1);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 218 */     else if (bool) {
/*     */       
/* 220 */       d4 *= transform3D.getScale();
/* 221 */       if (this.pickShape != null && this.pickShape instanceof PickCylinderRay) {
/*     */         
/* 223 */         ((PickCylinderRay)this.pickShape).set(point3d1, vector3d1, d4);
/*     */       } else {
/*     */         
/* 226 */         this.pickShape = new PickCylinderRay(point3d1, vector3d1, d4);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 233 */       d2 = Math.atan(d4 / d3);
/*     */       
/* 235 */       if (this.pickShape != null && this.pickShape instanceof PickConeRay) {
/*     */         
/* 237 */         ((PickConeRay)this.pickShape).set(point3d1, vector3d1, d2);
/*     */       } else {
/*     */         
/* 240 */         this.pickShape = new PickConeRay(point3d1, vector3d1, d2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\picking\PickCanvas.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */