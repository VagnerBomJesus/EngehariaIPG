/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import com.sun.j3d.utils.image.TextureLoader;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
/*     */ import javax.media.j3d.Texture;
/*     */ import javax.media.j3d.Texture2D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwoTexture
/*     */   extends ParserObject
/*     */ {
/*     */   LWOBFileReader theReader;
/*  85 */   int red = 255; int green = 255; int blue = 255; Color3f color;
/*     */   Color3f diffuseColor;
/*  87 */   Image theImage = null; Color3f specularColor; Color3f emissiveColor;
/*  88 */   String imageFile = null;
/*  89 */   Vector3f textureSize = new Vector3f(1.0F, 1.0F, 1.0F);
/*  90 */   Vector3f textureCenter = new Vector3f(0.0F, 0.0F, 0.0F);
/*     */   int textureAxis;
/*  92 */   int flags = 0;
/*     */   String type;
/*     */   String mappingType;
/*  95 */   String nextToken = null;
/*  96 */   static Hashtable imageTable = new Hashtable();
/*  97 */   static Hashtable textureTable = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LwoTexture(LWOBFileReader paramLWOBFileReader, int paramInt1, String paramString, int paramInt2) throws FileNotFoundException {
/* 105 */     super(paramInt2);
/* 106 */     debugOutputLn(1, "Constructor");
/* 107 */     this.theReader = paramLWOBFileReader;
/* 108 */     this.type = paramString;
/* 109 */     readTexture(paramInt1);
/*     */   }
/*     */ 
/*     */   
/* 113 */   String getNextToken() { return this.nextToken; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isHandled() {
/* 122 */     if ((this.type.equals("CTEX") || this.type.equals("DTEX")) && this.theImage != null)
/*     */     {
/*     */       
/* 125 */       return true; } 
/* 126 */     debugOutputLn(8, "failed isHandled(), type, theImage = " + this.type + ", " + this.theImage);
/*     */     
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Texture getTexture() {
/* 137 */     debugOutputLn(1, "getTexture()");
/* 138 */     if (this.theImage == null)
/* 139 */       return null; 
/* 140 */     Texture2D texture2D = (Texture2D)textureTable.get(this.theImage);
/* 141 */     if (texture2D == null) {
/* 142 */       ImageScaler imageScaler = new ImageScaler((BufferedImage)this.theImage);
/* 143 */       BufferedImage bufferedImage = (BufferedImage)imageScaler.getScaledImage();
/* 144 */       TextureLoader textureLoader = new TextureLoader(bufferedImage);
/* 145 */       texture2D = (Texture2D)textureLoader.getTexture();
/* 146 */       textureTable.put(this.theImage, texture2D);
/*     */     } 
/*     */     
/* 149 */     return texture2D;
/*     */   }
/*     */ 
/*     */   
/* 153 */   String getType() { return this.type; }
/*     */ 
/*     */ 
/*     */   
/* 157 */   Color3f getColor() { return this.color; }
/*     */ 
/*     */ 
/*     */   
/* 161 */   Image getImage() { return this.theImage; }
/*     */ 
/*     */ 
/*     */   
/* 165 */   Vector3f getTextureSize() { return this.textureSize; }
/*     */ 
/*     */ 
/*     */   
/* 169 */   int getTextureAxis() { return this.textureAxis; }
/*     */ 
/*     */ 
/*     */   
/* 173 */   Vector3f getTextureCenter() { return this.textureCenter; }
/*     */ 
/*     */ 
/*     */   
/* 177 */   String getMappingType() { return this.mappingType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void readTexture(int paramInt) throws FileNotFoundException, ParsingErrorException {
/* 189 */     debugOutputLn(1, "readTexture()");
/*     */     
/* 191 */     int i = this.theReader.getMarker() + paramInt;
/* 192 */     this.mappingType = this.theReader.getString();
/* 193 */     debugOutputLn(2, "mappingType = " + this.mappingType);
/* 194 */     String str = this.theReader.getToken();
/* 195 */     while (str != null && this.theReader.getMarker() < i) {
/*     */       
/* 197 */       debugOutputLn(2, "  tokenString = " + str);
/* 198 */       debugOutputLn(2, "  marker, stop = " + this.theReader.getMarker() + ", " + i);
/*     */       
/* 200 */       if (str.endsWith("TEX") || !str.startsWith("T") || str.equals("TRAN")) {
/*     */         
/* 202 */         this.nextToken = str;
/*     */         
/*     */         return;
/*     */       } 
/* 206 */       int j = this.theReader.getShortInt();
/* 207 */       debugOutputLn(2, "  fl = " + j);
/*     */       
/* 209 */       if (str.equals("TFLG")) {
/* 210 */         debugOutputLn(64, "Not yet handling: " + str);
/* 211 */         this.flags = this.theReader.getShortInt();
/* 212 */         this.textureAxis = this.flags & 0x7;
/* 213 */         debugOutputLn(64, "val = " + this.flags);
/*     */       }
/* 215 */       else if (str.equals("TCLR")) {
/* 216 */         debugOutputLn(64, "Not yet handling: " + str);
/*     */         try {
/* 218 */           this.red = this.theReader.read();
/* 219 */           this.green = this.theReader.read();
/* 220 */           this.blue = this.theReader.read();
/* 221 */           this.theReader.read();
/*     */         }
/* 223 */         catch (IOException iOException) {
/* 224 */           throw new ParsingErrorException(iOException.getMessage());
/*     */         } 
/* 226 */         debugOutputLn(64, "val = " + this.red + ", " + this.green + ", " + this.blue);
/*     */       
/*     */       }
/* 229 */       else if (str.equals("TIMG")) {
/* 230 */         debugOutputLn(64, "Not yet handling: " + str);
/* 231 */         this.imageFile = this.theReader.getString();
/* 232 */         debugOutputLn(2, "imageFile = " + this.imageFile);
/* 233 */         if (this.imageFile.indexOf("none") == -1 && (
/* 234 */           this.theImage = (Image)imageTable.get(this.imageFile)) == null) {
/*     */           
/*     */           try {
/* 237 */             TargaReader targaReader = new TargaReader(this.imageFile, this.debugPrinter.getValidOutput());
/*     */ 
/*     */             
/* 240 */             this.theImage = targaReader.getImage();
/* 241 */             imageTable.put(this.imageFile, this.theImage);
/*     */           }
/* 243 */           catch (FileNotFoundException fileNotFoundException) {
/*     */             
/* 245 */             debugOutputLn(64, "Image File skipped: " + this.imageFile);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 250 */         debugOutputLn(64, "val = __" + this.imageFile + "__");
/*     */       }
/* 252 */       else if (str.equals("TWRP")) {
/* 253 */         debugOutputLn(64, "Not yet handling: " + str);
/* 254 */         int k = this.theReader.getShortInt();
/* 255 */         int m = this.theReader.getShortInt();
/* 256 */         debugOutputLn(64, "val = " + k + ", " + m);
/*     */       
/*     */       }
/* 259 */       else if (str.equals("TCTR")) {
/* 260 */         debugOutputLn(64, "Not yet handling: " + str);
/* 261 */         this.textureCenter.x = this.theReader.getFloat();
/* 262 */         this.textureCenter.y = this.theReader.getFloat();
/* 263 */         this.textureCenter.z = this.theReader.getFloat();
/* 264 */         debugOutputLn(64, "val = " + this.textureCenter);
/*     */       }
/* 266 */       else if (str.equals("TSIZ")) {
/* 267 */         debugOutputLn(64, "Not yet handling: " + str);
/* 268 */         this.textureSize.x = this.theReader.getFloat();
/* 269 */         this.textureSize.y = this.theReader.getFloat();
/* 270 */         this.textureSize.z = this.theReader.getFloat();
/* 271 */         debugOutputLn(64, "val = " + this.textureSize);
/*     */       } else {
/*     */         
/* 274 */         debugOutputLn(64, "unrecognized token: " + str);
/*     */         
/* 276 */         this.theReader.skipLength(j);
/*     */       } 
/* 278 */       if (this.theReader.getMarker() < i)
/* 279 */         str = this.theReader.getToken(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\lw3d\LwoTexture.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */