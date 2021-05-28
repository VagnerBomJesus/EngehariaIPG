/*     */ package com.sun.j3d.utils.behaviors.picking;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
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
/*     */ public class PickZoomBehavior
/*     */   extends PickMouseBehavior
/*     */   implements MouseBehaviorCallback
/*     */ {
/*     */   MouseZoom zoom;
/*  68 */   int pickMode = 512;
/*  69 */   private PickingCallback callback = null;
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
/*  81 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  82 */     this.zoom = new MouseZoom(1);
/*  83 */     this.zoom.setTransformGroup(this.currGrp);
/*  84 */     this.currGrp.addChild(this.zoom);
/*  85 */     this.zoom.setSchedulingBounds(paramBounds);
/*  86 */     setSchedulingBounds(paramBounds);
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
/*     */   public PickZoomBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/* 102 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/* 103 */     this.zoom = new MouseZoom(1);
/* 104 */     this.zoom.setTransformGroup(this.currGrp);
/* 105 */     this.currGrp.addChild(this.zoom);
/* 106 */     this.zoom.setSchedulingBounds(paramBounds);
/* 107 */     setSchedulingBounds(paramBounds);
/* 108 */     this.pickMode = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public void setPickMode(int paramInt) { this.pickMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int getPickMode() { return this.pickMode; }
/*     */ 
/*     */ 
/*     */ 
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
/* 141 */     TransformGroup transformGroup = null;
/*     */     
/* 143 */     if (this.mevent.isAltDown() && !this.mevent.isMetaDown()) {
/*     */       
/* 145 */       transformGroup = (TransformGroup)this.pickScene.pickNode(this.pickScene.pickClosest(paramInt1, paramInt2, this.pickMode), 32);
/*     */ 
/*     */ 
/*     */       
/* 149 */       if (transformGroup != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */         
/* 152 */         this.zoom.setTransformGroup(transformGroup);
/* 153 */         this.zoom.wakeup();
/* 154 */         this.currentTG = transformGroup;
/* 155 */       } else if (this.callback != null) {
/* 156 */         this.callback.transformChanged(3, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(2, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 173 */     this.callback = paramPickingCallback;
/* 174 */     if (paramPickingCallback == null) {
/* 175 */       this.zoom.setupCallback(null);
/*     */     } else {
/* 177 */       this.zoom.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\picking\PickZoomBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */