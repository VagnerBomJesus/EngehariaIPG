/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Hashtable;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.ImageComponent;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.QuadArray;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.Texture;
/*     */ import javax.media.j3d.Texture2D;
/*     */ import javax.media.j3d.TransparencyAttributes;
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Color4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Text2D
/*     */   extends Shape3D
/*     */ {
/*  77 */   private static Hashtable metricsTable = new Hashtable(); public Text2D(String paramString1, Color3f paramColor3f, String paramString2, int paramInt1, int paramInt2) {
/*  78 */     this.rectangleScaleFactor = 0.00390625F;
/*     */     
/*  80 */     this.color = new Color3f();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     this.color.set(paramColor3f);
/* 103 */     this.fontName = paramString2;
/* 104 */     this.fontSize = paramInt1;
/* 105 */     this.fontStyle = paramInt2;
/* 106 */     this.text = paramString1;
/*     */     
/* 108 */     updateText2D(paramString1, paramColor3f, paramString2, paramInt1, paramInt2);
/*     */   }
/*     */   float rectangleScaleFactor;
/*     */   Color3f color;
/*     */   String fontName;
/*     */   int fontSize;
/*     */   int fontStyle;
/*     */   String text;
/*     */   
/*     */   public void setString(String paramString) {
/* 118 */     this.text = paramString;
/*     */     
/* 120 */     Texture texture = getAppearance().getTexture();
/* 121 */     int i = texture.getWidth();
/* 122 */     int j = texture.getHeight();
/*     */     
/* 124 */     ImageComponent imageComponent = setupImage(paramString, this.color, this.fontName, this.fontSize, this.fontStyle);
/*     */     
/* 126 */     if (imageComponent.getWidth() == i && imageComponent.getHeight() == j) {
/*     */       
/* 128 */       texture.setImage(0, imageComponent);
/*     */     } else {
/* 130 */       Texture2D texture2D = setupTexture(imageComponent);
/*     */ 
/*     */ 
/*     */       
/* 134 */       texture2D.setBoundaryModeS(texture.getBoundaryModeS());
/* 135 */       texture2D.setBoundaryModeT(texture.getBoundaryModeT());
/* 136 */       texture2D.setMinFilter(texture.getMinFilter());
/* 137 */       texture2D.setMagFilter(texture.getMagFilter());
/* 138 */       texture2D.setEnable(texture.getEnable());
/* 139 */       texture2D.setAnisotropicFilterMode(texture.getAnisotropicFilterMode());
/* 140 */       texture2D.setAnisotropicFilterDegree(texture.getAnisotropicFilterDegree());
/* 141 */       int k = texture.getFilter4FuncPointsCount();
/* 142 */       if (k > 0) {
/* 143 */         float[] arrayOfFloat = new float[k];
/* 144 */         texture.getFilter4Func(arrayOfFloat);
/* 145 */         texture2D.setFilter4Func(arrayOfFloat);
/*     */       } 
/* 147 */       Color4f color4f = new Color4f();
/* 148 */       texture.getBoundaryColor(color4f);
/* 149 */       texture2D.setBoundaryColor(color4f);
/* 150 */       texture2D.setUserData(texture.getUserData());
/* 151 */       getAppearance().setTexture(texture2D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateText2D(String paramString1, Color3f paramColor3f, String paramString2, int paramInt1, int paramInt2) {
/* 157 */     ImageComponent imageComponent = setupImage(paramString1, paramColor3f, paramString2, paramInt1, paramInt2);
/*     */ 
/*     */     
/* 160 */     Texture2D texture2D = setupTexture(imageComponent);
/*     */     
/* 162 */     QuadArray quadArray = setupGeometry(imageComponent.getWidth(), imageComponent.getHeight());
/*     */     
/* 164 */     setGeometry(quadArray);
/*     */     
/* 166 */     Appearance appearance = setupAppearance(texture2D);
/* 167 */     setAppearance(appearance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRectangleScaleFactor(float paramFloat) {
/* 178 */     this.rectangleScaleFactor = paramFloat;
/* 179 */     updateText2D(this.text, this.color, this.fontName, this.fontSize, this.fontStyle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public float getRectangleScaleFactor() { return this.rectangleScaleFactor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Texture2D setupTexture(ImageComponent paramImageComponent) {
/* 196 */     Texture2D texture2D = new Texture2D(1, 6, paramImageComponent.getWidth(), paramImageComponent.getHeight());
/*     */ 
/*     */ 
/*     */     
/* 200 */     texture2D; texture2D.setMinFilter(3);
/* 201 */     texture2D; texture2D.setMagFilter(3);
/* 202 */     texture2D.setImage(0, paramImageComponent);
/* 203 */     texture2D.setEnable(true);
/* 204 */     texture2D.setCapability(7);
/* 205 */     texture2D.setCapability(8);
/* 206 */     texture2D.setCapability(0);
/* 207 */     texture2D.setCapability(2);
/* 208 */     texture2D.setCapability(3);
/* 209 */     texture2D.setCapability(6);
/* 210 */     texture2D.setCapability(12);
/* 211 */     texture2D.setCapability(14);
/* 212 */     return texture2D;
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
/*     */   private ImageComponent setupImage(String paramString1, Color3f paramColor3f, String paramString2, int paramInt1, int paramInt2) {
/* 224 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 225 */     Font font = new Font(paramString2, paramInt2, paramInt1);
/*     */     
/*     */     FontMetrics fontMetrics;
/* 228 */     if ((fontMetrics = (FontMetrics)metricsTable.get(font)) == null) {
/* 229 */       fontMetrics = toolkit.getFontMetrics(font);
/* 230 */       metricsTable.put(font, fontMetrics);
/*     */     } 
/* 232 */     int i = fontMetrics.stringWidth(paramString1);
/* 233 */     int j = fontMetrics.getMaxDescent();
/* 234 */     int k = fontMetrics.getMaxAscent();
/* 235 */     int m = fontMetrics.getLeading();
/* 236 */     int n = j + k;
/*     */ 
/*     */ 
/*     */     
/* 240 */     byte b1 = 1; byte b2;
/* 241 */     for (b2 = 1; b2 < 32; b2++) {
/* 242 */       b1 *= 2;
/* 243 */       if (i <= b1)
/*     */         break; 
/*     */     } 
/* 246 */     i = Math.max(i, b1);
/* 247 */     b1 = 1;
/* 248 */     for (b2 = 1; b2 < 32; b2++) {
/* 249 */       b1 *= 2;
/* 250 */       if (n <= b1)
/*     */         break; 
/*     */     } 
/* 253 */     n = Math.max(n, b1);
/*     */ 
/*     */     
/* 256 */     BufferedImage bufferedImage = new BufferedImage(i, n, 2);
/*     */     
/* 258 */     Graphics2D graphics2D = bufferedImage.createGraphics();
/*     */ 
/*     */     
/* 261 */     Color color1 = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/* 262 */     graphics2D.setColor(color1);
/* 263 */     graphics2D.fillRect(0, 0, i, n);
/*     */ 
/*     */     
/* 266 */     graphics2D.setFont(font);
/* 267 */     Color color2 = new Color(paramColor3f.x, paramColor3f.y, paramColor3f.z, 1.0F);
/* 268 */     graphics2D.setColor(color2);
/* 269 */     graphics2D.drawString(paramString1, 0, n - j);
/*     */     
/* 271 */     ImageComponent2D imageComponent2D = new ImageComponent2D(2, bufferedImage);
/*     */ 
/*     */ 
/*     */     
/* 275 */     imageComponent2D.setCapability(0);
/*     */     
/* 277 */     return imageComponent2D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private QuadArray setupGeometry(int paramInt1, int paramInt2) {
/* 286 */     float f1 = 0.0F;
/* 287 */     float f2 = paramInt1 * this.rectangleScaleFactor;
/* 288 */     float f3 = paramInt2 * this.rectangleScaleFactor;
/* 289 */     float[] arrayOfFloat1 = { f2, 0.0F, f1, f2, f3, f1, 0.0F, f3, f1, 0.0F, 0.0F, f1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     float[] arrayOfFloat2 = { 0.0F, -1.0F, 0.0F, 0.0F, -1.0F, 0.0F, -1.0F, -1.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     QuadArray quadArray = new QuadArray(4, 33);
/*     */     
/* 304 */     quadArray.setCoordinates(0, arrayOfFloat1);
/* 305 */     quadArray.setTextureCoordinates(0, 0, arrayOfFloat2);
/*     */     
/* 307 */     return quadArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Appearance setupAppearance(Texture2D paramTexture2D) {
/* 318 */     TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
/* 319 */     transparencyAttributes.setTransparencyMode(2);
/* 320 */     transparencyAttributes.setTransparency(0.0F);
/* 321 */     Appearance appearance = new Appearance();
/* 322 */     appearance.setTransparencyAttributes(transparencyAttributes);
/* 323 */     appearance.setTexture(paramTexture2D);
/*     */     
/* 325 */     Material material = new Material();
/* 326 */     material.setLightingEnable(false);
/* 327 */     appearance.setMaterial(material);
/*     */     
/* 329 */     return appearance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public String getString() { return this.text; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 347 */   public Color3f getColor() { return this.color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public String getFontName() { return this.fontName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public int getFontSize() { return this.fontSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public int getFontStyle() { return this.fontStyle; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\Text2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */