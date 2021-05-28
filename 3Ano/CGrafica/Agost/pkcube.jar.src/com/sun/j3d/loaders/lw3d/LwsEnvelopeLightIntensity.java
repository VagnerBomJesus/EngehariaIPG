/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import java.io.StreamTokenizer;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsEnvelopeLightIntensity
/*     */   extends LwsEnvelope
/*     */ {
/*  69 */   LwsEnvelopeLightIntensity(StreamTokenizer paramStreamTokenizer, int paramInt, float paramFloat) { super(paramStreamTokenizer, paramInt, paramFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createJava3dBehaviors(Object paramObject) {
/*  77 */     if (this.numFrames <= 1) {
/*  78 */       this.behaviors = null;
/*     */     } else {
/*  80 */       long l = 0L;
/*     */       
/*  82 */       if (this.loop) {
/*  83 */         byte b2 = -1;
/*     */       } else {
/*  85 */         boolean bool = true;
/*     */       } 
/*  87 */       byte b = -1;
/*  88 */       debugOutputLn(2, "totalTime = " + this.totalTime);
/*  89 */       debugOutputLn(2, "loopCount = " + b);
/*  90 */       float f = 1000.0F * this.totalTime * (float)(this.frames[this.numFrames - 1].getFrameNum() / this.totalFrames);
/*     */       
/*  92 */       debugOutputLn(2, " anim time: " + f);
/*  93 */       debugOutputLn(2, " totalFrames = " + this.totalFrames);
/*  94 */       debugOutputLn(2, " lastFrame = " + this.frames[this.numFrames - 1].getFrameNum());
/*     */       
/*  96 */       if (!this.loop)
/*  97 */         l = (long)(1000.0D * this.totalTime - f); 
/*  98 */       Alpha alpha = new Alpha(b, 1, 0L, 0L, (long)f, 0L, l, 0L, 0L, 0L);
/*     */ 
/*     */ 
/*     */       
/* 102 */       float[] arrayOfFloat1 = new float[this.numFrames];
/* 103 */       float[] arrayOfFloat2 = new float[this.numFrames];
/* 104 */       for (byte b1 = 0; b1 < this.numFrames; b1++) {
/* 105 */         arrayOfFloat2[b1] = (float)this.frames[b1].getValue();
/* 106 */         arrayOfFloat1[b1] = (float)this.frames[b1].getFrameNum() / (float)this.frames[this.numFrames - 1].getFrameNum();
/*     */         
/* 108 */         debugOutputLn(2, "value, knot = " + arrayOfFloat2[b1] + ", " + arrayOfFloat1[b1]);
/*     */       } 
/*     */       
/* 111 */       LightIntensityPathInterpolator lightIntensityPathInterpolator = new LightIntensityPathInterpolator(alpha, arrayOfFloat1, arrayOfFloat2, paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 116 */       if (lightIntensityPathInterpolator != null) {
/* 117 */         this.behaviors = lightIntensityPathInterpolator;
/* 118 */         BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 1000000.0D);
/*     */         
/* 120 */         this.behaviors.setSchedulingBounds(boundingSphere);
/* 121 */         ((TransformGroup)paramObject).setCapability(18);
/*     */         
/* 123 */         ((TransformGroup)paramObject).addChild(this.behaviors);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 130 */   Behavior getBehaviors() { return this.behaviors; }
/*     */ 
/*     */ 
/*     */   
/*     */   LwsEnvelopeFrame getFirstFrame() {
/* 135 */     if (this.numFrames > 0) {
/* 136 */       return this.frames[0];
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   void printVals() {
/* 143 */     debugOutputLn(2, "   name = " + this.name);
/* 144 */     debugOutputLn(2, "   numChannels = " + this.numChannels);
/* 145 */     debugOutputLn(2, "   numFrames = " + this.numFrames);
/* 146 */     debugOutputLn(2, "   loop = " + this.loop);
/* 147 */     for (byte b = 0; b < this.numFrames; b++) {
/* 148 */       debugOutputLn(2, "       FRAME " + b);
/* 149 */       this.frames[b].printVals();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\LwsEnvelopeLightIntensity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */