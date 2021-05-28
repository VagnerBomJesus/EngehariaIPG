/*     */ package com.sun.j3d.utils.picking.behaviors;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
/*     */ import com.sun.j3d.utils.picking.PickResult;
/*     */ import javax.media.j3d.Bounds;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
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
/*     */   
/*     */   public PickTranslateBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/*  95 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  96 */     this.translate = new MouseTranslate(1);
/*  97 */     this.translate.setTransformGroup(this.currGrp);
/*  98 */     this.currGrp.addChild(this.translate);
/*  99 */     this.translate.setSchedulingBounds(paramBounds);
/* 100 */     setSchedulingBounds(paramBounds);
/* 101 */     setMode(paramInt);
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
/* 114 */     TransformGroup transformGroup = null;
/*     */     
/* 116 */     if (!this.mevent.isAltDown() && this.mevent.isMetaDown()) {
/*     */       
/* 118 */       this.pickCanvas.setShapeLocation(paramInt1, paramInt2);
/* 119 */       PickResult pickResult = this.pickCanvas.pickClosest();
/* 120 */       if (pickResult != null && (transformGroup = (TransformGroup)pickResult.getNode(32)) != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 126 */         this.translate.setTransformGroup(transformGroup);
/* 127 */         this.translate.wakeup();
/* 128 */         this.currentTG = transformGroup;
/*     */       
/*     */       }
/* 131 */       else if (this.callback != null) {
/* 132 */         this.callback.transformChanged(3, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(1, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 150 */     this.callback = paramPickingCallback;
/* 151 */     if (paramPickingCallback == null) {
/* 152 */       this.translate.setupCallback(null);
/*     */     } else {
/* 154 */       this.translate.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\picking\behaviors\PickTranslateBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */