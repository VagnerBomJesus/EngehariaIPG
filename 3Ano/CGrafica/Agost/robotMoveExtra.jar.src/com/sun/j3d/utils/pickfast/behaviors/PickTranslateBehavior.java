/*     */ package com.sun.j3d.utils.pickfast.behaviors;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
/*     */ import javax.media.j3d.Bounds;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.PickInfo;
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
/*     */ public class PickTranslateBehavior
/*     */   extends PickMouseBehavior
/*     */   implements MouseBehaviorCallback
/*     */ {
/*     */   MouseTranslate translate;
/*  63 */   private PickingCallback callback = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TransformGroup currentTG;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PickTranslateBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds) {
/*  75 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  76 */     this.translate = new MouseTranslate(1);
/*  77 */     this.translate.setTransformGroup(this.currGrp);
/*  78 */     this.currGrp.addChild(this.translate);
/*  79 */     this.translate.setSchedulingBounds(paramBounds);
/*  80 */     setSchedulingBounds(paramBounds);
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
/*     */   public PickTranslateBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/*  94 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  95 */     this.translate = new MouseTranslate(1);
/*  96 */     this.translate.setTransformGroup(this.currGrp);
/*  97 */     this.currGrp.addChild(this.translate);
/*  98 */     this.translate.setSchedulingBounds(paramBounds);
/*  99 */     setSchedulingBounds(paramBounds);
/* 100 */     setMode(paramInt);
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
/*     */   public void updateScene(int paramInt1, int paramInt2) {
/* 113 */     TransformGroup transformGroup = null;
/*     */     
/* 115 */     if (!this.mevent.isAltDown() && this.mevent.isMetaDown()) {
/*     */       
/* 117 */       this.pickCanvas.setShapeLocation(paramInt1, paramInt2);
/*     */ 
/*     */ 
/*     */       
/* 121 */       this.pickCanvas.setFlags(3);
/* 122 */       PickInfo pickInfo = this.pickCanvas.pickClosest();
/*     */       
/* 124 */       if (pickInfo != null) {
/*     */         
/* 126 */         transformGroup = (TransformGroup)this.pickCanvas.getNode(pickInfo, 32);
/* 127 */         if (transformGroup != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */           
/* 130 */           this.translate.setTransformGroup(transformGroup);
/* 131 */           this.translate.wakeup();
/* 132 */           this.currentTG = transformGroup;
/*     */         } 
/* 134 */       } else if (this.callback != null) {
/* 135 */         this.callback.transformChanged(3, null);
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
/* 146 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(1, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 154 */     this.callback = paramPickingCallback;
/* 155 */     if (paramPickingCallback == null) {
/* 156 */       this.translate.setupCallback(null);
/*     */     } else {
/* 158 */       this.translate.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\pickfast\behaviors\PickTranslateBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */