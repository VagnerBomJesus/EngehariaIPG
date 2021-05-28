/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.TexCoord2f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sphere
/*     */   extends Primitive
/*     */ {
/*     */   public static final int BODY = 0;
/*     */   static final int MID_REZ_DIV = 16;
/*     */   float radius;
/*     */   int divisions;
/*     */   
/*  91 */   public Sphere(float paramFloat) { this(paramFloat, 1, 16); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Sphere() { this(1.0F, 1, 16); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public Sphere(float paramFloat, Appearance paramAppearance) { this(paramFloat, 1, 16, paramAppearance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public Sphere(float paramFloat, int paramInt, Appearance paramAppearance) { this(paramFloat, paramInt, 16, paramAppearance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public Sphere(float paramFloat, int paramInt1, int paramInt2) { this(paramFloat, paramInt1, paramInt2, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape3D getShape(int paramInt) {
/* 146 */     if (paramInt != 0) return null;
/*     */     
/* 148 */     return (Shape3D)getChild(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public Shape3D getShape() { return (Shape3D)getChild(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public void setAppearance(Appearance paramAppearance) { ((Shape3D)getChild(0)).setAppearance(paramAppearance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Appearance getAppearance(int paramInt) {
/* 176 */     if (paramInt != 0) return null; 
/* 177 */     return getShape(paramInt).getAppearance();
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
/*     */   public Sphere(float paramFloat, int paramInt1, int paramInt2, Appearance paramAppearance) {
/* 196 */     this.radius = paramFloat;
/* 197 */     this.divisions = paramInt2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     this.flags = paramInt1;
/* 209 */     boolean bool = ((this.flags & 0x8) != 0) ? 1 : 0;
/*     */ 
/*     */     
/* 212 */     if ((this.flags & 0x4) != 0) {
/* 213 */       b = -1;
/*     */     } else {
/* 215 */       b = 1;
/*     */     } 
/*     */     
/* 218 */     if (paramInt2 < 4) {
/* 219 */       j = 1;
/* 220 */       i = 4;
/*     */     } else {
/* 222 */       int k = paramInt2 % 4;
/* 223 */       if (k == 0) {
/* 224 */         i = paramInt2;
/*     */       } else {
/* 226 */         i = paramInt2 + 4 - k;
/*     */       } 
/* 228 */       j = i / 4;
/*     */     } 
/*     */ 
/*     */     
/* 232 */     GeomBuffer geomBuffer = getCachedGeometry(1, paramFloat, 0.0F, 0.0F, paramInt2, 0, paramInt1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     if (geomBuffer != null) {
/* 239 */       shape3D = new Shape3D(geomBuffer.getComputedGeometry());
/* 240 */       this.numVerts += geomBuffer.getNumVerts();
/* 241 */       this.numTris += geomBuffer.getNumTris();
/*     */     }
/*     */     else {
/*     */       
/* 245 */       GeomBuffer geomBuffer1 = new GeomBuffer(8 * j * (j + 2));
/*     */       
/* 247 */       for (byte b1 = 0; b1 < 4; b1++) {
/* 248 */         buildQuadrant(geomBuffer1, b1 * Math.PI / 2.0D, (b1 + true) * Math.PI / 2.0D, b, j, i, true);
/* 249 */         buildQuadrant(geomBuffer1, b1 * Math.PI / 2.0D, (b1 + true) * Math.PI / 2.0D, b, j, i, false);
/*     */       } 
/*     */ 
/*     */       
/* 253 */       if (bool) {
/* 254 */         TexCoord2f[] arrayOfTexCoord2f = geomBuffer1.getTexCoords();
/* 255 */         if (arrayOfTexCoord2f != null) {
/* 256 */           for (byte b2 = 0; b2 < arrayOfTexCoord2f.length; b2++) {
/* 257 */             (arrayOfTexCoord2f[b2]).y = 1.0F - (arrayOfTexCoord2f[b2]).y;
/*     */           }
/*     */         }
/*     */       } 
/*     */       
/* 262 */       shape3D = new Shape3D(geomBuffer1.getGeom(this.flags));
/* 263 */       this.numVerts = geomBuffer1.getNumVerts();
/* 264 */       this.numTris = geomBuffer1.getNumTris();
/*     */       
/* 266 */       if ((paramInt1 & 0x10) == 0) {
/* 267 */         cacheGeometry(1, paramFloat, 0.0F, 0.0F, paramInt2, 0, paramInt1, geomBuffer1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 273 */     if ((this.flags & 0x40) != 0) {
/* 274 */       shape3D.setCapability(14);
/* 275 */       shape3D.setCapability(15);
/*     */     } 
/*     */     
/* 278 */     if ((this.flags & 0x20) != 0) {
/* 279 */       shape3D.setCapability(12);
/*     */     }
/*     */     
/* 282 */     addChild(shape3D);
/*     */     
/* 284 */     if (paramAppearance == null) {
/* 285 */       setAppearance();
/*     */     } else {
/* 287 */       setAppearance(paramAppearance);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 314 */     Sphere sphere = new Sphere(this.radius, this.flags, this.divisions, getAppearance());
/* 315 */     sphere.duplicateNode(this, paramBoolean);
/*     */     
/* 317 */     return sphere;
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
/* 347 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { super.duplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public float getRadius() { return this.radius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public int getDivisions() { return this.divisions; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void buildQuadrant(GeomBuffer paramGeomBuffer, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
/*     */     boolean bool;
/*     */     double d3;
/*     */     double d2;
/*     */     double d1;
/* 383 */     if (paramBoolean) {
/* 384 */       d1 = Math.PI / (2 * paramInt2);
/* 385 */       d2 = d1;
/* 386 */       d3 = 1.0D;
/* 387 */       bool = (paramInt1 > 0) ? 1 : 0;
/*     */     } else {
/* 389 */       d1 = -3.141592653589793D / (2 * paramInt2);
/* 390 */       d2 = Math.PI + d1;
/* 391 */       d3 = -1.0D;
/* 392 */       bool = (paramInt1 < 0) ? 1 : 0;
/*     */     } 
/*     */ 
/*     */     
/* 396 */     for (byte b = 1; b <= paramInt2; b++) {
/* 397 */       double d7, d5 = Math.cos(d2);
/* 398 */       double d6 = Math.sin(d2);
/* 399 */       if (paramInt1 > 0) {
/* 400 */         d7 = 1.0D - d2 / Math.PI;
/*     */       } else {
/* 402 */         d7 = d2 / Math.PI;
/*     */       } 
/*     */       
/* 405 */       int i = b << true;
/*     */       
/* 407 */       double d4 = (paramDouble2 - paramDouble1) / b;
/*     */       
/* 409 */       paramGeomBuffer.begin(32);
/*     */       
/* 411 */       if (bool) {
/*     */         
/* 413 */         double d8 = paramDouble1;
/*     */         
/* 415 */         for (byte b1 = 0; b1 < b; b1++) {
/* 416 */           double d11 = d6 * Math.cos(d8);
/* 417 */           double d12 = d6 * Math.sin(d8);
/*     */           
/* 419 */           paramGeomBuffer.normal3d(d11 * paramInt1, d5 * paramInt1, d12 * paramInt1);
/* 420 */           paramGeomBuffer.texCoord2d(0.75D - d8 / 6.283185307179586D, d7);
/* 421 */           paramGeomBuffer.vertex3d(d11 * this.radius, d5 * this.radius, d12 * this.radius);
/* 422 */           if (b > 1) {
/*     */             
/* 424 */             int j = paramGeomBuffer.currVertCnt - i;
/* 425 */             Point3f point3f = paramGeomBuffer.pts[j];
/* 426 */             Vector3f vector3f = paramGeomBuffer.normals[j];
/* 427 */             TexCoord2f texCoord2f = paramGeomBuffer.tcoords[j];
/*     */             
/* 429 */             paramGeomBuffer.normal3d(vector3f.x, vector3f.y, vector3f.z);
/* 430 */             paramGeomBuffer.texCoord2d(texCoord2f.x, texCoord2f.y);
/* 431 */             paramGeomBuffer.vertex3d(point3f.x, point3f.y, point3f.z);
/*     */           } else {
/* 433 */             paramGeomBuffer.normal3d(0.0D, paramInt1 * d3, 0.0D);
/* 434 */             if (paramInt1 > 0) {
/* 435 */               paramGeomBuffer.texCoord2d(0.75D - (paramDouble1 + paramDouble2) / 12.566370614359172D, 1.0D - (d2 - d1) / Math.PI);
/*     */             } else {
/*     */               
/* 438 */               paramGeomBuffer.texCoord2d(0.75D - (paramDouble1 + paramDouble2) / 12.566370614359172D, (d2 - d1) / Math.PI);
/*     */             } 
/*     */             
/* 441 */             paramGeomBuffer.vertex3d(0.0D, d3 * this.radius, 0.0D);
/*     */           } 
/*     */           
/* 444 */           d8 += d4;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 450 */         d8 = paramDouble2;
/* 451 */         double d9 = d6 * Math.cos(d8);
/* 452 */         double d10 = d6 * Math.sin(d8);
/* 453 */         paramGeomBuffer.normal3d(d9 * paramInt1, d5 * paramInt1, d10 * paramInt1);
/* 454 */         paramGeomBuffer.texCoord2d(0.75D - d8 / 6.283185307179586D, d7);
/* 455 */         paramGeomBuffer.vertex3d(d9 * this.radius, d5 * this.radius, d10 * this.radius);
/*     */       } else {
/* 457 */         double d8 = paramDouble2;
/*     */         
/* 459 */         for (byte b1 = b; b1; b1--) {
/* 460 */           double d11 = d6 * Math.cos(d8);
/* 461 */           double d12 = d6 * Math.sin(d8);
/*     */           
/* 463 */           paramGeomBuffer.normal3d(d11 * paramInt1, d5 * paramInt1, d12 * paramInt1);
/*     */ 
/*     */           
/* 466 */           paramGeomBuffer.texCoord2d(0.75D - d8 / 6.283185307179586D, d7);
/* 467 */           paramGeomBuffer.vertex3d(d11 * this.radius, d5 * this.radius, d12 * this.radius);
/* 468 */           if (b > 1) {
/*     */             
/* 470 */             int j = paramGeomBuffer.currVertCnt - i;
/* 471 */             Point3f point3f = paramGeomBuffer.pts[j];
/* 472 */             Vector3f vector3f = paramGeomBuffer.normals[j];
/* 473 */             TexCoord2f texCoord2f = paramGeomBuffer.tcoords[j];
/* 474 */             paramGeomBuffer.normal3d(vector3f.x, vector3f.y, vector3f.z);
/* 475 */             paramGeomBuffer.texCoord2d(texCoord2f.x, texCoord2f.y);
/* 476 */             paramGeomBuffer.vertex3d(point3f.x, point3f.y, point3f.z);
/*     */           } else {
/* 478 */             paramGeomBuffer.normal3d(0.0D, paramInt1 * d3, 0.0D);
/* 479 */             if (paramInt1 > 0) {
/* 480 */               paramGeomBuffer.texCoord2d(0.75D - (paramDouble1 + paramDouble2) / 12.566370614359172D, 1.0D - (d2 - d1) / Math.PI);
/*     */             } else {
/*     */               
/* 483 */               paramGeomBuffer.texCoord2d(0.75D - (paramDouble1 + paramDouble2) / 12.566370614359172D, (d2 - d1) / Math.PI);
/*     */             } 
/*     */             
/* 486 */             paramGeomBuffer.vertex3d(0.0D, d3 * this.radius, 0.0D);
/*     */           } 
/*     */           
/* 489 */           d8 -= d4;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 495 */         d8 = paramDouble1;
/* 496 */         double d9 = d6 * Math.cos(d8);
/* 497 */         double d10 = d6 * Math.sin(d8);
/* 498 */         paramGeomBuffer.normal3d(d9 * paramInt1, d5 * paramInt1, d10 * paramInt1);
/* 499 */         paramGeomBuffer.texCoord2d(0.75D - d8 / 6.283185307179586D, d7);
/* 500 */         paramGeomBuffer.vertex3d(d9 * this.radius, d5 * this.radius, d10 * this.radius);
/*     */       } 
/*     */ 
/*     */       
/* 504 */       paramGeomBuffer.end();
/*     */       
/* 506 */       if (b < paramInt2) {
/* 507 */         d2 += d1;
/*     */       } else {
/* 509 */         d2 = 1.5707963267948966D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\Sphere.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */