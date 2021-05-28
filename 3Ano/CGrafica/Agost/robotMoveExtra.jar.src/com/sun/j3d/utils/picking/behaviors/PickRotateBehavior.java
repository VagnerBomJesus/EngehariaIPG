/*     */ package com.sun.j3d.utils.picking.behaviors;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PickRotateBehavior
/*     */   extends PickMouseBehavior
/*     */   implements MouseBehaviorCallback
/*     */ {
/*     */   MouseRotate drag;
/*  78 */   private PickingCallback callback = null;
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
/*     */   public PickRotateBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds) {
/*  90 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  91 */     this.drag = new MouseRotate(1);
/*  92 */     this.drag.setTransformGroup(this.currGrp);
/*  93 */     this.currGrp.addChild(this.drag);
/*  94 */     this.drag.setSchedulingBounds(paramBounds);
/*  95 */     setSchedulingBounds(paramBounds);
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
/*     */   public PickRotateBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/* 111 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/* 112 */     this.drag = new MouseRotate(1);
/* 113 */     this.drag.setTransformGroup(this.currGrp);
/* 114 */     this.currGrp.addChild(this.drag);
/* 115 */     this.drag.setSchedulingBounds(paramBounds);
/* 116 */     setSchedulingBounds(paramBounds);
/* 117 */     setMode(paramInt);
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
/* 130 */     TransformGroup transformGroup = null;
/*     */     
/* 132 */     if (!this.mevent.isMetaDown() && !this.mevent.isAltDown()) {
/*     */       
/* 134 */       this.pickCanvas.setShapeLocation(paramInt1, paramInt2);
/* 135 */       PickResult pickResult = this.pickCanvas.pickClosest();
/* 136 */       if (pickResult != null && (transformGroup = (TransformGroup)pickResult.getNode(32)) != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         this.drag.setTransformGroup(transformGroup);
/* 142 */         this.drag.wakeup();
/* 143 */         this.currentTG = transformGroup;
/*     */ 
/*     */       
/*     */       }
/* 147 */       else if (this.callback != null) {
/* 148 */         this.callback.transformChanged(3, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(0, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 165 */     this.callback = paramPickingCallback;
/* 166 */     if (paramPickingCallback == null) {
/* 167 */       this.drag.setupCallback(null);
/*     */     } else {
/* 169 */       this.drag.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\picking\behaviors\PickRotateBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */