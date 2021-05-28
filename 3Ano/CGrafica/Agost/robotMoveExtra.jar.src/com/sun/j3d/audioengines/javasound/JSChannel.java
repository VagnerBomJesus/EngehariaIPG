/*     */ package com.sun.j3d.audioengines.javasound;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import javax.sound.sampled.DataLine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JSChannel
/*     */ {
/*  70 */   AudioInputStream ais = null;
/*  71 */   long startTime = 0L;
/*  72 */   URL url = null;
/*  73 */   InputStream inputStream = null;
/*  74 */   AudioFormat audioFormat = null;
/*     */ 
/*     */ 
/*     */   
/*  78 */   static double panLeft = 1.0D;
/*  79 */   static double panRight = -1.0D;
/*  80 */   float rateInHz = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean debugFlag = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void debugPrint(String paramString) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void debugPrintln(String paramString) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   boolean initialize() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AudioInputStream initAudioInputStream(InputStream paramInputStream, boolean paramBoolean) {
/* 111 */     this.ais = null;
/* 112 */     if (paramInputStream == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       this.inputStream = null;
/* 118 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 125 */       this.ais = AudioSystem.getAudioInputStream(paramInputStream);
/*     */     
/*     */     }
/* 128 */     catch (Exception exception) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       exception.printStackTrace();
/* 134 */       this.inputStream = null;
/* 135 */       return null;
/*     */     } 
/*     */     
/* 138 */     this.inputStream = paramInputStream;
/* 139 */     this.url = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     return this.ais;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AudioInputStream initAudioInputStream(URL paramURL, boolean paramBoolean) {
/* 154 */     this.ais = null;
/* 155 */     if (paramURL == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       this.url = null;
/* 161 */       return null;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 166 */       this.ais = AudioSystem.getAudioInputStream(paramURL.openStream());
/*     */     }
/* 168 */     catch (Exception exception) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 173 */       exception.printStackTrace();
/* 174 */       this.url = null;
/* 175 */       return null;
/*     */     } 
/*     */     
/* 178 */     this.url = paramURL;
/* 179 */     this.inputStream = null;
/* 180 */     return this.ais;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   AudioInputStream reinitAudioInputStream(URL paramURL) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   AudioInputStream reinitAudioInputStream(InputStream paramInputStream) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   DataLine initDataLine(AudioInputStream paramAudioInputStream) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getDuration() {
/* 262 */     if (this.ais == null || this.audioFormat == null)
/*     */     {
/*     */       
/* 265 */       return -1L;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     long l = this.ais.getFrameLength();
/*     */ 
/*     */     
/* 274 */     if (l <= 0L) {
/* 275 */       return -1L;
/*     */     }
/* 277 */     float f = this.audioFormat.getFrameRate();
/* 278 */     this.rateInHz = this.audioFormat.getSampleRate();
/*     */ 
/*     */     
/* 281 */     if (l <= 0L)
/* 282 */       return -1L; 
/* 283 */     return (long)((float)l / f);
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
/* 296 */   boolean startSamples(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   boolean startSample(int paramInt1, float paramFloat, int paramInt2) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int stopSample() {
/* 314 */     this.startTime = 0L;
/* 315 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int stopSamples() {
/* 326 */     this.startTime = 0L;
/* 327 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSampleGain(float paramFloat) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSampleDelay(int paramInt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSampleReverb(int paramInt, boolean paramBoolean) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSampleRate() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void scaleSampleRate(float paramFloat) {
/* 362 */     if (this.ais == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 370 */     AudioFormat audioFormat1 = this.ais.getFormat();
/* 371 */     float f = audioFormat1.getSampleRate();
/*     */     
/* 373 */     double d = (f * paramFloat);
/* 374 */     if (d > 48000.0D) {
/* 375 */       d = 48000.0D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   int pauseSamples() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   int pauseSample() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   int unpauseSamples() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 420 */   int unpauseSample() { return 0; }
/*     */   
/*     */   void setSampleFiltering(boolean paramBoolean, float paramFloat) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\audioengines\javasound\JSChannel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */