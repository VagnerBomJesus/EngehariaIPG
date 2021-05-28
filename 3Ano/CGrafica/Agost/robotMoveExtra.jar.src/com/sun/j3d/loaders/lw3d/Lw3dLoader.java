/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.Loader;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import com.sun.j3d.loaders.Scene;
/*     */ import com.sun.j3d.loaders.SceneBase;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.AmbientLight;
/*     */ import javax.media.j3d.Background;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Fog;
/*     */ import javax.media.j3d.TransformGroup;
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
/*     */ public class Lw3dLoader
/*     */   extends TextfileParser
/*     */   implements Loader
/*     */ {
/*     */   Vector objectList;
/*     */   Vector lightList;
/*     */   BranchGroup sceneGroupNode;
/*     */   Color3f ambientColor;
/*     */   LwsCamera camera;
/*     */   LwsFog fog;
/*     */   LwsBackground background;
/*     */   int loadFlags;
/*     */   int loadBehaviors;
/*     */   Vector sceneBehaviors;
/*     */   SceneBase scene;
/*     */   String basePath;
/*     */   String internalBasePath;
/*     */   URL baseUrl;
/*     */   String internalBaseUrl;
/*     */   static final int FILE_TYPE_NONE = 0;
/*     */   static final int FILE_TYPE_URL = 1;
/*     */   static final int FILE_TYPE_FILENAME = 2;
/*     */   static final int FILE_TYPE_READER = 4;
/*     */   int fileType;
/*     */   
/*     */   public Lw3dLoader() {
/*  76 */     this.camera = null;
/*  77 */     this.fog = null;
/*  78 */     this.background = null;
/*  79 */     this.loadFlags = 0;
/*  80 */     this.loadBehaviors = 0;
/*     */     
/*  82 */     this.scene = null;
/*  83 */     this.basePath = null;
/*  84 */     this.internalBasePath = null;
/*  85 */     this.baseUrl = null;
/*  86 */     this.internalBaseUrl = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     this.fileType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.ambientColor = new Color3f(0.0F, 0.0F, 0.0F);
/*  99 */     this.objectList = new Vector();
/* 100 */     this.lightList = new Vector();
/* 101 */     this.debugPrinter.setValidOutput(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Lw3dLoader(int paramInt) {
/* 112 */     this();
/* 113 */     this.loadFlags = paramInt;
/* 114 */     this.loadBehaviors = this.loadFlags & 0x8;
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
/*     */   public Scene load(URL paramURL) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
/*     */     InputStreamReader inputStreamReader;
/* 128 */     this.fileType = 1;
/* 129 */     setInternalBaseUrl(paramURL);
/*     */     
/*     */     try {
/* 132 */       inputStreamReader = new InputStreamReader(new BufferedInputStream(paramURL.openStream()));
/*     */     
/*     */     }
/* 135 */     catch (IOException iOException) {
/* 136 */       throw new FileNotFoundException(iOException.getMessage());
/*     */     } 
/* 138 */     Scene scene1 = load(inputStreamReader);
/* 139 */     this.fileType = 0;
/* 140 */     return scene1;
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
/*     */   public Scene load(String paramString) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
/* 153 */     this.fileType = 2;
/* 154 */     setInternalBasePath(paramString);
/* 155 */     BufferedReader bufferedReader = new BufferedReader(new FileReader(paramString));
/* 156 */     Scene scene1 = load(bufferedReader);
/* 157 */     this.fileType = 0;
/* 158 */     return scene1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Scene load(Reader paramReader) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
/* 169 */     if (this.fileType == 0)
/* 170 */       this.fileType = 4; 
/* 171 */     StreamTokenizer streamTokenizer = new StreamTokenizer(paramReader);
/* 172 */     setupTokenizer(streamTokenizer);
/*     */     
/* 174 */     getAndCheckString(streamTokenizer, "LWSC");
/* 175 */     getNumber(streamTokenizer);
/* 176 */     getAndCheckString(streamTokenizer, "FirstFrame");
/* 177 */     int i = (int)getNumber(streamTokenizer);
/* 178 */     getAndCheckString(streamTokenizer, "LastFrame");
/* 179 */     int j = (int)getNumber(streamTokenizer);
/* 180 */     skipUntilString(streamTokenizer, "FramesPerSecond");
/* 181 */     double d = getNumber(streamTokenizer);
/* 182 */     float f = (j - i) / (float)d;
/* 183 */     boolean bool = false;
/* 184 */     while (!bool) {
/*     */       int k;
/*     */       try {
/* 187 */         k = streamTokenizer.nextToken();
/*     */       }
/* 189 */       catch (IOException iOException) {
/* 190 */         throw new ParsingErrorException(iOException.getMessage());
/*     */       } 
/* 192 */       switch (streamTokenizer.ttype) {
/*     */         case -1:
/* 194 */           bool = true;
/*     */           continue;
/*     */         case -3:
/* 197 */           debugOutputLn(2, "  String = " + streamTokenizer.sval);
/* 198 */           if (streamTokenizer.sval.equals("AddNullObject")) {
/* 199 */             LwsObject lwsObject = new LwsObject(streamTokenizer, false, i, j, f, this, this.debugPrinter.getValidOutput());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 205 */             lwsObject.createJava3dObject(null, this.loadBehaviors);
/* 206 */             this.objectList.addElement(lwsObject); continue;
/*     */           } 
/* 208 */           if (streamTokenizer.sval.equals("LoadObject")) {
/* 209 */             String str = getString(streamTokenizer);
/* 210 */             streamTokenizer.pushBack();
/* 211 */             debugOutputLn(32, "loading " + str + " at " + System.currentTimeMillis());
/*     */             
/* 213 */             LwsObject lwsObject1 = new LwsObject(streamTokenizer, true, i, j, f, this, this.debugPrinter.getValidOutput());
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 218 */             debugOutputLn(32, "done loading at " + System.currentTimeMillis());
/*     */             
/* 220 */             LwsObject lwsObject2 = null;
/* 221 */             Enumeration enumeration = this.objectList.elements();
/* 222 */             while (enumeration.hasMoreElements()) {
/* 223 */               LwsObject lwsObject = (LwsObject)enumeration.nextElement();
/* 224 */               if (lwsObject.fileName != null && lwsObject.fileName.equals(str)) {
/*     */                 
/* 226 */                 lwsObject2 = lwsObject;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 230 */             lwsObject1.createJava3dObject(lwsObject2, this.loadBehaviors);
/* 231 */             this.objectList.addElement(lwsObject1); continue;
/*     */           } 
/* 233 */           if (streamTokenizer.sval.equals("AmbientColor")) {
/* 234 */             this.ambientColor.x = (float)getNumber(streamTokenizer) / 255.0F;
/* 235 */             this.ambientColor.y = (float)getNumber(streamTokenizer) / 255.0F;
/* 236 */             this.ambientColor.z = (float)getNumber(streamTokenizer) / 255.0F; continue;
/*     */           } 
/* 238 */           if (streamTokenizer.sval.equals("AmbIntensity")) {
/*     */             
/* 240 */             float f1 = (float)getNumber(streamTokenizer);
/* 241 */             this.ambientColor.x *= f1;
/* 242 */             this.ambientColor.y *= f1;
/* 243 */             this.ambientColor.z *= f1; continue;
/*     */           } 
/* 245 */           if (streamTokenizer.sval.equals("AddLight")) {
/* 246 */             LwsLight lwsLight = new LwsLight(streamTokenizer, j, f, this.debugPrinter.getValidOutput());
/*     */ 
/*     */ 
/*     */             
/* 250 */             lwsLight.createJava3dObject(this.loadBehaviors);
/* 251 */             this.lightList.addElement(lwsLight); continue;
/*     */           } 
/* 253 */           if (streamTokenizer.sval.equals("ShowCamera")) {
/* 254 */             this.camera = new LwsCamera(streamTokenizer, i, j, f, this.debugPrinter.getValidOutput());
/*     */ 
/*     */             
/* 257 */             this.camera.createJava3dObject(this.loadBehaviors); continue;
/*     */           } 
/* 259 */           if (streamTokenizer.sval.equals("FogType")) {
/* 260 */             int m = (int)getNumber(streamTokenizer);
/* 261 */             if (m != 0) {
/* 262 */               this.fog = new LwsFog(streamTokenizer, this.debugPrinter.getValidOutput());
/*     */               
/* 264 */               this.fog.createJava3dObject();
/*     */             }  continue;
/*     */           } 
/* 267 */           if (streamTokenizer.sval.equals("SolidBackdrop")) {
/* 268 */             this.background = new LwsBackground(streamTokenizer, this.debugPrinter.getValidOutput());
/*     */ 
/*     */             
/* 271 */             this.background.createJava3dObject();
/*     */           } 
/*     */           continue;
/*     */       } 
/* 275 */       debugOutputLn(2, "  Unknown ttype, token = " + streamTokenizer.ttype + ", " + k);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     this.sceneGroupNode = new BranchGroup();
/* 283 */     this.sceneBehaviors = new Vector();
/* 284 */     parentObjects();
/* 285 */     constructScene();
/*     */     
/* 287 */     return this.scene;
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
/*     */   void constructScene() {
/* 301 */     this.scene = new SceneBase();
/*     */     
/* 303 */     if ((this.loadFlags & true) != 0) {
/* 304 */       addLights();
/* 305 */       addAmbient();
/*     */     } 
/*     */     
/* 308 */     if ((this.loadFlags & 0x2) != 0) {
/* 309 */       addFog();
/*     */     }
/* 311 */     if ((this.loadFlags & 0x4) != 0) {
/* 312 */       addBackground();
/*     */     }
/* 314 */     if ((this.loadFlags & 0x10) != 0) {
/* 315 */       addCamera();
/*     */     }
/* 317 */     if (this.loadBehaviors != 0) {
/* 318 */       addBehaviors();
/*     */     }
/* 320 */     this.scene.setSceneGroup(this.sceneGroupNode);
/*     */ 
/*     */     
/* 323 */     for (Enumeration enumeration = this.objectList.elements(); enumeration.hasMoreElements(); ) {
/*     */       
/* 325 */       LwsObject lwsObject = (LwsObject)enumeration.nextElement();
/* 326 */       if (lwsObject.fileName != null) {
/* 327 */         this.scene.addNamedObject(lwsObject.fileName, lwsObject.getObjectNode()); continue;
/* 328 */       }  if (lwsObject.objName != null) {
/* 329 */         this.scene.addNamedObject(lwsObject.objName, lwsObject.getObjectNode());
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
/*     */   void setInternalBaseUrl(URL paramURL) {
/* 341 */     StringTokenizer stringTokenizer = new StringTokenizer(paramURL.toString(), "\\/");
/*     */ 
/*     */ 
/*     */     
/* 345 */     int i = stringTokenizer.countTokens() - 1;
/* 346 */     StringBuffer stringBuffer = new StringBuffer(80);
/* 347 */     for (byte b = 0; b < i; b++) {
/* 348 */       String str = stringTokenizer.nextToken();
/* 349 */       if (!b && !str.regionMatches(true, 0, "file:", 0, 5)) {
/*     */         
/* 351 */         stringBuffer.append(str);
/*     */ 
/*     */ 
/*     */         
/* 355 */         stringBuffer.append('/');
/* 356 */         stringBuffer.append('/');
/*     */       } else {
/* 358 */         stringBuffer.append(str);
/*     */ 
/*     */         
/* 361 */         stringBuffer.append('/');
/*     */       } 
/*     */     } 
/* 364 */     this.internalBaseUrl = stringBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInternalBasePath(String paramString) {
/* 372 */     StringTokenizer stringTokenizer = new StringTokenizer(paramString, File.separator);
/*     */ 
/*     */     
/* 375 */     int i = stringTokenizer.countTokens() - 1;
/* 376 */     StringBuffer stringBuffer = new StringBuffer(80);
/* 377 */     if (paramString != null && paramString.startsWith(File.separator))
/*     */     {
/* 379 */       stringBuffer.append(File.separator); } 
/* 380 */     for (byte b = 0; b < i; b++) {
/* 381 */       String str = stringTokenizer.nextToken();
/* 382 */       stringBuffer.append(str);
/* 383 */       stringBuffer.append(File.separator);
/*     */     } 
/* 385 */     this.internalBasePath = stringBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/* 389 */   String getInternalBasePath() { return this.internalBasePath; }
/*     */ 
/*     */ 
/*     */   
/* 393 */   String getInternalBaseUrl() { return this.internalBaseUrl; }
/*     */ 
/*     */ 
/*     */   
/* 397 */   int getFileType() { return this.fileType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void parentObjects() {
/* 407 */     debugOutputLn(1, "parentObjects()");
/* 408 */     for (Enumeration enumeration = this.objectList.elements(); enumeration.hasMoreElements(); ) {
/*     */       
/* 410 */       LwsObject lwsObject = (LwsObject)enumeration.nextElement();
/* 411 */       if (lwsObject.getParent() != -1) {
/*     */         
/* 413 */         LwsObject lwsObject1 = (LwsObject)this.objectList.elementAt(lwsObject.getParent() - 1);
/*     */         
/* 415 */         lwsObject1.addChild(lwsObject);
/* 416 */         debugOutputLn(2, "added child successfully");
/*     */ 
/*     */       
/*     */       }
/* 420 */       else if (lwsObject.getObjectNode() != null) {
/* 421 */         this.sceneGroupNode.addChild(lwsObject.getObjectNode());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 426 */       if (this.loadBehaviors != 0 && 
/* 427 */         !lwsObject.getObjectBehaviors().isEmpty()) {
/* 428 */         this.sceneBehaviors.addAll(lwsObject.getObjectBehaviors());
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 434 */     debugOutputLn(8, "Done with parentObjects()");
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
/* 448 */   public void setBaseUrl(URL paramURL) { this.baseUrl = paramURL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBasePath(String paramString) {
/* 461 */     this.basePath = paramString;
/* 462 */     if (this.basePath == null || this.basePath == "")
/* 463 */       this.basePath = "." + File.separator; 
/* 464 */     this.basePath = this.basePath.replace('/', File.separatorChar);
/* 465 */     this.basePath = this.basePath.replace('\\', File.separatorChar);
/* 466 */     if (!this.basePath.endsWith(File.separator)) {
/* 467 */       this.basePath += File.separator;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 474 */   public URL getBaseUrl() { return this.baseUrl; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   public String getBasePath() { return this.basePath; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 489 */   public void setFlags(int paramInt) { this.loadFlags = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public int getFlags() { return this.loadFlags; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformGroup getObject(String paramString) {
/* 514 */     debugOutputLn(1, "getObject()");
/* 515 */     int i = -1;
/* 516 */     byte b = 0;
/* 517 */     String str = paramString;
/* 518 */     if (paramString.indexOf("[") != -1) {
/*     */       
/* 520 */       int j = paramString.indexOf("[");
/* 521 */       str = paramString.substring(0, j);
/* 522 */       String str1 = paramString.substring(j);
/* 523 */       int k = str1.indexOf("]");
/* 524 */       String str2 = str1.substring(1, k);
/* 525 */       i = (new Integer(str2)).intValue();
/*     */     } 
/* 527 */     Enumeration enumeration = this.objectList.elements();
/* 528 */     while (enumeration.hasMoreElements()) {
/* 529 */       LwsObject lwsObject = (LwsObject)enumeration.nextElement();
/* 530 */       debugOutputLn(2, "tempObj, file, objname = " + lwsObject + lwsObject.fileName + lwsObject.objName);
/*     */ 
/*     */       
/* 533 */       if ((lwsObject.fileName != null && lwsObject.fileName.indexOf(str) != -1) || (lwsObject.objName != null && lwsObject.objName.indexOf(str) != -1)) {
/*     */ 
/*     */ 
/*     */         
/* 537 */         if (i < 0 || i == b)
/*     */         {
/* 539 */           return lwsObject.getObjectNode();
/*     */         }
/* 541 */         b++;
/*     */       } 
/*     */     } 
/* 544 */     debugOutputLn(2, " no luck - wanted " + paramString + " returning null");
/*     */     
/* 546 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setupTokenizer(StreamTokenizer paramStreamTokenizer) {
/* 556 */     paramStreamTokenizer.resetSyntax();
/* 557 */     paramStreamTokenizer.wordChars(97, 122);
/* 558 */     paramStreamTokenizer.wordChars(65, 90);
/* 559 */     paramStreamTokenizer.wordChars(160, 255);
/* 560 */     paramStreamTokenizer.whitespaceChars(0, 32);
/* 561 */     paramStreamTokenizer.commentChar(47);
/* 562 */     paramStreamTokenizer.quoteChar(34);
/* 563 */     paramStreamTokenizer.quoteChar(39);
/* 564 */     paramStreamTokenizer.wordChars(48, 57);
/* 565 */     paramStreamTokenizer.wordChars(46, 46);
/* 566 */     paramStreamTokenizer.wordChars(45, 45);
/* 567 */     paramStreamTokenizer.wordChars(47, 47);
/* 568 */     paramStreamTokenizer.wordChars(92, 92);
/* 569 */     paramStreamTokenizer.wordChars(95, 95);
/* 570 */     paramStreamTokenizer.wordChars(38, 38);
/* 571 */     paramStreamTokenizer.ordinaryChar(40);
/* 572 */     paramStreamTokenizer.ordinaryChar(41);
/* 573 */     paramStreamTokenizer.whitespaceChars(13, 13);
/*     */ 
/*     */     
/* 576 */     paramStreamTokenizer.wordChars(58, 58);
/*     */     
/* 578 */     paramStreamTokenizer.wordChars(126, 126);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addAmbient() {
/* 585 */     AmbientLight ambientLight = new AmbientLight(this.ambientColor);
/* 586 */     BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 100000.0D);
/*     */     
/* 588 */     ambientLight.setInfluencingBounds(boundingSphere);
/* 589 */     this.sceneGroupNode.addChild(ambientLight);
/*     */     
/* 591 */     ambientLight.addScope(this.sceneGroupNode);
/* 592 */     this.scene.addLightNode(ambientLight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addLights() {
/* 600 */     for (Enumeration enumeration = this.lightList.elements(); enumeration.hasMoreElements(); ) {
/*     */       
/* 602 */       debugOutputLn(8, "adding light to scene group");
/* 603 */       LwsLight lwsLight = (LwsLight)enumeration.nextElement();
/*     */       
/* 605 */       if (lwsLight.getObjectNode() != null) {
/*     */         
/* 607 */         lwsLight.getLight().addScope(this.sceneGroupNode);
/*     */         
/* 609 */         if (lwsLight.getParent() != -1) {
/* 610 */           LwsObject lwsObject = (LwsObject)this.objectList.elementAt(lwsLight.getParent() - 1);
/*     */           
/* 612 */           lwsObject.addChild(lwsLight);
/*     */         } else {
/*     */           
/* 615 */           this.sceneGroupNode.addChild(lwsLight.getObjectNode());
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 620 */         if (this.loadBehaviors != 0 && 
/* 621 */           !lwsLight.getObjectBehaviors().isEmpty()) {
/* 622 */           this.sceneBehaviors.addAll(lwsLight.getObjectBehaviors());
/*     */         }
/*     */ 
/*     */         
/* 626 */         this.scene.addLightNode(lwsLight.getLight());
/*     */         continue;
/*     */       } 
/* 629 */       debugOutputLn(8, "light object null?");
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
/*     */   void addCamera() {
/* 641 */     if (this.camera != null) {
/* 642 */       if (this.camera.getParent() != -1) {
/* 643 */         debugOutputLn(2, "camera parent = " + this.camera.getParent());
/*     */         
/* 645 */         LwsObject lwsObject = (LwsObject)this.objectList.elementAt(this.camera.getParent() - 1);
/*     */         
/* 647 */         lwsObject.addChild(this.camera);
/* 648 */         debugOutputLn(2, "added child successfully");
/*     */       } else {
/*     */         
/* 651 */         this.sceneGroupNode.addChild(this.camera.getObjectNode());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 656 */       if (this.loadBehaviors != 0 && 
/* 657 */         !this.camera.getObjectBehaviors().isEmpty()) {
/* 658 */         this.sceneBehaviors.addAll(this.camera.getObjectBehaviors());
/*     */       }
/*     */       
/* 661 */       this.scene.addViewGroup(this.camera.getObjectNode());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addFog() {
/* 669 */     if (this.fog != null) {
/* 670 */       Fog fog1 = this.fog.getObjectNode();
/* 671 */       if (fog1 != null) {
/* 672 */         this.sceneGroupNode.addChild(fog1);
/* 673 */         this.scene.addFogNode(fog1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviors() {
/* 682 */     if (!this.sceneBehaviors.isEmpty()) {
/* 683 */       Enumeration enumeration = this.sceneBehaviors.elements();
/* 684 */       while (enumeration.hasMoreElements()) {
/* 685 */         this.scene.addBehaviorNode((Behavior)enumeration.nextElement());
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
/*     */   void addBackground() {
/* 697 */     if (this.background != null) {
/* 698 */       Background background1 = this.background.getObjectNode();
/* 699 */       if (background1 != null) {
/* 700 */         this.sceneGroupNode.addChild(background1);
/* 701 */         this.scene.addBackgroundNode(background1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\Lw3dLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */