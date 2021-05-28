/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.Shape3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cone
/*     */   extends Primitive
/*     */ {
/*     */   float radius;
/*     */   float height;
/*     */   int xdivisions;
/*     */   int ydivisions;
/*     */   static final int MID_REZ_DIV_X = 15;
/*     */   static final int MID_REZ_DIV_Y = 1;
/*     */   public static final int BODY = 0;
/*     */   public static final int CAP = 1;
/*     */   
/* 100 */   public Cone() { this(1.0F, 2.0F, 1, 15, 1, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public Cone(float paramFloat1, float paramFloat2) { this(paramFloat1, paramFloat2, 1, 15, 1, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public Cone(float paramFloat1, float paramFloat2, Appearance paramAppearance) { this(paramFloat1, paramFloat2, 1, 15, 1, paramAppearance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public Cone(float paramFloat1, float paramFloat2, int paramInt, Appearance paramAppearance) { this(paramFloat1, paramFloat2, paramInt, 15, 1, paramAppearance); }
/*     */ 
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
/* 153 */     if (paramInt > 1 || paramInt < 0) return null; 
/* 154 */     return (Shape3D)getChild(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearance(Appearance paramAppearance) {
/* 165 */     ((Shape3D)getChild(0)).setAppearance(paramAppearance);
/* 166 */     ((Shape3D)getChild(1)).setAppearance(paramAppearance);
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
/*     */   public Appearance getAppearance(int paramInt) {
/* 180 */     if (paramInt > 1 || paramInt < 0) return null; 
/* 181 */     return getShape(paramInt).getAppearance();
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
/*     */   public Cone(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, Appearance paramAppearance) {
/* 207 */     Shape3D[] arrayOfShape3D = new Shape3D[2];
/* 208 */     this.radius = paramFloat1;
/* 209 */     this.height = paramFloat2;
/* 210 */     this.xdivisions = paramInt2;
/* 211 */     this.ydivisions = paramInt3;
/* 212 */     this.flags = paramInt1;
/* 213 */     boolean bool1 = ((this.flags & 0x4) == 0);
/* 214 */     boolean bool2 = ((this.flags & 0x8) != 0);
/* 215 */     Quadrics quadrics = new Quadrics();
/* 216 */     GeomBuffer geomBuffer1 = null;
/*     */     
/* 218 */     GeomBuffer geomBuffer2 = getCachedGeometry(4, paramFloat1, 0.0F, paramFloat2, paramInt2, paramInt3, paramInt1);
/*     */ 
/*     */     
/* 221 */     if (geomBuffer2 != null) {
/*     */       
/* 223 */       arrayOfShape3D[0] = new Shape3D(geomBuffer2.getComputedGeometry());
/* 224 */       this.numVerts += geomBuffer2.getNumVerts();
/* 225 */       this.numTris += geomBuffer2.getNumTris();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 230 */       geomBuffer1 = quadrics.coneTop(paramFloat2 / 2.0D - (paramFloat2 / this.ydivisions), (paramFloat1 / this.ydivisions), (paramFloat2 / this.ydivisions), this.xdivisions, 1.0D - 1.0D / this.ydivisions, bool1, bool2);
/*     */ 
/*     */ 
/*     */       
/* 234 */       arrayOfShape3D[0] = new Shape3D(geomBuffer1.getGeom(this.flags));
/* 235 */       this.numVerts += geomBuffer1.getNumVerts();
/* 236 */       this.numTris += geomBuffer1.getNumTris();
/* 237 */       if ((paramInt1 & 0x10) == 0) {
/* 238 */         cacheGeometry(4, paramFloat1, 0.0F, paramFloat2, paramInt2, paramInt3, paramInt1, geomBuffer1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     if (this.ydivisions > 1) {
/* 246 */       geomBuffer2 = getCachedGeometry(64, paramFloat1, 0.0F, paramFloat2, paramInt2, paramInt3, paramInt1);
/*     */       
/* 248 */       if (geomBuffer2 != null) {
/*     */         
/* 250 */         arrayOfShape3D[0].addGeometry(geomBuffer2.getComputedGeometry());
/* 251 */         this.numVerts += geomBuffer2.getNumVerts();
/* 252 */         this.numTris += geomBuffer2.getNumTris();
/*     */       } else {
/*     */         
/* 255 */         geomBuffer1 = quadrics.coneBody(-(paramFloat2 / 2.0D), paramFloat2 / 2.0D - (paramFloat2 / this.ydivisions), paramFloat1, (paramFloat1 / this.ydivisions), this.xdivisions, this.ydivisions - 1, 1.0D / this.ydivisions, bool1, bool2);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 260 */         arrayOfShape3D[0].addGeometry(geomBuffer1.getGeom(this.flags));
/* 261 */         this.numVerts += geomBuffer1.getNumVerts();
/* 262 */         this.numTris += geomBuffer1.getNumTris();
/* 263 */         if ((paramInt1 & 0x10) == 0) {
/* 264 */           cacheGeometry(64, paramFloat1, 0.0F, paramFloat2, paramInt2, paramInt3, paramInt1, geomBuffer1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 270 */     if ((this.flags & 0x40) != 0) {
/* 271 */       arrayOfShape3D[0].setCapability(14);
/* 272 */       arrayOfShape3D[0].setCapability(15);
/*     */     } 
/*     */     
/* 275 */     if ((this.flags & 0x20) != 0) {
/* 276 */       arrayOfShape3D[0].setCapability(12);
/*     */     }
/*     */     
/* 279 */     addChild(arrayOfShape3D[0]);
/*     */ 
/*     */     
/* 282 */     geomBuffer2 = getCachedGeometry(32, paramFloat1, paramFloat1, -paramFloat2 / 2.0F, paramInt2, paramInt2, paramInt1);
/*     */ 
/*     */     
/* 285 */     if (geomBuffer2 != null) {
/*     */       
/* 287 */       arrayOfShape3D[1] = new Shape3D(geomBuffer2.getComputedGeometry());
/* 288 */       this.numVerts += geomBuffer2.getNumVerts();
/* 289 */       this.numTris += geomBuffer2.getNumTris();
/*     */     } else {
/*     */       
/* 292 */       geomBuffer1 = quadrics.disk(paramFloat1, paramInt2, -(paramFloat2) / 2.0D, !bool1, bool2);
/*     */       
/* 294 */       arrayOfShape3D[1] = new Shape3D(geomBuffer1.getGeom(this.flags));
/* 295 */       this.numVerts += geomBuffer1.getNumVerts();
/* 296 */       this.numTris += geomBuffer1.getNumTris();
/* 297 */       if ((paramInt1 & 0x10) == 0) {
/* 298 */         cacheGeometry(32, paramFloat1, paramFloat1, -paramFloat2 / 2.0F, paramInt2, paramInt2, paramInt1, geomBuffer1);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 303 */     if ((this.flags & 0x40) != 0) {
/* 304 */       arrayOfShape3D[1].setCapability(14);
/* 305 */       arrayOfShape3D[1].setCapability(15);
/*     */     } 
/*     */     
/* 308 */     if ((this.flags & 0x20) != 0) {
/* 309 */       arrayOfShape3D[1].setCapability(12);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     addChild(arrayOfShape3D[1]);
/*     */     
/* 327 */     if (paramAppearance == null) {
/* 328 */       setAppearance();
/*     */     } else {
/* 330 */       setAppearance(paramAppearance);
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
/* 357 */     Cone cone = new Cone(this.radius, this.height, this.flags, this.xdivisions, this.ydivisions, getAppearance());
/*     */     
/* 359 */     cone.duplicateNode(this, paramBoolean);
/* 360 */     return cone;
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
/* 390 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { super.duplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 399 */   public float getRadius() { return this.radius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 408 */   public float getHeight() { return this.height; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   public int getXdivisions() { return this.xdivisions; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   public int getYdivisions() { return this.ydivisions; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\Cone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */