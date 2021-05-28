/*     */ package com.sun.j3d.utils.picking.behaviors;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
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
/*     */ public class PickZoomBehavior
/*     */   extends PickMouseBehavior
/*     */   implements MouseBehaviorCallback
/*     */ {
/*     */   MouseZoom zoom;
/*  64 */   private PickingCallback callback = null;
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
/*     */   public PickZoomBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds) {
/*  76 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  77 */     this.zoom = new MouseZoom(1);
/*  78 */     this.zoom.setTransformGroup(this.currGrp);
/*  79 */     this.currGrp.addChild(this.zoom);
/*  80 */     this.zoom.setSchedulingBounds(paramBounds);
/*  81 */     setSchedulingBounds(paramBounds);
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
/*     */   public PickZoomBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/*  96 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  97 */     this.zoom = new MouseZoom(1);
/*  98 */     this.zoom.setTransformGroup(this.currGrp);
/*  99 */     this.currGrp.addChild(this.zoom);
/* 100 */     this.zoom.setSchedulingBounds(paramBounds);
/* 101 */     setSchedulingBounds(paramBounds);
/* 102 */     setMode(paramInt);
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
/* 115 */     TransformGroup transformGroup = null;
/*     */     
/* 117 */     if (this.mevent.isAltDown() && !this.mevent.isMetaDown()) {
/*     */       
/* 119 */       this.pickCanvas.setShapeLocation(paramInt1, paramInt2);
/* 120 */       PickResult pickResult = this.pickCanvas.pickClosest();
/* 121 */       if (pickResult != null && (transformGroup = (TransformGroup)pickResult.getNode(32)) != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 126 */         this.zoom.setTransformGroup(transformGroup);
/* 127 */         this.zoom.wakeup();
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
/* 141 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(2, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 149 */     this.callback = paramPickingCallback;
/* 150 */     if (paramPickingCallback == null) {
/* 151 */       this.zoom.setupCallback(null);
/*     */     } else {
/* 153 */       this.zoom.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\picking\behaviors\PickZoomBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */