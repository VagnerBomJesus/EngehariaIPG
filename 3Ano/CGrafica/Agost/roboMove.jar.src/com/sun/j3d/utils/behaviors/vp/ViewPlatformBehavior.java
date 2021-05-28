/*     */ package com.sun.j3d.utils.behaviors.vp;
/*     */ 
/*     */ import com.sun.j3d.utils.universe.ViewingPlatform;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ViewPlatformBehavior
/*     */   extends Behavior
/*     */ {
/*     */   protected ViewingPlatform vp;
/*     */   protected TransformGroup targetTG;
/*  77 */   protected Transform3D homeTransform = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewingPlatform(ViewingPlatform paramViewingPlatform) {
/*  89 */     this.vp = paramViewingPlatform;
/*     */     
/*  91 */     if (paramViewingPlatform != null) {
/*  92 */       this.targetTG = paramViewingPlatform.getViewPlatformTransform();
/*     */     } else {
/*  94 */       this.targetTG = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public ViewingPlatform getViewingPlatform() { return this.vp; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHomeTransform(Transform3D paramTransform3D) {
/* 113 */     if (this.homeTransform == null) {
/* 114 */       this.homeTransform = new Transform3D(paramTransform3D);
/*     */     } else {
/* 116 */       this.homeTransform.set(paramTransform3D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void getHomeTransform(Transform3D paramTransform3D) { paramTransform3D.set(this.homeTransform); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void goHome() {
/* 134 */     if (this.targetTG != null && this.homeTransform != null)
/* 135 */       this.targetTG.setTransform(this.homeTransform); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\behaviors\vp\ViewPlatformBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */