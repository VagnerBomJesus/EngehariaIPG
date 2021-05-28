/*     */ package com.sun.j3d.utils.behaviors.picking;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
/*     */ import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
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
/*     */ public class PickTranslateBehavior
/*     */   extends PickMouseBehavior
/*     */   implements MouseBehaviorCallback
/*     */ {
/*     */   MouseTranslate translate;
/*  67 */   int pickMode = 512;
/*  68 */   private PickingCallback callback = null;
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
/*  80 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/*  81 */     this.translate = new MouseTranslate(1);
/*  82 */     this.translate.setTransformGroup(this.currGrp);
/*  83 */     this.currGrp.addChild(this.translate);
/*  84 */     this.translate.setSchedulingBounds(paramBounds);
/*  85 */     setSchedulingBounds(paramBounds);
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
/*     */   public PickTranslateBehavior(BranchGroup paramBranchGroup, Canvas3D paramCanvas3D, Bounds paramBounds, int paramInt) {
/* 101 */     super(paramCanvas3D, paramBranchGroup, paramBounds);
/* 102 */     this.translate = new MouseTranslate(1);
/* 103 */     this.translate.setTransformGroup(this.currGrp);
/* 104 */     this.currGrp.addChild(this.translate);
/* 105 */     this.translate.setSchedulingBounds(paramBounds);
/* 106 */     setSchedulingBounds(paramBounds);
/* 107 */     this.pickMode = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public void setPickMode(int paramInt) { this.pickMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public int getPickMode() { return this.pickMode; }
/*     */ 
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
/* 137 */     TransformGroup transformGroup = null;
/*     */     
/* 139 */     if (!this.mevent.isAltDown() && this.mevent.isMetaDown()) {
/*     */       
/* 141 */       transformGroup = (TransformGroup)this.pickScene.pickNode(this.pickScene.pickClosest(paramInt1, paramInt2, this.pickMode), 32);
/*     */ 
/*     */       
/* 144 */       if (transformGroup != null && transformGroup.getCapability(17) && transformGroup.getCapability(18)) {
/*     */ 
/*     */ 
/*     */         
/* 148 */         this.translate.setTransformGroup(transformGroup);
/* 149 */         this.translate.wakeup();
/* 150 */         this.currentTG = transformGroup;
/* 151 */       } else if (this.callback != null) {
/* 152 */         this.callback.transformChanged(3, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public void transformChanged(int paramInt, Transform3D paramTransform3D) { this.callback.transformChanged(1, this.currentTG); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupCallback(PickingCallback paramPickingCallback) {
/* 170 */     this.callback = paramPickingCallback;
/* 171 */     if (paramPickingCallback == null) {
/* 172 */       this.translate.setupCallback(null);
/*     */     } else {
/* 174 */       this.translate.setupCallback(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\picking\PickTranslateBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */