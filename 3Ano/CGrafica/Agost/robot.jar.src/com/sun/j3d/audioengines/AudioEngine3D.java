/*     */ package com.sun.j3d.audioengines;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.media.j3d.AudioDevice3D;
/*     */ import javax.media.j3d.MediaContainer;
/*     */ import javax.media.j3d.PhysicalEnvironment;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.View;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Vector3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AudioEngine3D
/*     */   extends AudioEngine
/*     */   implements AudioDevice3D
/*     */ {
/*  76 */   protected ArrayList samples = new ArrayList(64);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   protected View currentView = (View)null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   protected AuralParameters attribs = new AuralParameters();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public AudioEngine3D(PhysicalEnvironment paramPhysicalEnvironment) { super(paramPhysicalEnvironment); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setView(View paramView) { this.currentView = paramView; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public View getView() { return this.currentView; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public int prepareSound(int paramInt, MediaContainer paramMediaContainer) { return -1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void clearSound(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVworldXfrm(int paramInt, Transform3D paramTransform3D) {
/* 153 */     Sample sample = getSample(paramInt);
/* 154 */     if (sample != null) {
/* 155 */       sample.vworldXfrm.set(paramTransform3D);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int startSample(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int stopSample(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void updateSample(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void muteSample(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void unmuteSample(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void pauseSample(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void unpauseSample(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSampleGain(int paramInt, float paramFloat) {
/* 216 */     Sample sample = getSample(paramInt);
/* 217 */     if (sample != null) {
/* 218 */       sample.setGain(paramFloat);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoop(int paramInt1, int paramInt2) {
/* 228 */     Sample sample = getSample(paramInt1);
/* 229 */     if (sample != null) {
/* 230 */       sample.setLoopCount(paramInt2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(int paramInt, Point3d paramPoint3d) {
/* 240 */     Sample sample = getSample(paramInt);
/* 241 */     if (sample != null) {
/* 242 */       sample.setPosition(paramPoint3d);
/*     */     }
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
/*     */   public void setDistanceGain(int paramInt, double[] paramArrayOfDouble1, float[] paramArrayOfFloat1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat2) {
/* 258 */     Sample sample = getSample(paramInt);
/* 259 */     if (sample != null) {
/* 260 */       sample.setDistanceGain(paramArrayOfDouble1, paramArrayOfFloat1, paramArrayOfDouble2, paramArrayOfFloat2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(int paramInt, Vector3d paramVector3d) {
/* 271 */     Sample sample = getSample(paramInt);
/* 272 */     if (sample != null) {
/* 273 */       sample.setDirection(paramVector3d);
/*     */     }
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
/*     */   public void setAngularAttenuation(int paramInt1, int paramInt2, double[] paramArrayOfDouble, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 290 */     Sample sample = getSample(paramInt1);
/* 291 */     if (sample != null) {
/* 292 */       sample.setAngularAttenuation(paramInt2, paramArrayOfDouble, paramArrayOfFloat1, paramArrayOfFloat2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public void setRolloff(float paramFloat) { this.attribs.rolloff = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public void setReflectionCoefficient(float paramFloat) { this.attribs.reflectionCoefficient = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public void setReverbDelay(float paramFloat) { this.attribs.reverbDelay = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public void setReverbOrder(int paramInt) { this.attribs.reverbOrder = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public void setDistanceFilter(int paramInt, double[] paramArrayOfDouble, float[] paramArrayOfFloat) { this.attribs.setDistanceFilter(paramInt, paramArrayOfDouble, paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 357 */   public void setFrequencyScaleFactor(float paramFloat) { this.attribs.frequencyScaleFactor = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 368 */   public void setVelocityScaleFactor(float paramFloat) { this.attribs.velocityScaleFactor = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfChannelsUsed(int paramInt) {
/* 379 */     Sample sample = getSample(paramInt);
/* 380 */     if (sample != null) {
/* 381 */       return sample.getNumberOfChannelsUsed();
/*     */     }
/* 383 */     return 0;
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
/*     */   public int getNumberOfChannelsUsed(int paramInt, boolean paramBoolean) {
/* 398 */     Sample sample = getSample(paramInt);
/* 399 */     if (sample != null) {
/* 400 */       return sample.getNumberOfChannelsUsed();
/*     */     }
/* 402 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSampleDuration(int paramInt) {
/* 411 */     Sample sample = getSample(paramInt);
/* 412 */     if (sample != null) {
/* 413 */       return sample.getDuration();
/*     */     }
/* 415 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStartTime(int paramInt) {
/* 424 */     Sample sample = getSample(paramInt);
/* 425 */     if (sample != null) {
/* 426 */       return sample.getStartTime();
/*     */     }
/* 428 */     return 0L;
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
/* 439 */   protected ArrayList getSampleList() { return this.samples; }
/*     */ 
/*     */ 
/*     */   
/* 443 */   public int getSampleListSize() { return this.samples.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sample getSample(int paramInt) {
/* 455 */     synchronized (this.samples) {
/* 456 */       if (paramInt >= 0 && paramInt < this.samples.size()) {
/* 457 */         return (Sample)this.samples.get(paramInt);
/*     */       }
/*     */ 
/*     */       
/* 461 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 471 */   public AuralParameters getAuralParameters() { return this.attribs; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\audioengines\AudioEngine3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */