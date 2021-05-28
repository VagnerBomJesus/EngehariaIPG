/*    */ package com.sun.j3d.loaders.lw3d;
/*    */ 
/*    */ import javax.media.j3d.Light;
/*    */ import javax.vecmath.Color3f;
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
/*    */ 
/*    */ class LwLightObject
/*    */ {
/*    */   float intensity;
/*    */   Color3f color;
/*    */   Light theLight;
/*    */   
/*    */   LwLightObject(Light paramLight, float paramFloat, Color3f paramColor3f) {
/* 67 */     this.intensity = paramFloat;
/* 68 */     this.color = paramColor3f;
/* 69 */     this.theLight = paramLight;
/*    */   }
/*    */   
/*    */   void setIntensity(float paramFloat) {
/* 73 */     Color3f color3f = new Color3f(this.color.x * paramFloat, this.color.y * paramFloat, this.color.z * paramFloat);
/*    */ 
/*    */     
/* 76 */     if (this.theLight != null)
/* 77 */       this.theLight.setColor(color3f); 
/* 78 */     this.intensity = paramFloat;
/*    */   }
/*    */   
/*    */   void setColor(Color3f paramColor3f) {
/* 82 */     Color3f color3f = new Color3f(paramColor3f.x * this.intensity, paramColor3f.y * this.intensity, paramColor3f.z * this.intensity);
/*    */ 
/*    */     
/* 85 */     if (this.theLight != null)
/* 86 */       this.theLight.setColor(color3f); 
/* 87 */     this.color = paramColor3f;
/*    */   }
/*    */   
/*    */   void setLight(Light paramLight) {
/* 91 */     this.theLight = paramLight;
/* 92 */     Color3f color3f = new Color3f(this.color.x * this.intensity, this.color.y * this.intensity, this.color.z * this.intensity);
/*    */ 
/*    */     
/* 95 */     if (this.theLight != null)
/* 96 */       this.theLight.setColor(color3f); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\LwLightObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */