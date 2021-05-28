/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.media.j3d.PhysicalBody;
/*     */ import javax.media.j3d.PhysicalEnvironment;
/*     */ import javax.media.j3d.View;
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
/*     */ class ConfigView
/*     */   extends ConfigObject
/*     */ {
/*  56 */   View j3dView = null;
/*  57 */   Viewer j3dViewer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   Set screens = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean stereoEnable = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean antialiasingEnable = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   PhysicalBody physicalBody = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   PhysicalEnvironment physicalEnvironment = null;
/*     */ 
/*     */   
/*  91 */   private double fieldOfView = 0.7853981633974483D;
/*  92 */   private int backClipPolicy = 3;
/*  93 */   private int frontClipPolicy = 3;
/*  94 */   private double backClipDistance = 10.0D;
/*  95 */   private double frontClipDistance = 0.1D;
/*  96 */   private int screenScalePolicy = 0;
/*  97 */   private double screenScale = 1.0D;
/*     */   private boolean trackingEnable = false;
/*  99 */   private int viewPolicy = 0;
/* 100 */   private int windowEyepointPolicy = -1;
/* 101 */   private int windowMovementPolicy = -1;
/* 102 */   private int windowResizePolicy = -1;
/*     */   private boolean coeCenteringEnableSet = false;
/*     */   private boolean coeCenteringEnable = false;
/* 105 */   private Point3d centerEyeInCoexistence = null;
/*     */   
/* 107 */   private ConfigPhysicalBody configBody = null;
/* 108 */   private ConfigPhysicalEnvironment configEnv = null;
/* 109 */   private ConfigViewPlatform configViewPlatform = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialize(ConfigCommand paramConfigCommand) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/* 125 */     int i = paramConfigCommand.argc;
/* 126 */     Object[] arrayOfObject = paramConfigCommand.argv;
/* 127 */     String str1 = null;
/* 128 */     Object object = null;
/* 129 */     String str2 = null;
/* 130 */     ConfigScreen configScreen = null;
/*     */ 
/*     */     
/* 133 */     if (i != 4) {
/* 134 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/* 138 */     if (!isName(arrayOfObject[1])) {
/* 139 */       syntaxError("The first argument to " + paramConfigCommand.commandName + " must be the instance name");
/*     */     }
/*     */ 
/*     */     
/* 143 */     if (!isName(arrayOfObject[2])) {
/* 144 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be a property name");
/*     */     }
/*     */ 
/*     */     
/* 148 */     str1 = (String)arrayOfObject[2];
/* 149 */     object = arrayOfObject[3];
/*     */     
/* 151 */     if (str1.equals("Screen") || str1.equals("Window")) {
/* 152 */       if (!(object instanceof String)) {
/* 153 */         syntaxError("Value for " + str1 + " must be a name");
/*     */       }
/* 155 */       configScreen = (ConfigScreen)this.configContainer.findConfigObject("Screen", (String)object);
/*     */ 
/*     */       
/* 158 */       if (!this.screens.add(configScreen)) {
/* 159 */         syntaxError(str1 + " \"" + (String)object + "\" has already been added to " + this.instanceName);
/*     */       
/*     */       }
/*     */     }
/* 163 */     else if (str1.equals("ViewPlatform")) {
/* 164 */       if (!(object instanceof String)) {
/* 165 */         syntaxError("value for ViewPlatform  must be an instance name");
/*     */       }
/*     */       
/* 168 */       this.configViewPlatform = (ConfigViewPlatform)this.configContainer.findConfigObject("ViewPlatform", (String)object);
/*     */ 
/*     */ 
/*     */       
/* 172 */       this.configViewPlatform.addConfigView(this);
/*     */     }
/* 174 */     else if (str1.equals("PhysicalEnvironment")) {
/* 175 */       if (!(object instanceof String)) {
/* 176 */         syntaxError("value for PhysicalEnvironment must be an instance name");
/*     */       }
/*     */       
/* 179 */       this.configEnv = (ConfigPhysicalEnvironment)this.configContainer.findConfigObject("PhysicalEnvironment", (String)object);
/*     */ 
/*     */     
/*     */     }
/* 183 */     else if (str1.equals("PhysicalBody")) {
/* 184 */       if (!(object instanceof String)) {
/* 185 */         syntaxError("value for PhysicalBody must be an instance name");
/*     */       }
/*     */       
/* 188 */       this.configBody = (ConfigPhysicalBody)this.configContainer.findConfigObject("PhysicalBody", (String)object);
/*     */     
/*     */     }
/* 191 */     else if (str1.equals("BackClipPolicy")) {
/* 192 */       if (!(object instanceof String)) {
/* 193 */         syntaxError("value for BackClipPolicy must be a string");
/*     */       }
/* 195 */       str2 = (String)object;
/* 196 */       if (str2.equals("PHYSICAL_EYE")) {
/* 197 */         this.backClipPolicy = 3;
/* 198 */       } else if (str2.equals("PHYSICAL_SCREEN")) {
/* 199 */         this.backClipPolicy = 1;
/* 200 */       } else if (str2.equals("VIRTUAL_EYE")) {
/* 201 */         this.backClipPolicy = 2;
/* 202 */       } else if (str2.equals("VIRTUAL_SCREEN")) {
/* 203 */         this.backClipPolicy = 0;
/*     */       } else {
/* 205 */         syntaxError("Invalid value for BackClipPolicy " + str2);
/*     */       } 
/* 207 */     } else if (str1.equals("FrontClipPolicy")) {
/* 208 */       if (!(object instanceof String)) {
/* 209 */         syntaxError("value for FrontClipPolicy must be a string");
/*     */       }
/* 211 */       str2 = (String)object;
/* 212 */       if (str2.equals("PHYSICAL_EYE")) {
/* 213 */         this.frontClipPolicy = 3;
/* 214 */       } else if (str2.equals("PHYSICAL_SCREEN")) {
/* 215 */         this.frontClipPolicy = 1;
/* 216 */       } else if (str2.equals("VIRTUAL_EYE")) {
/* 217 */         this.frontClipPolicy = 2;
/* 218 */       } else if (str2.equals("VIRTUAL_SCREEN")) {
/* 219 */         this.frontClipPolicy = 0;
/*     */       } else {
/* 221 */         syntaxError("Invalid value for FrontClipPolicy " + str2);
/*     */       } 
/* 223 */     } else if (str1.equals("ScreenScalePolicy")) {
/* 224 */       if (!(object instanceof String)) {
/* 225 */         syntaxError("value for ScreenScalePolicy must be a string");
/*     */       }
/* 227 */       str2 = (String)object;
/* 228 */       if (str2.equals("SCALE_SCREEN_SIZE")) {
/* 229 */         this.screenScalePolicy = 0;
/* 230 */       } else if (str2.equals("SCALE_EXPLICIT")) {
/* 231 */         this.screenScalePolicy = 1;
/*     */       } else {
/* 233 */         syntaxError("Invalid value for ScreenScalePolicy " + str2);
/*     */       } 
/* 235 */     } else if (str1.equals("FieldOfView")) {
/* 236 */       if (!(object instanceof Double)) {
/* 237 */         syntaxError("value for FieldOfView must be a number");
/*     */       }
/* 239 */       this.fieldOfView = ((Double)object).doubleValue();
/*     */     }
/* 241 */     else if (str1.equals("BackClipDistance")) {
/* 242 */       if (!(object instanceof Double)) {
/* 243 */         syntaxError("value for BackClipDistance must be a number");
/*     */       }
/* 245 */       this.backClipDistance = ((Double)object).doubleValue();
/*     */     }
/* 247 */     else if (str1.equals("FrontClipDistance")) {
/* 248 */       if (!(object instanceof Double)) {
/* 249 */         syntaxError("value for FrontClipDistance must be a number");
/*     */       }
/* 251 */       this.frontClipDistance = ((Double)object).doubleValue();
/*     */     }
/* 253 */     else if (str1.equals("ScreenScale")) {
/* 254 */       if (!(object instanceof Double)) {
/* 255 */         syntaxError("value for ScreenScale must be a number");
/*     */       }
/* 257 */       this.screenScale = ((Double)object).doubleValue();
/*     */     }
/* 259 */     else if (str1.equals("TrackingEnable")) {
/* 260 */       if (!(object instanceof Boolean)) {
/* 261 */         syntaxError("value for TrackingEnable must be a boolean");
/*     */       }
/* 263 */       this.trackingEnable = ((Boolean)object).booleanValue();
/*     */     }
/* 265 */     else if (str1.equals("CoexistenceCenteringEnable")) {
/* 266 */       if (!(object instanceof Boolean)) {
/* 267 */         syntaxError("value for CoexistenceCenteringEnable must be a boolean");
/*     */       }
/*     */       
/* 270 */       this.coeCenteringEnable = ((Boolean)object).booleanValue();
/* 271 */       this.coeCenteringEnableSet = true;
/*     */     }
/* 273 */     else if (str1.equals("ViewPolicy")) {
/* 274 */       if (!(object instanceof String)) {
/* 275 */         syntaxError("value for ViewPolicy must be a string");
/*     */       }
/* 277 */       str2 = (String)object;
/* 278 */       if (str2.equals("SCREEN_VIEW")) {
/* 279 */         this.viewPolicy = 0;
/* 280 */       } else if (str2.equals("HMD_VIEW")) {
/* 281 */         this.viewPolicy = 1;
/*     */       } else {
/* 283 */         syntaxError("Invalid value for ViewPolicy " + str2);
/*     */       } 
/* 285 */     } else if (str1.equals("WindowEyepointPolicy")) {
/* 286 */       if (!(object instanceof String)) {
/* 287 */         syntaxError("value for WindowEyepointPolicy must be a string");
/*     */       }
/*     */       
/* 290 */       str2 = (String)object;
/* 291 */       if (str2.equals("RELATIVE_TO_SCREEN")) {
/* 292 */         this.windowEyepointPolicy = 0;
/* 293 */       } else if (str2.equals("RELATIVE_TO_COEXISTENCE")) {
/* 294 */         this.windowEyepointPolicy = 3;
/* 295 */       } else if (str2.equals("RELATIVE_TO_WINDOW")) {
/* 296 */         this.windowEyepointPolicy = 1;
/* 297 */       } else if (str2.equals("RELATIVE_TO_FIELD_OF_VIEW")) {
/* 298 */         this.windowEyepointPolicy = 2;
/*     */       } else {
/* 300 */         syntaxError("Invalid value for WindowEyepointPolicy " + str2);
/*     */       } 
/* 302 */     } else if (str1.equals("WindowMovementPolicy")) {
/* 303 */       if (!(object instanceof String)) {
/* 304 */         syntaxError("value for WindowEyeMovementPolicy must be a string");
/*     */       }
/*     */       
/* 307 */       str2 = (String)object;
/* 308 */       if (str2.equals("VIRTUAL_WORLD")) {
/* 309 */         this.windowMovementPolicy = 0;
/* 310 */       } else if (str2.equals("PHYSICAL_WORLD")) {
/* 311 */         this.windowMovementPolicy = 1;
/*     */       } else {
/* 313 */         syntaxError("Invalid value for WindowMovementPolicy " + str2);
/*     */       } 
/* 315 */     } else if (str1.equals("WindowResizePolicy")) {
/* 316 */       if (!(object instanceof String)) {
/* 317 */         syntaxError("value for WindowResizePolicy must be a string");
/*     */       }
/*     */       
/* 320 */       str2 = (String)object;
/* 321 */       if (str2.equals("VIRTUAL_WORLD")) {
/* 322 */         this.windowResizePolicy = 0;
/* 323 */       } else if (str2.equals("PHYSICAL_WORLD")) {
/* 324 */         this.windowResizePolicy = 1;
/*     */       } else {
/* 326 */         syntaxError("Invalid value for WindowResizePolicy " + str2);
/*     */       } 
/* 328 */     } else if (str1.equals("CenterEyeInCoexistence")) {
/* 329 */       if (object instanceof Point3d) {
/* 330 */         this.centerEyeInCoexistence = (Point3d)object;
/*     */       } else {
/* 332 */         syntaxError("value for CenterEyeInCoexistence must be a Point3d");
/*     */       }
/*     */     
/* 335 */     } else if (str1.equals("StereoEnable")) {
/* 336 */       if (!(object instanceof Boolean)) {
/* 337 */         syntaxError("value for StereoEnable must be a boolean");
/*     */       }
/* 339 */       this.stereoEnable = ((Boolean)object).booleanValue();
/*     */     }
/* 341 */     else if (str1.equals("AntialiasingEnable")) {
/* 342 */       if (!(object instanceof Boolean)) {
/* 343 */         syntaxError("value for AntialiasingEnable must be a boolean");
/*     */       }
/* 345 */       this.antialiasingEnable = ((Boolean)object).booleanValue();
/*     */     } else {
/*     */       
/* 348 */       syntaxError("Unknown " + paramConfigCommand.commandName + " \"" + str1 + "\"");
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
/*     */   protected Viewer createViewer(boolean paramBoolean) {
/* 360 */     this.j3dView = new View();
/* 361 */     this.j3dView.setViewPolicy(this.viewPolicy);
/*     */     
/* 363 */     if (this.configBody == null) {
/* 364 */       this.physicalBody = new PhysicalBody();
/*     */     } else {
/* 366 */       this.physicalBody = this.configBody.j3dPhysicalBody;
/*     */     } 
/* 368 */     if (this.configEnv == null) {
/* 369 */       this.physicalEnvironment = new PhysicalEnvironment();
/*     */     } else {
/* 371 */       this.physicalEnvironment = this.configEnv.j3dPhysicalEnvironment;
/*     */     } 
/* 373 */     this.j3dView.setPhysicalBody(this.physicalBody);
/* 374 */     this.j3dView.setPhysicalEnvironment(this.physicalEnvironment);
/*     */     
/* 376 */     boolean bool = true;
/* 377 */     if (this.coeCenteringEnableSet && !this.coeCenteringEnable) {
/* 378 */       bool = false;
/*     */     }
/* 380 */     if (this.configEnv != null && this.configEnv.coexistenceToTrackerBase != null) {
/* 381 */       bool = false;
/*     */     } else {
/*     */       
/* 384 */       Iterator iterator = this.screens.iterator();
/* 385 */       while (iterator.hasNext()) {
/* 386 */         ConfigScreen configScreen = (ConfigScreen)iterator.next();
/* 387 */         if (configScreen.trackerBaseToImagePlate != null) {
/* 388 */           bool = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 394 */     if (bool) {
/*     */ 
/*     */ 
/*     */       
/* 398 */       if (this.windowEyepointPolicy == -1)
/* 399 */         this.windowEyepointPolicy = 2; 
/* 400 */       if (this.windowMovementPolicy == -1)
/* 401 */         this.windowMovementPolicy = 1; 
/* 402 */       if (this.windowResizePolicy == -1)
/* 403 */         this.windowResizePolicy = 1; 
/* 404 */       if (!this.coeCenteringEnableSet) {
/* 405 */         this.coeCenteringEnable = true;
/*     */       }
/*     */     } else {
/*     */       
/* 409 */       if (this.windowEyepointPolicy == -1)
/* 410 */         this.windowEyepointPolicy = 3; 
/* 411 */       if (this.windowMovementPolicy == -1)
/* 412 */         this.windowMovementPolicy = 0; 
/* 413 */       if (this.windowResizePolicy == -1)
/* 414 */         this.windowResizePolicy = 0; 
/* 415 */       if (!this.coeCenteringEnableSet) {
/* 416 */         this.coeCenteringEnable = false;
/*     */       }
/*     */     } 
/* 419 */     this.j3dView.setWindowEyepointPolicy(this.windowEyepointPolicy);
/* 420 */     this.j3dView.setWindowMovementPolicy(this.windowMovementPolicy);
/* 421 */     this.j3dView.setWindowResizePolicy(this.windowResizePolicy);
/* 422 */     this.j3dView.setCoexistenceCenteringEnable(this.coeCenteringEnable);
/*     */     
/* 424 */     if (this.centerEyeInCoexistence == null) {
/* 425 */       this.centerEyeInCoexistence = new Point3d(0.0D, 0.0D, 0.4572D);
/*     */     }
/*     */     
/* 428 */     Point3d point3d1 = new Point3d(this.centerEyeInCoexistence);
/* 429 */     Point3d point3d2 = new Point3d(this.centerEyeInCoexistence);
/*     */     
/* 431 */     if (this.stereoEnable) {
/* 432 */       Point3d point3d3 = new Point3d();
/* 433 */       Point3d point3d4 = new Point3d();
/*     */       
/* 435 */       this.physicalBody.getLeftEyePosition(point3d3);
/* 436 */       this.physicalBody.getRightEyePosition(point3d4);
/*     */       
/* 438 */       point3d1.add(point3d3);
/* 439 */       point3d2.add(point3d4);
/*     */     } 
/*     */     
/* 442 */     this.j3dView.setLeftManualEyeInCoexistence(point3d1);
/* 443 */     this.j3dView.setRightManualEyeInCoexistence(point3d2);
/*     */     
/* 445 */     this.j3dView.setBackClipPolicy(this.backClipPolicy);
/* 446 */     this.j3dView.setFrontClipPolicy(this.frontClipPolicy);
/* 447 */     this.j3dView.setBackClipDistance(this.backClipDistance);
/* 448 */     this.j3dView.setFrontClipDistance(this.frontClipDistance);
/*     */     
/* 450 */     this.j3dView.setScreenScalePolicy(this.screenScalePolicy);
/* 451 */     this.j3dView.setScreenScale(this.screenScale);
/*     */     
/* 453 */     this.j3dView.setFieldOfView(this.fieldOfView);
/* 454 */     this.j3dView.setTrackingEnable(this.trackingEnable);
/* 455 */     this.j3dView.setSceneAntialiasingEnable(this.antialiasingEnable);
/*     */     
/* 457 */     if (this.screens.size() == 0) {
/* 458 */       throw new IllegalStateException(errorMessage(this.creatingCommand, "View \"" + this.instanceName + "\" has no canvases or screens"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 463 */     ConfigScreen[] arrayOfConfigScreen = new ConfigScreen[this.screens.size()];
/* 464 */     this.screens.toArray(arrayOfConfigScreen);
/*     */     
/* 466 */     this.j3dViewer = new Viewer(arrayOfConfigScreen, this, paramBoolean);
/* 467 */     return this.j3dViewer;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\util\\universe\ConfigView.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */