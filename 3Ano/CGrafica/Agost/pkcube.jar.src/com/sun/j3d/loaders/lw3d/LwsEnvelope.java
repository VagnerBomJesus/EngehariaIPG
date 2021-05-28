/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsEnvelope
/*     */   extends TextfileParser
/*     */ {
/*     */   String name;
/*     */   LwsEnvelopeFrame[] frames;
/*     */   int numFrames;
/*     */   int numChannels;
/*     */   boolean loop;
/*     */   float totalTime;
/*     */   int totalFrames;
/*     */   Behavior behaviors;
/*     */   
/*     */   LwsEnvelope(StreamTokenizer paramStreamTokenizer, int paramInt, float paramFloat) {
/*  77 */     this.numFrames = 0;
/*  78 */     this.totalTime = paramFloat;
/*  79 */     this.totalFrames = paramInt;
/*  80 */     this.name = getName(paramStreamTokenizer);
/*  81 */     getEnvelope(paramStreamTokenizer);
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
/*     */   void getEnvelope(StreamTokenizer paramStreamTokenizer) throws IncorrectFormatException, ParsingErrorException {
/*  93 */     debugOutputLn(1, "getEnvelope()");
/*  94 */     this.numChannels = (int)getNumber(paramStreamTokenizer);
/*  95 */     if (this.numChannels != 1) {
/*  96 */       throw new IncorrectFormatException(J3dUtilsI18N.getString("LwsEnvelope0"));
/*     */     }
/*     */     
/*  99 */     debugOutputLn(8, "got channels");
/*     */     
/* 101 */     this.numFrames = (int)getNumber(paramStreamTokenizer);
/* 102 */     this.frames = new LwsEnvelopeFrame[this.numFrames];
/* 103 */     debugOutputLn(2, "got frames" + this.numFrames);
/*     */     
/* 105 */     for (i = 0; i < this.numFrames; i++) {
/* 106 */       this.frames[i] = new LwsEnvelopeFrame(paramStreamTokenizer);
/*     */     }
/* 108 */     debugOutput(8, "got all frames");
/*     */     
/*     */     try {
/* 111 */       paramStreamTokenizer.nextToken();
/* 112 */       while (!isCurrentToken(paramStreamTokenizer, "EndBehavior"))
/*     */       {
/* 114 */         paramStreamTokenizer.nextToken();
/*     */       }
/*     */     }
/* 117 */     catch (IOException i) {
/* 118 */       IOException iOException; throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/* 120 */     i = (int)getNumber(paramStreamTokenizer);
/* 121 */     if (i == 1) {
/* 122 */       this.loop = false;
/*     */     } else {
/* 124 */       this.loop = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   void createJava3dBehaviors(TransformGroup paramTransformGroup) { this.behaviors = null; }
/*     */ 
/*     */ 
/*     */   
/* 137 */   Behavior getBehaviors() { return this.behaviors; }
/*     */ 
/*     */ 
/*     */   
/*     */   LwsEnvelopeFrame getFirstFrame() {
/* 142 */     if (this.numFrames > 0) {
/* 143 */       return this.frames[0];
/*     */     }
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   void printVals() {
/* 150 */     debugOutputLn(2, "   name = " + this.name);
/* 151 */     debugOutputLn(2, "   numChannels = " + this.numChannels);
/* 152 */     debugOutputLn(2, "   numFrames = " + this.numFrames);
/* 153 */     debugOutputLn(2, "   loop = " + this.loop);
/* 154 */     for (byte b = 0; b < this.numFrames; b++) {
/* 155 */       debugOutputLn(2, "       FRAME " + b);
/* 156 */       this.frames[b].printVals();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\LwsEnvelope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */