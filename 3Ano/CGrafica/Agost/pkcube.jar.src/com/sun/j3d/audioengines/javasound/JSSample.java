/*     */ package com.sun.j3d.audioengines.javasound;
/*     */ 
/*     */ import com.sun.j3d.audioengines.AuralParameters;
/*     */ import com.sun.j3d.audioengines.Sample;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import javax.media.j3d.MediaContainer;
/*     */ import javax.media.j3d.View;
/*     */ import javax.sound.sampled.AudioInputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JSSample
/*     */   extends Sample
/*     */ {
/*     */   static final int STREAMING_AUDIO_DATA = 1;
/*     */   static final int BUFFERED_AUDIO_DATA = 2;
/*     */   static final int STREAMING_MIDI_DATA = 3;
/*     */   static final int BUFFERED_MIDI_DATA = 3;
/*     */   static final int UNSUPPORTED_DATA_TYPE = -1;
/*     */   static final int NULL_SAMPLE = -1;
/* 103 */   int dataType = 2;
/*     */   
/* 105 */   JSChannel channel = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   long dataOffset = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   long timeDeactivated = 0L;
/* 116 */   long positionDeactivated = 0L;
/*     */   
/* 118 */   long sampleLength = 0L;
/* 119 */   long loopStartOffset = 0L;
/* 120 */   long loopLength = 0L;
/* 121 */   long attackLength = 0L;
/* 122 */   long releaseLength = 0L;
/*     */   
/* 124 */   float rateRatio = 1.0F;
/* 125 */   float currentRateRatio = -1.0F;
/* 126 */   float targetRateRatio = -1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean rampRateFlag = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int paramInt, View paramView, AuralParameters paramAuralParameters) {
/* 148 */     float f = paramAuralParameters.frequencyScaleFactor;
/* 149 */     if (paramAuralParameters != null) {
/* 150 */       if (f > 0.0F)
/*     */       {
/*     */ 
/*     */         
/* 154 */         this.rateRatio = this.currentRateRatio * f;
/*     */       }
/*     */     } else {
/* 157 */       this.rateRatio = this.currentRateRatio;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 166 */     super.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     this.dataType = -1;
/* 175 */     this.dataOffset = 0L;
/* 176 */     this.timeDeactivated = 0L;
/* 177 */     this.positionDeactivated = 0L;
/* 178 */     this.sampleLength = 0L;
/* 179 */     this.loopStartOffset = 0L;
/* 180 */     this.loopLength = 0L;
/* 181 */     this.attackLength = 0L;
/* 182 */     this.releaseLength = 0L;
/* 183 */     this.rateRatio = 1.0F;
/* 184 */     this.channel = null;
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
/*     */   boolean load(MediaContainer paramMediaContainer) {
/* 196 */     String str = paramMediaContainer.getURLString();
/* 197 */     URL uRL = paramMediaContainer.getURLObject();
/* 198 */     InputStream inputStream = paramMediaContainer.getInputStream();
/* 199 */     boolean bool = paramMediaContainer.getCacheEnable();
/* 200 */     AudioInputStream audioInputStream = null;
/* 201 */     DataLine dataLine = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     if (bool) {
/* 220 */       this.dataType = 2;
/*     */     } else {
/* 222 */       this.dataType = 1;
/*     */     } 
/* 224 */     if (uRL == null && inputStream == null && str == null)
/*     */     {
/*     */       
/* 227 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 231 */     if (str != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 237 */         uRL = new URL(str);
/*     */       }
/* 239 */       catch (Exception exception) {
/*     */         
/* 241 */         return true;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 246 */     if (this.dataType == 2) {
/*     */ 
/*     */       
/* 249 */       this.channel = new JSClip();
/*     */ 
/*     */       
/* 252 */       if (uRL != null) {
/* 253 */         audioInputStream = this.channel.initAudioInputStream(uRL, bool);
/* 254 */       } else if (inputStream != null) {
/* 255 */         audioInputStream = this.channel.initAudioInputStream(inputStream, bool);
/* 256 */       }  if (audioInputStream == null)
/*     */       {
/*     */ 
/*     */         
/* 260 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 264 */       dataLine = this.channel.initDataLine(audioInputStream);
/*     */     }
/* 266 */     else if (this.dataType == 1) {
/*     */ 
/*     */       
/* 269 */       this.channel = new JSStream();
/*     */ 
/*     */       
/* 272 */       if (uRL != null) {
/* 273 */         audioInputStream = this.channel.initAudioInputStream(uRL, bool);
/* 274 */       } else if (inputStream != null) {
/* 275 */         audioInputStream = this.channel.initAudioInputStream(inputStream, bool);
/* 276 */       }  if (audioInputStream == null)
/*     */       {
/*     */ 
/*     */         
/* 280 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 284 */       dataLine = this.channel.initDataLine(audioInputStream);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     if (dataLine == null) {
/*     */ 
/*     */       
/* 293 */       this.channel = null;
/* 294 */       return true;
/*     */     } 
/* 296 */     this.duration = this.channel.getDuration();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     setDirtyFlags(65535);
/* 304 */     setSoundType(this.soundType);
/* 305 */     setSoundData(paramMediaContainer);
/*     */ 
/*     */ 
/*     */     
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   void reset() { this.rateRatio = 1.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   boolean getFilterFlag() { return false; }
/*     */ 
/*     */   
/* 327 */   float getFilterFreq() { return -1.0F; }
/*     */ 
/*     */ 
/*     */   
/* 331 */   void setCurrentRateRatio(float paramFloat) { this.currentRateRatio = paramFloat; }
/*     */ 
/*     */ 
/*     */   
/* 335 */   float getCurrentRateRatio() { return this.currentRateRatio; }
/*     */ 
/*     */ 
/*     */   
/* 339 */   void setTargetRateRatio(float paramFloat) { this.targetRateRatio = paramFloat; }
/*     */ 
/*     */ 
/*     */   
/* 343 */   float getTargetRateRatio() { return this.targetRateRatio; }
/*     */ 
/*     */ 
/*     */   
/* 347 */   void setRampRateFlag(boolean paramBoolean) { this.rampRateFlag = paramBoolean; }
/*     */ 
/*     */ 
/*     */   
/* 351 */   boolean getRampRateFlag() { return this.rampRateFlag; }
/*     */ 
/*     */ 
/*     */   
/* 355 */   void setDataType(int paramInt) { this.dataType = paramInt; }
/*     */ 
/*     */ 
/*     */   
/* 359 */   int getDataType() { return this.dataType; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\audioengines\javasound\JSSample.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */