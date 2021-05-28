/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.awt.Image;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ import javax.vecmath.Color3f;
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
/*     */ class LwoSurface
/*     */   extends ParserObject
/*     */ {
/*     */   LWOBFileReader theReader;
/*  72 */   int red = 255; int green = 255; int blue = 255;
/*  73 */   float diffuse = 0.0F, specular = 0.0F, transparency = 0.0F, luminosity = 0.0F;
/*  74 */   float creaseAngle = 0.0F;
/*  75 */   int gloss = 128;
/*     */   Color3f color;
/*     */   Color3f diffuseColor;
/*  78 */   Image theImage = null; Color3f specularColor; Color3f emissiveColor; float shininess;
/*  79 */   Vector3f textureCenter = null; Vector3f textureSize = null;
/*     */   int textureAxis;
/*     */   String surfName;
/*  82 */   Vector textureList = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LwoSurface(LWOBFileReader paramLWOBFileReader, int paramInt1, int paramInt2) throws FileNotFoundException {
/*  91 */     super(paramInt2);
/*  92 */     debugOutputLn(1, "LwoSurface()");
/*  93 */     this.theReader = paramLWOBFileReader;
/*  94 */     getSurf(paramInt1);
/*  95 */     setJ3dColors();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setJ3dColors() {
/* 102 */     this.color = new Color3f(this.red / 255.0F, this.green / 255.0F, this.blue / 255.0F);
/*     */ 
/*     */     
/* 105 */     this.diffuseColor = new Color3f(this.diffuse * this.color.x, this.diffuse * this.color.y, this.diffuse * this.color.z);
/*     */ 
/*     */     
/* 108 */     this.specularColor = new Color3f(this.specular * this.color.x, this.specular * this.color.y, this.specular * this.color.z);
/*     */ 
/*     */     
/* 111 */     this.emissiveColor = new Color3f(this.luminosity * this.color.x, this.luminosity * this.color.y, this.luminosity * this.color.z);
/*     */ 
/*     */     
/* 114 */     this.shininess = (float)(128.0D * this.gloss / 1024.0D);
/*     */   }
/*     */ 
/*     */   
/* 118 */   Color3f getColor() { return this.color; }
/*     */ 
/*     */ 
/*     */   
/* 122 */   Color3f getDiffuseColor() { return this.diffuseColor; }
/*     */ 
/*     */ 
/*     */   
/* 126 */   Color3f getSpecularColor() { return this.specularColor; }
/*     */ 
/*     */ 
/*     */   
/* 130 */   Color3f getEmissiveColor() { return this.emissiveColor; }
/*     */ 
/*     */ 
/*     */   
/* 134 */   float getShininess() { return this.shininess; }
/*     */ 
/*     */ 
/*     */   
/* 138 */   float getCreaseAngle() { return this.creaseAngle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LwoTexture getTexture() {
/* 148 */     debugOutputLn(1, "getTexture()");
/*     */     try {
/* 150 */       if (this.textureList.isEmpty()) {
/* 151 */         return null;
/*     */       }
/*     */       
/* 154 */       return (LwoTexture)this.textureList.elementAt(0);
/*     */     
/*     */     }
/* 157 */     catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
/* 158 */       debugOutputLn(16, "getTexture(), exception returning first element: " + arrayIndexOutOfBoundsException);
/*     */       
/* 160 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 165 */   String getSurfName() { return this.surfName; }
/*     */ 
/*     */ 
/*     */   
/* 169 */   float getTransparency() { return this.transparency; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getSurf(int paramInt) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
/* 179 */     debugOutputLn(1, "getSurf()");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     boolean bool1 = false;
/* 185 */     boolean bool2 = false;
/* 186 */     boolean bool3 = false;
/* 187 */     boolean bool4 = false;
/* 188 */     int i = this.theReader.getMarker() + paramInt;
/* 189 */     this.surfName = this.theReader.getString();
/* 190 */     String str = this.theReader.getToken();
/*     */     
/* 192 */     while (str != null && this.theReader.getMarker() < i) {
/* 193 */       debugOutputLn(2, "  tokenString = " + str);
/* 194 */       debugOutputLn(2, "  marker, stop = " + this.theReader.getMarker() + ", " + i);
/*     */       
/* 196 */       String str1 = null;
/* 197 */       int j = this.theReader.getShortInt();
/* 198 */       debugOutputLn(2, "  fl = " + j);
/*     */       
/* 200 */       if (str.equals("COLR")) {
/* 201 */         debugOutputLn(8, "  COLR");
/*     */         try {
/* 203 */           this.red = this.theReader.read();
/* 204 */           this.green = this.theReader.read();
/* 205 */           this.blue = this.theReader.read();
/* 206 */           this.theReader.read();
/*     */         }
/* 208 */         catch (IOException iOException) {
/* 209 */           throw new ParsingErrorException(iOException.getMessage());
/*     */         } 
/* 211 */         if (j != 4) {
/* 212 */           throw new IncorrectFormatException(J3dUtilsI18N.getString("LwoSurface0"));
/*     */         }
/*     */       }
/* 215 */       else if (str.equals("FLAG")) {
/* 216 */         debugOutputLn(8, "  FLAG");
/* 217 */         this.theReader.skipLength(j);
/*     */       }
/* 219 */       else if (str.equals("VLUM")) {
/* 220 */         debugOutputLn(8, "  VLUM");
/* 221 */         this.luminosity = this.theReader.getFloat();
/* 222 */         bool1 = true;
/*     */       }
/* 224 */       else if (str.equals("LUMI")) {
/* 225 */         debugOutputLn(8, "  LUMI");
/* 226 */         if (bool1) {
/* 227 */           this.theReader.skipLength(j);
/*     */         } else {
/* 229 */           this.luminosity = this.theReader.getShortInt() / 255.0F;
/*     */         } 
/* 231 */       } else if (str.equals("VDIF")) {
/* 232 */         debugOutputLn(8, "  VDIF");
/* 233 */         if (j != 4)
/* 234 */           throw new IncorrectFormatException("VDIF problem"); 
/* 235 */         this.diffuse = this.theReader.getFloat();
/* 236 */         bool3 = true;
/* 237 */         debugOutputLn(2, "diff = " + this.diffuse);
/*     */       }
/* 239 */       else if (str.equals("DIFF")) {
/* 240 */         debugOutputLn(8, "  DIFF");
/* 241 */         if (bool3) {
/* 242 */           this.theReader.skipLength(j);
/*     */         } else {
/* 244 */           this.diffuse = this.theReader.getShortInt() / 255.0F;
/*     */         } 
/* 246 */       } else if (str.equals("VTRN")) {
/* 247 */         debugOutputLn(8, "  VTRN");
/* 248 */         this.transparency = this.theReader.getFloat();
/* 249 */         bool2 = true;
/*     */       }
/* 251 */       else if (str.equals("TRAN")) {
/* 252 */         debugOutputLn(8, "  TRAN");
/* 253 */         if (bool2) {
/* 254 */           this.theReader.skipLength(j);
/*     */         } else {
/* 256 */           this.transparency = this.theReader.getShortInt() / 255.0F;
/*     */         } 
/* 258 */       } else if (str.equals("VSPC")) {
/* 259 */         debugOutputLn(8, "  VSPC");
/* 260 */         this.specular = this.theReader.getFloat();
/* 261 */         bool4 = true;
/* 262 */         debugOutputLn(2, "spec = " + this.specular);
/*     */       }
/* 264 */       else if (str.equals("SPEC")) {
/* 265 */         debugOutputLn(8, "  SPEC");
/* 266 */         if (bool4) {
/* 267 */           this.theReader.skipLength(j);
/*     */         }
/* 269 */         else if (j == 4) {
/* 270 */           this.specular = this.theReader.getInt() / 255.0F;
/*     */         } else {
/* 272 */           this.specular = this.theReader.getShortInt() / 255.0F;
/*     */         }
/*     */       
/* 275 */       } else if (str.equals("GLOS")) {
/* 276 */         debugOutputLn(8, "  GLOS");
/* 277 */         if (j == 4) {
/* 278 */           this.gloss = this.theReader.getInt();
/*     */         } else {
/* 280 */           this.gloss = this.theReader.getShortInt();
/*     */         } 
/* 282 */       } else if (str.equals("SMAN")) {
/* 283 */         debugOutputLn(8, "  SMAN");
/* 284 */         this.creaseAngle = this.theReader.getFloat();
/*     */       }
/* 286 */       else if (str.endsWith("TEX")) {
/*     */ 
/*     */         
/* 289 */         LwoTexture lwoTexture = new LwoTexture(this.theReader, i - this.theReader.getMarker(), str, this.debugPrinter.getValidOutput());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 294 */         str1 = lwoTexture.getNextToken();
/* 295 */         if (lwoTexture.isHandled())
/* 296 */           this.textureList.addElement(lwoTexture); 
/* 297 */         debugOutputLn(64, "val = " + str);
/*     */       } else {
/*     */         
/* 300 */         debugOutputLn(64, "unrecognized token: " + str);
/*     */         
/* 302 */         this.theReader.skipLength(j);
/*     */       } 
/* 304 */       if (this.theReader.getMarker() < i) {
/* 305 */         if (str1 == null) {
/* 306 */           str = this.theReader.getToken(); continue;
/*     */         } 
/* 308 */         str = str1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\LwoSurface.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */