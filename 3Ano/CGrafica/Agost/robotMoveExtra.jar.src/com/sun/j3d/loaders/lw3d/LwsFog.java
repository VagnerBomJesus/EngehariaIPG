/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.Fog;
/*     */ import javax.media.j3d.LinearFog;
/*     */ import javax.vecmath.Color3f;
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
/*     */ class LwsFog
/*     */   extends TextfileParser
/*     */ {
/*     */   float minDist;
/*     */   float maxDist;
/*     */   float minAmount;
/*     */   float maxAmount;
/*     */   int backdropFog;
/*     */   Color3f color;
/*     */   int type;
/*     */   Fog fogObject;
/*     */   
/*     */   LwsFog(StreamTokenizer paramStreamTokenizer, int paramInt) throws ParsingErrorException {
/*  67 */     this.fogObject = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.debugPrinter.setValidOutput(paramInt);
/*  74 */     debugOutput(1, "LwsFog()");
/*  75 */     this.color = new Color3f(0.0F, 0.0F, 0.0F);
/*     */     
/*  77 */     while (!isCurrentToken(paramStreamTokenizer, "DitherIntensity")) {
/*  78 */       debugOutputLn(8, "currentToken = " + paramStreamTokenizer.sval);
/*     */       
/*  80 */       if (isCurrentToken(paramStreamTokenizer, "FogMinDist")) {
/*  81 */         this.minDist = (float)getNumber(paramStreamTokenizer);
/*     */       }
/*  83 */       else if (isCurrentToken(paramStreamTokenizer, "FogMaxDist")) {
/*  84 */         this.maxDist = (float)getNumber(paramStreamTokenizer);
/*     */       }
/*  86 */       else if (isCurrentToken(paramStreamTokenizer, "FogMinAmount")) {
/*  87 */         this.minAmount = (float)getNumber(paramStreamTokenizer);
/*     */       }
/*  89 */       else if (isCurrentToken(paramStreamTokenizer, "FogMaxAmount")) {
/*  90 */         this.maxAmount = (float)getNumber(paramStreamTokenizer);
/*     */       }
/*  92 */       else if (isCurrentToken(paramStreamTokenizer, "BackdropFog")) {
/*  93 */         this.backdropFog = (int)getNumber(paramStreamTokenizer);
/*     */       }
/*  95 */       else if (isCurrentToken(paramStreamTokenizer, "FogColor")) {
/*  96 */         this.color.x = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*  97 */         this.color.y = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*  98 */         this.color.z = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*     */       } 
/*     */       try {
/* 101 */         paramStreamTokenizer.nextToken();
/*     */       }
/* 103 */       catch (IOException iOException) {
/* 104 */         throw new ParsingErrorException(iOException.getMessage());
/*     */       } 
/*     */     } 
/* 107 */     paramStreamTokenizer.pushBack();
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
/*     */   void createJava3dObject() {
/* 122 */     this.fogObject = new LinearFog(this.color, this.minDist, this.maxDist);
/* 123 */     debugOutputLn(2, "just set linearFog with color, minDist, maxDist = " + this.color + ", " + this.minDist + ", " + this.maxDist);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 100000.0D);
/* 129 */     this.fogObject.setInfluencingBounds(boundingSphere);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 134 */   Fog getObjectNode() { return this.fogObject; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   void printVals() { debugOutputLn(2, "  FOG vals: "); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\LwsFog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */