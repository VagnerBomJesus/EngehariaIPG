/*     */ package com.sun.j3d.utils.behaviors.picking;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
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
/*  82 */   int pickMode = 512;
/*  83 */   private PickingCallback callback = null;
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
/*  95 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  96 */     this.drag = new MouseRotate(1);
/*  97 */     this.drag.setTransformGroup(this.currGrp);
/*  98 */     this.currGrp.addChild(this.drag);
/*  99 */     this.drag.setSchedulingBounds(paramBounds);
/* 100 */     setSchedulingBounds(paramBounds);
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
/* 116 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/* 117 */     this.drag = new MouseRotate(1);
/* 118 */     this.drag.setTransformGroup(this.currGrp);
/* 119 */     this.currGrp.addChild(this.drag);
/* 120 */     this.drag.setSchedulingBounds(paramBounds);
/* 121 */     setSchedulingBounds(paramBounds);
/* 122 */     this.pickMode = paramInt;
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
/* 133 */   public void setPickMode(int paramInt) { this.pickMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public int getPickMode() { return this.pickMode; }
/*     */ 
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
/* 153 */     TransformGroup transformGroup = null;
/*     */     
/* 155 */     if (!this.mevent.isMetaDown() && !this.mevent.isAltDown()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       transformGroup = (TransformGroup)this.pickScene.pickNode(this.pickScene.pickClosest(paramInt1, paramInt2, this.pickMode), 32);
/*     */ 
/*     */       
/* 163 */       if (transformGroup != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */         
/* 166 */         this.drag.setTransformGroup(transformGroup);
/* 167 */         this.drag.wakeup();
/* 168 */         this.currentTG = transformGroup;
/* 169 */       } else if (this.callback != null) {
/* 170 */         this.callback.transformChanged(3, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(0, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 187 */     this.callback = paramPickingCallback;
/* 188 */     if (paramPickingCallback == null) {
/* 189 */       this.drag.setupCallback(null);
/*     */     } else {
/* 191 */       this.drag.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\picking\PickRotateBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */