/*     */ package com.sun.j3d.audioengines.javasound;
/*     */ 
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import javax.sound.sampled.Clip;
/*     */ import javax.sound.sampled.DataLine;
/*     */ import javax.sound.sampled.LineEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JSClip
/*     */   extends JSChannel
/*     */ {
/*     */   Clip line;
/*  71 */   Clip otherChannel = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   Clip reverbChannel = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   DataLine initDataLine(AudioInputStream paramAudioInputStream) {
/*     */     try {
/*  96 */       this.audioFormat = paramAudioInputStream.getFormat();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 102 */       if (this.audioFormat.getEncoding() == AudioFormat.Encoding.ULAW || this.audioFormat.getEncoding() == AudioFormat.Encoding.ALAW) {
/*     */ 
/*     */         
/* 105 */         AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, this.audioFormat.getSampleRate(), this.audioFormat.getSampleSizeInBits() * 2, this.audioFormat.getChannels(), this.audioFormat.getFrameSize() * 2, this.audioFormat.getFrameRate(), true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 114 */         paramAudioInputStream = AudioSystem.getAudioInputStream(audioFormat, paramAudioInputStream);
/* 115 */         this.audioFormat = audioFormat;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       DataLine.Info info = new DataLine.Info(Clip.class, this.audioFormat);
/*     */       
/* 132 */       this.line = (Clip)AudioSystem.getLine(info);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 144 */       this.line.open(paramAudioInputStream);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 150 */     catch (Exception exception) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       exception.printStackTrace();
/*     */ 
/*     */       
/* 158 */       return null;
/*     */     } 
/* 160 */     return this.line;
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
/*     */   
/*     */   boolean startSamples(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3) {
/* 191 */     if (this.otherChannel == null || this.reverbChannel == null) {
/* 192 */       startSample(paramInt1, paramFloat1, paramInt2);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     if (this.ais == null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 203 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 207 */     Clip clip1 = this.line;
/* 208 */     Clip clip2 = this.otherChannel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     double d1 = 0.0039D;
/* 224 */     double d2 = paramFloat1;
/* 225 */     double d3 = paramFloat2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     this.startTime = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 254 */       this.line.setLoopPoints(0, -1);
/* 255 */       this.line.loop(paramInt1);
/* 256 */       this.line.start();
/*     */     }
/* 258 */     catch (Exception exception) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 263 */       exception.printStackTrace();
/* 264 */       this.startTime = 0L;
/* 265 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 270 */     return true;
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
/*     */   boolean startSample(int paramInt1, float paramFloat, int paramInt2) {
/* 299 */     this.line.setFramePosition(0);
/* 300 */     this.line.setLoopPoints(0, -1);
/* 301 */     this.line.loop(paramInt1);
/* 302 */     this.line.start();
/* 303 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int stopSample() {
/* 312 */     this.line.stop();
/*     */     
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
/*     */ 
/*     */   
/*     */   int stopSamples() {
/* 328 */     this.line.stop();
/*     */     
/* 330 */     this.startTime = 0L;
/* 331 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(LineEvent paramLineEvent) {
/* 338 */     if (paramLineEvent.getType().equals(LineEvent.Type.STOP)) {
/* 339 */       this.line.close();
/*     */     }
/* 341 */     else if (paramLineEvent.getType().equals(LineEvent.Type.CLOSE)) {
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\audioengines\javasound\JSClip.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */