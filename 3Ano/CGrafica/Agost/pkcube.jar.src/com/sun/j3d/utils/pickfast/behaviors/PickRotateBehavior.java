/*     */ package com.sun.j3d.utils.pickfast.behaviors;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   MouseRotate rotate;
/*  77 */   private PickingCallback callback = null;
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
/*  89 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  90 */     this.rotate = new MouseRotate(1);
/*  91 */     this.rotate.setTransformGroup(this.currGrp);
/*  92 */     this.currGrp.addChild(this.rotate);
/*  93 */     this.rotate.setSchedulingBounds(paramBounds);
/*  94 */     setSchedulingBounds(paramBounds);
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
/*     */   public PickRotateBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/* 109 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/* 110 */     this.rotate = new MouseRotate(1);
/* 111 */     this.rotate.setTransformGroup(this.currGrp);
/* 112 */     this.currGrp.addChild(this.rotate);
/* 113 */     this.rotate.setSchedulingBounds(paramBounds);
/* 114 */     setSchedulingBounds(paramBounds);
/* 115 */     setMode(paramInt);
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
/* 128 */     TransformGroup transformGroup = null;
/*     */     
/* 130 */     if (!this.mevent.isMetaDown() && !this.mevent.isAltDown()) {
/*     */ 
/*     */       
/* 133 */       this.pickCanvas.setFlags(3);
/*     */       
/* 135 */       this.pickCanvas.setShapeLocation(paramInt1, paramInt2);
/* 136 */       PickInfo pickInfo = this.pickCanvas.pickClosest();
/* 137 */       if (pickInfo != null) {
/*     */         
/* 139 */         transformGroup = (TransformGroup)this.pickCanvas.getNode(pickInfo, 32);
/* 140 */         if (transformGroup != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */           
/* 143 */           this.rotate.setTransformGroup(transformGroup);
/* 144 */           this.rotate.wakeup();
/* 145 */           this.currentTG = transformGroup;
/*     */         } 
/* 147 */       } else if (this.callback != null) {
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
/* 167 */       this.rotate.setupCallback(null);
/*     */     } else {
/* 169 */       this.rotate.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\pickfast\behaviors\PickRotateBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */