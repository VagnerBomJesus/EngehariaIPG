/*     */ package com.sun.j3d.utils.behaviors.interpolators;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KBKeyFrame
/*     */ {
/*     */   public Point3f position;
/*     */   public float heading;
/*     */   public float pitch;
/*     */   public float bank;
/*     */   public Point3f scale;
/*     */   public float tension;
/*     */   public float continuity;
/*     */   public float bias;
/*     */   public float knot;
/*     */   public int linear;
/*     */   
/*  80 */   KBKeyFrame() { this.tension = this.continuity = this.bias = 0.0F; }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public KBKeyFrame(KBKeyFrame paramKBKeyFrame) { this(paramKBKeyFrame.knot, paramKBKeyFrame.linear, paramKBKeyFrame.position, paramKBKeyFrame.heading, paramKBKeyFrame.pitch, paramKBKeyFrame.bank, paramKBKeyFrame.scale, paramKBKeyFrame.tension, paramKBKeyFrame.continuity, paramKBKeyFrame.bias); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KBKeyFrame(float paramFloat1, int paramInt, Point3f paramPoint3f1, float paramFloat2, float paramFloat3, float paramFloat4, Point3f paramPoint3f2, float paramFloat5, float paramFloat6, float paramFloat7) {
/* 107 */     this.knot = paramFloat1;
/* 108 */     this.linear = paramInt;
/* 109 */     this.position = new Point3f(paramPoint3f1);
/* 110 */     this.heading = paramFloat2;
/* 111 */     this.pitch = paramFloat3;
/* 112 */     this.bank = paramFloat4;
/* 113 */     this.scale = new Point3f(paramPoint3f2);
/*     */ 
/*     */     
/* 116 */     if (paramFloat5 < -1.0F || paramFloat5 > 1.0F) {
/* 117 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("KBKeyFrame0"));
/*     */     }
/*     */     
/* 120 */     if (paramFloat7 < -1.0F || paramFloat7 > 1.0F) {
/* 121 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("KBKeyFrame1"));
/*     */     }
/*     */     
/* 124 */     if (paramFloat6 < -1.0F || paramFloat6 > 1.0F) {
/* 125 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("KBKeyFrame2"));
/*     */     }
/*     */ 
/*     */     
/* 129 */     this.tension = paramFloat5;
/* 130 */     this.continuity = paramFloat6;
/* 131 */     this.bias = paramFloat7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void debugPrint(String paramString) {
/* 139 */     System.out.println("\n" + paramString);
/* 140 */     System.out.println(" knot = " + this.knot);
/* 141 */     System.out.println(" linear = " + this.linear);
/* 142 */     System.out.println(" position(x,y,z) = " + this.position.x + " " + this.position.y + " " + this.position.z);
/*     */ 
/*     */     
/* 145 */     System.out.println(" tension = " + this.tension);
/* 146 */     System.out.println(" continuity = " + this.continuity);
/* 147 */     System.out.println(" bias = " + this.bias);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\interpolators\KBKeyFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */