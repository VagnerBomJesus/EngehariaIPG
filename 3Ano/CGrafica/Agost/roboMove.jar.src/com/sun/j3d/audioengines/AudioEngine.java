/*     */ package com.sun.j3d.audioengines;
/*     */ 
/*     */ import javax.media.j3d.AudioDevice;
/*     */ import javax.media.j3d.PhysicalEnvironment;
/*     */ import javax.media.j3d.Sound;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AudioEngine
/*     */   implements AudioDevice
/*     */ {
/*     */   int fileDescriptor;
/*     */   int audioPlaybackType;
/*     */   float distanceToSpeaker;
/*     */   float angleOffsetToSpeaker;
/*     */   int channelsAvailable;
/*     */   int totalChannels;
/*     */   
/*     */   public AudioEngine(PhysicalEnvironment paramPhysicalEnvironment) {
/*  69 */     this.audioPlaybackType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.distanceToSpeaker = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.angleOffsetToSpeaker = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     this.channelsAvailable = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     this.totalChannels = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     paramPhysicalEnvironment.setAudioDevice(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean initialize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean close();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void setAudioPlaybackType(int paramInt) { this.audioPlaybackType = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public int getAudioPlaybackType() { return this.audioPlaybackType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public void setCenterEarToSpeaker(float paramFloat) { this.distanceToSpeaker = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public float getCenterEarToSpeaker() { return this.distanceToSpeaker; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void setAngleOffsetToSpeaker(float paramFloat) { this.angleOffsetToSpeaker = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public float getAngleOffsetToSpeaker() { return this.angleOffsetToSpeaker; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public int getTotalChannels() { return this.totalChannels; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public int getChannelsAvailable() { return this.channelsAvailable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChannelsUsedForSound(Sound paramSound) {
/* 197 */     if (paramSound != null) {
/* 198 */       return paramSound.getNumberOfChannelsUsed();
/*     */     }
/* 200 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\audioengines\AudioEngine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */