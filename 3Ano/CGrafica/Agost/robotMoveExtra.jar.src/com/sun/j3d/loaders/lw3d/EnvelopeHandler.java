/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class EnvelopeHandler
/*     */   extends TextfileParser
/*     */ {
/*  69 */   float theValue = 0.0F;
/*     */   boolean hasValue = false;
/*     */   boolean hasEnvelope = true;
/*  72 */   LwsEnvelope theEnvelope = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   EnvelopeHandler(StreamTokenizer paramStreamTokenizer, int paramInt, float paramFloat) { this(paramStreamTokenizer, paramInt, paramFloat, "com.sun.j3d.utils.loaders.lw3d.LwsEnvelope"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EnvelopeHandler(StreamTokenizer paramStreamTokenizer, int paramInt, float paramFloat, String paramString) throws ParsingErrorException {
/*     */     try {
/*  98 */       this.theValue = (float)getNumber(paramStreamTokenizer);
/*  99 */       this.hasValue = true;
/*     */     }
/* 101 */     catch (NumberFormatException numberFormatException) {
/* 102 */       if (paramStreamTokenizer.ttype == 40) {
/* 103 */         paramStreamTokenizer.pushBack();
/*     */         
/*     */         try {
/* 106 */           Class clazz = Class.forName(paramString);
/* 107 */           Constructor[] arrayOfConstructor = clazz.getConstructors();
/* 108 */           Constructor constructor = arrayOfConstructor[0];
/* 109 */           Object[] arrayOfObject = new Object[3];
/* 110 */           arrayOfObject[0] = paramStreamTokenizer;
/* 111 */           arrayOfObject[1] = new Integer(paramInt);
/* 112 */           arrayOfObject[2] = new Float(paramFloat);
/*     */           try {
/* 114 */             this.theEnvelope = (LwsEnvelope)constructor.newInstance(arrayOfObject);
/* 115 */             this.hasEnvelope = true;
/*     */           }
/* 117 */           catch (InstantiationException instantiationException) {
/*     */ 
/*     */           
/* 120 */           } catch (IllegalAccessException illegalAccessException) {
/*     */ 
/*     */           
/* 123 */           } catch (InvocationTargetException invocationTargetException) {}
/*     */ 
/*     */         
/*     */         }
/* 127 */         catch (ClassNotFoundException classNotFoundException) {}
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\EnvelopeHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */