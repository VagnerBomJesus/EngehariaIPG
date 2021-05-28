/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import javax.media.j3d.Background;
/*     */ import javax.media.j3d.BoundingSphere;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsBackground
/*     */   extends TextfileParser
/*     */ {
/*     */   int solidBackdrop;
/*     */   Color3f color;
/*     */   Color3f zenithColor;
/*     */   Color3f skyColor;
/*     */   Color3f groundColor;
/*     */   Color3f nadirColor;
/*     */   Background backgroundObject;
/*     */   
/*     */   LwsBackground(StreamTokenizer paramStreamTokenizer, int paramInt) throws ParsingErrorException {
/*  69 */     this.backgroundObject = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.debugPrinter.setValidOutput(paramInt);
/*  79 */     debugOutput(1, "LwsBackground()");
/*  80 */     this.color = new Color3f(0.0F, 0.0F, 0.0F);
/*  81 */     this.zenithColor = new Color3f(0.0F, 0.0F, 0.0F);
/*  82 */     this.skyColor = new Color3f(0.0F, 0.0F, 0.0F);
/*  83 */     this.groundColor = new Color3f(0.0F, 0.0F, 0.0F);
/*  84 */     this.nadirColor = new Color3f(0.0F, 0.0F, 0.0F);
/*     */     
/*  86 */     this.solidBackdrop = (int)getNumber(paramStreamTokenizer);
/*  87 */     while (!isCurrentToken(paramStreamTokenizer, "FogType")) {
/*  88 */       debugOutputLn(8, "currentToken = " + paramStreamTokenizer.sval);
/*     */       
/*  90 */       if (isCurrentToken(paramStreamTokenizer, "BackdropColor")) {
/*  91 */         this.color.x = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*  92 */         this.color.y = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*  93 */         this.color.z = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*     */       }
/*  95 */       else if (isCurrentToken(paramStreamTokenizer, "NadirColor")) {
/*  96 */         this.nadirColor.x = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*  97 */         this.nadirColor.y = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*  98 */         this.nadirColor.z = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*     */       }
/* 100 */       else if (isCurrentToken(paramStreamTokenizer, "SkyColor")) {
/* 101 */         this.skyColor.x = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 102 */         this.skyColor.y = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 103 */         this.skyColor.z = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*     */       }
/* 105 */       else if (isCurrentToken(paramStreamTokenizer, "GroundColor")) {
/* 106 */         this.groundColor.x = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 107 */         this.groundColor.y = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 108 */         this.groundColor.z = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*     */       }
/* 110 */       else if (isCurrentToken(paramStreamTokenizer, "NadirColor")) {
/* 111 */         this.nadirColor.x = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 112 */         this.nadirColor.y = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 113 */         this.nadirColor.z = (float)getNumber(paramStreamTokenizer) / 255.0F;
/*     */       } 
/*     */       try {
/* 116 */         paramStreamTokenizer.nextToken();
/*     */       }
/* 118 */       catch (IOException iOException) {
/* 119 */         throw new ParsingErrorException(iOException.getMessage());
/*     */       } 
/*     */     } 
/* 122 */     paramStreamTokenizer.pushBack();
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
/*     */   void createJava3dObject() {
/* 142 */     if (this.solidBackdrop != 0) {
/* 143 */       this.backgroundObject = new Background(this.color);
/* 144 */       debugOutput(2, "Background color = " + this.color);
/*     */     } else {
/*     */       
/* 147 */       this.backgroundObject = new Background(this.skyColor);
/* 148 */       debugOutput(2, "Background color = " + this.skyColor);
/*     */     } 
/* 150 */     BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 100000.0D);
/* 151 */     this.backgroundObject.setApplicationBounds(boundingSphere);
/*     */   }
/*     */ 
/*     */   
/* 155 */   Background getObjectNode() { return this.backgroundObject; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   void printVals() { debugOutputLn(2, "  BACKGROUND vals: "); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\lw3d\LwsBackground.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */