/*     */ package com.sun.j3d.utils.behaviors.sensor;
/*     */ 
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransparencyAttributes;
/*     */ import javax.media.j3d.TriangleArray;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SensorGnomonEcho
/*     */   extends Shape3D
/*     */ {
/*     */   public SensorGnomonEcho(Transform3D paramTransform3D, double paramDouble1, double paramDouble2, boolean paramBoolean) {
/*  85 */     boolean bool1 = false;
/*  86 */     boolean bool2 = true;
/*  87 */     byte b1 = 2;
/*  88 */     byte b2 = 3;
/*  89 */     byte b3 = 4;
/*  90 */     byte b4 = 5;
/*  91 */     Point3f[] arrayOfPoint3f1 = new Point3f[6];
/*  92 */     float f1 = (float)paramDouble2;
/*     */     
/*  94 */     arrayOfPoint3f1[bool1] = new Point3f(0.0F, 0.0F, f1);
/*  95 */     arrayOfPoint3f1[bool2] = new Point3f(0.0F, 0.0F, -f1);
/*  96 */     arrayOfPoint3f1[b1] = new Point3f(-f1, 0.0F, 0.0F);
/*  97 */     arrayOfPoint3f1[b2] = new Point3f(f1, 0.0F, 0.0F);
/*  98 */     arrayOfPoint3f1[b3] = new Point3f(0.0F, f1, 0.0F);
/*  99 */     arrayOfPoint3f1[b4] = new Point3f(0.0F, -f1, 0.0F);
/*     */     
/* 101 */     if (paramTransform3D != null)
/* 102 */       for (byte b = bool1; b <= b4; b++) {
/* 103 */         paramTransform3D.transform(arrayOfPoint3f1[b]);
/*     */       } 
/* 105 */     float f2 = (float)paramDouble1 / 2.0F;
/* 106 */     Point3f[][] arrayOfPoint3f = new Point3f[6][4];
/*     */     
/* 108 */     arrayOfPoint3f[bool1][0] = new Point3f(-f2, -f2, f2);
/* 109 */     arrayOfPoint3f[bool1][1] = new Point3f(f2, -f2, f2);
/* 110 */     arrayOfPoint3f[bool1][2] = new Point3f(f2, f2, f2);
/* 111 */     arrayOfPoint3f[bool1][3] = new Point3f(-f2, f2, f2);
/*     */     
/* 113 */     arrayOfPoint3f[bool2][0] = new Point3f(f2, -f2, -f2);
/* 114 */     arrayOfPoint3f[bool2][1] = new Point3f(-f2, -f2, -f2);
/* 115 */     arrayOfPoint3f[bool2][2] = new Point3f(-f2, f2, -f2);
/* 116 */     arrayOfPoint3f[bool2][3] = new Point3f(f2, f2, -f2);
/*     */     
/* 118 */     if (paramTransform3D != null)
/* 119 */       for (byte b = bool1; b <= bool2; b++) {
/* 120 */         for (byte b7 = 0; b7 < 4; b7++)
/* 121 */           paramTransform3D.transform(arrayOfPoint3f[b][b7]); 
/*     */       }  
/* 123 */     arrayOfPoint3f[b1][0] = arrayOfPoint3f[bool2][1];
/* 124 */     arrayOfPoint3f[b1][1] = arrayOfPoint3f[bool1][0];
/* 125 */     arrayOfPoint3f[b1][2] = arrayOfPoint3f[bool1][3];
/* 126 */     arrayOfPoint3f[b1][3] = arrayOfPoint3f[bool2][2];
/*     */     
/* 128 */     arrayOfPoint3f[b2][0] = arrayOfPoint3f[bool1][1];
/* 129 */     arrayOfPoint3f[b2][1] = arrayOfPoint3f[bool2][0];
/* 130 */     arrayOfPoint3f[b2][2] = arrayOfPoint3f[bool2][3];
/* 131 */     arrayOfPoint3f[b2][3] = arrayOfPoint3f[bool1][2];
/*     */     
/* 133 */     arrayOfPoint3f[b3][0] = arrayOfPoint3f[bool1][3];
/* 134 */     arrayOfPoint3f[b3][1] = arrayOfPoint3f[bool1][2];
/* 135 */     arrayOfPoint3f[b3][2] = arrayOfPoint3f[bool2][3];
/* 136 */     arrayOfPoint3f[b3][3] = arrayOfPoint3f[bool2][2];
/*     */     
/* 138 */     arrayOfPoint3f[b4][0] = arrayOfPoint3f[bool2][1];
/* 139 */     arrayOfPoint3f[b4][1] = arrayOfPoint3f[bool2][0];
/* 140 */     arrayOfPoint3f[b4][2] = arrayOfPoint3f[bool1][1];
/* 141 */     arrayOfPoint3f[b4][3] = arrayOfPoint3f[bool1][0];
/*     */     
/* 143 */     byte b5 = 0;
/* 144 */     Point3f[] arrayOfPoint3f2 = new Point3f[72];
/*     */     byte b6;
/* 146 */     for (b6 = 0; b6 < 6; b6++) {
/* 147 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][0];
/* 148 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][1];
/* 149 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f1[b6];
/* 150 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][1];
/* 151 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][2];
/* 152 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f1[b6];
/* 153 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][2];
/* 154 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][3];
/* 155 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f1[b6];
/* 156 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][3];
/* 157 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f[b6][0];
/* 158 */       arrayOfPoint3f2[b5++] = arrayOfPoint3f1[b6];
/*     */     } 
/*     */ 
/*     */     
/* 162 */     Material material = new Material();
/* 163 */     material.setCapability(0);
/* 164 */     material.setCapability(1);
/*     */     
/* 166 */     if (paramBoolean) {
/* 167 */       b6 = 3;
/*     */       
/* 169 */       material.setLightingEnable(true);
/*     */     } else {
/*     */       
/* 172 */       b6 = 1;
/* 173 */       material.setLightingEnable(false);
/*     */     } 
/*     */     
/* 176 */     TriangleArray triangleArray = new TriangleArray(72, b6);
/* 177 */     triangleArray.setCoordinates(0, arrayOfPoint3f2);
/*     */     
/* 179 */     if (paramBoolean) {
/* 180 */       Vector3f vector3f1 = new Vector3f();
/* 181 */       Vector3f vector3f2 = new Vector3f();
/* 182 */       Vector3f[] arrayOfVector3f = new Vector3f[72];
/*     */       
/* 184 */       for (boolean bool = false; bool < 72; bool += true) {
/* 185 */         vector3f1.sub(arrayOfPoint3f2[bool + true], arrayOfPoint3f2[bool]);
/* 186 */         vector3f2.sub(arrayOfPoint3f2[bool + 2], arrayOfPoint3f2[bool]);
/*     */         
/* 188 */         Vector3f vector3f = new Vector3f();
/* 189 */         vector3f.cross(vector3f1, vector3f2);
/* 190 */         vector3f.normalize();
/*     */         
/* 192 */         arrayOfVector3f[bool] = vector3f;
/* 193 */         arrayOfVector3f[bool + true] = vector3f;
/* 194 */         arrayOfVector3f[bool + 2] = vector3f;
/*     */       } 
/* 196 */       triangleArray.setNormals(0, arrayOfVector3f);
/*     */     } 
/*     */     
/* 199 */     Appearance appearance = new Appearance();
/* 200 */     appearance.setMaterial(material);
/* 201 */     appearance.setCapability(0);
/* 202 */     appearance.setCapability(1);
/*     */     
/* 204 */     TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
/* 205 */     transparencyAttributes.setCapability(0);
/* 206 */     transparencyAttributes.setCapability(1);
/* 207 */     transparencyAttributes.setCapability(2);
/* 208 */     transparencyAttributes.setCapability(3);
/* 209 */     triangleArray.setCapability(4);
/*     */     
/* 211 */     triangleArray.setCapability(5);
/*     */ 
/*     */     
/* 214 */     appearance.setTransparencyAttributes(transparencyAttributes);
/* 215 */     appearance.setCapability(10);
/* 216 */     appearance.setCapability(11);
/*     */     
/* 218 */     setGeometry(triangleArray);
/* 219 */     setAppearance(appearance);
/*     */     
/* 221 */     setCapability(14);
/* 222 */     setCapability(15);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\sensor\SensorGnomonEcho.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */