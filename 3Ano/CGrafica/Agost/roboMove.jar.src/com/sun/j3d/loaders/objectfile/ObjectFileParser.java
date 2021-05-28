/*     */ package com.sun.j3d.loaders.objectfile;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
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
/*     */ class ObjectFileParser
/*     */   extends StreamTokenizer
/*     */ {
/*     */   private static final char BACKSLASH = '\\';
/*     */   
/*     */   void setup() {
/*  63 */     resetSyntax();
/*  64 */     eolIsSignificant(true);
/*  65 */     lowerCaseMode(true);
/*     */ 
/*     */     
/*  68 */     wordChars(33, 126);
/*     */ 
/*     */     
/*  71 */     commentChar(33);
/*     */     
/*  73 */     whitespaceChars(32, 32);
/*  74 */     whitespaceChars(10, 10);
/*  75 */     whitespaceChars(13, 13);
/*  76 */     whitespaceChars(9, 9);
/*     */ 
/*     */     
/*  79 */     ordinaryChar(35);
/*  80 */     ordinaryChar(47);
/*  81 */     ordinaryChar(92);
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
/*     */   void getToken() {
/*  95 */     boolean bool = false;
/*     */     
/*     */     try {
/*     */       do {
/*  99 */         int i = nextToken();
/* 100 */         if (i == 92)
/* 101 */         { i = nextToken();
/* 102 */           if (this.ttype != 10) bool = true;  }
/* 103 */         else { bool = true; } 
/* 104 */       } while (!bool);
/*     */     }
/* 106 */     catch (IOException iOException) {
/* 107 */       throw new ParsingErrorException("IO error on line " + lineno() + ": " + iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void printToken() {
/* 114 */     switch (this.ttype) {
/*     */       case 10:
/* 116 */         System.out.println("Token EOL");
/*     */         break;
/*     */       case -1:
/* 119 */         System.out.println("Token EOF");
/*     */         break;
/*     */       case -3:
/* 122 */         System.out.println("Token TT_WORD: " + this.sval);
/*     */         break;
/*     */       case 47:
/* 125 */         System.out.println("Token /");
/*     */         break;
/*     */       case 92:
/* 128 */         System.out.println("Token \\");
/*     */         break;
/*     */       case 35:
/* 131 */         System.out.println("Token #");
/*     */         break;
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
/*     */   void skipToNextLine() {
/* 144 */     while (this.ttype != 10) {
/* 145 */       getToken();
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
/*     */   void getNumber() {
/*     */     try {
/* 162 */       getToken();
/* 163 */       if (this.ttype != -3)
/* 164 */         throw new ParsingErrorException("Expected number on line " + lineno()); 
/* 165 */       this.nval = Double.valueOf(this.sval).doubleValue();
/*     */     }
/* 167 */     catch (NumberFormatException numberFormatException) {
/* 168 */       throw new ParsingErrorException(numberFormatException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   ObjectFileParser(Reader paramReader) {
/* 175 */     super(paramReader);
/* 176 */     setup();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\objectfile\ObjectFileParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */