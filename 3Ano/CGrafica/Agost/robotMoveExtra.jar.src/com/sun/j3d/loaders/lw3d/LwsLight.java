/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.DirectionalLight;
/*     */ import javax.media.j3d.Light;
/*     */ import javax.media.j3d.PointLight;
/*     */ import javax.media.j3d.SpotLight;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
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
/*     */ class LwsLight
/*     */   extends TextfileParser
/*     */   implements LwsPrimitive
/*     */ {
/*     */   String fileName;
/*     */   String objName;
/*     */   LwsMotion motion;
/*     */   int parent;
/*     */   TransformGroup objectTransform;
/*     */   Vector objectBehavior;
/*     */   Color3f color;
/*     */   int type;
/*     */   Point3f attenuation;
/*     */   float spotConeAngle;
/*     */   LwLightObject lwLight;
/*     */   LwsEnvelopeLightIntensity intensityEnvelope;
/*     */   Light light;
/*     */   static final int DIRECTIONAL = 0;
/*     */   static final int POINT = 1;
/*     */   static final int SPOT = 2;
/*     */   
/*     */   LwsLight(StreamTokenizer paramStreamTokenizer, int paramInt1, float paramFloat, int paramInt2) throws ParsingErrorException {
/*  73 */     this.attenuation = new Point3f(1.0F, 0.0F, 0.0F);
/*  74 */     this.spotConeAngle = 3.1415927F;
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.intensityEnvelope = null;
/*  79 */     this.light = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     this.debugPrinter.setValidOutput(paramInt2);
/*     */     
/*  91 */     debugOutput(1, "LwsLight()");
/*  92 */     this.color = new Color3f(1.0F, 1.0F, 1.0F);
/*  93 */     this.lwLight = new LwLightObject(null, 0.0F, null);
/*     */     
/*  95 */     this.parent = -1;
/*  96 */     debugOutputLn(8, "about to get LightName");
/*  97 */     getAndCheckString(paramStreamTokenizer, "LightName");
/*  98 */     debugOutputLn(8, "about to get LightName value");
/*  99 */     this.objName = getName(paramStreamTokenizer);
/* 100 */     debugOutputLn(8, "got LightName");
/* 101 */     skip(paramStreamTokenizer, "ShowLight", 2);
/* 102 */     debugOutputLn(8, "got ShowLight");
/* 103 */     getAndCheckString(paramStreamTokenizer, "LightMotion");
/* 104 */     debugOutputLn(8, "got LightMotion");
/* 105 */     this.motion = new LwsMotion(paramStreamTokenizer, paramInt1, paramFloat);
/* 106 */     debugOutputLn(8, "got motions");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     while (!isCurrentToken(paramStreamTokenizer, "ShowCamera") && !isCurrentToken(paramStreamTokenizer, "AddLight")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 122 */       debugOutputLn(8, "currentToken = " + paramStreamTokenizer.sval);
/*     */       
/* 124 */       if (isCurrentToken(paramStreamTokenizer, "ParentObject")) {
/* 125 */         this.parent = (int)getNumber(paramStreamTokenizer);
/*     */       }
/* 127 */       else if (isCurrentToken(paramStreamTokenizer, "LightColor")) {
/* 128 */         this.color.x = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 129 */         this.color.y = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 130 */         this.color.z = (float)getNumber(paramStreamTokenizer) / 255.0F;
/* 131 */         this.lwLight.setColor(this.color);
/*     */       }
/* 133 */       else if (isCurrentToken(paramStreamTokenizer, "LgtIntensity")) {
/*     */         
/* 135 */         String str2, str1 = getClass().getName();
/* 136 */         int i = str1.lastIndexOf('.');
/*     */         
/* 138 */         if (i < 0) {
/* 139 */           str2 = "";
/*     */         } else {
/* 141 */           str2 = str1.substring(0, i) + ".";
/* 142 */         }  EnvelopeHandler envelopeHandler = new EnvelopeHandler(paramStreamTokenizer, paramInt1, paramFloat, str2 + "LwsEnvelopeLightIntensity");
/*     */ 
/*     */         
/* 145 */         if (envelopeHandler.hasValue) {
/* 146 */           float f = envelopeHandler.theValue;
/* 147 */           this.color.x *= f;
/* 148 */           this.color.y *= f;
/* 149 */           this.color.z *= f;
/* 150 */           this.lwLight.setIntensity(f);
/*     */         } else {
/*     */           
/* 153 */           this.intensityEnvelope = (LwsEnvelopeLightIntensity)envelopeHandler.theEnvelope;
/*     */         }
/*     */       
/*     */       }
/* 157 */       else if (isCurrentToken(paramStreamTokenizer, "LightType")) {
/* 158 */         this.type = (int)getNumber(paramStreamTokenizer);
/*     */       }
/* 160 */       else if (isCurrentToken(paramStreamTokenizer, "Falloff")) {
/* 161 */         float f = (float)getNumber(paramStreamTokenizer);
/* 162 */         this.attenuation.y = 1.0F / (1.0F - f) - 1.0F;
/*     */       }
/* 164 */       else if (isCurrentToken(paramStreamTokenizer, "ConeAngle")) {
/* 165 */         this.spotConeAngle = (float)getNumber(paramStreamTokenizer) * 0.017453292F;
/*     */       } 
/*     */       try {
/* 168 */         paramStreamTokenizer.nextToken();
/*     */       }
/* 170 */       catch (IOException iOException) {
/* 171 */         throw new ParsingErrorException(iOException.getMessage());
/*     */       } 
/*     */     } 
/* 174 */     paramStreamTokenizer.pushBack();
/*     */   }
/*     */ 
/*     */   
/* 178 */   int getParent() { return this.parent; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createJava3dObject(int paramInt) {
/* 185 */     Matrix4d matrix4d = new Matrix4d();
/* 186 */     matrix4d.setIdentity();
/*     */ 
/*     */     
/* 189 */     LwsFrame lwsFrame = this.motion.getFirstFrame();
/* 190 */     lwsFrame.setMatrix(matrix4d);
/* 191 */     debugOutputLn(2, "Light transform = " + matrix4d);
/* 192 */     Transform3D transform3D = new Transform3D();
/* 193 */     transform3D.set(matrix4d);
/* 194 */     this.objectTransform = new TransformGroup(transform3D);
/* 195 */     this.objectTransform.setCapability(18);
/* 196 */     Vector3f vector3f = new Vector3f(0.0F, 0.0F, -1.0F);
/* 197 */     Point3f point3f = new Point3f(0.0F, 0.0F, 0.0F);
/*     */     
/* 199 */     switch (this.type) {
/*     */       case 0:
/* 201 */         this.light = new DirectionalLight(this.color, vector3f);
/*     */         break;
/*     */       case 1:
/* 204 */         this.light = new PointLight(this.color, point3f, this.attenuation);
/*     */         break;
/*     */       
/*     */       case 2:
/* 208 */         this.light = new SpotLight(this.color, point3f, this.attenuation, vector3f, 2.0F * this.spotConeAngle, 0.0F);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     this.light.setCapability(15);
/* 217 */     if (this.light != null) {
/* 218 */       this.lwLight.setLight(this.light);
/* 219 */       BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 100000.0D);
/*     */       
/* 221 */       this.light.setInfluencingBounds(boundingSphere);
/* 222 */       this.objectTransform.addChild(this.light);
/*     */ 
/*     */       
/* 225 */       this.objectBehavior = new Vector();
/* 226 */       if (paramInt != 0) {
/*     */         
/* 228 */         Behavior behavior = null;
/* 229 */         this.motion.createJava3dBehaviors(this.objectTransform);
/* 230 */         behavior = this.motion.getBehaviors();
/* 231 */         if (behavior != null) {
/* 232 */           this.objectBehavior.addElement(behavior);
/*     */         }
/* 234 */         if (this.intensityEnvelope != null) {
/* 235 */           behavior = null;
/* 236 */           this.intensityEnvelope.createJava3dBehaviors(this.lwLight);
/* 237 */           behavior = this.intensityEnvelope.getBehaviors();
/* 238 */           if (behavior != null) {
/* 239 */             this.objectBehavior.addElement(behavior);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 247 */   public TransformGroup getObjectNode() { return this.objectTransform; }
/*     */ 
/*     */ 
/*     */   
/* 251 */   Light getLight() { return this.light; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getObjectBehaviors() {
/* 256 */     debugOutputLn(1, "getObjectBehaviors()");
/* 257 */     return this.objectBehavior;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void printVals() {
/* 263 */     debugOutputLn(2, "  LIGHT vals: ");
/* 264 */     debugOutputLn(2, "   objName = " + this.objName);
/* 265 */     this.motion.printVals();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\LwsLight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */