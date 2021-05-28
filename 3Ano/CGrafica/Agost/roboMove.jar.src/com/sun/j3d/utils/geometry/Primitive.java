/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Group;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.Shape3D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Primitive
/*     */   extends Group
/*     */ {
/*     */   public static final int GENERATE_NORMALS = 1;
/*     */   public static final int GENERATE_TEXTURE_COORDS = 2;
/*     */   public static final int GENERATE_NORMALS_INWARD = 4;
/*     */   public static final int GENERATE_TEXTURE_COORDS_Y_UP = 8;
/*     */   public static final int GEOMETRY_NOT_SHARED = 16;
/*     */   public static final int ENABLE_GEOMETRY_PICKING = 32;
/*     */   public static final int ENABLE_APPEARANCE_MODIFY = 64;
/*     */   static final int SPHERE = 1;
/*     */   static final int CYLINDER = 2;
/*     */   static final int CONE = 4;
/*     */   static final int BOX = 8;
/*     */   static final int TOP_DISK = 16;
/*     */   static final int BOTTOM_DISK = 32;
/*     */   static final int CONE_DIVISIONS = 64;
/*     */   int numTris;
/*     */   int numVerts;
/*     */   int flags;
/*     */   
/*     */   public Primitive() {
/* 127 */     this.numTris = 0;
/* 128 */     this.numVerts = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     this.flags = 0;
/* 142 */     setCapability(1);
/* 143 */     setCapability(12);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public int getNumTriangles() { return this.numTris; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void setNumTriangles(int paramInt) { System.err.println("Warning: setNumTriangles has no effect"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public int getNumVertices() { return this.numVerts; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public void setNumVertices(int paramInt) { System.err.println("Warning: setNumVertices has no effect"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public int getPrimitiveFlags() { return this.flags; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setPrimitiveFlags(int paramInt) { System.err.println("Warning: setPrimitiveFlags has no effect"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Shape3D getShape(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public Appearance getAppearance() { return getShape(0).getAppearance(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Appearance getAppearance(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public void setAppearance(int paramInt, Appearance paramAppearance) { getShape(paramInt).setAppearance(paramAppearance); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void setAppearance(Appearance paramAppearance);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearance() {
/* 233 */     Color3f color3f1 = new Color3f(0.1F, 0.1F, 0.1F);
/* 234 */     Color3f color3f2 = new Color3f(0.0F, 0.0F, 0.0F);
/* 235 */     Color3f color3f3 = new Color3f(0.6F, 0.6F, 0.6F);
/* 236 */     Color3f color3f4 = new Color3f(1.0F, 1.0F, 1.0F);
/*     */     
/* 238 */     Material material = new Material(color3f1, color3f2, color3f3, color3f4, 100.0F);
/* 239 */     Appearance appearance = new Appearance();
/* 240 */     material.setLightingEnable(true);
/* 241 */     appearance.setMaterial(material);
/* 242 */     setAppearance(appearance);
/*     */   }
/*     */   
/* 245 */   static Hashtable geomCache = new Hashtable();
/*     */ 
/*     */ 
/*     */   
/* 249 */   String strfloat(float paramFloat) { return (new Float(paramFloat)).toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void cacheGeometry(int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt2, int paramInt3, int paramInt4, GeomBuffer paramGeomBuffer) {
/* 256 */     String str = new String(paramInt1 + strfloat(paramFloat1) + strfloat(paramFloat2) + strfloat(paramFloat3) + paramInt2 + paramInt3 + paramInt4);
/*     */     
/* 258 */     geomCache.put(str, paramGeomBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected GeomBuffer getCachedGeometry(int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt2, int paramInt3, int paramInt4) {
/* 264 */     String str = new String(paramInt1 + strfloat(paramFloat1) + strfloat(paramFloat2) + strfloat(paramFloat3) + paramInt2 + paramInt3 + paramInt4);
/*     */     
/* 266 */     Object object = geomCache.get(str);
/*     */     
/* 268 */     return (GeomBuffer)object;
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
/* 279 */   public static void clearGeometryCache() { geomCache.clear(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\Primitive.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */