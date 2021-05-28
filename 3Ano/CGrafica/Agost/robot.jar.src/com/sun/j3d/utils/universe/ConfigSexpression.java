/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.ArrayList;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Matrix3d;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point2d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point4d;
/*     */ import javax.vecmath.Vector3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ConfigSexpression
/*     */ {
/*  57 */   private ArrayList elements = new ArrayList();
/*     */   
/*     */   private void syntaxError(StreamTokenizer paramStreamTokenizer, String paramString1, String paramString2) {
/*  60 */     System.out.println(paramString2 + ":\nat line " + paramStreamTokenizer.lineno() + " in " + paramString1);
/*  61 */     print(); System.out.print("\n\n");
/*     */   }
/*     */   
/*     */   private int myNextToken(StreamTokenizer paramStreamTokenizer, String paramString) {
/*  65 */     int i = 0;
/*     */     try {
/*  67 */       i = paramStreamTokenizer.nextToken();
/*     */     }
/*  69 */     catch (IOException iOException) {
/*  70 */       throw new RuntimeException(iOException + "\nwhile reading " + paramString);
/*     */     } 
/*  72 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object parseAndEval(ConfigContainer paramConfigContainer, StreamTokenizer paramStreamTokenizer, int paramInt) {
/*     */     int i;
/*  80 */     String str = paramConfigContainer.currentFileName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     this.elements.clear();
/*     */ 
/*     */     
/*     */     do {
/*  90 */       i = myNextToken(paramStreamTokenizer, str);
/*     */       
/*  92 */       if (i == -1) {
/*  93 */         return Boolean.FALSE;
/*     */       }
/*  95 */       if (i != 41)
/*  96 */         continue;  syntaxError(paramStreamTokenizer, str, "Premature closing parenthesis");
/*     */     }
/*  98 */     while (i != 40);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     for (b = 0;; b++) {
/* 104 */       i = myNextToken(paramStreamTokenizer, str);
/*     */       
/* 106 */       if (i == -1) {
/* 107 */         syntaxError(paramStreamTokenizer, str, "Missing closing parenthesis");
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 113 */       if (i == 40) {
/* 114 */         paramStreamTokenizer.pushBack();
/* 115 */         ConfigSexpression configSexpression = new ConfigSexpression();
/* 116 */         this.elements.add(configSexpression.parseAndEval(paramConfigContainer, paramStreamTokenizer, paramInt + 1));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 121 */         if (i == 41) {
/*     */           break;
/*     */         }
/*     */         
/* 125 */         if (b >= 20) {
/* 126 */           syntaxError(paramStreamTokenizer, str, "Too many arguments");
/*     */         }
/*     */         
/* 129 */         if (i == -2) {
/* 130 */           this.elements.add(new Double(paramStreamTokenizer.nval));
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 135 */         else if (i != -3 && i != 34 && i != 39) {
/* 136 */           String str1 = String.valueOf((char)i);
/* 137 */           this.elements.add(str1);
/* 138 */           syntaxError(paramStreamTokenizer, str, "Invalid token \"" + str1 + "\" must be enclosed in quotes");
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 144 */           String str1 = scanJavaProperties(paramStreamTokenizer, str, paramStreamTokenizer.sval);
/* 145 */           if (str1 != null)
/*     */           {
/* 147 */             if (str1.equalsIgnoreCase("true")) {
/*     */               
/* 149 */               this.elements.add(new Boolean(true));
/*     */             }
/* 151 */             else if (str1.equalsIgnoreCase("false")) {
/*     */               
/* 153 */               this.elements.add(new Boolean(false));
/*     */             }
/*     */             else {
/*     */               
/* 157 */               this.elements.add(str1);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 164 */     if (this.elements.size() == 0) {
/* 165 */       syntaxError(paramStreamTokenizer, str, "Null command");
/*     */     }
/*     */ 
/*     */     
/* 169 */     if (this.elements.get(0) instanceof String) {
/*     */       try {
/* 171 */         if (paramInt == 0) {
/* 172 */           paramConfigContainer.evaluateCommand(this.elements, paramStreamTokenizer.lineno());
/*     */ 
/*     */           
/* 175 */           return Boolean.TRUE;
/*     */         } 
/*     */ 
/*     */         
/* 179 */         return evaluateBuiltIn(paramConfigContainer, this.elements, paramStreamTokenizer.lineno());
/*     */ 
/*     */       
/*     */       }
/* 183 */       catch (IllegalArgumentException b) {
/* 184 */         IllegalArgumentException illegalArgumentException; syntaxError(paramStreamTokenizer, str, illegalArgumentException.getMessage());
/* 185 */         if (paramInt == 0)
/*     */         {
/* 187 */           return Boolean.TRUE;
/*     */         }
/*     */ 
/*     */         
/* 191 */         return this;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (paramInt == 0) {
/* 198 */       syntaxError(paramStreamTokenizer, str, "Malformed top-level command name");
/*     */     }
/*     */ 
/*     */     
/* 202 */     if (this.elements.get(0) instanceof Double) {
/* 203 */       if (this.elements.size() == 1) {
/* 204 */         syntaxError(paramStreamTokenizer, str, "Can't have single-element vector");
/*     */       }
/*     */       
/* 207 */       if (this.elements.size() == 2) {
/* 208 */         if (!(this.elements.get(1) instanceof Double)) {
/* 209 */           syntaxError(paramStreamTokenizer, str, "Both elements must be numbers");
/*     */         }
/* 211 */         return new Point2d(((Double)this.elements.get(0)).doubleValue(), ((Double)this.elements.get(1)).doubleValue());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 216 */       if (this.elements.size() == 3) {
/* 217 */         if (!(this.elements.get(1) instanceof Double) || !(this.elements.get(2) instanceof Double))
/*     */         {
/* 219 */           syntaxError(paramStreamTokenizer, str, "All elements must be numbers");
/*     */         }
/* 221 */         return new Point3d(((Double)this.elements.get(0)).doubleValue(), ((Double)this.elements.get(1)).doubleValue(), ((Double)this.elements.get(2)).doubleValue());
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       if (this.elements.size() == 4) {
/* 228 */         if (!(this.elements.get(1) instanceof Double) || !(this.elements.get(2) instanceof Double) || !(this.elements.get(3) instanceof Double))
/*     */         {
/*     */           
/* 231 */           syntaxError(paramStreamTokenizer, str, "All elements must be numbers");
/*     */         }
/* 233 */         return new Point4d(((Double)this.elements.get(0)).doubleValue(), ((Double)this.elements.get(1)).doubleValue(), ((Double)this.elements.get(2)).doubleValue(), ((Double)this.elements.get(3)).doubleValue());
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 240 */       syntaxError(paramStreamTokenizer, str, "Too many vector elements");
/*     */     } 
/*     */ 
/*     */     
/* 244 */     if (this.elements.get(0) instanceof Point3d) {
/* 245 */       if (this.elements.size() != 3) {
/* 246 */         syntaxError(paramStreamTokenizer, str, "Matrix must have three rows");
/*     */       }
/* 248 */       if (!(this.elements.get(1) instanceof Point3d) || !(this.elements.get(2) instanceof Point3d))
/*     */       {
/* 250 */         syntaxError(paramStreamTokenizer, str, "All rows must have three elements");
/*     */       }
/* 252 */       return new Matrix3d(((Point3d)this.elements.get(0)).x, ((Point3d)this.elements.get(0)).y, ((Point3d)this.elements.get(0)).z, ((Point3d)this.elements.get(1)).x, ((Point3d)this.elements.get(1)).y, ((Point3d)this.elements.get(1)).z, ((Point3d)this.elements.get(2)).x, ((Point3d)this.elements.get(2)).y, ((Point3d)this.elements.get(2)).z);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     if (this.elements.get(0) instanceof Point4d) {
/* 265 */       if (this.elements.size() == 3) {
/* 266 */         if (!(this.elements.get(1) instanceof Point4d) || !(this.elements.get(2) instanceof Point4d))
/*     */         {
/* 268 */           syntaxError(paramStreamTokenizer, str, "All rows must have four elements");
/*     */         }
/* 270 */         return new Matrix4d(((Point4d)this.elements.get(0)).x, ((Point4d)this.elements.get(0)).y, ((Point4d)this.elements.get(0)).z, ((Point4d)this.elements.get(0)).w, ((Point4d)this.elements.get(1)).x, ((Point4d)this.elements.get(1)).y, ((Point4d)this.elements.get(1)).z, ((Point4d)this.elements.get(1)).w, ((Point4d)this.elements.get(2)).x, ((Point4d)this.elements.get(2)).y, ((Point4d)this.elements.get(2)).z, ((Point4d)this.elements.get(2)).w, 0.0D, 0.0D, 0.0D, 1.0D);
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
/* 284 */       if (this.elements.size() != 4) {
/* 285 */         syntaxError(paramStreamTokenizer, str, "Matrix must have three or four rows");
/*     */       }
/* 287 */       if (!(this.elements.get(1) instanceof Point4d) || !(this.elements.get(2) instanceof Point4d) || !(this.elements.get(3) instanceof Point4d))
/*     */       {
/*     */         
/* 290 */         syntaxError(paramStreamTokenizer, str, "All rows must have four elements");
/*     */       }
/* 292 */       return new Matrix4d(((Point4d)this.elements.get(0)).x, ((Point4d)this.elements.get(0)).y, ((Point4d)this.elements.get(0)).z, ((Point4d)this.elements.get(0)).w, ((Point4d)this.elements.get(1)).x, ((Point4d)this.elements.get(1)).y, ((Point4d)this.elements.get(1)).z, ((Point4d)this.elements.get(1)).w, ((Point4d)this.elements.get(2)).x, ((Point4d)this.elements.get(2)).y, ((Point4d)this.elements.get(2)).z, ((Point4d)this.elements.get(2)).w, ((Point4d)this.elements.get(3)).x, ((Point4d)this.elements.get(3)).y, ((Point4d)this.elements.get(3)).z, ((Point4d)this.elements.get(3)).w);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     syntaxError(paramStreamTokenizer, str, "Syntax error");
/* 312 */     return null;
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
/*     */   private String scanJavaProperties(StreamTokenizer paramStreamTokenizer, String paramString1, String paramString2) {
/* 326 */     int i = paramString2.indexOf("${");
/* 327 */     if (i == -1) return paramString2;
/*     */     
/* 329 */     int j = 0;
/* 330 */     StringBuffer stringBuffer = new StringBuffer();
/* 331 */     while (i != -1) {
/* 332 */       stringBuffer.append(paramString2.substring(j, i));
/* 333 */       j = paramString2.indexOf('}', i);
/* 334 */       if (j == -1) {
/* 335 */         this.elements.add(paramString2);
/* 336 */         syntaxError(paramStreamTokenizer, paramString1, "Java property substitution syntax error");
/* 337 */         return null;
/*     */       } 
/*     */       
/* 340 */       String str1 = paramString2.substring(i + 2, j);
/* 341 */       String str2 = ConfigCommand.evaluateJavaProperty(str1);
/* 342 */       if (str2 == null) {
/* 343 */         this.elements.add(paramString2);
/* 344 */         syntaxError(paramStreamTokenizer, paramString1, "Java property \"" + str1 + "\" has a null value");
/*     */         
/* 346 */         return null;
/*     */       } 
/*     */       
/* 349 */       stringBuffer.append(str2);
/* 350 */       i = paramString2.indexOf("${", j);
/* 351 */       j++;
/*     */     } 
/*     */     
/* 354 */     stringBuffer.append(paramString2.substring(j));
/* 355 */     return stringBuffer.toString();
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
/*     */   private Object evaluateBuiltIn(ConfigContainer paramConfigContainer, ArrayList paramArrayList, int paramInt) {
/* 370 */     int i = paramArrayList.size();
/* 371 */     String str = (String)paramArrayList.get(0);
/*     */     
/* 373 */     if (str.equals("Rotate")) {
/* 374 */       return makeRotate(paramArrayList);
/*     */     }
/* 376 */     if (str.equals("Translate")) {
/* 377 */       return makeTranslate(paramArrayList);
/*     */     }
/* 379 */     if (str.equals("RotateTranslate") || str.equals("TranslateRotate") || str.equals("Concatenate"))
/*     */     {
/*     */ 
/*     */       
/* 383 */       return concatenate(paramArrayList);
/*     */     }
/* 385 */     if (str.equals("BoundingSphere")) {
/* 386 */       return makeBoundingSphere(paramArrayList);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 391 */     return new ConfigCommand(paramArrayList, paramConfigContainer.currentFileName, paramInt);
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
/*     */   private Matrix4d makeTranslate(ArrayList paramArrayList) {
/* 405 */     if (paramArrayList.size() != 4) {
/* 406 */       throw new IllegalArgumentException("Incorrect number of arguments to Translate");
/*     */     }
/*     */ 
/*     */     
/* 410 */     if (!(paramArrayList.get(1) instanceof Double) || !(paramArrayList.get(2) instanceof Double) || !(paramArrayList.get(3) instanceof Double))
/*     */     {
/*     */       
/* 413 */       throw new IllegalArgumentException("All arguments to Translate must be numbers");
/*     */     }
/*     */ 
/*     */     
/* 417 */     Matrix4d matrix4d = new Matrix4d();
/* 418 */     matrix4d.set(new Vector3d(((Double)paramArrayList.get(1)).doubleValue(), ((Double)paramArrayList.get(2)).doubleValue(), ((Double)paramArrayList.get(3)).doubleValue()));
/*     */ 
/*     */ 
/*     */     
/* 422 */     return matrix4d;
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
/*     */   private Matrix4d makeRotate(ArrayList paramArrayList) {
/* 437 */     if (paramArrayList.size() != 4) {
/* 438 */       throw new IllegalArgumentException("Incorrect number of arguments to Rotate");
/*     */     }
/*     */ 
/*     */     
/* 442 */     if (!(paramArrayList.get(1) instanceof Double) || !(paramArrayList.get(2) instanceof Double) || !(paramArrayList.get(3) instanceof Double))
/*     */     {
/*     */       
/* 445 */       throw new IllegalArgumentException("All arguments to Rotate must be numbers");
/*     */     }
/*     */ 
/*     */     
/* 449 */     double d1 = Math.toRadians(((Double)paramArrayList.get(1)).doubleValue());
/* 450 */     double d2 = Math.toRadians(((Double)paramArrayList.get(2)).doubleValue());
/* 451 */     double d3 = Math.toRadians(((Double)paramArrayList.get(3)).doubleValue());
/*     */     
/* 453 */     Transform3D transform3D = new Transform3D();
/* 454 */     transform3D.setEuler(new Vector3d(d1, d2, d3));
/*     */     
/* 456 */     Matrix4d matrix4d = new Matrix4d();
/* 457 */     transform3D.get(matrix4d);
/*     */     
/* 459 */     return matrix4d;
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
/*     */   private Matrix4d concatenate(ArrayList paramArrayList) {
/* 476 */     String str = (String)paramArrayList.get(0);
/*     */     
/* 478 */     if (paramArrayList.size() != 3) {
/* 479 */       throw new IllegalArgumentException("Incorrect number of arguments to " + str);
/*     */     }
/*     */ 
/*     */     
/* 483 */     if (!(paramArrayList.get(1) instanceof Matrix4d) || !(paramArrayList.get(2) instanceof Matrix4d))
/*     */     {
/* 485 */       throw new IllegalArgumentException("Both arguments to " + str + " must be Matrix4d");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 493 */     Matrix4d matrix4d = new Matrix4d((Matrix4d)paramArrayList.get(2));
/* 494 */     matrix4d.mul((Matrix4d)paramArrayList.get(1));
/*     */     
/* 496 */     return matrix4d;
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
/*     */   private BoundingSphere makeBoundingSphere(ArrayList paramArrayList) {
/*     */     double d;
/* 510 */     if (paramArrayList.size() != 3) {
/* 511 */       throw new IllegalArgumentException("Incorrect number of arguments to BoundingSphere");
/*     */     }
/*     */ 
/*     */     
/* 515 */     if (!(paramArrayList.get(1) instanceof Point3d) || (!(paramArrayList.get(2) instanceof Double) && !(paramArrayList.get(2) instanceof String)))
/*     */     {
/*     */       
/* 518 */       throw new IllegalArgumentException("BoundingSphere needs a Point3d center followed by a Double radius or the String \"infinite\"");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 523 */     if (paramArrayList.get(2) instanceof Double) {
/* 524 */       d = ((Double)paramArrayList.get(2)).doubleValue();
/*     */     } else {
/* 526 */       d = Double.POSITIVE_INFINITY;
/*     */     } 
/* 528 */     return new BoundingSphere((Point3d)paramArrayList.get(1), d);
/*     */   }
/*     */   
/*     */   void print() {
/* 532 */     System.out.print("(");
/* 533 */     int i = this.elements.size();
/* 534 */     for (byte b = 0; b < i; b++) {
/* 535 */       if (this.elements.get(b) instanceof Matrix3d) {
/* 536 */         String[] arrayOfString = ConfigCommand.formatMatrixRows((Matrix3d)this.elements.get(b));
/*     */         
/* 538 */         System.out.println("\n ((" + arrayOfString[0] + ")");
/* 539 */         System.out.println("  (" + arrayOfString[1] + ")");
/* 540 */         System.out.print("  (" + arrayOfString[2] + "))");
/* 541 */         if (b != i - 1) System.out.println();
/*     */       
/* 543 */       } else if (this.elements.get(b) instanceof Matrix4d) {
/* 544 */         String[] arrayOfString = ConfigCommand.formatMatrixRows((Matrix4d)this.elements.get(b));
/*     */         
/* 546 */         System.out.println("\n ((" + arrayOfString[0] + ")");
/* 547 */         System.out.println("  (" + arrayOfString[1] + ")");
/* 548 */         System.out.println("  (" + arrayOfString[2] + ")");
/* 549 */         System.out.print("  (" + arrayOfString[3] + "))");
/* 550 */         if (b != i - 1) System.out.println();
/*     */       
/* 552 */       } else if (this.elements.get(b) instanceof ConfigSexpression) {
/* 553 */         if (b > 0) System.out.print(" "); 
/* 554 */         ((ConfigSexpression)this.elements.get(b)).print();
/* 555 */         if (b != i - 1) System.out.println();
/*     */       
/* 557 */       } else if (this.elements.get(b) instanceof ConfigCommand) {
/* 558 */         if (b > 0) System.out.print(" "); 
/* 559 */         System.out.print(this.elements.get(b).toString());
/* 560 */         if (b != i - 1) System.out.println();
/*     */       
/*     */       } else {
/* 563 */         if (b > 0) System.out.print(" "); 
/* 564 */         System.out.print(this.elements.get(b).toString());
/*     */       } 
/*     */     } 
/* 567 */     System.out.print(")");
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\util\\universe\ConfigSexpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */