/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.awt.Window;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point2d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ConfigScreen
/*     */   extends ConfigObject
/*     */ {
/*     */   int frameBufferNumber;
/*  68 */   double physicalScreenWidth = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   double physicalScreenHeight = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   Matrix4d trackerBaseToImagePlate = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   Matrix4d headTrackerToLeftImagePlate = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   Matrix4d headTrackerToRightImagePlate = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   int monoscopicViewPolicy = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean fullScreen = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean noBorderFullScreen = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   int windowWidthInPixels = 512;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   int windowHeightInPixels = 512;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   int windowX = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   int windowY = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JFrame j3dJFrame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Window j3dWindow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JPanel j3dJPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Canvas3D j3dCanvas;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/* 175 */     String str1 = null;
/* 176 */     Object object = null;
/* 177 */     String str2 = null;
/*     */     
/* 179 */     if (paramConfigCommand.argc != 4) {
/* 180 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/* 184 */     if (!isName(paramConfigCommand.argv[2])) {
/* 185 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be a property name");
/*     */     }
/*     */ 
/*     */     
/* 189 */     str1 = (String)paramConfigCommand.argv[2];
/* 190 */     object = paramConfigCommand.argv[3];
/*     */     
/* 192 */     if (str1.equals("PhysicalScreenWidth")) {
/* 193 */       if (!(object instanceof Double)) {
/* 194 */         syntaxError("Value for PhysicalScreenWidth must be a number");
/*     */       }
/*     */       
/* 197 */       this.physicalScreenWidth = ((Double)object).doubleValue();
/*     */     }
/* 199 */     else if (str1.equals("PhysicalScreenHeight")) {
/* 200 */       if (!(object instanceof Double)) {
/* 201 */         syntaxError("Value for PhysicalScreenHeight must be a number");
/*     */       }
/*     */       
/* 204 */       this.physicalScreenHeight = ((Double)object).doubleValue();
/*     */     }
/* 206 */     else if (str1.equals("TrackerBaseToImagePlate")) {
/* 207 */       if (!(object instanceof Matrix4d)) {
/* 208 */         syntaxError("Value for TrackerBaseToImagePlate must be a 4x3 or 4x4 matrix");
/*     */       }
/*     */       
/* 211 */       this.trackerBaseToImagePlate = (Matrix4d)object;
/*     */     }
/* 213 */     else if (str1.equals("HeadTrackerToLeftImagePlate")) {
/* 214 */       if (!(object instanceof Matrix4d)) {
/* 215 */         syntaxError("Value for HeadTrackerToLeftImagePlate must be a 4x3 or 4x4 matrix");
/*     */       }
/*     */       
/* 218 */       this.headTrackerToLeftImagePlate = (Matrix4d)object;
/*     */     }
/* 220 */     else if (str1.equals("HeadTrackerToRightImagePlate")) {
/* 221 */       if (!(object instanceof Matrix4d)) {
/* 222 */         syntaxError("Value for HeadTrackerToRightImagePlate must be a 4x3 or 4x4 matrix");
/*     */       }
/*     */       
/* 225 */       this.headTrackerToRightImagePlate = (Matrix4d)object;
/*     */     }
/* 227 */     else if (str1.equals("MonoscopicViewPolicy")) {
/* 228 */       if (!(object instanceof String)) {
/* 229 */         syntaxError("Value for MonoscopicViewPolicy must be a name");
/*     */       }
/*     */       
/* 232 */       str2 = (String)object;
/* 233 */       if (str2.equals("LEFT_EYE_VIEW")) {
/* 234 */         this.monoscopicViewPolicy = 0;
/* 235 */       } else if (str2.equals("RIGHT_EYE_VIEW")) {
/* 236 */         this.monoscopicViewPolicy = 1;
/* 237 */       } else if (str2.equals("CYCLOPEAN_EYE_VIEW")) {
/* 238 */         this.monoscopicViewPolicy = 2;
/*     */       } else {
/* 240 */         syntaxError("Invalid value for MonoscopicViewPolicy \"" + str2 + "\"");
/*     */       }
/*     */     
/* 243 */     } else if (str1.equals("WindowPosition")) {
/* 244 */       if (!(object instanceof Point2d)) {
/* 245 */         syntaxError("WindowPosition must be a Point2d");
/*     */       }
/* 247 */       Point2d point2d = (Point2d)object;
/* 248 */       this.windowX = (int)point2d.x;
/* 249 */       this.windowY = (int)point2d.y;
/*     */     }
/* 251 */     else if (str1.equals("WindowSize")) {
/* 252 */       if (object instanceof Point2d) {
/* 253 */         this.fullScreen = false;
/* 254 */         this.noBorderFullScreen = false;
/*     */         
/* 256 */         Point2d point2d = (Point2d)object;
/* 257 */         this.windowWidthInPixels = (int)point2d.x;
/* 258 */         this.windowHeightInPixels = (int)point2d.y;
/*     */       }
/* 260 */       else if (object instanceof String) {
/* 261 */         String str = (String)object;
/*     */         
/* 263 */         if (str.equals("FullScreen")) {
/* 264 */           this.fullScreen = true;
/* 265 */           this.noBorderFullScreen = false;
/* 266 */         } else if (str.equals("NoBorderFullScreen")) {
/* 267 */           this.fullScreen = false;
/* 268 */           this.noBorderFullScreen = true;
/*     */         } else {
/* 270 */           syntaxError("Value for WindowSize must be one of\n\"FullScreen\" \"NoBorderFullScreen\" or Point2d");
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 276 */         syntaxError("Invalid WindowSize value: " + object + "\nValue for WindowSize " + "must be one of\n" + "\"FullScreen\" " + "\"NoBorderFullScreen\" or Point2d");
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 283 */       syntaxError("Unknown " + paramConfigCommand.commandName + " \"" + str1 + "\"");
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
/*     */   protected void initialize(ConfigCommand paramConfigCommand) {
/* 295 */     if (paramConfigCommand.argc != 3) {
/* 296 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/* 300 */     if (!(paramConfigCommand.argv[2] instanceof Double)) {
/* 301 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be a GraphicsDevice index");
/*     */     }
/*     */ 
/*     */     
/* 305 */     this.frameBufferNumber = ((Double)paramConfigCommand.argv[2]).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\util\\universe\ConfigScreen.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */