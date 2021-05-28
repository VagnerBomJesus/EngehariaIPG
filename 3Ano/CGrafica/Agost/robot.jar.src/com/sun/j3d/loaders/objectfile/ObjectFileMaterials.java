/*     */ package com.sun.j3d.loaders.objectfile;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import com.sun.j3d.utils.image.TextureLoader;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.GeometryArray;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.TexCoordGeneration;
/*     */ import javax.media.j3d.Texture2D;
/*     */ import javax.media.j3d.TransparencyAttributes;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ObjectFileMaterials
/*     */   implements ImageObserver
/*     */ {
/*     */   private static final int DEBUG = 0;
/*  88 */   private String curName = null;
/*  89 */   private ObjectFileMaterial cur = null;
/*     */   
/*     */   private HashMap materials;
/*     */   
/*     */   private String basePath;
/*     */   
/*     */   private boolean fromUrl;
/*     */   
/*     */   private class ObjectFileMaterial
/*     */   {
/*     */     public Color3f Ka;
/*     */     public Color3f Kd;
/*     */     public Color3f Ks;
/*     */     public int illum;
/*     */     public float Ns;
/*     */     public Texture2D t;
/*     */     public boolean transparent;
/*     */     public float transparencyLevel;
/*     */     
/*     */     private ObjectFileMaterial() {}
/*     */   }
/*     */   
/*     */   void assignMaterial(String paramString, Shape3D paramShape3D) {
/* 112 */     ObjectFileMaterial objectFileMaterial = null;
/*     */ 
/*     */ 
/*     */     
/* 116 */     Material material = new Material();
/* 117 */     objectFileMaterial = (ObjectFileMaterial)this.materials.get(paramString);
/* 118 */     Appearance appearance = new Appearance();
/*     */     
/* 120 */     if (objectFileMaterial != null) {
/*     */       
/* 122 */       if (objectFileMaterial.Ka != null) material.setAmbientColor(objectFileMaterial.Ka); 
/* 123 */       if (objectFileMaterial.Kd != null) material.setDiffuseColor(objectFileMaterial.Kd);
/*     */ 
/*     */       
/* 126 */       if (objectFileMaterial.Ks != null && objectFileMaterial.illum != 1) { material.setSpecularColor(objectFileMaterial.Ks); }
/* 127 */       else if (objectFileMaterial.illum == 1) { material.setSpecularColor(0.0F, 0.0F, 0.0F); }
/*     */       
/* 129 */       if (objectFileMaterial.illum >= 1) { material.setLightingEnable(true); }
/* 130 */       else if (objectFileMaterial.illum == 0) { material.setLightingEnable(false); }
/*     */       
/* 132 */       if (objectFileMaterial.Ns != -1.0F) material.setShininess(objectFileMaterial.Ns);
/*     */       
/* 134 */       if (objectFileMaterial.t != null) {
/* 135 */         appearance.setTexture(objectFileMaterial.t);
/*     */         
/* 137 */         if ((((GeometryArray)paramShape3D.getGeometry()).getVertexFormat() & 0x20) == 0) {
/*     */           
/* 139 */           TexCoordGeneration texCoordGeneration = new TexCoordGeneration();
/* 140 */           appearance.setTexCoordGeneration(texCoordGeneration);
/*     */         } 
/*     */       } 
/*     */       
/* 144 */       if (objectFileMaterial.transparent) {
/* 145 */         appearance.setTransparencyAttributes(new TransparencyAttributes(1, objectFileMaterial.transparencyLevel));
/*     */       }
/*     */     } 
/*     */     
/* 149 */     appearance.setMaterial(material);
/*     */     
/* 151 */     paramShape3D.setAppearance(appearance);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readName(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 156 */     paramObjectFileParser.getToken();
/*     */     
/* 158 */     if (paramObjectFileParser.ttype == -3) {
/*     */       
/* 160 */       if (this.curName != null) this.materials.put(this.curName, this.cur); 
/* 161 */       this.curName = new String(paramObjectFileParser.sval);
/* 162 */       this.cur = new ObjectFileMaterial(null);
/*     */     } 
/* 164 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readAmbient(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 169 */     Color3f color3f = new Color3f();
/*     */     
/* 171 */     paramObjectFileParser.getNumber();
/* 172 */     color3f.x = (float)paramObjectFileParser.nval;
/* 173 */     paramObjectFileParser.getNumber();
/* 174 */     color3f.y = (float)paramObjectFileParser.nval;
/* 175 */     paramObjectFileParser.getNumber();
/* 176 */     color3f.z = (float)paramObjectFileParser.nval;
/*     */     
/* 178 */     this.cur.Ka = color3f;
/*     */     
/* 180 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readDiffuse(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 185 */     Color3f color3f = new Color3f();
/*     */     
/* 187 */     paramObjectFileParser.getNumber();
/* 188 */     color3f.x = (float)paramObjectFileParser.nval;
/* 189 */     paramObjectFileParser.getNumber();
/* 190 */     color3f.y = (float)paramObjectFileParser.nval;
/* 191 */     paramObjectFileParser.getNumber();
/* 192 */     color3f.z = (float)paramObjectFileParser.nval;
/*     */     
/* 194 */     this.cur.Kd = color3f;
/*     */     
/* 196 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readSpecular(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 201 */     Color3f color3f = new Color3f();
/*     */     
/* 203 */     paramObjectFileParser.getNumber();
/* 204 */     color3f.x = (float)paramObjectFileParser.nval;
/* 205 */     paramObjectFileParser.getNumber();
/* 206 */     color3f.y = (float)paramObjectFileParser.nval;
/* 207 */     paramObjectFileParser.getNumber();
/* 208 */     color3f.z = (float)paramObjectFileParser.nval;
/*     */     
/* 210 */     this.cur.Ks = color3f;
/*     */     
/* 212 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readIllum(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 218 */     paramObjectFileParser.getNumber();
/* 219 */     this.cur.illum = (int)paramObjectFileParser.nval;
/*     */     
/* 221 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readTransparency(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 226 */     paramObjectFileParser.getNumber();
/* 227 */     this.cur.transparencyLevel = (float)paramObjectFileParser.nval;
/* 228 */     if (this.cur.transparencyLevel < 1.0F) {
/* 229 */       this.cur.transparent = true;
/*     */     }
/* 231 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readShininess(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 237 */     paramObjectFileParser.getNumber();
/* 238 */     this.cur.Ns = (float)paramObjectFileParser.nval;
/* 239 */     if (this.cur.Ns < 1.0F) { this.cur.Ns = 1.0F; }
/* 240 */     else if (this.cur.Ns > 128.0F) { this.cur.Ns = 128.0F; }
/*     */     
/* 242 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readMapKd(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 248 */     paramObjectFileParser.lowerCaseMode(false);
/*     */ 
/*     */     
/* 251 */     String str = null;
/*     */     do {
/* 253 */       paramObjectFileParser.getToken();
/* 254 */       if (paramObjectFileParser.ttype != -3) continue;  str = paramObjectFileParser.sval;
/* 255 */     } while (paramObjectFileParser.ttype != 10);
/*     */     
/* 257 */     paramObjectFileParser.lowerCaseMode(true);
/*     */     
/* 259 */     if (str != null)
/*     */     {
/* 261 */       if (str.lastIndexOf('.') != -1) {
/*     */         
/*     */         try {
/* 264 */           String str1 = str.substring(str.lastIndexOf('.') + 1).toLowerCase();
/*     */ 
/*     */           
/* 267 */           TextureLoader textureLoader = null;
/*     */           
/* 269 */           if (str1.equals("int") || str1.equals("inta") || str1.equals("rgb") || str1.equals("rgba") || str1.equals("bw") || str1.equals("sgi")) {
/*     */             RgbFile rgbFile;
/*     */ 
/*     */             
/* 273 */             if (this.fromUrl) {
/* 274 */               rgbFile = new RgbFile((new URL(this.basePath + str)).openStream());
/*     */             } else {
/* 276 */               rgbFile = new RgbFile(new FileInputStream(this.basePath + str));
/*     */             } 
/* 278 */             BufferedImage bufferedImage = rgbFile.getImage();
/*     */             
/* 280 */             boolean bool1 = (str1.equals("int") || str1.equals("inta")) ? 1 : 0;
/* 281 */             boolean bool2 = (str1.equals("inta") || str1.equals("rgba")) ? 1 : 0;
/* 282 */             this.cur.transparent = bool2;
/*     */             
/* 284 */             String str2 = null;
/* 285 */             if (bool1 && bool2) { str2 = "LUM8_ALPHA8"; }
/* 286 */             else if (bool1) { str2 = "LUMINANCE"; }
/* 287 */             else if (bool2) { str2 = "RGBA"; }
/* 288 */             else { str2 = "RGB"; }
/*     */             
/* 290 */             textureLoader = new TextureLoader(bufferedImage, str2, 1);
/*     */           
/*     */           }
/* 293 */           else if (this.fromUrl) {
/* 294 */             textureLoader = new TextureLoader(new URL(this.basePath + str), "RGB", 1, null);
/*     */           } else {
/*     */             
/* 297 */             textureLoader = new TextureLoader(this.basePath + str, "RGB", 1, null);
/*     */           } 
/*     */ 
/*     */           
/* 301 */           Texture2D texture2D = (Texture2D)textureLoader.getTexture();
/* 302 */           if (texture2D != null) this.cur.t = texture2D;
/*     */         
/* 304 */         } catch (FileNotFoundException fileNotFoundException) {
/*     */ 
/*     */         
/* 307 */         } catch (MalformedURLException malformedURLException) {
/*     */ 
/*     */         
/* 310 */         } catch (IOException iOException) {}
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 315 */     paramObjectFileParser.skipToNextLine();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFile(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/* 321 */     paramObjectFileParser.getToken();
/* 322 */     while (paramObjectFileParser.ttype != -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 333 */       if (paramObjectFileParser.ttype == -3) {
/* 334 */         if (paramObjectFileParser.sval.equals("newmtl")) {
/* 335 */           readName(paramObjectFileParser);
/* 336 */         } else if (paramObjectFileParser.sval.equals("ka")) {
/* 337 */           readAmbient(paramObjectFileParser);
/* 338 */         } else if (paramObjectFileParser.sval.equals("kd")) {
/* 339 */           readDiffuse(paramObjectFileParser);
/* 340 */         } else if (paramObjectFileParser.sval.equals("ks")) {
/* 341 */           readSpecular(paramObjectFileParser);
/* 342 */         } else if (paramObjectFileParser.sval.equals("illum")) {
/* 343 */           readIllum(paramObjectFileParser);
/* 344 */         } else if (paramObjectFileParser.sval.equals("d")) {
/* 345 */           readTransparency(paramObjectFileParser);
/* 346 */         } else if (paramObjectFileParser.sval.equals("ns")) {
/* 347 */           readShininess(paramObjectFileParser);
/* 348 */         } else if (paramObjectFileParser.sval.equals("tf")) {
/* 349 */           paramObjectFileParser.skipToNextLine();
/* 350 */         } else if (paramObjectFileParser.sval.equals("sharpness")) {
/* 351 */           paramObjectFileParser.skipToNextLine();
/* 352 */         } else if (paramObjectFileParser.sval.equals("map_kd")) {
/* 353 */           readMapKd(paramObjectFileParser);
/* 354 */         } else if (paramObjectFileParser.sval.equals("map_ka")) {
/* 355 */           paramObjectFileParser.skipToNextLine();
/* 356 */         } else if (paramObjectFileParser.sval.equals("map_ks")) {
/* 357 */           paramObjectFileParser.skipToNextLine();
/* 358 */         } else if (paramObjectFileParser.sval.equals("map_ns")) {
/* 359 */           paramObjectFileParser.skipToNextLine();
/* 360 */         } else if (paramObjectFileParser.sval.equals("bump")) {
/* 361 */           paramObjectFileParser.skipToNextLine();
/*     */         } 
/*     */       }
/*     */       
/* 365 */       paramObjectFileParser.skipToNextLine();
/*     */ 
/*     */       
/* 368 */       paramObjectFileParser.getToken();
/*     */     } 
/* 370 */     if (this.curName != null) this.materials.put(this.curName, this.cur);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void readMaterialFile(boolean paramBoolean, String paramString1, String paramString2) throws ParsingErrorException {
/*     */     BufferedReader bufferedReader;
/* 379 */     this.basePath = paramString1;
/* 380 */     this.fromUrl = paramBoolean;
/*     */     
/*     */     try {
/* 383 */       if (paramBoolean) {
/* 384 */         bufferedReader = new InputStreamReader(new BufferedInputStream((new URL(paramString1 + paramString2)).openStream()));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 389 */         bufferedReader = new BufferedReader(new FileReader(paramString1 + paramString2));
/*     */       }
/*     */     
/* 392 */     } catch (IOException iOException) {
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 399 */     ObjectFileParser objectFileParser = new ObjectFileParser(bufferedReader);
/* 400 */     readFile(objectFileParser);
/*     */   }
/*     */ 
/*     */   
/*     */   ObjectFileMaterials() throws ParsingErrorException {
/* 405 */     StringReader stringReader = new StringReader("newmtl amber\nKa 0.0531 0.0531 0.0531\nKd 0.5755 0.2678 0.0000\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl amber_trans\nKa 0.0531 0.0531 0.0531\nKd 0.5755 0.2678 0.0000\nKs 0.3000 0.3000 0.3000\nillum 2\nd 0.8400\nNs 60.0000\n\nnewmtl charcoal\nKa 0.0082 0.0082 0.0082\nKd 0.0041 0.0041 0.0041\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl lavendar\nKa 0.1281 0.0857 0.2122\nKd 0.2187 0.0906 0.3469\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl navy_blue\nKa 0.0000 0.0000 0.0490\nKd 0.0000 0.0000 0.0531\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl pale_green\nKa 0.0444 0.0898 0.0447\nKd 0.0712 0.3796 0.0490\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl pale_pink\nKa 0.0898 0.0444 0.0444\nKd 0.6531 0.2053 0.4160\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl pale_yellow\nKa 0.3606 0.3755 0.0935\nKd 0.6898 0.6211 0.1999\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl peach\nKa 0.3143 0.1187 0.0167\nKd 0.6367 0.1829 0.0156\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl periwinkle\nKa 0.0000 0.0000 0.1184\nKd 0.0000 0.0396 0.8286\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl redwood\nKa 0.0204 0.0027 0.0000\nKd 0.2571 0.0330 0.0000\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl smoked_glass\nKa 0.0000 0.0000 0.0000\nKd 0.0041 0.0041 0.0041\nKs 0.1878 0.1878 0.1878\nillum 2\nd 0.9800\nNs 91.4700\n\nnewmtl aqua_filter\nKa 0.0000 0.0000 0.0000\nKd 0.3743 0.6694 0.5791\nKs 0.1878 0.1878 0.1878\nillum 2\nd 0.9800\nNs 91.4700\n\nnewmtl yellow_green\nKa 0.0000 0.0000 0.0000\nKd 0.1875 0.4082 0.0017\nKs 0.1878 0.1878 0.1878\nillum 2\nNs 91.4700\n\nnewmtl bluetint\nKa 0.1100 0.4238 0.5388\nKd 0.0468 0.7115 0.9551\nKs 0.3184 0.3184 0.3184\nillum 9\nd 0.5700\nNs 60.0000\nsharpness 60.0000\n\nnewmtl plasma\nKa 0.4082 0.0816 0.2129\nKd 1.0000 0.0776 0.4478\nKs 0.3000 0.3000 0.3000\nillum 9\nd 0.7500\nNs 60.0000\nsharpness 60.0000\n\nnewmtl emerald\nKa 0.0470 1.0000 0.0000\nKd 0.0470 1.0000 0.0000\nKs 0.2000 0.2000 0.2000\nillum 9\nd 0.7500\nNs 60.0000\nsharpness 60.0000\n\nnewmtl ruby\nKa 1.0000 0.0000 0.0000\nKd 1.0000 0.0000 0.0000\nKs 0.2000 0.2000 0.2000\nillum 9\nd 0.7500\nNs 60.0000\nsharpness 60.0000\n\nnewmtl sapphire\nKa 0.0235 0.0000 1.0000\nKd 0.0235 0.0000 1.0000\nKs 0.2000 0.2000 0.2000\nillum 9\nd 0.7500\nNs 60.0000\nsharpness 60.0000\n\nnewmtl white\nKa 0.4000 0.4000 0.4000\nKd 1.0000 1.0000 1.0000\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl red\nKa 0.4449 0.0000 0.0000\nKd 0.7714 0.0000 0.0000\nKs 0.8857 0.0000 0.0000\nillum 2\nNs 136.4300\n\nnewmtl blue_pure\nKa 0.0000 0.0000 0.5000\nKd 0.0000 0.0000 1.0000\nKs 0.0000 0.0000 0.5000\nillum 2\nNs 65.8900\n\nnewmtl lime\nKa 0.0000 0.5000 0.0000\nKd 0.0000 1.0000 0.0000\nKs 0.0000 0.5000 0.0000\nillum 2\nNs 65.8900\n\nnewmtl green\nKa 0.0000 0.2500 0.0000\nKd 0.0000 0.2500 0.0000\nKs 0.0000 0.2500 0.0000\nillum 2\nNs 65.8900\n\nnewmtl yellow\nKa 1.0000 0.6667 0.0000\nKd 1.0000 0.6667 0.0000\nKs 1.0000 0.6667 0.0000\nillum 2\nNs 65.8900\n\nnewmtl purple\nKa 0.5000 0.0000 1.0000\nKd 0.5000 0.0000 1.0000\nKs 0.5000 0.0000 1.0000\nillum 2\nNs 65.8900\n\nnewmtl orange\nKa 1.0000 0.1667 0.0000\nKd 1.0000 0.1667 0.0000\nKs 1.0000 0.1667 0.0000\nillum 2\nNs 65.8900\n\nnewmtl grey\nKa 0.5000 0.5000 0.5000\nKd 0.1837 0.1837 0.1837\nKs 0.5000 0.5000 0.5000\nillum 2\nNs 65.8900\n\nnewmtl rubber\nKa 0.0000 0.0000 0.0000\nKd 0.0100 0.0100 0.0100\nKs 0.1000 0.1000 0.1000\nillum 2\nNs 65.8900\n\nnewmtl flaqua\nKa 0.0000 0.4000 0.4000\nKd 0.0000 0.5000 0.5000\nillum 1\n\nnewmtl flblack\nKa 0.0000 0.0000 0.0000\nKd 0.0041 0.0041 0.0041\nillum 1\n\nnewmtl flblue_pure\nKa 0.0000 0.0000 0.5592\nKd 0.0000 0.0000 0.7102\nillum 1\n\nnewmtl flgrey\nKa 0.2163 0.2163 0.2163\nKd 0.5000 0.5000 0.5000\nillum 1\n\nnewmtl fllime\nKa 0.0000 0.3673 0.0000\nKd 0.0000 1.0000 0.0000\nillum 1\n\nnewmtl florange\nKa 0.6857 0.1143 0.0000\nKd 1.0000 0.1667 0.0000\nillum 1\n\nnewmtl flpurple\nKa 0.2368 0.0000 0.4735\nKd 0.3755 0.0000 0.7510\nillum 1\n\nnewmtl flred\nKa 0.4000 0.0000 0.0000\nKd 1.0000 0.0000 0.0000\nillum 1\n\nnewmtl flyellow\nKa 0.7388 0.4925 0.0000\nKd 1.0000 0.6667 0.0000\nillum 1\n\nnewmtl pink\nKa 0.9469 0.0078 0.2845\nKd 0.9878 0.1695 0.6702\nKs 0.7429 0.2972 0.2972\nillum 2\nNs 106.2000\n\nnewmtl flbrown\nKa 0.0571 0.0066 0.0011\nKd 0.1102 0.0120 0.0013\nillum 1\n\nnewmtl brown\nKa 0.1020 0.0185 0.0013\nKd 0.0857 0.0147 0.0000\nKs 0.1633 0.0240 0.0000\nillum 2\nNs 65.8900\n\nnewmtl glass\nKa 1.0000 1.0000 1.0000\nKd 0.4873 0.4919 0.5306\nKs 0.6406 0.6939 0.9020\nillum 2\nNs 200.0000\n\nnewmtl flesh\nKa 0.4612 0.3638 0.2993\nKd 0.5265 0.4127 0.3374\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl aqua\nKa 0.0000 0.4000 0.4000\nKd 0.0000 0.5000 0.5000\nKs 0.5673 0.5673 0.5673\nillum 2\nNs 60.0000\n\nnewmtl black\nKa 0.0000 0.0000 0.0000\nKd 0.0020 0.0020 0.0020\nKs 0.5184 0.5184 0.5184\nillum 2\nNs 157.3600\n\nnewmtl silver\nKa 0.9551 0.9551 0.9551\nKd 0.6163 0.6163 0.6163\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl dkblue_pure\nKa 0.0000 0.0000 0.0449\nKd 0.0000 0.0000 0.1347\nKs 0.0000 0.0000 0.5673\nillum 2\nNs 65.8900\n\nnewmtl fldkblue_pure\nKa 0.0000 0.0000 0.0449\nKd 0.0000 0.0000 0.1347\nillum 1\n\nnewmtl dkgreen\nKa 0.0000 0.0122 0.0000\nKd 0.0058 0.0245 0.0000\nKs 0.0000 0.0490 0.0000\nillum 2\nNs 60.0000\n\nnewmtl dkgrey\nKa 0.0490 0.0490 0.0490\nKd 0.0490 0.0490 0.0490\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl ltbrown\nKa 0.1306 0.0538 0.0250\nKd 0.2776 0.1143 0.0531\nKs 0.3000 0.1235 0.0574\nillum 2\nNs 60.0000\n\nnewmtl fldkgreen\nKa 0.0000 0.0122 0.0000\nKd 0.0058 0.0245 0.0000\nillum 1\n\nnewmtl flltbrown\nKa 0.1306 0.0538 0.0250\nKd 0.2776 0.1143 0.0531\nillum 1\n\nnewmtl tan\nKa 0.4000 0.3121 0.1202\nKd 0.6612 0.5221 0.2186\nKs 0.5020 0.4118 0.2152\nillum 2\nNs 60.0000\n\nnewmtl fltan\nKa 0.4000 0.3121 0.1202\nKd 0.6612 0.4567 0.1295\nillum 1\n\nnewmtl brzskin\nKa 0.4408 0.2694 0.1592\nKd 0.3796 0.2898 0.2122\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 25.0000\n\nnewmtl lips\nKa 0.4408 0.2694 0.1592\nKd 0.9265 0.2612 0.2898\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 25.0000\n\nnewmtl redorange\nKa 0.3918 0.0576 0.0000\nKd 0.7551 0.0185 0.0000\nKs 0.4694 0.3224 0.1667\nillum 2\nNs 132.5600\n\nnewmtl blutan\nKa 0.4408 0.2694 0.1592\nKd 0.0776 0.2571 0.2041\nKs 0.1467 0.1469 0.0965\nillum 2\nNs 25.0000\n\nnewmtl bluteal\nKa 0.0041 0.1123 0.1224\nKd 0.0776 0.2571 0.2041\nKs 0.1467 0.1469 0.0965\nillum 2\nNs 25.0000\n\nnewmtl pinktan\nKa 0.4408 0.2694 0.1592\nKd 0.6857 0.2571 0.2163\nKs 0.1467 0.1469 0.0965\nillum 2\nNs 25.0000\n\nnewmtl brnhair\nKa 0.0612 0.0174 0.0066\nKd 0.0898 0.0302 0.0110\nKs 0.1306 0.0819 0.0352\nillum 2\nNs 60.4700\n\nnewmtl blondhair\nKa 0.4449 0.2632 0.0509\nKd 0.5714 0.3283 0.0443\nKs 0.7755 0.4602 0.0918\nillum 2\nNs 4.6500\n\nnewmtl flblonde\nKa 0.4449 0.2632 0.0509\nKd 0.5714 0.3283 0.0443\nillum 1\n\nnewmtl yelloworng\nKa 0.5837 0.1715 0.0000\nKd 0.8857 0.2490 0.0000\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl bone\nKa 0.3061 0.1654 0.0650\nKd 0.9000 0.7626 0.4261\nKs 0.8939 0.7609 0.5509\nillum 2\nNs 200.0000\n\nnewmtl teeth\nKa 0.6408 0.5554 0.3845\nKd 0.9837 0.7959 0.4694\nillum 1\n\nnewmtl brass\nKa 0.2490 0.1102 0.0000\nKd 0.4776 0.1959 0.0000\nKs 0.5796 0.5796 0.5796\nillum 2\nNs 134.8800\n\nnewmtl dkred\nKa 0.0939 0.0000 0.0000\nKd 0.2286 0.0000 0.0000\nKs 0.2490 0.0000 0.0000\nillum 2\nNs 60.0000\n\nnewmtl taupe\nKa 0.1061 0.0709 0.0637\nKd 0.2041 0.1227 0.1058\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 84.5000\n\nnewmtl dkteal\nKa 0.0000 0.0245 0.0163\nKd 0.0000 0.0653 0.0449\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 55.0400\n\nnewmtl dkdkgrey\nKa 0.0000 0.0000 0.0000\nKd 0.0122 0.0122 0.0122\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl dkblue\nKa 0.0000 0.0029 0.0408\nKd 0.0000 0.0041 0.0571\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl gold\nKa 0.7224 0.1416 0.0000\nKd 1.0000 0.4898 0.0000\nKs 0.7184 0.3695 0.3695\nillum 2\nNs 123.2600\n\nnewmtl redbrick\nKa 0.1102 0.0067 0.0067\nKd 0.3306 0.0398 0.0081\nillum 1\n\nnewmtl flmustard\nKa 0.4245 0.2508 0.0000\nKd 0.8898 0.3531 0.0073\nillum 1\n\nnewmtl flpinegreen\nKa 0.0367 0.0612 0.0204\nKd 0.1061 0.2163 0.0857\nillum 1\n\nnewmtl fldkred\nKa 0.0939 0.0000 0.0000\nKd 0.2286 0.0082 0.0082\nillum 1\n\nnewmtl fldkgreen2\nKa 0.0025 0.0122 0.0014\nKd 0.0245 0.0694 0.0041\nillum 1\n\nnewmtl flmintgreen\nKa 0.0408 0.1429 0.0571\nKd 0.1306 0.2898 0.1673\nillum 1\n\nnewmtl olivegreen\nKa 0.0167 0.0245 0.0000\nKd 0.0250 0.0367 0.0000\nKs 0.2257 0.2776 0.1167\nillum 2\nNs 97.6700\n\nnewmtl skin\nKa 0.2286 0.0187 0.0187\nKd 0.1102 0.0328 0.0139\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 17.8300\n\nnewmtl redbrown\nKa 0.1469 0.0031 0.0000\nKd 0.2816 0.0060 0.0000\nKs 0.3714 0.3714 0.3714\nillum 2\nNs 141.0900\n\nnewmtl deepgreen\nKa 0.0000 0.0050 0.0000\nKd 0.0000 0.0204 0.0050\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 113.1800\n\nnewmtl flltolivegreen\nKa 0.0167 0.0245 0.0000\nKd 0.0393 0.0531 0.0100\nillum 1\n\nnewmtl jetflame\nKa 0.7714 0.0000 0.0000\nKd 0.9510 0.4939 0.0980\nKs 0.8531 0.5222 0.0000\nillum 2\nNs 132.5600\n\nnewmtl brownskn\nKa 0.0122 0.0041 0.0000\nKd 0.0204 0.0082 0.0000\nKs 0.0735 0.0508 0.0321\nillum 2\nNs 20.1600\n\nnewmtl greenskn\nKa 0.0816 0.0449 0.0000\nKd 0.0000 0.0735 0.0000\nKs 0.0490 0.1224 0.0898\nillum 3\nNs 46.5100\nsharpness 146.5100\n\nnewmtl ltgrey\nKa 0.5000 0.5000 0.5000\nKd 0.3837 0.3837 0.3837\nKs 0.5000 0.5000 0.5000\nillum 2\nNs 65.8900\n\nnewmtl bronze\nKa 0.0449 0.0204 0.0000\nKd 0.0653 0.0367 0.0122\nKs 0.0776 0.0408 0.0000\nillum 3\nNs 137.2100\nsharpness 125.5800\n\nnewmtl bone1\nKa 0.6408 0.5554 0.3845\nKd 0.9837 0.7959 0.4694\nillum 1\n\nnewmtl flwhite1\nKa 0.9306 0.9306 0.9306\nKd 1.0000 1.0000 1.0000\nillum 1\n\nnewmtl flwhite\nKa 0.6449 0.6116 0.5447\nKd 0.9837 0.9309 0.8392\nKs 0.8082 0.7290 0.5708\nillum 2\nNs 200.0000\n\nnewmtl shadow\nKd 0.0350 0.0248 0.0194\nillum 0\nd 0.7500\n\nnewmtl fldkolivegreen\nKa 0.0056 0.0082 0.0000\nKd 0.0151 0.0204 0.0038\nillum 1\n\nnewmtl fldkdkgrey\nKa 0.0000 0.0000 0.0000\nKd 0.0122 0.0122 0.0122\nillum 1\n\nnewmtl lcdgreen\nKa 0.4000 0.4000 0.4000\nKd 0.5878 1.0000 0.5061\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl brownlips\nKa 0.1143 0.0694 0.0245\nKd 0.1429 0.0653 0.0408\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 25.0000\n\nnewmtl muscle\nKa 0.2122 0.0077 0.0154\nKd 0.4204 0.0721 0.0856\nKs 0.1184 0.1184 0.1184\nillum 2\nNs 25.5800\n\nnewmtl flltgrey\nKa 0.5224 0.5224 0.5224\nKd 0.8245 0.8245 0.8245\nillum 1\n\nnewmtl offwhite.warm\nKa 0.5184 0.4501 0.3703\nKd 0.8367 0.6898 0.4490\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl offwhite.cool\nKa 0.5184 0.4501 0.3703\nKd 0.8367 0.6812 0.5703\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl yellowbrt\nKa 0.4000 0.4000 0.4000\nKd 1.0000 0.7837 0.0000\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl chappie\nKa 0.4000 0.4000 0.4000\nKd 0.5837 0.1796 0.0367\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 60.0000\n\nnewmtl archwhite\nKa 0.2816 0.2816 0.2816\nKd 0.9959 0.9959 0.9959\nillum 1\n\nnewmtl archwhite2\nKa 0.2816 0.2816 0.2816\nKd 0.8408 0.8408 0.8408\nillum 1\n\nnewmtl lighttan\nKa 0.0980 0.0536 0.0220\nKd 0.7020 0.4210 0.2206\nKs 0.8286 0.8057 0.5851\nillum 2\nNs 177.5200\n\nnewmtl lighttan2\nKa 0.0980 0.0492 0.0144\nKd 0.3143 0.1870 0.0962\nKs 0.8286 0.8057 0.5851\nillum 2\nNs 177.5200\n\nnewmtl lighttan3\nKa 0.0980 0.0492 0.0144\nKd 0.1796 0.0829 0.0139\nKs 0.8286 0.8057 0.5851\nillum 2\nNs 177.5200\n\nnewmtl lightyellow\nKa 0.5061 0.1983 0.0000\nKd 1.0000 0.9542 0.3388\nKs 1.0000 0.9060 0.0000\nillum 2\nNs 177.5200\n\nnewmtl lighttannew\nKa 0.0980 0.0492 0.0144\nKd 0.7878 0.6070 0.3216\nKs 0.8286 0.8057 0.5851\nillum 2\nNs 177.5200\n\nnewmtl default\nKa 0.4000 0.4000 0.4000\nKd 0.7102 0.7020 0.6531\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 128.0000\n\nnewmtl ship2\nKa 0.0000 0.0000 0.0000\nKd 1.0000 1.0000 1.0000\nKs 0.1143 0.1143 0.1143\nillum 2\nNs 60.0000\n\nnewmtl dkpurple\nKa 0.0082 0.0000 0.0163\nKd 0.0245 0.0000 0.0490\nKs 0.1266 0.0000 0.2531\nillum 2\nNs 65.8900\n\nnewmtl dkorange\nKa 0.4041 0.0123 0.0000\nKd 0.7143 0.0350 0.0000\nKs 0.7102 0.0870 0.0000\nillum 2\nNs 65.8900\n\nnewmtl mintgrn\nKa 0.0101 0.1959 0.0335\nKd 0.0245 0.4776 0.0816\nKs 0.0245 0.4776 0.0816\nillum 2\nNs 65.8900\n\nnewmtl fgreen\nKa 0.0000 0.0449 0.0000\nKd 0.0000 0.0449 0.0004\nKs 0.0062 0.0694 0.0000\nillum 2\nNs 106.2000\n\nnewmtl glassblutint\nKa 0.4000 0.4000 0.4000\nKd 0.5551 0.8000 0.7730\nKs 0.7969 0.9714 0.9223\nillum 4\nd 0.3300\nNs 60.0000\nsharpness 60.0000\n\nnewmtl bflesh\nKa 0.0122 0.0122 0.0122\nKd 0.0245 0.0081 0.0021\nKs 0.0531 0.0460 0.0153\nillum 2\nNs 20.1600\n\nnewmtl meh\nKa 0.4000 0.4000 0.4000\nKd 0.5551 0.8000 0.7730\nKs 0.7969 0.9714 0.9223\nillum 4\nd 0.7500\nNs 183.7200\nsharpness 60.0000\n\nnewmtl violet\nKa 0.0083 0.0000 0.1265\nKd 0.0287 0.0269 0.1347\nKs 0.2267 0.4537 0.6612\nillum 2\nNs 96.9000\n\nnewmtl iris\nKa 0.3061 0.0556 0.0037\nKd 0.0000 0.0572 0.3184\nKs 0.8041 0.6782 0.1477\nillum 2\nNs 188.3700\n\nnewmtl blugrn\nKa 0.4408 0.4144 0.1592\nKd 0.0811 0.6408 0.2775\nKs 0.1467 0.1469 0.0965\nillum 2\nNs 25.0000\n\nnewmtl glasstransparent\nKa 0.2163 0.2163 0.2163\nKd 0.4694 0.4694 0.4694\nKs 0.6082 0.6082 0.6082\nillum 4\nd 0.7500\nNs 200.0000\nsharpness 60.0000\n\nnewmtl fleshtransparent\nKa 0.4000 0.2253 0.2253\nKd 0.6898 0.2942 0.1295\nKs 0.7388 0.4614 0.4614\nillum 4\nd 0.7500\nNs 6.2000\nsharpness 60.0000\n\nnewmtl fldkgrey\nKa 0.0449 0.0449 0.0449\nKd 0.0939 0.0939 0.0939\nillum 1\n\nnewmtl sky_blue\nKa 0.1363 0.2264 0.4122\nKd 0.1241 0.5931 0.8000\nKs 0.0490 0.0490 0.0490\nillum 2\nNs 13.9500\n\nnewmtl fldkpurple\nKa 0.0443 0.0257 0.0776\nKd 0.1612 0.0000 0.3347\nKs 0.0000 0.0000 0.0000\nillum 2\nNs 13.9500\n\nnewmtl dkbrown\nKa 0.0143 0.0062 0.0027\nKd 0.0087 0.0038 0.0016\nKs 0.2370 0.2147 0.1821\nillum 3\nNs 60.0000\nsharpness 60.0000\n\nnewmtl bone2\nKa 0.6408 0.5388 0.3348\nKd 0.9837 0.8620 0.6504\nillum 1\n\nnewmtl bluegrey\nKa 0.4000 0.4000 0.4000\nKd 0.1881 0.2786 0.2898\nKs 0.3000 0.3000 0.3000\nillum 2\nNs 14.7300\n\nnewmtl metal\nKa 0.9102 0.8956 0.1932\nKd 0.9000 0.7626 0.4261\nKs 0.8939 0.8840 0.8683\nillum 2\nNs 200.0000\n\nnewmtl sand_stone\nKa 0.1299 0.1177 0.0998\nKd 0.1256 0.1138 0.0965\nKs 0.2370 0.2147 0.1821\nillum 3\nNs 60.0000\nsharpness 60.0000\n\nnewmtl hair\nKa 0.0013 0.0012 0.0010\nKd 0.0008 0.0007 0.0006\nKs 0.0000 0.0000 0.0000\nillum 3\nNs 60.0000\nsharpness 60.0000\n");
/*     */     
/* 407 */     ObjectFileParser objectFileParser = new ObjectFileParser(stringReader);
/* 408 */     this.materials = new HashMap(50);
/* 409 */     readFile(objectFileParser);
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
/* 420 */   public boolean imageUpdate(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) { return ((paramInt1 & 0xA0) == 0); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\objectfile\ObjectFileMaterials.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */