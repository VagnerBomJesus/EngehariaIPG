/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.media.j3d.InputDevice;
/*     */ import javax.media.j3d.PhysicalEnvironment;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Matrix4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ConfigPhysicalEnvironment
/*     */   extends ConfigObject
/*     */ {
/*  56 */   PhysicalEnvironment j3dPhysicalEnvironment = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   Matrix4d coexistenceToTrackerBase = null;
/*     */ 
/*     */   
/*  64 */   private ConfigSensor headTracker = null;
/*  65 */   private ArrayList inputDevices = new ArrayList();
/*  66 */   private int coexistenceCenterInPworldPolicy = 2;
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
/*     */   
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/*  83 */     Object[] arrayOfObject = paramConfigCommand.argv;
/*  84 */     int i = paramConfigCommand.argc;
/*     */ 
/*     */     
/*  87 */     if (i != 4) {
/*  88 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/*  92 */     if (!isName(arrayOfObject[1])) {
/*  93 */       syntaxError("The first argument to " + paramConfigCommand.commandName + " must be a name");
/*     */     }
/*     */ 
/*     */     
/*  97 */     if (!isName(arrayOfObject[2])) {
/*  98 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be a property name");
/*     */     }
/*     */ 
/*     */     
/* 102 */     String str = (String)arrayOfObject[2];
/* 103 */     Object object = arrayOfObject[3];
/*     */     
/* 105 */     if (str.equals("CoexistenceCenterInPworldPolicy")) {
/* 106 */       if (!(object instanceof String)) {
/* 107 */         syntaxError("CoexistenceCenterInPworldPolicy must be string");
/*     */       }
/* 109 */       String str1 = (String)object;
/* 110 */       if (str1.equals("NOMINAL_HEAD")) {
/* 111 */         this.coexistenceCenterInPworldPolicy = 0;
/* 112 */       } else if (str1.equals("NOMINAL_SCREEN")) {
/* 113 */         this.coexistenceCenterInPworldPolicy = 2;
/* 114 */       } else if (str1.equals("NOMINAL_FEET")) {
/* 115 */         this.coexistenceCenterInPworldPolicy = 1;
/*     */       } else {
/* 117 */         syntaxError("Illegal value " + str1 + " for CoexistenceCenterInPworldPolicy");
/*     */       }
/*     */     
/* 120 */     } else if (str.equals("CoexistenceToTrackerBase")) {
/* 121 */       if (object instanceof Matrix4d) {
/* 122 */         this.coexistenceToTrackerBase = (Matrix4d)object;
/*     */       } else {
/* 124 */         syntaxError("CoexistenceToTrackerBase must be a Matrix4d");
/*     */       } 
/* 126 */     } else if (str.equals("InputDevice")) {
/* 127 */       if (!(object instanceof String)) {
/* 128 */         syntaxError("InputDevice must be a name");
/*     */       }
/* 130 */       String str1 = (String)object;
/* 131 */       this.inputDevices.add(this.configContainer.findConfigObject("Device", str1));
/*     */     }
/* 133 */     else if (str.equals("HeadTracker")) {
/* 134 */       if (!(object instanceof String)) {
/* 135 */         syntaxError("HeadTracker must be a Sensor name");
/*     */       }
/* 137 */       String str1 = (String)object;
/* 138 */       this.headTracker = (ConfigSensor)this.configContainer.findConfigObject("Sensor", str1);
/*     */     }
/*     */     else {
/*     */       
/* 142 */       syntaxError("Unknown " + paramConfigCommand.commandName + " \"" + str + "\"");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PhysicalEnvironment createJ3dPhysicalEnvironment() {
/* 152 */     this.j3dPhysicalEnvironment = new PhysicalEnvironment();
/*     */     
/* 154 */     this.j3dPhysicalEnvironment.setCoexistenceCenterInPworldPolicy(this.coexistenceCenterInPworldPolicy);
/*     */ 
/*     */     
/* 157 */     if (this.coexistenceToTrackerBase != null) {
/* 158 */       this.j3dPhysicalEnvironment.setCoexistenceToTrackerBase(new Transform3D(this.coexistenceToTrackerBase));
/*     */     }
/*     */     
/* 161 */     return this.j3dPhysicalEnvironment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processDevices() {
/* 168 */     for (byte b = 0; b < this.inputDevices.size(); b++) {
/* 169 */       ConfigDevice configDevice = (ConfigDevice)this.inputDevices.get(b);
/* 170 */       InputDevice inputDevice = configDevice.j3dInputDevice;
/* 171 */       this.j3dPhysicalEnvironment.addInputDevice(inputDevice);
/*     */     } 
/*     */     
/* 174 */     if (this.headTracker != null) {
/* 175 */       this.j3dPhysicalEnvironment.setHeadIndex(0);
/* 176 */       this.j3dPhysicalEnvironment.setSensor(0, this.headTracker.j3dSensor);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\util\\universe\ConfigPhysicalEnvironment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */