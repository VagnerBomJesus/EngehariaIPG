/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.IOException;
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
/*     */ class TextfileParser
/*     */ {
/*  59 */   static int WORD = -3;
/*  60 */   static int NUMBER = -2;
/*  61 */   int currentLevel = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   char lineSeparatorChar = Character.MIN_VALUE;
/*     */ 
/*     */   
/*  70 */   protected DebugOutput debugPrinter = new DebugOutput(16); static final int TRACE = 1; static final int VALUES = 2; static final int MISC = 4; TextfileParser() {
/*  71 */     String str = System.getProperty("line.separator");
/*  72 */     this.lineSeparatorChar = str.charAt(0);
/*  73 */     debugOutputLn(2, "lineSeparatorChar = " + this.lineSeparatorChar);
/*     */   }
/*     */   static final int LINE_TRACE = 8; static final int NONE = 0; static final int EXCEPTION = 16; static final int TIME = 32;
/*     */   
/*     */   protected void debugOutputLn(int paramInt, String paramString) {
/*  78 */     if (paramString.equals("")) {
/*  79 */       this.debugPrinter.println(paramInt, paramString);
/*     */     } else {
/*  81 */       this.debugPrinter.println(paramInt, getClass().getName() + "::" + paramString);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  87 */   protected void debugOutput(int paramInt, String paramString) { this.debugPrinter.print(paramInt, paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void skipUntilString(StreamTokenizer paramStreamTokenizer, String paramString) throws ParsingErrorException {
/*  97 */     boolean bool = false;
/*     */     try {
/*  99 */       while (!bool) {
/* 100 */         paramStreamTokenizer.nextToken();
/* 101 */         if (paramStreamTokenizer.ttype == WORD && paramStreamTokenizer.sval.equals(paramString))
/*     */         {
/* 103 */           bool = true;
/*     */         }
/*     */       } 
/* 106 */     } catch (IOException iOException) {
/* 107 */       throw new ParsingErrorException(iOException.getMessage());
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
/*     */   double getNumber(StreamTokenizer paramStreamTokenizer) throws ParsingErrorException, NumberFormatException {
/*     */     try {
/* 121 */       int i = paramStreamTokenizer.nextToken();
/*     */     }
/* 123 */     catch (IOException iOException) {
/* 124 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/* 126 */     checkType(paramStreamTokenizer, WORD);
/* 127 */     return Double.valueOf(paramStreamTokenizer.sval).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getString(StreamTokenizer paramStreamTokenizer) throws ParsingErrorException {
/*     */     try {
/* 135 */       paramStreamTokenizer.nextToken();
/*     */     }
/* 137 */     catch (IOException iOException) {
/* 138 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/* 140 */     checkType(paramStreamTokenizer, WORD);
/* 141 */     return paramStreamTokenizer.sval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName(StreamTokenizer paramStreamTokenizer) throws ParsingErrorException {
/* 152 */     String str = "";
/* 153 */     paramStreamTokenizer.ordinaryChar(this.lineSeparatorChar);
/* 154 */     paramStreamTokenizer.ordinaryChar(10);
/* 155 */     paramStreamTokenizer.ordinaryChar(13);
/*     */     try {
/* 157 */       paramStreamTokenizer.nextToken();
/*     */ 
/*     */       
/* 160 */       while (paramStreamTokenizer.ttype != this.lineSeparatorChar && paramStreamTokenizer.ttype != 13 && paramStreamTokenizer.ttype != 10) {
/* 161 */         if (paramStreamTokenizer.ttype != 40 && paramStreamTokenizer.ttype != 41)
/*     */         {
/* 163 */           str = str + paramStreamTokenizer.sval; } 
/* 164 */         paramStreamTokenizer.nextToken();
/*     */       }
/*     */     
/* 167 */     } catch (IOException iOException) {
/* 168 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/* 170 */     paramStreamTokenizer.whitespaceChars(this.lineSeparatorChar, this.lineSeparatorChar);
/* 171 */     paramStreamTokenizer.whitespaceChars(10, 10);
/* 172 */     paramStreamTokenizer.whitespaceChars(13, 13);
/* 173 */     debugOutputLn(2, "name = " + str);
/* 174 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getAndCheckString(StreamTokenizer paramStreamTokenizer, String paramString) throws ParsingErrorException {
/*     */     try {
/* 184 */       paramStreamTokenizer.nextToken();
/* 185 */       checkString(paramStreamTokenizer, paramString);
/*     */     }
/* 187 */     catch (IOException iOException) {
/* 188 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void checkString(StreamTokenizer paramStreamTokenizer, String paramString) throws ParsingErrorException {
/* 198 */     if (paramStreamTokenizer.ttype != -3 || !paramStreamTokenizer.sval.equals(paramString))
/*     */     {
/* 200 */       throw new ParsingErrorException("Bad String Token (wanted " + paramString + ", got " + paramStreamTokenizer.sval + ": " + paramStreamTokenizer.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void checkType(StreamTokenizer paramStreamTokenizer, int paramInt) throws ParsingErrorException {
/* 211 */     if (paramStreamTokenizer.ttype != paramInt) {
/* 212 */       throw new ParsingErrorException("Bad Type Token, Expected " + paramInt + " and received" + paramStreamTokenizer.ttype);
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
/*     */   void skip(StreamTokenizer paramStreamTokenizer, String paramString, int paramInt) throws ParsingErrorException {
/*     */     try {
/* 225 */       paramStreamTokenizer.nextToken();
/* 226 */       checkString(paramStreamTokenizer, paramString);
/* 227 */       for (byte b = 0; b < paramInt; b++) {
/* 228 */         paramStreamTokenizer.nextToken();
/*     */       }
/*     */     }
/* 231 */     catch (IOException iOException) {
/* 232 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isCurrentToken(StreamTokenizer paramStreamTokenizer, String paramString) {
/* 241 */     if (paramStreamTokenizer.ttype == WORD)
/* 242 */       return paramStreamTokenizer.sval.equals(paramString); 
/* 243 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\TextfileParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */