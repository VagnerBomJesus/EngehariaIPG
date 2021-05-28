/*     */ package com.sun.j3d.utils.pickfast.behaviors;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
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
/*     */   public PickZoomBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/*  95 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  96 */     this.zoom = new MouseZoom(1);
/*  97 */     this.zoom.setTransformGroup(this.currGrp);
/*  98 */     this.currGrp.addChild(this.zoom);
/*  99 */     this.zoom.setSchedulingBounds(paramBounds);
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
/* 116 */     if (this.mevent.isAltDown() && !this.mevent.isMetaDown()) {
/*     */       
/* 118 */       this.pickCanvas.setShapeLocation(paramInt1, paramInt2);
/*     */ 
/*     */       
/* 121 */       this.pickCanvas.setFlags(3);
/*     */       
/* 123 */       PickInfo pickInfo = this.pickCanvas.pickClosest();
/* 124 */       if (pickInfo != null) {
/*     */         
/* 126 */         transformGroup = (TransformGroup)this.pickCanvas.getNode(pickInfo, 32);
/* 127 */         if (transformGroup != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */           
/* 130 */           this.zoom.setTransformGroup(transformGroup);
/* 131 */           this.zoom.wakeup();
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
/* 146 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(2, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 154 */     this.callback = paramPickingCallback;
/* 155 */     if (paramPickingCallback == null) {
/* 156 */       this.zoom.setupCallback(null);
/*     */     } else {
/* 158 */       this.zoom.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\pickfast\behaviors\PickZoomBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */