/*     */ package com.sun.j3d.utils.universe;
/*     */ 
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
/*     */ public class MultiTransformGroup
/*     */ {
/*     */   TransformGroup[] transforms;
/*     */   
/*  73 */   public MultiTransformGroup() { this(1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiTransformGroup(int paramInt) {
/*  89 */     if (paramInt < 1) {
/*  90 */       paramInt = 1;
/*     */     }
/*  92 */     this.transforms = new TransformGroup[paramInt];
/*     */ 
/*     */     
/*  95 */     this.transforms[0] = new TransformGroup();
/*  96 */     this.transforms[0].setCapability(18);
/*  97 */     this.transforms[0].setCapability(17);
/*  98 */     this.transforms[0].setCapability(13);
/*  99 */     this.transforms[0].setCapability(14);
/*     */     
/* 101 */     for (byte b = 1; b < paramInt; b++) {
/* 102 */       this.transforms[b] = new TransformGroup();
/* 103 */       this.transforms[b].setCapability(18);
/* 104 */       this.transforms[b].setCapability(17);
/* 105 */       this.transforms[b].setCapability(13);
/* 106 */       this.transforms[b].setCapability(14);
/* 107 */       this.transforms[b - true].addChild(this.transforms[b]);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformGroup getTransformGroup(int paramInt) {
/* 125 */     if (paramInt >= this.transforms.length || paramInt < 0) {
/* 126 */       return null;
/*     */     }
/* 128 */     return this.transforms[paramInt];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public int getNumTransforms() { return this.transforms.length; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\util\\universe\MultiTransformGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */