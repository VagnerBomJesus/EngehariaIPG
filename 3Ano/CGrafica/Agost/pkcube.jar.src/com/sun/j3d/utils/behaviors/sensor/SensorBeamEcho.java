/*     */ package com.sun.j3d.utils.behaviors.sensor;
/*     */ 
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransparencyAttributes;
/*     */ import javax.media.j3d.TriangleStripArray;
/*     */ import javax.vecmath.AxisAngle4f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SensorBeamEcho
/*     */   extends Shape3D
/*     */ {
/*     */   public SensorBeamEcho(Point3d paramPoint3d, double paramDouble, boolean paramBoolean) {
/*  82 */     if (paramPoint3d.distance(new Point3d()) == 0.0D) {
/*  83 */       throw new IllegalArgumentException("\nBeam echo can't have hotspot at origin");
/*     */     }
/*     */     
/*  86 */     Vector3f vector3f1 = new Vector3f((float)paramPoint3d.x, (float)paramPoint3d.y, (float)paramPoint3d.z);
/*     */ 
/*     */ 
/*     */     
/*  90 */     Vector3f vector3f2 = new Vector3f();
/*  91 */     vector3f2.normalize(vector3f1);
/*     */ 
/*     */     
/*  94 */     Vector3f vector3f3 = new Vector3f(0.0F, 1.0F, 0.0F);
/*  95 */     vector3f3.cross(vector3f2, vector3f3);
/*  96 */     if (vector3f3.lengthSquared() < 0.5F) {
/*  97 */       vector3f3.set(0.0F, 0.0F, 1.0F);
/*  98 */       vector3f3.cross(vector3f2, vector3f3);
/*     */     } 
/* 100 */     vector3f3.normalize();
/*     */ 
/*     */     
/* 103 */     byte b1 = 18;
/* 104 */     Point3f[] arrayOfPoint3f1 = new Point3f[b1];
/* 105 */     Point3f[] arrayOfPoint3f2 = new Point3f[b1];
/* 106 */     Vector3f[] arrayOfVector3f1 = new Vector3f[b1];
/* 107 */     Vector3f vector3f4 = new Vector3f(vector3f2);
/* 108 */     Vector3f vector3f5 = new Vector3f(vector3f2);
/* 109 */     vector3f4.negate();
/*     */     
/* 111 */     AxisAngle4f axisAngle4f = new AxisAngle4f(vector3f2, -3.1415927F / b1 / 2.0F);
/*     */     
/* 113 */     Transform3D transform3D = new Transform3D();
/* 114 */     transform3D.set(axisAngle4f);
/*     */     
/* 116 */     float f = (float)paramDouble / 2.0F; byte b2;
/* 117 */     for (b2 = 0; b2 < b1; b2++) {
/* 118 */       arrayOfVector3f1[b2] = new Vector3f(vector3f3);
/* 119 */       arrayOfPoint3f1[b2] = new Point3f(vector3f3);
/* 120 */       arrayOfPoint3f1[b2].scale(f);
/* 121 */       arrayOfPoint3f2[b2] = new Point3f(arrayOfPoint3f1[b2]);
/* 122 */       arrayOfPoint3f2[b2].add(vector3f1);
/* 123 */       transform3D.transform(vector3f3);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     b2 = 2 + 4 * b1;
/* 130 */     Point3f[] arrayOfPoint3f3 = new Point3f[b2];
/* 131 */     Vector3f[] arrayOfVector3f2 = new Vector3f[b2];
/*     */     
/*     */     byte b3;
/* 134 */     for (b3 = 0; b3 < b1; b3++) {
/* 135 */       arrayOfPoint3f3[b3 * 2] = arrayOfPoint3f1[b3];
/* 136 */       arrayOfPoint3f3[b3 * 2 + 1] = arrayOfPoint3f2[b3];
/*     */       
/* 138 */       arrayOfVector3f2[b3 * 2] = arrayOfVector3f1[b3];
/* 139 */       arrayOfVector3f2[b3 * 2 + 1] = arrayOfVector3f1[b3];
/*     */     } 
/*     */     
/* 142 */     arrayOfPoint3f3[b1 * 2] = arrayOfPoint3f1[0];
/* 143 */     arrayOfPoint3f3[b1 * 2 + 1] = arrayOfPoint3f2[0];
/*     */     
/* 145 */     arrayOfVector3f2[b1 * 2] = arrayOfVector3f1[0];
/* 146 */     arrayOfVector3f2[b1 * 2 + 1] = arrayOfVector3f1[0];
/*     */ 
/*     */     
/* 149 */     b3 = (b1 + 1) * 2;
/* 150 */     arrayOfPoint3f3[b3] = arrayOfPoint3f1[0];
/* 151 */     arrayOfVector3f2[b3++] = vector3f4;
/*     */     
/* 153 */     byte b4 = 1;
/* 154 */     byte b5 = b1 - 1;
/* 155 */     while (b4 <= b5) {
/* 156 */       arrayOfPoint3f3[b3] = arrayOfPoint3f1[b4++];
/* 157 */       arrayOfVector3f2[b3++] = vector3f4;
/* 158 */       if (b4 > b5)
/* 159 */         break;  arrayOfPoint3f3[b3] = arrayOfPoint3f1[b5--];
/* 160 */       arrayOfVector3f2[b3++] = vector3f4;
/*     */     } 
/*     */     
/* 163 */     arrayOfPoint3f3[b3] = arrayOfPoint3f2[0];
/* 164 */     arrayOfVector3f2[b3++] = vector3f5;
/*     */     
/* 166 */     b4 = 1;
/* 167 */     b5 = b1 - 1;
/* 168 */     while (b4 <= b5) {
/* 169 */       arrayOfPoint3f3[b3] = arrayOfPoint3f2[b5--];
/* 170 */       arrayOfVector3f2[b3++] = vector3f5;
/* 171 */       if (b4 > b5)
/* 172 */         break;  arrayOfPoint3f3[b3] = arrayOfPoint3f2[b4++];
/* 173 */       arrayOfVector3f2[b3++] = vector3f5;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 178 */     Material material = new Material();
/* 179 */     material.setCapability(0);
/* 180 */     material.setCapability(1);
/*     */     
/* 182 */     if (paramBoolean) {
/* 183 */       b6 = 3;
/*     */       
/* 185 */       material.setLightingEnable(true);
/*     */     } else {
/*     */       
/* 188 */       b6 = 1;
/* 189 */       material.setLightingEnable(false);
/*     */     } 
/*     */     
/* 192 */     int[] arrayOfInt = new int[3];
/* 193 */     arrayOfInt[0] = 2 + 2 * b1;
/* 194 */     arrayOfInt[1] = b1;
/* 195 */     arrayOfInt[2] = b1;
/*     */     
/* 197 */     TriangleStripArray triangleStripArray = new TriangleStripArray(b2, b6, arrayOfInt);
/*     */ 
/*     */ 
/*     */     
/* 201 */     triangleStripArray.setCoordinates(0, arrayOfPoint3f3);
/* 202 */     if (paramBoolean) {
/* 203 */       triangleStripArray.setNormals(0, arrayOfVector3f2);
/*     */     }
/* 205 */     Appearance appearance = new Appearance();
/* 206 */     appearance.setMaterial(material);
/* 207 */     appearance.setCapability(0);
/* 208 */     appearance.setCapability(1);
/*     */     
/* 210 */     TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
/* 211 */     transparencyAttributes.setCapability(0);
/* 212 */     transparencyAttributes.setCapability(1);
/* 213 */     transparencyAttributes.setCapability(2);
/* 214 */     transparencyAttributes.setCapability(3);
/* 215 */     transparencyAttributes.setCapability(4);
/*     */     
/* 217 */     transparencyAttributes.setCapability(5);
/*     */ 
/*     */     
/* 220 */     appearance.setTransparencyAttributes(transparencyAttributes);
/* 221 */     appearance.setCapability(10);
/* 222 */     appearance.setCapability(11);
/*     */     
/* 224 */     setGeometry(triangleStripArray);
/* 225 */     setAppearance(appearance);
/*     */     
/* 227 */     setCapability(14);
/* 228 */     setCapability(15);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\sensor\SensorBeamEcho.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */