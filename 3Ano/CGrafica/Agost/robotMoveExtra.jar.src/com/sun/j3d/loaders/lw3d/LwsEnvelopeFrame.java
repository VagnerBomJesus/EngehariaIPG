/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import java.io.StreamTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsEnvelopeFrame
/*     */   extends TextfileParser
/*     */ {
/*     */   double value;
/*     */   double frameNumber;
/*     */   int linearValue;
/*     */   double tension;
/*     */   double continuity;
/*     */   double bias;
/*     */   
/*     */   LwsEnvelopeFrame(StreamTokenizer paramStreamTokenizer) {
/*  72 */     this.value = getNumber(paramStreamTokenizer);
/*  73 */     debugOutputLn(2, "value = " + this.value);
/*  74 */     this.frameNumber = (int)getNumber(paramStreamTokenizer);
/*  75 */     this.linearValue = (int)getNumber(paramStreamTokenizer);
/*  76 */     debugOutputLn(2, "framenum, linear " + this.frameNumber + " , " + this.linearValue);
/*  77 */     this.tension = getNumber(paramStreamTokenizer);
/*  78 */     this.continuity = getNumber(paramStreamTokenizer);
/*  79 */     this.bias = getNumber(paramStreamTokenizer);
/*  80 */     debugOutputLn(2, "tension, cont, bias = " + this.tension + ", " + this.continuity + ", " + this.bias);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   double getValue() { return this.value; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   double getFrameNum() { return this.frameNumber; }
/*     */ 
/*     */ 
/*     */   
/*     */   void printVals() {
/*  97 */     debugOutputLn(2, "         value = " + this.value);
/*  98 */     debugOutputLn(2, "         frameNum = " + this.frameNumber);
/*  99 */     debugOutputLn(2, "         lin = " + this.linearValue);
/* 100 */     debugOutputLn(2, "         tension = " + this.tension);
/* 101 */     debugOutputLn(2, "         continuity = " + this.continuity);
/* 102 */     debugOutputLn(2, "         bias = " + this.bias);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\LwsEnvelopeFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */