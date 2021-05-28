/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import javax.media.j3d.PhysicalBody;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Matrix4d;
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
/*     */ class ConfigPhysicalBody
/*     */   extends ConfigObject
/*     */ {
/*  56 */   Point3d leftEyePosition = new Point3d(-0.033D, 0.0D, 0.0D);
/*  57 */   Point3d rightEyePosition = new Point3d(0.033D, 0.0D, 0.0D);
/*  58 */   double stereoEyeSeparation = Double.MAX_VALUE;
/*     */   
/*  60 */   Point3d leftEarPosition = new Point3d(-0.08D, -0.03D, 0.09D);
/*  61 */   Point3d rightEarPosition = new Point3d(0.08D, -0.03D, 0.09D);
/*     */   
/*  63 */   double nominalEyeHeightFromGround = 1.68D;
/*  64 */   double nominalEyeOffsetFromNominalScreen = 0.4572D;
/*     */   
/*  66 */   Matrix4d headToHeadTracker = new Matrix4d(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */ 
/*     */ 
/*     */   
/*     */   PhysicalBody j3dPhysicalBody;
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialize(ConfigCommand paramConfigCommand) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/*  79 */     int i = paramConfigCommand.argc;
/*  80 */     Object[] arrayOfObject = paramConfigCommand.argv;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     if (i != 4) {
/*  86 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/*  90 */     if (!isName(arrayOfObject[1])) {
/*  91 */       syntaxError("The first argument to " + paramConfigCommand.commandName + " must be a name");
/*     */     }
/*     */ 
/*     */     
/*  95 */     if (!isName(arrayOfObject[2])) {
/*  96 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be an property/attribute name");
/*     */     }
/*     */ 
/*     */     
/* 100 */     String str = (String)arrayOfObject[2];
/* 101 */     Object object = arrayOfObject[3];
/*     */     
/* 103 */     if (str.equals("StereoEyeSeparation")) {
/* 104 */       if (!(object instanceof Double)) {
/* 105 */         syntaxError("StereoEyeSeparation must be a number");
/*     */       }
/* 107 */       this.stereoEyeSeparation = ((Double)object).doubleValue();
/*     */     }
/* 109 */     else if (str.equals("LeftEyePosition")) {
/* 110 */       if (!(object instanceof Point3d)) {
/* 111 */         syntaxError("LeftEyePosition must be a point");
/*     */       }
/* 113 */       this.leftEyePosition = (Point3d)object;
/*     */     }
/* 115 */     else if (str.equals("RightEyePosition")) {
/* 116 */       if (!(object instanceof Point3d)) {
/* 117 */         syntaxError("RightEyePosition must be a point");
/*     */       }
/* 119 */       this.rightEyePosition = (Point3d)object;
/*     */     }
/* 121 */     else if (str.equals("LeftEarPosition")) {
/* 122 */       if (!(object instanceof Point3d)) {
/* 123 */         syntaxError("LeftEarPosition must be a point");
/*     */       }
/* 125 */       this.leftEarPosition = (Point3d)object;
/*     */     }
/* 127 */     else if (str.equals("RightEarPosition")) {
/* 128 */       if (!(object instanceof Point3d)) {
/* 129 */         syntaxError("RightEarPosition must be a point");
/*     */       }
/* 131 */       this.leftEarPosition = (Point3d)object;
/*     */     }
/* 133 */     else if (str.equals("NominalEyeHeightFromGround")) {
/* 134 */       if (!(object instanceof Double)) {
/* 135 */         syntaxError("NominalEyeHeightFromGround must be a number");
/*     */       }
/* 137 */       this.nominalEyeHeightFromGround = ((Double)object).doubleValue();
/*     */     }
/* 139 */     else if (str.equals("NominalEyeOffsetFromNominalScreen")) {
/* 140 */       if (!(object instanceof Double)) {
/* 141 */         syntaxError("NominalEyeOffsetFromNominalScreen must be a number");
/*     */       }
/*     */       
/* 144 */       this.nominalEyeOffsetFromNominalScreen = ((Double)object).doubleValue();
/*     */     }
/* 146 */     else if (str.equals("HeadToHeadTracker")) {
/* 147 */       if (!(object instanceof Matrix4d)) {
/* 148 */         syntaxError("HeadToHeadTracker must be a matrix");
/*     */       }
/* 150 */       this.headToHeadTracker = (Matrix4d)object;
/*     */     } else {
/*     */       
/* 153 */       syntaxError("Unknown " + paramConfigCommand.commandName + " \"" + str + "\"");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   PhysicalBody createJ3dPhysicalBody() {
/* 160 */     if (this.stereoEyeSeparation < Double.MAX_VALUE) {
/* 161 */       this.leftEyePosition.set(-this.stereoEyeSeparation / 2.0D, 0.0D, 0.0D);
/* 162 */       this.rightEyePosition.set(this.stereoEyeSeparation / 2.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/* 165 */     this.j3dPhysicalBody = new PhysicalBody(this.leftEyePosition, this.rightEyePosition, this.leftEarPosition, this.rightEarPosition);
/*     */ 
/*     */     
/* 168 */     this.j3dPhysicalBody.setHeadToHeadTracker(new Transform3D(this.headToHeadTracker));
/*     */ 
/*     */     
/* 171 */     this.j3dPhysicalBody.setNominalEyeHeightFromGround(this.nominalEyeHeightFromGround);
/*     */     
/* 173 */     this.j3dPhysicalBody.setNominalEyeOffsetFromNominalScreen(this.nominalEyeOffsetFromNominalScreen);
/*     */ 
/*     */     
/* 176 */     return this.j3dPhysicalBody;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\util\\universe\ConfigPhysicalBody.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */