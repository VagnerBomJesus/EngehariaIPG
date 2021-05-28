/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import java.util.Enumeration;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Switch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SwitchPathInterpolator
/*     */   extends FloatValueInterpolator
/*     */ {
/*     */   Switch target;
/*     */   int firstSwitchIndex;
/*     */   int lastSwitchIndex;
/*     */   int currentChild;
/*     */   int childCount;
/*     */   
/*     */   SwitchPathInterpolator(Alpha paramAlpha, float[] paramArrayOfFloat, Switch paramSwitch) {
/*  85 */     super(paramAlpha, paramArrayOfFloat, new float[paramArrayOfFloat.length]);
/*     */     
/*  87 */     if (paramArrayOfFloat.length != paramSwitch.numChildren() + 1) {
/*  88 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("SwitchPathInterpolator0"));
/*     */     }
/*  90 */     this.target = paramSwitch;
/*  91 */     this.firstSwitchIndex = 0;
/*  92 */     this.lastSwitchIndex = paramSwitch.numChildren() - 1;
/*  93 */     this.childCount = this.lastSwitchIndex + 1;
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
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 107 */     if (getAlpha() != null) {
/*     */       byte b;
/*     */ 
/*     */       
/* 111 */       computePathInterpolation();
/*     */       
/* 113 */       if (this.currentKnotIndex > 0) {
/* 114 */         b = this.currentKnotIndex - 1;
/*     */       } else {
/* 116 */         b = 0;
/*     */       } 
/* 118 */       if (this.target.getWhichChild() != b) {
/* 119 */         this.target.setWhichChild(b);
/*     */       }
/*     */       
/* 122 */       if (getAlpha().finished()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 126 */     wakeupOn(this.defaultWakeupCriterion);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\lw3d\SwitchPathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */