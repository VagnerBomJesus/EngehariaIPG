/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsObject
/*     */   extends TextfileParser
/*     */   implements LwsPrimitive
/*     */ {
/*     */   String fileName;
/*     */   String objName;
/*     */   LwsMotion motion;
/*     */   int parent;
/*     */   TransformGroup objectTransform;
/*     */   Vector objectBehavior;
/*     */   Vector shapeList;
/*     */   boolean hasPivot;
/*     */   TransformGroup pivotTransGroup;
/*     */   URL urlName;
/*     */   String protocol;
/*     */   int fileType;
/*     */   
/*     */   LwsObject(StreamTokenizer paramStreamTokenizer, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat, Lw3dLoader paramLw3dLoader, int paramInt3) throws FileNotFoundException, ParsingErrorException {
/*  84 */     this.shapeList = null;
/*  85 */     this.hasPivot = false;
/*  86 */     this.pivotTransGroup = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     this.debugPrinter.setValidOutput(paramInt3);
/* 110 */     this.parent = -1;
/*     */     
/* 112 */     this.fileType = paramLw3dLoader.getFileType();
/*     */     
/*     */     try {
/* 115 */       if (paramBoolean) {
/*     */         URL uRL;
/*     */         
/* 118 */         this.fileName = getString(paramStreamTokenizer);
/* 119 */         String str = null;
/* 120 */         switch (paramLw3dLoader.getFileType()) {
/*     */           
/*     */           case 2:
/* 123 */             str = paramLw3dLoader.getBasePath();
/* 124 */             if (str == null)
/* 125 */               str = paramLw3dLoader.getInternalBasePath(); 
/* 126 */             if (str != null)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 146 */               this.fileName = getQualifiedFilename(str, this.fileName);
/*     */             }
/*     */             break;
/*     */           case 1:
/* 150 */             str = "";
/* 151 */             uRL = paramLw3dLoader.getBaseUrl();
/* 152 */             if (uRL != null) {
/* 153 */               str = uRL.toString();
/*     */               
/* 155 */               this.protocol = uRL.getProtocol();
/*     */             } else {
/*     */               
/* 158 */               str = paramLw3dLoader.getInternalBaseUrl();
/*     */               
/* 160 */               this.protocol = (new URL(str)).getProtocol();
/*     */             } 
/*     */             
/* 163 */             this.urlName = getQualifiedURL(str, this.fileName);
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } else {
/* 170 */         this.objName = getString(paramStreamTokenizer);
/* 171 */       }  skip(paramStreamTokenizer, "ShowObject", 2);
/* 172 */       debugOutputLn(8, "skipped showobject, about to get objectmotion");
/*     */       
/* 174 */       getAndCheckString(paramStreamTokenizer, "ObjectMotion");
/* 175 */       debugOutputLn(8, "got string " + paramStreamTokenizer.sval);
/*     */       
/* 177 */       this.motion = new LwsMotion(paramStreamTokenizer, paramInt1, paramInt2, paramFloat, paramInt3);
/*     */       
/* 179 */       debugOutputLn(8, "got motion");
/* 180 */       boolean bool = false;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       while (!isCurrentToken(paramStreamTokenizer, "ShadowOptions")) {
/* 186 */         if (!bool && isCurrentToken(paramStreamTokenizer, "ParentObject")) {
/*     */           
/* 188 */           this.parent = (int)getNumber(paramStreamTokenizer);
/* 189 */           bool = true;
/*     */         }
/* 191 */         else if (isCurrentToken(paramStreamTokenizer, "PivotPoint")) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 196 */           this.hasPivot = true;
/* 197 */           float f1 = (float)getNumber(paramStreamTokenizer);
/* 198 */           float f2 = (float)getNumber(paramStreamTokenizer);
/* 199 */           float f3 = (float)getNumber(paramStreamTokenizer);
/* 200 */           Vector3f vector3f = new Vector3f(-f1, -f2, f3);
/* 201 */           Transform3D transform3D = new Transform3D();
/* 202 */           transform3D.set(vector3f);
/* 203 */           this.pivotTransGroup = new TransformGroup(transform3D);
/* 204 */           this.pivotTransGroup.setCapability(18);
/*     */         
/*     */         }
/* 207 */         else if (isCurrentToken(paramStreamTokenizer, "ObjDissolve")) {
/*     */           
/* 209 */           EnvelopeHandler envelopeHandler = new EnvelopeHandler(paramStreamTokenizer, paramInt2, paramFloat);
/*     */         } 
/*     */         
/* 212 */         paramStreamTokenizer.nextToken();
/*     */       } 
/* 214 */       getNumber(paramStreamTokenizer);
/* 215 */       debugOutputLn(8, "done with LwsObject constructor");
/*     */     }
/* 217 */     catch (MalformedURLException malformedURLException) {
/* 218 */       throw new FileNotFoundException(malformedURLException.getMessage());
/*     */     }
/* 220 */     catch (IOException iOException) {
/* 221 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     }
/* 223 */     catch (NumberFormatException numberFormatException) {
/* 224 */       throw new ParsingErrorException("Expected a number, got " + numberFormatException.getMessage());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getQualifiedFilename(String paramString1, String paramString2) throws FileNotFoundException {
/* 250 */     String str1 = "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     if (paramString2.indexOf(File.separator) == 0) {
/* 256 */       int j; if ((j = paramString2.lastIndexOf(File.separator)) != -1) {
/* 257 */         str1 = paramString2.substring(0, j + 1);
/* 258 */         paramString2 = paramString2.substring(j + 1);
/*     */       } else {
/*     */         
/* 261 */         return null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 270 */       if ((new File(str1 + paramString2)).exists()) {
/* 271 */         return str1 + paramString2;
/*     */       }
/*     */     }
/* 274 */     catch (NullPointerException nullPointerException) {
/* 275 */       nullPointerException.printStackTrace();
/*     */     } 
/*     */     
/* 278 */     if ((new File(str1 + paramString2.toLowerCase())).exists()) {
/* 279 */       return str1 + paramString2.toLowerCase();
/*     */     }
/*     */ 
/*     */     
/* 283 */     if ((new File(paramString1 + paramString2)).exists()) {
/* 284 */       return paramString1 + paramString2;
/*     */     }
/*     */     
/* 287 */     if ((new File(paramString1 + paramString2.toLowerCase())).exists()) {
/* 288 */       return paramString1 + paramString2.toLowerCase();
/*     */     }
/*     */ 
/*     */     
/* 292 */     if ((new File(paramString2)).exists()) {
/* 293 */       return paramString2;
/*     */     }
/*     */     
/* 296 */     if ((new File(paramString2.toLowerCase())).exists()) {
/* 297 */       return paramString2.toLowerCase();
/*     */     }
/*     */ 
/*     */     
/* 301 */     if (paramString1.equals(File.separator) || paramString1 == null || paramString1.equals(""))
/*     */     {
/*     */ 
/*     */       
/* 305 */       throw new FileNotFoundException(paramString2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 310 */     StringBuffer stringBuffer = new StringBuffer(128);
/* 311 */     StringTokenizer stringTokenizer = new StringTokenizer(paramString1, File.separator);
/* 312 */     int i = stringTokenizer.countTokens() - 1;
/* 313 */     if (paramString1.startsWith(File.separator))
/* 314 */       stringBuffer.append(File.separator); 
/* 315 */     for (byte b = 0; b < i; b++) {
/* 316 */       String str = stringTokenizer.nextToken();
/* 317 */       stringBuffer.append(str);
/* 318 */       stringBuffer.append(File.separator);
/*     */     } 
/*     */     
/* 321 */     String str2 = stringBuffer.toString();
/* 322 */     return getQualifiedFilename(str2, paramString2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   URL getQualifiedURL(String paramString1, String paramString2) throws MalformedURLException {
/* 328 */     URL uRL = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 333 */       uRL = new URL(paramString1 + paramString2);
/*     */       
/* 335 */       uRL.getContent();
/*     */       
/* 337 */       return uRL;
/*     */     }
/* 339 */     catch (IOException iOException) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 348 */         uRL = new URL(paramString2);
/* 349 */         uRL.getContent();
/*     */       }
/* 351 */       catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 357 */         uRL = new URL(this.protocol + ":" + paramString2);
/* 358 */         uRL.getContent();
/* 359 */         return uRL;
/*     */       }
/* 361 */       catch (IOException iOException) {
/*     */         
/* 363 */         throw new MalformedURLException(paramString1 + paramString2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   int getParent() { return this.parent; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addChild(LwsPrimitive paramLwsPrimitive) {
/* 378 */     debugOutputLn(1, "addChild()");
/* 379 */     if (this.objectTransform != null) {
/* 380 */       debugOutputLn(8, "objectTransform = " + this.objectTransform);
/* 381 */       if (paramLwsPrimitive.getObjectNode() != null) {
/* 382 */         debugOutputLn(8, "child has object node");
/* 383 */         if (this.hasPivot) {
/* 384 */           this.pivotTransGroup.addChild(paramLwsPrimitive.getObjectNode());
/*     */         } else {
/* 386 */           this.objectTransform.addChild(paramLwsPrimitive.getObjectNode());
/*     */         } 
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createJava3dObject(LwsObject paramLwsObject, int paramInt) throws IncorrectFormatException, ParsingErrorException, FileNotFoundException {
/* 412 */     String str = new String("_sequence_");
/* 413 */     Matrix4d matrix4d = new Matrix4d();
/* 414 */     matrix4d.setIdentity();
/*     */ 
/*     */     
/* 417 */     LwsFrame lwsFrame = this.motion.getFirstFrame();
/* 418 */     lwsFrame.setMatrix(matrix4d);
/* 419 */     Transform3D transform3D = new Transform3D();
/* 420 */     transform3D.set(matrix4d);
/* 421 */     this.objectTransform = new TransformGroup(transform3D);
/* 422 */     this.objectTransform.setCapability(18);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 441 */     if (this.fileName != null && this.fileName.indexOf(str) != -1) {
/*     */       
/* 443 */       int i = this.fileName.indexOf(str);
/* 444 */       i += str.length();
/* 445 */       String str1 = this.fileName.substring(i);
/* 446 */       int j = str1.indexOf(".lwo");
/* 447 */       if (j != -1)
/* 448 */         str1 = str1.substring(0, j); 
/* 449 */       if ((new File(str1)).exists()) {
/* 450 */         SequenceReader sequenceReader = new SequenceReader(str1, this.motion.totalTime, this.motion.totalFrames);
/*     */ 
/*     */ 
/*     */         
/* 454 */         sequenceReader.printLines();
/* 455 */         sequenceReader.createJava3dObjects(this.debugPrinter.getValidOutput(), paramInt);
/*     */         
/* 457 */         TransformGroup transformGroup = sequenceReader.getObjectNode();
/* 458 */         if (transformGroup != null) {
/* 459 */           this.objectTransform.addChild(transformGroup);
/*     */         }
/*     */         
/* 462 */         this.objectBehavior = sequenceReader.getObjectBehaviors();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 470 */     if (this.fileName != null || this.urlName != null)
/*     */     {
/*     */       
/* 473 */       if (paramLwsObject == null) {
/* 474 */         debugOutputLn(2, "About to load binary file for " + this.fileName);
/*     */ 
/*     */ 
/*     */         
/* 478 */         J3dLwoParser j3dLwoParser = null;
/* 479 */         switch (this.fileType) {
/*     */           case 2:
/* 481 */             j3dLwoParser = new J3dLwoParser(this.fileName, this.debugPrinter.getValidOutput());
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/* 486 */             j3dLwoParser = new J3dLwoParser(this.urlName, this.debugPrinter.getValidOutput());
/*     */             break;
/*     */         } 
/*     */         
/* 490 */         j3dLwoParser.createJava3dGeometry();
/*     */         
/* 492 */         if (this.hasPivot) {
/* 493 */           this.objectTransform.addChild(this.pivotTransGroup);
/*     */         }
/* 495 */         if (j3dLwoParser.getJava3dShapeList() != null) {
/* 496 */           this.shapeList = j3dLwoParser.getJava3dShapeList();
/* 497 */           Enumeration enumeration = this.shapeList.elements();
/* 498 */           while (enumeration.hasMoreElements()) {
/* 499 */             if (!this.hasPivot || this.pivotTransGroup == null) {
/* 500 */               this.objectTransform.addChild((Shape3D)enumeration.nextElement()); continue;
/*     */             } 
/* 502 */             this.pivotTransGroup.addChild((Shape3D)enumeration.nextElement());
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 508 */         debugOutputLn(8, "Cloning shapes");
/* 509 */         Vector vector = paramLwsObject.getShapeList();
/* 510 */         Enumeration enumeration = vector.elements();
/* 511 */         while (enumeration.hasMoreElements()) {
/* 512 */           debugOutputLn(8, "   shape clone");
/* 513 */           Shape3D shape3D1 = (Shape3D)enumeration.nextElement();
/* 514 */           Shape3D shape3D2 = (Shape3D)shape3D1.cloneTree();
/* 515 */           this.objectTransform.addChild(shape3D2);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 521 */     this.objectBehavior = new Vector();
/* 522 */     if (paramInt != 0) {
/* 523 */       this.motion.createJava3dBehaviors(this.objectTransform);
/* 524 */       Behavior behavior = this.motion.getBehaviors();
/* 525 */       if (behavior != null) {
/* 526 */         this.objectBehavior.addElement(behavior);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 537 */   Vector getShapeList() { return this.shapeList; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 544 */   public TransformGroup getObjectNode() { return this.objectTransform; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getObjectBehaviors() {
/* 554 */     debugOutputLn(1, "getObjectBehaviors()");
/* 555 */     return this.objectBehavior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void printVals() {
/* 565 */     debugOutputLn(2, "  OBJECT vals: ");
/* 566 */     debugOutputLn(2, "   fileName = " + this.fileName);
/* 567 */     debugOutputLn(2, "   objName = " + this.objName);
/* 568 */     this.motion.printVals();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\lw3d\LwsObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */