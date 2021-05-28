/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import javax.media.j3d.Sensor;
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
/*     */ 
/*     */ 
/*     */ class ConfigSensor
/*     */   extends ConfigObject
/*     */ {
/*     */   private int sensorIndex;
/*     */   private ConfigDevice configDevice;
/*  59 */   private Point3d hotspot = null;
/*  60 */   private int predictor = -1;
/*  61 */   private int predictionPolicy = -1;
/*  62 */   private int sensorReadCount = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Sensor j3dSensor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialize(ConfigCommand paramConfigCommand) {
/*  78 */     int i = paramConfigCommand.argc;
/*  79 */     Object[] arrayOfObject = paramConfigCommand.argv;
/*     */ 
/*     */     
/*  82 */     if (i != 4) {
/*  83 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/*  87 */     if (!isName(arrayOfObject[2])) {
/*  88 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be the device name");
/*     */     }
/*     */ 
/*     */     
/*  92 */     if (!(arrayOfObject[3] instanceof Double)) {
/*  93 */       syntaxError("The third argument to " + paramConfigCommand.commandName + " must be a sensor index");
/*     */     }
/*     */ 
/*     */     
/*  97 */     this.sensorIndex = ((Double)arrayOfObject[3]).intValue();
/*  98 */     this.configDevice = (ConfigDevice)this.configContainer.findConfigObject("Device", (String)arrayOfObject[2]);
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
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/* 111 */     int i = paramConfigCommand.argc;
/* 112 */     Object[] arrayOfObject = paramConfigCommand.argv;
/*     */ 
/*     */ 
/*     */     
/* 116 */     if (i != 4) {
/* 117 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/* 121 */     if (!isName(arrayOfObject[1])) {
/* 122 */       syntaxError("The first argument to " + paramConfigCommand.commandName + " must be the instance name");
/*     */     }
/*     */ 
/*     */     
/* 126 */     if (!isName(arrayOfObject[2])) {
/* 127 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be a property name");
/*     */     }
/*     */ 
/*     */     
/* 131 */     String str = (String)arrayOfObject[2];
/* 132 */     if (str.equals("Hotspot")) {
/* 133 */       if (!(arrayOfObject[3] instanceof Point3d)) {
/* 134 */         syntaxError("Hotspot must be a 3D point");
/*     */       }
/* 136 */       this.hotspot = (Point3d)arrayOfObject[3];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       syntaxError("Unknown " + paramConfigCommand.commandName + " \"" + str + "\"");
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
/*     */   void configureSensor() {
/* 197 */     this.j3dSensor = this.configDevice.j3dInputDevice.getSensor(this.sensorIndex);
/*     */     
/* 199 */     if (this.hotspot != null) {
/* 200 */       this.j3dSensor.setHotspot(this.hotspot);
/*     */     }
/* 202 */     if (this.predictor != -1) {
/* 203 */       this.j3dSensor.setPredictor(this.predictor);
/*     */     }
/* 205 */     if (this.predictionPolicy != -1) {
/* 206 */       this.j3dSensor.setPredictionPolicy(this.predictionPolicy);
/*     */     }
/* 208 */     if (this.sensorReadCount != -1)
/* 209 */       this.j3dSensor.setSensorReadCount(this.sensorReadCount); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\util\\universe\ConfigSensor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */