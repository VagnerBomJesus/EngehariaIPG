/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.Shape3D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Box
/*     */   extends Primitive
/*     */ {
/*     */   public static final int FRONT = 0;
/*     */   public static final int BACK = 1;
/*     */   public static final int RIGHT = 2;
/*     */   public static final int LEFT = 3;
/*     */   public static final int TOP = 4;
/*     */   public static final int BOTTOM = 5;
/*     */   float xDim;
/*     */   float yDim;
/*     */   float zDim;
/*     */   int numTexUnit;
/*     */   
/* 132 */   public Box() { this(1.0F, 1.0F, 1.0F, 1, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public Box(float paramFloat1, float paramFloat2, float paramFloat3, Appearance paramAppearance) { this(paramFloat1, paramFloat2, paramFloat3, 1, paramAppearance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Box(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, Appearance paramAppearance, int paramInt2) {
/*     */     this.numTexUnit = 1;
/* 165 */     this.xDim = paramFloat1;
/* 166 */     this.yDim = paramFloat2;
/* 167 */     this.zDim = paramFloat3;
/* 168 */     this.flags = paramInt1;
/* 169 */     paramInt2 = paramInt2;
/* 170 */     boolean bool = ((this.flags & 0x8) != 0) ? 1 : 0;
/*     */ 
/*     */     
/* 173 */     if ((this.flags & 0x4) != 0)
/* 174 */     { d = -1.0D; }
/* 175 */     else { d = 1.0D; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     Shape3D[] arrayOfShape3D = new Shape3D[6];
/*     */     
/* 184 */     GeomBuffer geomBuffer = null;
/*     */     
/* 186 */     for (byte b = 0; b <= 5; b++) {
/*     */       
/* 188 */       geomBuffer = getCachedGeometry(8, paramFloat1, paramFloat2, paramFloat3, b, b, paramInt1);
/*     */       
/* 190 */       if (geomBuffer != null) {
/*     */         
/* 192 */         arrayOfShape3D[b] = new Shape3D(geomBuffer.getComputedGeometry());
/* 193 */         this.numVerts += geomBuffer.getNumVerts();
/* 194 */         this.numTris += geomBuffer.getNumTris();
/*     */       }
/*     */       else {
/*     */         
/* 198 */         GeomBuffer geomBuffer1 = new GeomBuffer(4, paramInt2);
/*     */         
/* 200 */         geomBuffer1.begin(1); byte b1;
/* 201 */         for (b1 = 0; b1 < 2; b1++) {
/* 202 */           geomBuffer1.normal3d((normals[b]).x * d, (normals[b]).y * d, (normals[b]).z * d);
/*     */ 
/*     */           
/* 205 */           if (bool) {
/* 206 */             geomBuffer1.texCoord2d(tcoords[b * 8 + b1 * 2], 1.0D - tcoords[b * 8 + b1 * 2 + 1]);
/*     */           } else {
/*     */             
/* 209 */             geomBuffer1.texCoord2d(tcoords[b * 8 + b1 * 2], tcoords[b * 8 + b1 * 2 + 1]);
/*     */           } 
/*     */           
/* 212 */           geomBuffer1.vertex3d(verts[b * 12 + b1 * 3] * paramFloat1, verts[b * 12 + b1 * 3 + 1] * paramFloat2, verts[b * 12 + b1 * 3 + 2] * paramFloat3);
/*     */         } 
/*     */ 
/*     */         
/* 216 */         for (b1 = 3; b1 > 1; b1--) {
/* 217 */           geomBuffer1.normal3d((normals[b]).x * d, (normals[b]).y * d, (normals[b]).z * d);
/*     */ 
/*     */           
/* 220 */           if (bool) {
/* 221 */             geomBuffer1.texCoord2d(tcoords[b * 8 + b1 * 2], 1.0D - tcoords[b * 8 + b1 * 2 + 1]);
/*     */           } else {
/*     */             
/* 224 */             geomBuffer1.texCoord2d(tcoords[b * 8 + b1 * 2], tcoords[b * 8 + b1 * 2 + 1]);
/*     */           } 
/* 226 */           geomBuffer1.vertex3d(verts[b * 12 + b1 * 3] * paramFloat1, verts[b * 12 + b1 * 3 + 1] * paramFloat2, verts[b * 12 + b1 * 3 + 2] * paramFloat3);
/*     */         } 
/*     */ 
/*     */         
/* 230 */         geomBuffer1.end();
/* 231 */         arrayOfShape3D[b] = new Shape3D(geomBuffer1.getGeom(this.flags));
/* 232 */         this.numVerts = geomBuffer1.getNumVerts();
/* 233 */         this.numTris = geomBuffer1.getNumTris();
/*     */         
/* 235 */         if ((paramInt1 & 0x10) == 0) {
/* 236 */           cacheGeometry(8, paramFloat1, paramFloat2, paramFloat3, b, b, paramInt1, geomBuffer1);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 241 */       if ((this.flags & 0x40) != 0) {
/* 242 */         arrayOfShape3D[b].setCapability(14);
/* 243 */         arrayOfShape3D[b].setCapability(15);
/*     */       } 
/*     */       
/* 246 */       if ((this.flags & 0x20) != 0) {
/* 247 */         arrayOfShape3D[b].setCapability(12);
/*     */       }
/*     */ 
/*     */       
/* 251 */       addChild(arrayOfShape3D[b]);
/*     */     } 
/*     */     
/* 254 */     if (paramAppearance == null) {
/* 255 */       setAppearance();
/*     */     } else {
/* 257 */       setAppearance(paramAppearance);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 262 */   public Box(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, Appearance paramAppearance) { this(paramFloat1, paramFloat2, paramFloat3, paramInt, paramAppearance, 1); }
/*     */ 
/*     */ 
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
/* 275 */     if (paramInt >= 0 && paramInt <= 5)
/*     */     {
/* 277 */       return (Shape3D)getChild(paramInt); } 
/* 278 */     return null;
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
/*     */   public void setAppearance(Appearance paramAppearance) {
/* 295 */     ((Shape3D)getChild(4)).setAppearance(paramAppearance);
/* 296 */     ((Shape3D)getChild(3)).setAppearance(paramAppearance);
/* 297 */     ((Shape3D)getChild(2)).setAppearance(paramAppearance);
/* 298 */     ((Shape3D)getChild(0)).setAppearance(paramAppearance);
/* 299 */     ((Shape3D)getChild(1)).setAppearance(paramAppearance);
/* 300 */     ((Shape3D)getChild(5)).setAppearance(paramAppearance);
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
/* 314 */     if (paramInt > 5 || paramInt < 0) return null; 
/* 315 */     return getShape(paramInt).getAppearance();
/*     */   }
/*     */   
/*     */   private static final float[] verts = { 
/* 319 */       1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, -1.0F, -1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final double[] tcoords = { 
/* 352 */       1.0D, 0.0D, 1.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 1.0D };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 386 */   private static final Vector3f[] normals = { new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(0.0F, 0.0F, -1.0F), new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, -1.0F, 0.0F) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 420 */     Box box = new Box(this.xDim, this.yDim, this.zDim, this.flags, getAppearance());
/* 421 */     box.duplicateNode(this, paramBoolean);
/* 422 */     return box;
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
/* 452 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { super.duplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 461 */   public float getXdimension() { return this.xDim; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 470 */   public float getYdimension() { return this.yDim; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 479 */   public float getZdimension() { return this.zDim; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\Box.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */