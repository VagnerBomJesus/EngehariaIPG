/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TCBKeyFrame
/*     */ {
/*     */   public Point3f position;
/*     */   public Quat4f quat;
/*     */   public Point3f scale;
/*     */   public float tension;
/*     */   public float continuity;
/*     */   public float bias;
/*     */   public float knot;
/*     */   public int linear;
/*     */   
/*  79 */   TCBKeyFrame() { this.tension = this.continuity = this.bias = 0.0F; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public TCBKeyFrame(TCBKeyFrame paramTCBKeyFrame) { this(paramTCBKeyFrame.knot, paramTCBKeyFrame.linear, paramTCBKeyFrame.position, paramTCBKeyFrame.quat, paramTCBKeyFrame.scale, paramTCBKeyFrame.tension, paramTCBKeyFrame.continuity, paramTCBKeyFrame.bias); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TCBKeyFrame(float paramFloat1, int paramInt, Point3f paramPoint3f1, Quat4f paramQuat4f, Point3f paramPoint3f2, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 103 */     this.knot = paramFloat1;
/* 104 */     this.linear = paramInt;
/* 105 */     this.position = new Point3f(paramPoint3f1);
/* 106 */     this.quat = new Quat4f(paramQuat4f);
/* 107 */     this.scale = new Point3f(paramPoint3f2);
/*     */ 
/*     */     
/* 110 */     if (paramFloat2 < -1.0F || paramFloat2 > 1.0F) {
/* 111 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("TCBKeyFrame0"));
/*     */     }
/*     */     
/* 114 */     if (paramFloat4 < -1.0F || paramFloat4 > 1.0F) {
/* 115 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("TCBKeyFrame1"));
/*     */     }
/*     */     
/* 118 */     if (paramFloat3 < -1.0F || paramFloat3 > 1.0F) {
/* 119 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("TCBKeyFrame2"));
/*     */     }
/*     */ 
/*     */     
/* 123 */     this.tension = paramFloat2;
/* 124 */     this.continuity = paramFloat3;
/* 125 */     this.bias = paramFloat4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void debugPrint(String paramString) {
/* 133 */     System.out.println("\n" + paramString);
/* 134 */     System.out.println(" knot = " + this.knot);
/* 135 */     System.out.println(" linear = " + this.linear);
/* 136 */     System.out.println(" position(x,y,z) = " + this.position.x + " " + this.position.y + " " + this.position.z);
/*     */ 
/*     */     
/* 139 */     System.out.println(" tension = " + this.tension);
/* 140 */     System.out.println(" continuity = " + this.continuity);
/* 141 */     System.out.println(" bias = " + this.bias);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\interpolators\TCBKeyFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */