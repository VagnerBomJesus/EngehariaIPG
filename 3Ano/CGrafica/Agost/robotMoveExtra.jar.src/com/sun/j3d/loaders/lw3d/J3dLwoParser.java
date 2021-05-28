/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.utils.geometry.GeometryInfo;
/*     */ import com.sun.j3d.utils.geometry.NormalGenerator;
/*     */ import com.sun.j3d.utils.geometry.Stripifier;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.ColoringAttributes;
/*     */ import javax.media.j3d.LineArray;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.PointArray;
/*     */ import javax.media.j3d.PointAttributes;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.Texture;
/*     */ import javax.media.j3d.TextureAttributes;
/*     */ import javax.media.j3d.TransparencyAttributes;
/*     */ import javax.media.j3d.TriangleFanArray;
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
/*     */ class J3dLwoParser
/*     */   extends LwoParser
/*     */ {
/*     */   float[] normalCoordsArray;
/*     */   int[] normalIndicesArray;
/*     */   Shape3D objectShape;
/*     */   Color3f color;
/*     */   Color3f diffuseColor;
/*     */   Color3f specularColor;
/*     */   Color3f emissiveColor;
/*     */   float shininess;
/*  85 */   Vector objectShapeList = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   J3dLwoParser(String paramString, int paramInt) throws FileNotFoundException { super(paramString, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   J3dLwoParser(URL paramURL, int paramInt) throws FileNotFoundException { super(paramURL, paramInt); }
/*     */ 
/*     */ 
/*     */   
/* 101 */   void getSurf(int paramInt) throws FileNotFoundException { super.getSurf(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createJava3dGeometry() throws IncorrectFormatException {
/* 114 */     Enumeration enumeration = this.shapeList.elements();
/* 115 */     while (enumeration.hasMoreElements()) {
/* 116 */       TriangleFanArray triangleFanArray; byte b1 = 1;
/* 117 */       ShapeHolder shapeHolder = (ShapeHolder)enumeration.nextElement();
/* 118 */       debugOutputLn(8, "about to create Arrays for Shape");
/* 119 */       debugOutputLn(2, "shape = " + shapeHolder);
/* 120 */       shapeHolder.createArrays(true);
/* 121 */       int i = shapeHolder.coordsArray.length / 3;
/* 122 */       int j = 0;
/* 123 */       if (shapeHolder.facetIndices != null)
/* 124 */         j = shapeHolder.facetIndices.length; 
/* 125 */       debugOutputLn(2, "numSurf = " + shapeHolder.numSurf);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       String str = (String)this.surfNameList.elementAt(shapeHolder.numSurf - 1);
/*     */       
/* 132 */       LwoSurface lwoSurface = null;
/* 133 */       byte b2 = 0;
/* 134 */       for (; b2 < this.surfaceList.size(); 
/* 135 */         b2++) {
/* 136 */         LwoSurface lwoSurface1 = (LwoSurface)this.surfaceList.elementAt(b2);
/*     */         
/* 138 */         String str1 = lwoSurface1.surfName;
/* 139 */         if (str.equals(str1)) {
/* 140 */           lwoSurface = lwoSurface1;
/*     */           break;
/*     */         } 
/*     */       } 
/* 144 */       if (lwoSurface == null) {
/* 145 */         throw new IncorrectFormatException("bad surf for surfnum/name = " + shapeHolder.numSurf + ", " + str);
/*     */       }
/*     */ 
/*     */       
/* 149 */       debugOutputLn(2, "surf = " + lwoSurface);
/*     */ 
/*     */       
/* 152 */       LwoTexture lwoTexture = lwoSurface.getTexture();
/*     */       
/* 154 */       Appearance appearance = new Appearance();
/* 155 */       if (shapeHolder.facetSizes[0] == 1) {
/*     */ 
/*     */         
/* 158 */         triangleFanArray = new PointArray(i, b1);
/*     */         
/* 160 */         triangleFanArray.setCoordinates(0, shapeHolder.coordsArray);
/* 161 */         ColoringAttributes coloringAttributes = new ColoringAttributes(lwoSurface.getColor(), 0);
/*     */ 
/*     */         
/* 164 */         PointAttributes pointAttributes = new PointAttributes();
/* 165 */         pointAttributes.setPointSize(1.0F);
/*     */         
/* 167 */         appearance.setColoringAttributes(coloringAttributes);
/* 168 */         appearance.setPointAttributes(pointAttributes);
/*     */       }
/* 170 */       else if (shapeHolder.facetSizes[0] == 2) {
/*     */ 
/*     */         
/* 173 */         debugOutputLn(8, "Creating IndexedLineArray");
/* 174 */         triangleFanArray = new LineArray(i, b1);
/*     */         
/* 176 */         triangleFanArray.setCoordinates(0, shapeHolder.coordsArray);
/* 177 */         ColoringAttributes coloringAttributes = new ColoringAttributes(lwoSurface.getColor(), 0);
/*     */ 
/*     */         
/* 180 */         appearance.setColoringAttributes(coloringAttributes);
/*     */       }
/*     */       else {
/*     */         
/* 184 */         debugOutputLn(8, "Creating IndexedTriFanArray");
/*     */         
/* 186 */         b1 |= 0x2;
/*     */         
/* 188 */         debugOutputLn(8, "about to process vertices/indices, facetIndices = " + shapeHolder.facetIndices);
/*     */         
/* 190 */         if (shapeHolder.facetIndices != null) {
/* 191 */           float[] arrayOfFloat = null;
/* 192 */           int[] arrayOfInt = null;
/*     */           
/* 194 */           debugOutputLn(8, "setting vertexCount, normind = " + shapeHolder.normalIndices);
/*     */           
/* 196 */           debugOutputLn(8, "vtxcount, format, indcount = " + i + ", " + b1 + ", " + j);
/*     */ 
/*     */           
/* 199 */           if (lwoTexture != null) {
/*     */ 
/*     */             
/* 202 */             b1 |= 0x20;
/* 203 */             arrayOfFloat = new float[i * 2];
/* 204 */             arrayOfInt = new int[shapeHolder.facetIndices.length];
/* 205 */             calculateTextureCoords(lwoTexture, shapeHolder.coordsArray, shapeHolder.facetIndices, arrayOfFloat, arrayOfInt);
/*     */ 
/*     */             
/* 208 */             debugOutputLn(8, "textureCoords:");
/* 209 */             debugOutputLn(8, "texture Coords, Indices.length = " + arrayOfFloat.length + ", " + arrayOfInt.length);
/*     */           } 
/* 211 */           debugOutputLn(8, "about to create GeometryInfo");
/*     */ 
/*     */           
/* 214 */           GeometryInfo geometryInfo = new GeometryInfo(3);
/*     */           
/* 216 */           geometryInfo.setCoordinates(shapeHolder.coordsArray);
/* 217 */           geometryInfo.setCoordinateIndices(shapeHolder.facetIndices);
/* 218 */           geometryInfo.setStripCounts(shapeHolder.facetSizes);
/* 219 */           if (lwoTexture != null) {
/* 220 */             geometryInfo.setTextureCoordinateParams(1, 2);
/* 221 */             geometryInfo.setTextureCoordinates(0, arrayOfFloat);
/* 222 */             geometryInfo.setTextureCoordinateIndices(0, arrayOfInt);
/*     */           } 
/* 224 */           geometryInfo.recomputeIndices();
/* 225 */           NormalGenerator normalGenerator = new NormalGenerator(lwoSurface.getCreaseAngle());
/*     */           
/* 227 */           normalGenerator.generateNormals(geometryInfo);
/* 228 */           Stripifier stripifier = new Stripifier();
/* 229 */           stripifier.stripify(geometryInfo);
/* 230 */           triangleFanArray = geometryInfo.getGeometryArray(true, true, false);
/* 231 */           debugOutputLn(8, "done.");
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 237 */           debugOutputLn(8, "about to create trifanarray with vertexCount, facetSizes.len = " + i + ", " + shapeHolder.facetSizes.length);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 242 */           triangleFanArray = new TriangleFanArray(i, b1, shapeHolder.facetSizes);
/*     */ 
/*     */ 
/*     */           
/* 246 */           triangleFanArray.setCoordinates(0, shapeHolder.coordsArray);
/* 247 */           triangleFanArray.setNormals(0, shapeHolder.normalCoords);
/* 248 */           debugOutputLn(2, "passed in normalCoords, length = " + shapeHolder.normalCoords.length);
/*     */         } 
/*     */         
/* 251 */         debugOutputLn(8, "created fan array");
/*     */ 
/*     */         
/* 254 */         Material material = new Material(lwoSurface.getColor(), lwoSurface.getEmissiveColor(), lwoSurface.getDiffuseColor(), lwoSurface.getSpecularColor(), lwoSurface.getShininess());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 259 */         material.setLightingEnable(true);
/* 260 */         appearance.setMaterial(material);
/* 261 */         if (lwoSurface.getTransparency() != 0.0F) {
/* 262 */           TransparencyAttributes transparencyAttributes = new TransparencyAttributes();
/* 263 */           transparencyAttributes.setTransparency(lwoSurface.getTransparency());
/* 264 */           transparencyAttributes; transparencyAttributes.setTransparencyMode(2);
/* 265 */           appearance.setTransparencyAttributes(transparencyAttributes);
/*     */         } 
/* 267 */         if (lwoTexture != null) {
/* 268 */           debugOutputLn(8, "texture != null, enable texturing");
/* 269 */           Texture texture = lwoTexture.getTexture();
/* 270 */           texture.setEnable(true);
/* 271 */           appearance.setTexture(texture);
/* 272 */           TextureAttributes textureAttributes = new TextureAttributes();
/* 273 */           if (lwoTexture.getType().equals("DTEX")) {
/* 274 */             textureAttributes.setTextureMode(2);
/* 275 */           } else if (lwoTexture.getType().equals("CTEX")) {
/* 276 */             textureAttributes.setTextureMode(3);
/* 277 */           }  appearance.setTextureAttributes(textureAttributes);
/*     */         } else {
/*     */           
/* 280 */           debugOutputLn(8, "texture == null, no texture to use");
/*     */         } 
/*     */       } 
/* 283 */       debugOutputLn(8, "done creating object");
/*     */ 
/*     */       
/* 286 */       shapeHolder.nullify();
/*     */       
/* 288 */       this.objectShape = new Shape3D(triangleFanArray);
/*     */ 
/*     */       
/* 291 */       this.objectShape.setAppearance(appearance);
/* 292 */       this.objectShapeList.addElement(this.objectShape);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void calculateTextureCoords(LwoTexture paramLwoTexture, float[] paramArrayOfFloat1, int[] paramArrayOfInt1, float[] paramArrayOfFloat2, int[] paramArrayOfInt2) {
/* 409 */     debugOutputLn(1, "calculateTextureCoords()");
/*     */     
/* 411 */     float f1 = 0.0F, f2 = 0.0F, f3 = 0.0F, f4 = 0.0F;
/*     */     
/* 413 */     int i = paramLwoTexture.getTextureAxis();
/* 414 */     Vector3f vector3f1 = paramLwoTexture.getTextureSize();
/* 415 */     Vector3f vector3f2 = paramLwoTexture.getTextureCenter();
/*     */     
/* 417 */     String str = paramLwoTexture.getMappingType();
/* 418 */     if (str.startsWith("Cylindrical")) {
/* 419 */       calculateCylindricalTextureCoords(i, vector3f1, vector3f2, paramArrayOfFloat2, paramArrayOfInt2, paramArrayOfFloat1, paramArrayOfInt1);
/*     */ 
/*     */     
/*     */     }
/* 423 */     else if (str.startsWith("Spherical")) {
/* 424 */       calculateSphericalTextureCoords(i, vector3f2, paramArrayOfFloat2, paramArrayOfInt2, paramArrayOfFloat1, paramArrayOfInt1);
/*     */ 
/*     */     
/*     */     }
/* 428 */     else if (str.startsWith("Planar")) {
/* 429 */       calculatePlanarTextureCoords(i, vector3f1, vector3f2, paramArrayOfFloat2, paramArrayOfInt2, paramArrayOfFloat1, paramArrayOfInt1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double xyztoh(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 438 */     if (paramFloat1 == 0.0D && paramFloat3 == 0.0D) {
/* 439 */       return 0.0D;
/*     */     }
/* 441 */     if (paramFloat3 == 0.0D)
/* 442 */       return (paramFloat1 < 0.0D) ? 1.5707963267948966D : -1.5707963267948966D; 
/* 443 */     if (paramFloat3 < 0.0D) {
/* 444 */       return -Math.atan((paramFloat1 / paramFloat3)) + Math.PI;
/*     */     }
/* 446 */     return -Math.atan((paramFloat1 / paramFloat3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double xyztop(float paramFloat1, float paramFloat2, float paramFloat3) {
/*     */     double d;
/* 454 */     if (paramFloat1 == 0.0D && paramFloat3 == 0.0D) {
/* 455 */       if (paramFloat2 != 0.0D) {
/* 456 */         d = (paramFloat2 < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */       } else {
/* 458 */         d = 0.0D;
/*     */       } 
/*     */     } else {
/* 461 */       paramFloat1 = (float)Math.sqrt((paramFloat1 * paramFloat1 + paramFloat3 * paramFloat3));
/* 462 */       if (paramFloat1 == 0.0D) {
/* 463 */         d = (paramFloat2 < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */       } else {
/* 465 */         d = Math.atan((paramFloat2 / paramFloat1));
/*     */       } 
/* 467 */     }  return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void calculateSphericalTextureCoords(int paramInt, Vector3f paramVector3f, float[] paramArrayOfFloat1, int[] paramArrayOfInt1, float[] paramArrayOfFloat2, int[] paramArrayOfInt2) {
/* 477 */     debugOutputLn(1, "calculateSphericalTextureCoords");
/*     */ 
/*     */ 
/*     */     
/* 481 */     for (byte b = 0; b < paramArrayOfInt2.length; b++) {
/* 482 */       float f1 = paramArrayOfFloat2[3 * paramArrayOfInt2[b]] - paramVector3f.x;
/* 483 */       float f2 = paramArrayOfFloat2[3 * paramArrayOfInt2[b] + 1] - paramVector3f.y;
/* 484 */       float f3 = -(paramArrayOfFloat2[3 * paramArrayOfInt2[b] + 2] + paramVector3f.z);
/* 485 */       if (paramInt == 1) {
/* 486 */         d1 = xyztoh(f3, f1, -f2);
/* 487 */         d2 = xyztop(f3, f1, -f2);
/*     */       }
/* 489 */       else if (paramInt == 2) {
/* 490 */         d1 = xyztoh(-f1, f2, f3);
/* 491 */         d2 = xyztop(-f1, f2, f3);
/*     */       } else {
/*     */         
/* 494 */         d1 = xyztoh(-f1, f3, -f2);
/* 495 */         d2 = xyztop(-f1, f3, -f2);
/*     */       } 
/* 497 */       double d1 = 1.0D - d1 / 6.283185307179586D;
/* 498 */       double d2 = -(0.5D - d2 / Math.PI);
/* 499 */       paramArrayOfFloat1[paramArrayOfInt2[b] * 2] = (float)d1;
/* 500 */       paramArrayOfFloat1[paramArrayOfInt2[b] * 2 + 1] = (float)d2;
/* 501 */       paramArrayOfInt1[b] = paramArrayOfInt2[b];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void calculateCylindricalTextureCoords(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, float[] paramArrayOfFloat1, int[] paramArrayOfInt1, float[] paramArrayOfFloat2, int[] paramArrayOfInt2) {
/* 512 */     debugOutputLn(1, "calculateCylindricalTextureCoords");
/* 513 */     debugOutputLn(2, "axis, size, center, tc, ti, v, i = " + paramInt + ", " + paramVector3f1 + ", " + paramVector3f2 + ", " + paramArrayOfFloat1 + ", " + paramArrayOfInt1 + ", " + paramArrayOfFloat2 + ", " + paramArrayOfInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 523 */     debugOutputLn(2, "Cyl Texture Coords:");
/* 524 */     for (byte b = 0; b < paramArrayOfInt2.length; b++) {
/* 525 */       double d2; float f1 = paramArrayOfFloat2[3 * paramArrayOfInt2[b]] - paramVector3f2.x;
/* 526 */       float f2 = paramArrayOfFloat2[3 * paramArrayOfInt2[b] + 1] - paramVector3f2.y;
/* 527 */       float f3 = -(paramArrayOfFloat2[3 * paramArrayOfInt2[b] + 2] + paramVector3f2.z);
/*     */       
/* 529 */       if (paramInt == 1) {
/* 530 */         d1 = xyztoh(f3, f1, -f2);
/* 531 */         d2 = (f1 / paramVector3f1.x) + 0.5D;
/*     */       }
/* 533 */       else if (paramInt == 2) {
/* 534 */         d1 = xyztoh(-f1, f2, f3);
/* 535 */         d2 = (f2 / paramVector3f1.y) + 0.5D;
/*     */       } else {
/*     */         
/* 538 */         d1 = xyztoh(-f1, f3, -f2);
/* 539 */         d2 = (f3 / paramVector3f1.z) + 0.5D;
/*     */       } 
/* 541 */       double d1 = 1.0D - d1 / 6.283185307179586D;
/* 542 */       paramArrayOfFloat1[paramArrayOfInt2[b] * 2] = (float)d1;
/* 543 */       paramArrayOfFloat1[paramArrayOfInt2[b] * 2 + 1] = (float)d2;
/* 544 */       paramArrayOfInt1[b] = paramArrayOfInt2[b];
/* 545 */       debugOutputLn(2, "x, y, z = " + f1 + ", " + f2 + ", " + f3 + "    " + "s, t = " + d1 + ", " + d2);
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
/*     */   void calculatePlanarTextureCoords(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, float[] paramArrayOfFloat1, int[] paramArrayOfInt1, float[] paramArrayOfFloat2, int[] paramArrayOfInt2) {
/* 557 */     debugOutputLn(1, "calculatePlanarTextureCoords");
/* 558 */     debugOutputLn(2, "size, center, axis = " + paramVector3f1 + paramVector3f2 + ", " + paramInt);
/*     */     
/* 560 */     float f1 = 0.0F, f2 = 0.0F, f3 = 0.0F, f4 = 0.0F;
/*     */ 
/*     */     
/* 563 */     if (paramInt == 1) {
/* 564 */       f2 = -1.0F / paramVector3f1.z;
/* 565 */       f3 = 1.0F / paramVector3f1.y;
/*     */     }
/* 567 */     else if (paramInt == 2) {
/* 568 */       f1 = 1.0F / paramVector3f1.x;
/* 569 */       f4 = -1.0F / paramVector3f1.z;
/*     */     } else {
/*     */       
/* 572 */       f1 = 1.0F / paramVector3f1.x;
/* 573 */       f3 = 1.0F / paramVector3f1.y;
/*     */     } 
/*     */     
/* 576 */     debugOutputLn(2, "Planar Texture Coords:");
/* 577 */     for (byte b = 0; b < paramArrayOfInt2.length; b++) {
/* 578 */       float f5 = paramArrayOfFloat2[3 * paramArrayOfInt2[b]] - paramVector3f2.x;
/* 579 */       float f6 = paramArrayOfFloat2[3 * paramArrayOfInt2[b] + 1] - paramVector3f2.y;
/* 580 */       float f7 = paramArrayOfFloat2[3 * paramArrayOfInt2[b] + 2] + paramVector3f2.z;
/* 581 */       double d1 = (f5 * f1 + f7 * f2) + 0.5D;
/* 582 */       double d2 = (f6 * f3 + f7 * f4) + 0.5D;
/* 583 */       paramArrayOfFloat1[paramArrayOfInt2[b] * 2] = (float)d1;
/* 584 */       paramArrayOfFloat1[paramArrayOfInt2[b] * 2 + 1] = (float)d2;
/* 585 */       paramArrayOfInt1[b] = paramArrayOfInt2[b];
/* 586 */       debugOutputLn(2, "x, y, z = " + f5 + ", " + f6 + ", " + f7 + "    " + "s, t = " + d1 + ", " + d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 594 */   Shape3D getJava3dShape() { return this.objectShape; }
/*     */ 
/*     */ 
/*     */   
/* 598 */   Vector getJava3dShapeList() { return this.objectShapeList; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\J3dLwoParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */