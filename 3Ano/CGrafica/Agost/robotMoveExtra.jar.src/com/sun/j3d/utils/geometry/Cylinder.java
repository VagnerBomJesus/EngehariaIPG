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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cylinder
/*     */   extends Primitive
/*     */ {
/*     */   float radius;
/*     */   float height;
/*     */   int xdivisions;
/*     */   int ydivisions;
/*     */   static final int MID_REZ_DIV_X = 15;
/*     */   static final int MID_REZ_DIV_Y = 1;
/*     */   public static final int BODY = 0;
/*     */   public static final int TOP = 1;
/*     */   public static final int BOTTOM = 2;
/*     */   
/* 110 */   public Cylinder() { this(1.0F, 2.0F, 1, 15, 1, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public Cylinder(float paramFloat1, float paramFloat2) { this(paramFloat1, paramFloat2, 1, 15, 1, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public Cylinder(float paramFloat1, float paramFloat2, Appearance paramAppearance) { this(paramFloat1, paramFloat2, 1, 15, 1, paramAppearance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public Cylinder(float paramFloat1, float paramFloat2, int paramInt, Appearance paramAppearance) { this(paramFloat1, paramFloat2, paramInt, 15, 1, paramAppearance); }
/*     */ 
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
/* 161 */     if (paramInt > 2 || paramInt < 0) return null; 
/* 162 */     return (Shape3D)getChild(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearance(Appearance paramAppearance) {
/* 171 */     ((Shape3D)getChild(0)).setAppearance(paramAppearance);
/* 172 */     ((Shape3D)getChild(1)).setAppearance(paramAppearance);
/* 173 */     ((Shape3D)getChild(2)).setAppearance(paramAppearance);
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
/* 187 */     if (paramInt > 2 || paramInt < 0) return null; 
/* 188 */     return getShape(paramInt).getAppearance();
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
/*     */   public Cylinder(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, Appearance paramAppearance) {
/* 209 */     this.radius = paramFloat1;
/* 210 */     this.height = paramFloat2;
/* 211 */     this.xdivisions = paramInt2;
/* 212 */     this.ydivisions = paramInt3;
/* 213 */     this.flags = paramInt1;
/* 214 */     boolean bool1 = ((this.flags & 0x4) == 0);
/* 215 */     boolean bool2 = ((this.flags & 0x8) != 0);
/*     */     
/* 217 */     Quadrics quadrics = new Quadrics();
/* 218 */     GeomBuffer geomBuffer1 = null;
/* 219 */     Shape3D[] arrayOfShape3D = new Shape3D[3];
/*     */     
/* 221 */     GeomBuffer geomBuffer2 = getCachedGeometry(2, 0.0F, paramFloat1, paramFloat2, paramInt2, paramInt3, paramInt1);
/*     */ 
/*     */     
/* 224 */     if (geomBuffer2 != null) {
/*     */       
/* 226 */       arrayOfShape3D[0] = new Shape3D(geomBuffer2.getComputedGeometry());
/* 227 */       this.numVerts += geomBuffer2.getNumVerts();
/* 228 */       this.numTris += geomBuffer2.getNumTris();
/*     */     } else {
/*     */       
/* 231 */       geomBuffer1 = quadrics.cylinder(paramFloat2, paramFloat1, paramInt2, paramInt3, bool1, bool2);
/*     */       
/* 233 */       arrayOfShape3D[0] = new Shape3D(geomBuffer1.getGeom(this.flags));
/* 234 */       this.numVerts += geomBuffer1.getNumVerts();
/* 235 */       this.numTris += geomBuffer1.getNumTris();
/* 236 */       if ((paramInt1 & 0x10) == 0) {
/* 237 */         cacheGeometry(2, 0.0F, paramFloat1, paramFloat2, paramInt2, paramInt3, paramInt1, geomBuffer1);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 242 */     if ((this.flags & 0x40) != 0) {
/* 243 */       arrayOfShape3D[0].setCapability(14);
/* 244 */       arrayOfShape3D[0].setCapability(15);
/*     */     } 
/*     */     
/* 247 */     if ((this.flags & 0x20) != 0) {
/* 248 */       arrayOfShape3D[0].setCapability(12);
/*     */     }
/*     */     
/* 251 */     addChild(arrayOfShape3D[0]);
/*     */ 
/*     */     
/* 254 */     geomBuffer2 = getCachedGeometry(16, paramFloat1, paramFloat1, paramFloat2 / 2.0F, paramInt2, paramInt2, paramInt1);
/*     */     
/* 256 */     if (geomBuffer2 != null) {
/*     */       
/* 258 */       arrayOfShape3D[1] = new Shape3D(geomBuffer2.getComputedGeometry());
/* 259 */       this.numVerts += geomBuffer2.getNumVerts();
/* 260 */       this.numTris += geomBuffer2.getNumTris();
/*     */     } else {
/*     */       
/* 263 */       geomBuffer1 = quadrics.disk(paramFloat1, paramInt2, paramFloat2 / 2.0D, bool1, bool2);
/*     */       
/* 265 */       arrayOfShape3D[1] = new Shape3D(geomBuffer1.getGeom(this.flags));
/* 266 */       this.numVerts += geomBuffer1.getNumVerts();
/* 267 */       this.numTris += geomBuffer1.getNumTris();
/* 268 */       if ((paramInt1 & 0x10) == 0) {
/* 269 */         cacheGeometry(16, paramFloat1, paramFloat1, paramFloat2 / 2.0F, paramInt2, paramInt2, paramInt1, geomBuffer1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 275 */     if ((this.flags & 0x40) != 0) {
/* 276 */       arrayOfShape3D[1].setCapability(14);
/* 277 */       arrayOfShape3D[1].setCapability(15);
/*     */     } 
/*     */     
/* 280 */     if ((this.flags & 0x20) != 0) {
/* 281 */       arrayOfShape3D[1].setCapability(12);
/*     */     }
/*     */     
/* 284 */     addChild(arrayOfShape3D[1]);
/*     */ 
/*     */     
/* 287 */     geomBuffer2 = getCachedGeometry(32, paramFloat1, paramFloat1, -paramFloat2 / 2.0F, paramInt2, paramInt2, paramInt1);
/*     */ 
/*     */     
/* 290 */     if (geomBuffer2 != null) {
/*     */       
/* 292 */       arrayOfShape3D[2] = new Shape3D(geomBuffer2.getComputedGeometry());
/* 293 */       this.numVerts += geomBuffer2.getNumVerts();
/* 294 */       this.numTris += geomBuffer2.getNumTris();
/*     */     } else {
/*     */       
/* 297 */       geomBuffer1 = quadrics.disk(paramFloat1, paramInt2, -paramFloat2 / 2.0D, !bool1, bool2);
/* 298 */       arrayOfShape3D[2] = new Shape3D(geomBuffer1.getGeom(this.flags));
/* 299 */       this.numVerts += geomBuffer1.getNumVerts();
/* 300 */       this.numTris += geomBuffer1.getNumTris();
/* 301 */       if ((paramInt1 & 0x10) == 0) {
/* 302 */         cacheGeometry(32, paramFloat1, paramFloat1, -paramFloat2 / 2.0F, paramInt2, paramInt2, paramInt1, geomBuffer1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 308 */     if ((this.flags & 0x40) != 0) {
/* 309 */       arrayOfShape3D[2].setCapability(14);
/* 310 */       arrayOfShape3D[2].setCapability(15);
/*     */     } 
/*     */     
/* 313 */     if ((this.flags & 0x20) != 0) {
/* 314 */       arrayOfShape3D[2].setCapability(12);
/*     */     }
/*     */     
/* 317 */     addChild(arrayOfShape3D[2]);
/*     */ 
/*     */     
/* 320 */     if (paramAppearance == null) {
/* 321 */       setAppearance();
/*     */     } else {
/* 323 */       setAppearance(paramAppearance);
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
/* 350 */     Cylinder cylinder = new Cylinder(this.radius, this.height, this.flags, this.xdivisions, this.ydivisions, getAppearance());
/*     */     
/* 352 */     cylinder.duplicateNode(this, paramBoolean);
/* 353 */     return cylinder;
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
/* 383 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { super.duplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 392 */   public float getRadius() { return this.radius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public float getHeight() { return this.height; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 410 */   public int getXdivisions() { return this.xdivisions; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 419 */   public int getYdivisions() { return this.ydivisions; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\Cylinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */