/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import java.io.StreamTokenizer;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsFrame
/*     */   extends TextfileParser
/*     */ {
/*     */   double x;
/*     */   double y;
/*     */   double z;
/*     */   double heading;
/*     */   double pitch;
/*     */   double bank;
/*     */   double xScale;
/*     */   double yScale;
/*     */   double zScale;
/*     */   double frameNumber;
/*     */   int linearValue;
/*     */   double tension;
/*     */   double continuity;
/*     */   double bias;
/*     */   
/*     */   LwsFrame(StreamTokenizer paramStreamTokenizer) {
/*  77 */     this.x = getNumber(paramStreamTokenizer);
/*  78 */     this.y = getNumber(paramStreamTokenizer);
/*  79 */     this.z = -getNumber(paramStreamTokenizer);
/*  80 */     debugOutputLn(2, "x, y, z " + this.x + ", " + this.y + ", " + this.z);
/*  81 */     this.heading = getNumber(paramStreamTokenizer);
/*  82 */     this.pitch = getNumber(paramStreamTokenizer);
/*  83 */     this.bank = getNumber(paramStreamTokenizer);
/*  84 */     debugOutputLn(2, "(degrees) h, p, b = " + this.heading + ", " + this.pitch + ", " + this.bank);
/*  85 */     this.heading *= 0.017453292519943295D;
/*  86 */     this.pitch *= 0.017453292519943295D;
/*  87 */     this.bank *= 0.017453292519943295D;
/*  88 */     debugOutputLn(2, "(radians) h, p, b = " + this.heading + ", " + this.pitch + ", " + this.bank);
/*  89 */     debugOutputLn(8, "got pos and ori");
/*  90 */     this.xScale = getNumber(paramStreamTokenizer);
/*  91 */     this.yScale = getNumber(paramStreamTokenizer);
/*  92 */     this.zScale = getNumber(paramStreamTokenizer);
/*  93 */     debugOutputLn(2, "xs, ys, zs " + this.xScale + ", " + this.yScale + ", " + this.zScale);
/*  94 */     this.frameNumber = (int)getNumber(paramStreamTokenizer);
/*     */     
/*  96 */     this.linearValue = (int)getNumber(paramStreamTokenizer);
/*  97 */     debugOutputLn(2, "framenum, linear " + this.frameNumber + " , " + this.linearValue);
/*  98 */     this.tension = getNumber(paramStreamTokenizer);
/*  99 */     this.continuity = getNumber(paramStreamTokenizer);
/* 100 */     this.bias = getNumber(paramStreamTokenizer);
/* 101 */     debugOutputLn(2, "tension, cont, bias = " + this.tension + ", " + this.continuity + ", " + this.bias);
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
/*     */   LwsFrame(LwsFrame paramLwsFrame1, LwsFrame paramLwsFrame2, double paramDouble) {
/* 113 */     paramLwsFrame1.x += (paramLwsFrame2.x - paramLwsFrame1.x) * paramDouble;
/* 114 */     paramLwsFrame1.y += (paramLwsFrame2.y - paramLwsFrame1.y) * paramDouble;
/* 115 */     paramLwsFrame1.z += (paramLwsFrame2.z - paramLwsFrame1.z) * paramDouble;
/*     */     
/* 117 */     paramLwsFrame1.heading += (paramLwsFrame2.heading - paramLwsFrame1.heading) * paramDouble;
/*     */     
/* 119 */     paramLwsFrame1.pitch += (paramLwsFrame2.pitch - paramLwsFrame1.pitch) * paramDouble;
/*     */     
/* 121 */     paramLwsFrame1.bank += (paramLwsFrame2.bank - paramLwsFrame1.bank) * paramDouble;
/*     */     
/* 123 */     paramLwsFrame1.xScale += (paramLwsFrame2.xScale - paramLwsFrame1.xScale) * paramDouble;
/*     */     
/* 125 */     paramLwsFrame1.yScale += (paramLwsFrame2.yScale - paramLwsFrame1.yScale) * paramDouble;
/*     */     
/* 127 */     paramLwsFrame1.zScale += (paramLwsFrame2.zScale - paramLwsFrame1.zScale) * paramDouble;
/*     */     
/* 129 */     paramLwsFrame1.frameNumber += (paramLwsFrame2.frameNumber - paramLwsFrame1.frameNumber) * paramDouble;
/*     */ 
/*     */ 
/*     */     
/* 133 */     this.linearValue = paramLwsFrame1.linearValue;
/* 134 */     this.tension = paramLwsFrame1.tension;
/* 135 */     this.continuity = paramLwsFrame1.continuity;
/* 136 */     this.bias = paramLwsFrame1.bias;
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
/*     */   LwsFrame(LwsFrame paramLwsFrame1, LwsFrame paramLwsFrame2, LwsFrame paramLwsFrame3, LwsFrame paramLwsFrame4, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 158 */     double d9 = paramDouble1 * paramDouble1;
/* 159 */     double d10 = d9 * paramDouble1;
/* 160 */     double d11 = 3.0D * d9 - d10 - d10;
/* 161 */     double d1 = 1.0D - d11;
/* 162 */     double d2 = d11;
/* 163 */     double d3 = d10 - d9 - d9 + paramDouble1;
/* 164 */     double d4 = d10 - d9;
/*     */     
/* 166 */     double d5 = (1.0D - paramLwsFrame2.tension) * (1.0D + paramLwsFrame2.continuity) * (1.0D + paramLwsFrame2.bias);
/*     */ 
/*     */     
/* 169 */     double d6 = (1.0D - paramLwsFrame2.tension) * (1.0D - paramLwsFrame2.continuity) * (1.0D - paramLwsFrame2.bias);
/*     */ 
/*     */     
/* 172 */     double d7 = (1.0D - paramLwsFrame3.tension) * (1.0D - paramLwsFrame3.continuity) * (1.0D + paramLwsFrame3.bias);
/*     */ 
/*     */     
/* 175 */     double d8 = (1.0D - paramLwsFrame3.tension) * (1.0D + paramLwsFrame3.continuity) * (1.0D - paramLwsFrame3.bias);
/*     */ 
/*     */     
/* 178 */     double[] arrayOfDouble = new double[4];
/*     */ 
/*     */     
/* 181 */     arrayOfDouble[0] = paramLwsFrame1.x; arrayOfDouble[1] = paramLwsFrame2.x;
/* 182 */     arrayOfDouble[2] = paramLwsFrame3.x; arrayOfDouble[3] = paramLwsFrame4.x;
/* 183 */     this.x = computeInterpolation(arrayOfDouble, d5, d6, d7, d8, paramDouble2, paramDouble3, d1, d2, d3, d4);
/*     */     
/* 185 */     arrayOfDouble[0] = paramLwsFrame1.y; arrayOfDouble[1] = paramLwsFrame2.y;
/* 186 */     arrayOfDouble[2] = paramLwsFrame3.y; arrayOfDouble[3] = paramLwsFrame4.y;
/* 187 */     this.y = computeInterpolation(arrayOfDouble, d5, d6, d7, d8, paramDouble2, paramDouble3, d1, d2, d3, d4);
/*     */     
/* 189 */     arrayOfDouble[0] = paramLwsFrame1.z; arrayOfDouble[1] = paramLwsFrame2.z;
/* 190 */     arrayOfDouble[2] = paramLwsFrame3.z; arrayOfDouble[3] = paramLwsFrame4.z;
/* 191 */     this.z = computeInterpolation(arrayOfDouble, d5, d6, d7, d8, paramDouble2, paramDouble3, d1, d2, d3, d4);
/*     */ 
/*     */ 
/*     */     
/* 195 */     arrayOfDouble[0] = paramLwsFrame1.heading; arrayOfDouble[1] = paramLwsFrame2.heading;
/* 196 */     arrayOfDouble[2] = paramLwsFrame3.heading; arrayOfDouble[3] = paramLwsFrame4.heading;
/* 197 */     this.heading = computeInterpolation(arrayOfDouble, d5, d6, d7, d8, paramDouble2, paramDouble3, d1, d2, d3, d4);
/*     */ 
/*     */     
/* 200 */     arrayOfDouble[0] = paramLwsFrame1.pitch; arrayOfDouble[1] = paramLwsFrame2.pitch;
/* 201 */     arrayOfDouble[2] = paramLwsFrame3.pitch; arrayOfDouble[3] = paramLwsFrame4.pitch;
/* 202 */     this.pitch = computeInterpolation(arrayOfDouble, d5, d6, d7, d8, paramDouble2, paramDouble3, d1, d2, d3, d4);
/*     */ 
/*     */     
/* 205 */     arrayOfDouble[0] = paramLwsFrame1.bank; arrayOfDouble[1] = paramLwsFrame2.bank;
/* 206 */     arrayOfDouble[2] = paramLwsFrame3.bank; arrayOfDouble[3] = paramLwsFrame4.bank;
/* 207 */     this.bank = computeInterpolation(arrayOfDouble, d5, d6, d7, d8, paramDouble2, paramDouble3, d1, d2, d3, d4);
/*     */ 
/*     */ 
/*     */     
/* 211 */     paramLwsFrame2.xScale += (paramLwsFrame3.xScale - paramLwsFrame2.xScale) * paramDouble1;
/* 212 */     paramLwsFrame2.yScale += (paramLwsFrame3.yScale - paramLwsFrame2.yScale) * paramDouble1;
/* 213 */     paramLwsFrame2.zScale += (paramLwsFrame3.zScale - paramLwsFrame2.zScale) * paramDouble1;
/*     */ 
/*     */     
/* 216 */     paramLwsFrame2.frameNumber += (paramLwsFrame3.frameNumber - paramLwsFrame2.frameNumber) * paramDouble1;
/*     */ 
/*     */ 
/*     */     
/* 220 */     this.linearValue = paramLwsFrame3.linearValue;
/*     */ 
/*     */     
/* 223 */     this.tension = 0.0D;
/* 224 */     this.continuity = 0.0D;
/* 225 */     this.bias = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double computeInterpolation(double[] paramArrayOfDouble, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9, double paramDouble10) {
/* 236 */     double d2, d1, d3 = paramArrayOfDouble[2] - paramArrayOfDouble[1];
/*     */ 
/*     */ 
/*     */     
/* 240 */     if (paramDouble5 < -1.0E-4D || paramDouble5 > 1.0E-4D) {
/* 241 */       d1 = paramDouble5 * (paramDouble1 * (paramArrayOfDouble[1] - paramArrayOfDouble[0]) + paramDouble2 * d3);
/*     */     } else {
/* 243 */       d1 = 0.5D * (paramDouble1 + paramDouble2) * d3;
/*     */     } 
/*     */     
/* 246 */     if (paramDouble6 < -1.0E-4D || paramDouble6 > 1.0E-4D) {
/* 247 */       d2 = paramDouble6 * (paramDouble3 * d3 + paramDouble4 * (paramArrayOfDouble[3] - paramArrayOfDouble[2]));
/*     */     } else {
/* 249 */       d2 = 0.5D * (paramDouble3 + paramDouble4) * d3;
/*     */     } 
/* 251 */     return paramArrayOfDouble[1] * paramDouble7 + paramArrayOfDouble[2] * paramDouble8 + d1 * paramDouble9 + d2 * paramDouble10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   double getHeading() { return this.heading; }
/*     */ 
/*     */ 
/*     */   
/* 261 */   double getPitch() { return this.pitch; }
/*     */ 
/*     */ 
/*     */   
/* 265 */   double getBank() { return this.bank; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMatrix(Matrix4d paramMatrix4d) {
/* 273 */     setRotationMatrix(paramMatrix4d);
/* 274 */     paramMatrix4d.setTranslation(new Vector3d(this.x, this.y, this.z));
/* 275 */     Matrix4d matrix4d = new Matrix4d();
/* 276 */     matrix4d.setColumn(0, this.xScale, 0.0D, 0.0D, 0.0D);
/* 277 */     matrix4d.setColumn(1, 0.0D, this.yScale, 0.0D, 0.0D);
/* 278 */     matrix4d.setColumn(2, 0.0D, 0.0D, this.zScale, 0.0D);
/* 279 */     matrix4d.setColumn(3, 0.0D, 0.0D, 0.0D, 1.0D);
/* 280 */     paramMatrix4d.mul(matrix4d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setRotationMatrix(Matrix4d paramMatrix4d) {
/* 288 */     debugOutputLn(1, "setRotMat()");
/* 289 */     debugOutputLn(2, " p, h, b = " + this.pitch + ", " + this.heading + ", " + this.bank);
/*     */ 
/*     */ 
/*     */     
/* 293 */     Matrix4d matrix4d1 = new Matrix4d();
/* 294 */     matrix4d1.rotX(-this.pitch);
/* 295 */     Matrix4d matrix4d2 = new Matrix4d();
/* 296 */     matrix4d2.rotZ(this.bank);
/* 297 */     paramMatrix4d.rotY(-this.heading);
/* 298 */     paramMatrix4d.mul(matrix4d1);
/* 299 */     paramMatrix4d.mul(matrix4d2);
/* 300 */     debugOutputLn(2, "setRotMat(), mat = " + paramMatrix4d);
/*     */   }
/*     */ 
/*     */   
/* 304 */   Point3f getPosition() { return new Point3f((float)this.x, (float)this.y, (float)this.z); }
/*     */ 
/*     */ 
/*     */   
/*     */   Point3f getScale() {
/* 309 */     if ((this.xScale < -1.0E-4D || this.xScale > 1.0E-4D) && (this.yScale < -1.0E-4D || this.yScale > 1.0E-4D) && (this.zScale < -1.0E-4D || this.zScale > 1.0E-4D))
/*     */     {
/*     */       
/* 312 */       return new Point3f((float)this.xScale, (float)this.yScale, (float)this.zScale);
/*     */     }
/* 314 */     return new Point3f(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 319 */   double getFrameNum() { return this.frameNumber; }
/*     */ 
/*     */   
/*     */   void printVals() {
/* 323 */     debugOutputLn(2, "         x = " + this.x);
/* 324 */     debugOutputLn(2, "         y = " + this.y);
/* 325 */     debugOutputLn(2, "         z = " + this.z);
/* 326 */     debugOutputLn(2, "         xScale = " + this.xScale);
/* 327 */     debugOutputLn(2, "         yScale = " + this.yScale);
/* 328 */     debugOutputLn(2, "         zScale = " + this.zScale);
/* 329 */     debugOutputLn(2, "         heading = " + this.heading);
/* 330 */     debugOutputLn(2, "         pitch = " + this.pitch);
/* 331 */     debugOutputLn(2, "         bank = " + this.bank);
/* 332 */     debugOutputLn(2, "         frameNum = " + this.frameNumber);
/* 333 */     debugOutputLn(2, "         lin = " + this.linearValue);
/* 334 */     debugOutputLn(2, "         tension = " + this.tension);
/* 335 */     debugOutputLn(2, "         continuity = " + this.continuity);
/* 336 */     debugOutputLn(2, "         bias = " + this.bias);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\lw3d\LwsFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */