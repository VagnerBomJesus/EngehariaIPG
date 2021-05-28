/*     */ package com.sun.j3d.audioengines;
/*     */ 
/*     */ import javax.media.j3d.MediaContainer;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.View;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3d;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sample
/*     */ {
/*     */   protected static final boolean debugFlag = false;
/*     */   protected static final boolean internalErrors = false;
/*     */   public static final int NULL_SAMPLE = -1;
/*     */   
/*     */   protected void debugPrint(String paramString) {}
/*     */   
/*     */   protected void debugPrintln(String paramString) {}
/*     */   
/*  80 */   protected MediaContainer soundData = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   protected int soundType = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   protected float gain = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   protected float rateScaleFactor = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   protected int loopCount = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int DURATION_UNKNOWN = -1;
/*     */ 
/*     */ 
/*     */   
/* 109 */   protected long duration = -1L;
/*     */   
/* 111 */   protected int numberOfChannels = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mute = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   protected Transform3D vworldXfrm = new Transform3D();
/*     */ 
/*     */   
/*     */   protected boolean vwXfrmFlag = false;
/*     */ 
/*     */   
/* 129 */   protected Point3f position = new Point3f(0.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   protected double[] attenuationDistance = null;
/* 136 */   protected float[] attenuationGain = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   protected int dirtyFlags = 65535;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   protected Vector3f direction = new Vector3f(0.0F, 0.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   protected double[] backAttenuationDistance = null;
/* 159 */   protected float[] backAttenuationGain = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   protected double[] angularDistance = { 0.0D, 1.5707963267948966D };
/* 170 */   protected float[] angularGain = { 1.0F, 0.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int NO_FILTERING = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LOW_PASS = 1;
/*     */ 
/*     */ 
/*     */   
/* 183 */   protected int angularFilterType = -1;
/* 184 */   protected float[] angularFilterCutoff = { -1.0F, -1.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   protected float obstructionGain = 1.0F;
/* 193 */   protected int obstructionFilterType = -1;
/* 194 */   protected float obstructionFilterCutoff = -1.0F;
/* 195 */   protected float occlusionGain = 1.0F;
/* 196 */   protected int occlusionFilterType = -1;
/* 197 */   protected float occlusionFilterCutoff = -1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public long getDuration() { return 0L; }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public long getStartTime() { return 0L; }
/*     */ 
/*     */ 
/*     */   
/* 216 */   public int getNumberOfChannelsUsed() { return 0; }
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setDirtyFlags(int paramInt) { this.dirtyFlags = paramInt; }
/*     */ 
/*     */ 
/*     */   
/* 224 */   public int getDirtyFlags() { return this.dirtyFlags; }
/*     */ 
/*     */ 
/*     */   
/* 228 */   public void setSoundType(int paramInt) { this.soundType = paramInt; }
/*     */ 
/*     */ 
/*     */   
/* 232 */   public int getSoundType() { return this.soundType; }
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setSoundData(MediaContainer paramMediaContainer) { this.soundData = paramMediaContainer; }
/*     */ 
/*     */ 
/*     */   
/* 240 */   public MediaContainer getSoundData() { return this.soundData; }
/*     */ 
/*     */ 
/*     */   
/* 244 */   public void setMuteFlag(boolean paramBoolean) { this.mute = paramBoolean; }
/*     */ 
/*     */ 
/*     */   
/* 248 */   public boolean getMuteFlag() { return this.mute; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public void setVWrldXfrmFlag(boolean paramBoolean) { this.vwXfrmFlag = paramBoolean; }
/*     */ 
/*     */ 
/*     */   
/* 257 */   public boolean getVWrldXfrmFlag() { return this.vwXfrmFlag; }
/*     */ 
/*     */ 
/*     */   
/* 261 */   public void setGain(float paramFloat) { this.gain = paramFloat; }
/*     */ 
/*     */ 
/*     */   
/* 265 */   public float getGain() { return this.gain; }
/*     */ 
/*     */ 
/*     */   
/* 269 */   public void setLoopCount(int paramInt) { this.loopCount = paramInt; }
/*     */ 
/*     */ 
/*     */   
/* 273 */   public int getLoopCount() { return this.loopCount; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public void setPosition(Point3d paramPoint3d) { this.position.set(paramPoint3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDistanceGain(double[] paramArrayOfDouble1, float[] paramArrayOfFloat1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat2) {
/* 288 */     if (paramArrayOfDouble1 != null) {
/* 289 */       int i = paramArrayOfDouble1.length;
/* 290 */       this.attenuationDistance = new double[i];
/* 291 */       this.attenuationGain = new float[i];
/* 292 */       for (byte b = 0; b < i; b++) {
/* 293 */         this.attenuationDistance[b] = paramArrayOfDouble1[b];
/* 294 */         this.attenuationGain[b] = paramArrayOfFloat1[b];
/*     */       } 
/*     */     } else {
/*     */       
/* 298 */       this.attenuationDistance = null;
/* 299 */       this.attenuationGain = null;
/*     */     } 
/* 301 */     if (paramArrayOfDouble2 != null && paramArrayOfDouble1 != null) {
/* 302 */       int i = paramArrayOfDouble2.length;
/* 303 */       this.backAttenuationDistance = new double[i];
/* 304 */       this.backAttenuationGain = new float[i];
/* 305 */       for (byte b = 0; b < i; b++) {
/* 306 */         this.backAttenuationDistance[b] = paramArrayOfDouble2[b];
/* 307 */         this.backAttenuationGain[b] = paramArrayOfFloat2[b];
/*     */       } 
/*     */     } else {
/*     */       
/* 311 */       this.backAttenuationDistance = null;
/* 312 */       this.backAttenuationGain = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public void setDirection(Vector3d paramVector3d) { this.direction.set(paramVector3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAngularAttenuation(int paramInt, double[] paramArrayOfDouble, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 330 */     if (paramArrayOfDouble != null) {
/* 331 */       int i = paramArrayOfDouble.length;
/* 332 */       this.angularDistance = new double[i];
/* 333 */       this.angularGain = new float[i];
/* 334 */       if (paramInt != -1 && paramArrayOfFloat2 != null) {
/* 335 */         this.angularFilterCutoff = new float[i];
/*     */       } else {
/* 337 */         this.angularFilterCutoff = null;
/* 338 */       }  for (byte b = 0; b < i; b++) {
/* 339 */         this.angularDistance[b] = paramArrayOfDouble[b];
/* 340 */         this.angularGain[b] = paramArrayOfFloat1[b];
/* 341 */         if (paramInt != -1)
/* 342 */           this.angularFilterCutoff[b] = paramArrayOfFloat2[b]; 
/*     */       } 
/* 344 */       this.angularFilterType = paramInt;
/*     */     } else {
/*     */       
/* 347 */       this.angularDistance = null;
/* 348 */       this.angularGain = null;
/* 349 */       this.angularFilterCutoff = null;
/* 350 */       this.angularFilterType = -1;
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
/* 362 */   public void setRateScaleFactor(float paramFloat) { this.rateScaleFactor = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public float getRateScaleFactor() { return this.rateScaleFactor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public void setObstructionGain(float paramFloat) { this.obstructionGain = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 387 */   public float getObstructionGain() { return this.obstructionGain; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObstructionFilter(float paramFloat) {
/* 395 */     this.obstructionFilterType = 1;
/* 396 */     this.obstructionFilterCutoff = paramFloat;
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
/* 407 */   public void setOcclusionGain(float paramFloat) { this.occlusionGain = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public float getOcclusionGain() { return this.occlusionGain; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOcclusionFilter(float paramFloat) {
/* 423 */     this.occlusionFilterType = 1;
/* 424 */     this.occlusionFilterCutoff = paramFloat;
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
/*     */   public void clear() {
/* 438 */     this.soundData = (MediaContainer)null;
/* 439 */     this.soundType = -1;
/* 440 */     this.gain = 1.0F;
/* 441 */     this.loopCount = 0;
/* 442 */     this.duration = -1L;
/* 443 */     this.numberOfChannels = 0;
/* 444 */     this.vworldXfrm.setIdentity();
/* 445 */     this.vwXfrmFlag = false;
/* 446 */     this.position.set(0.0F, 0.0F, 0.0F);
/* 447 */     this.attenuationDistance = null;
/* 448 */     this.attenuationGain = null;
/* 449 */     this.direction.set(0.0F, 0.0F, 1.0F);
/* 450 */     this.backAttenuationDistance = null;
/* 451 */     this.backAttenuationGain = null;
/* 452 */     if (this.angularDistance != null) {
/* 453 */       this.angularDistance[0] = 0.0D;
/* 454 */       this.angularDistance[1] = 1.5707963705062866D;
/*     */     } 
/* 456 */     if (this.angularGain != null) {
/* 457 */       this.angularGain[0] = 1.0F;
/* 458 */       this.angularGain[1] = 0.0F;
/*     */     } 
/* 460 */     this.angularFilterType = -1;
/* 461 */     if (this.angularFilterCutoff != null) {
/* 462 */       this.angularFilterCutoff[0] = -1.0F;
/* 463 */       this.angularFilterCutoff[1] = -1.0F;
/*     */     } 
/* 465 */     this.obstructionGain = 1.0F;
/* 466 */     this.obstructionFilterType = -1;
/* 467 */     this.obstructionFilterCutoff = -1.0F;
/* 468 */     this.occlusionGain = 1.0F;
/* 469 */     this.occlusionFilterType = -1;
/* 470 */     this.occlusionFilterCutoff = -1.0F;
/*     */   }
/*     */   
/*     */   public void render(int paramInt, View paramView, AuralParameters paramAuralParameters) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\audioengines\Sample.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */