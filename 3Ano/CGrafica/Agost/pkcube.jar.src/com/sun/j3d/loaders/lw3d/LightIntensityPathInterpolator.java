/*    */ package com.sun.j3d.loaders.lw3d;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import javax.media.j3d.Alpha;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class LightIntensityPathInterpolator
/*    */   extends FloatValueInterpolator
/*    */ {
/*    */   LwLightObject theLight;
/*    */   
/*    */   LightIntensityPathInterpolator(Alpha paramAlpha, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, Object paramObject) {
/* 65 */     super(paramAlpha, paramArrayOfFloat1, paramArrayOfFloat2);
/* 66 */     this.theLight = (LwLightObject)paramObject;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processStimulus(Enumeration paramEnumeration) {
/* 81 */     if (getAlpha() != null) {
/*    */ 
/*    */ 
/*    */       
/* 85 */       computePathInterpolation();
/*    */ 
/*    */ 
/*    */       
/* 89 */       if (this.theLight != null) {
/* 90 */         this.theLight.setIntensity(this.currentValue);
/*    */       }
/* 92 */       if (getAlpha().finished()) {
/*    */         return;
/*    */       }
/*    */     } 
/* 96 */     wakeupOn(this.defaultWakeupCriterion);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\LightIntensityPathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */