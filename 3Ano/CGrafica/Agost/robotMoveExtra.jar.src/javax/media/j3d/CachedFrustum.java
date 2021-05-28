/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Vector4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CachedFrustum
/*     */ {
/*     */   static final double EPSILON = 1.0E-8D;
/*     */   Vector4d[] clipPlanes;
/*     */   Point3d[] verts;
/*     */   Point3d upper;
/*     */   Point3d lower;
/*     */   Point3d center;
/*     */   
/*     */   CachedFrustum(Vector4d[] paramArrayOfVector4d) {
/*  49 */     if (paramArrayOfVector4d.length < 6) {
/*  50 */       throw new IllegalArgumentException(J3dI18N.getString("CachedFrustum0"));
/*     */     }
/*  52 */     this.clipPlanes = new Vector4d[6];
/*  53 */     this.verts = new Point3d[8];
/*  54 */     this.upper = new Point3d();
/*  55 */     this.lower = new Point3d();
/*  56 */     this.center = new Point3d();
/*     */     byte b;
/*  58 */     for (b = 0; b < 8; b++) {
/*  59 */       this.verts[b] = new Point3d();
/*     */     }
/*     */     
/*  62 */     for (b = 0; b < 6; b++) {
/*  63 */       this.clipPlanes[b] = new Vector4d(paramArrayOfVector4d[b]);
/*     */     }
/*  65 */     computeValues(this.clipPlanes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CachedFrustum() {
/*  74 */     this.clipPlanes = new Vector4d[6];
/*  75 */     this.upper = new Point3d();
/*  76 */     this.lower = new Point3d();
/*  77 */     this.verts = new Point3d[8];
/*  78 */     this.center = new Point3d();
/*     */     byte b;
/*  80 */     for (b = 0; b < 8; b++) {
/*  81 */       this.verts[b] = new Point3d();
/*     */     }
/*  83 */     for (b = 0; b < 6; b++) {
/*  84 */       this.clipPlanes[b] = new Vector4d();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public String toString() { return this.clipPlanes[0].toString() + "\n" + this.clipPlanes[1].toString() + "\n" + this.clipPlanes[2].toString() + "\n" + this.clipPlanes[3].toString() + "\n" + this.clipPlanes[4].toString() + "\n" + this.clipPlanes[5].toString() + "\n" + "corners=" + "\n" + this.verts[0].toString() + "\n" + this.verts[1].toString() + "\n" + this.verts[2].toString() + "\n" + this.verts[3].toString() + "\n" + this.verts[4].toString() + "\n" + this.verts[5].toString() + "\n" + this.verts[6].toString() + "\n" + this.verts[7].toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void set(Vector4d[] paramArrayOfVector4d) {
/* 119 */     if (paramArrayOfVector4d.length != 6) {
/* 120 */       throw new IllegalArgumentException(J3dI18N.getString("CachedFrustum1"));
/*     */     }
/*     */     
/* 123 */     for (byte b = 0; b < 6; b++) {
/* 124 */       this.clipPlanes[b].set(paramArrayOfVector4d[b]);
/*     */     }
/*     */     
/* 127 */     computeValues(this.clipPlanes);
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
/*     */   private void computeValues(Vector4d[] paramArrayOfVector4d) {
/* 140 */     computeVertex(0, 3, 4, this.verts[0]);
/* 141 */     computeVertex(0, 2, 4, this.verts[1]);
/* 142 */     computeVertex(1, 2, 4, this.verts[2]);
/* 143 */     computeVertex(1, 3, 4, this.verts[3]);
/* 144 */     computeVertex(0, 3, 5, this.verts[4]);
/* 145 */     computeVertex(0, 2, 5, this.verts[5]);
/* 146 */     computeVertex(1, 2, 5, this.verts[6]);
/* 147 */     computeVertex(1, 3, 5, this.verts[7]);
/*     */ 
/*     */ 
/*     */     
/* 151 */     this.upper.x = (this.verts[0]).x;
/* 152 */     this.upper.y = (this.verts[0]).y;
/* 153 */     this.upper.z = (this.verts[0]).z;
/* 154 */     this.lower.x = (this.verts[0]).x;
/* 155 */     this.lower.y = (this.verts[0]).y;
/* 156 */     this.lower.z = (this.verts[0]).z;
/*     */     
/* 158 */     this.center.x = (this.verts[0]).x;
/* 159 */     this.center.y = (this.verts[0]).y;
/* 160 */     this.center.z = (this.verts[0]).z;
/*     */ 
/*     */ 
/*     */     
/* 164 */     for (byte b = 1; b < 8; b++) {
/* 165 */       if ((this.verts[b]).x > this.upper.x) this.upper.x = (this.verts[b]).x; 
/* 166 */       if ((this.verts[b]).x < this.lower.x) this.lower.x = (this.verts[b]).x;
/*     */       
/* 168 */       if ((this.verts[b]).y > this.upper.y) this.upper.y = (this.verts[b]).y; 
/* 169 */       if ((this.verts[b]).y < this.lower.y) this.lower.y = (this.verts[b]).y;
/*     */       
/* 171 */       if ((this.verts[b]).z > this.upper.z) this.upper.z = (this.verts[b]).z; 
/* 172 */       if ((this.verts[b]).z < this.lower.z) this.lower.z = (this.verts[b]).z;
/*     */       
/* 174 */       this.center.x += (this.verts[b]).x;
/* 175 */       this.center.y += (this.verts[b]).y;
/* 176 */       this.center.z += (this.verts[b]).z;
/*     */     } 
/*     */     
/* 179 */     this.center.x *= 0.125D;
/* 180 */     this.center.y *= 0.125D;
/* 181 */     this.center.z *= 0.125D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeVertex(int paramInt1, int paramInt2, int paramInt3, Point3d paramPoint3d) {
/* 188 */     double d = (this.clipPlanes[paramInt1]).x * (this.clipPlanes[paramInt2]).y * (this.clipPlanes[paramInt3]).z + (this.clipPlanes[paramInt1]).y * (this.clipPlanes[paramInt2]).z * (this.clipPlanes[paramInt3]).x + (this.clipPlanes[paramInt1]).z * (this.clipPlanes[paramInt2]).x * (this.clipPlanes[paramInt3]).y - (this.clipPlanes[paramInt1]).z * (this.clipPlanes[paramInt2]).y * (this.clipPlanes[paramInt3]).x - (this.clipPlanes[paramInt1]).y * (this.clipPlanes[paramInt2]).x * (this.clipPlanes[paramInt3]).z - (this.clipPlanes[paramInt1]).x * (this.clipPlanes[paramInt2]).z * (this.clipPlanes[paramInt3]).y;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     if (d * d < 1.0E-8D) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 198 */     d = 1.0D / d;
/*     */     
/* 200 */     paramPoint3d.x = ((this.clipPlanes[paramInt2]).y * (this.clipPlanes[paramInt3]).z - (this.clipPlanes[paramInt2]).z * (this.clipPlanes[paramInt3]).y) * -(this.clipPlanes[paramInt1]).w;
/* 201 */     paramPoint3d.y = ((this.clipPlanes[paramInt2]).z * (this.clipPlanes[paramInt3]).x - (this.clipPlanes[paramInt2]).x * (this.clipPlanes[paramInt3]).z) * -(this.clipPlanes[paramInt1]).w;
/* 202 */     paramPoint3d.z = ((this.clipPlanes[paramInt2]).x * (this.clipPlanes[paramInt3]).y - (this.clipPlanes[paramInt2]).y * (this.clipPlanes[paramInt3]).x) * -(this.clipPlanes[paramInt1]).w;
/*     */     
/* 204 */     paramPoint3d.x += ((this.clipPlanes[paramInt3]).y * (this.clipPlanes[paramInt1]).z - (this.clipPlanes[paramInt3]).z * (this.clipPlanes[paramInt1]).y) * -(this.clipPlanes[paramInt2]).w;
/* 205 */     paramPoint3d.y += ((this.clipPlanes[paramInt3]).z * (this.clipPlanes[paramInt1]).x - (this.clipPlanes[paramInt3]).x * (this.clipPlanes[paramInt1]).z) * -(this.clipPlanes[paramInt2]).w;
/* 206 */     paramPoint3d.z += ((this.clipPlanes[paramInt3]).x * (this.clipPlanes[paramInt1]).y - (this.clipPlanes[paramInt3]).y * (this.clipPlanes[paramInt1]).x) * -(this.clipPlanes[paramInt2]).w;
/*     */     
/* 208 */     paramPoint3d.x += ((this.clipPlanes[paramInt1]).y * (this.clipPlanes[paramInt2]).z - (this.clipPlanes[paramInt1]).z * (this.clipPlanes[paramInt2]).y) * -(this.clipPlanes[paramInt3]).w;
/* 209 */     paramPoint3d.y += ((this.clipPlanes[paramInt1]).z * (this.clipPlanes[paramInt2]).x - (this.clipPlanes[paramInt1]).x * (this.clipPlanes[paramInt2]).z) * -(this.clipPlanes[paramInt3]).w;
/* 210 */     paramPoint3d.z += ((this.clipPlanes[paramInt1]).x * (this.clipPlanes[paramInt2]).y - (this.clipPlanes[paramInt1]).y * (this.clipPlanes[paramInt2]).x) * -(this.clipPlanes[paramInt3]).w;
/*     */     
/* 212 */     paramPoint3d.x *= d;
/* 213 */     paramPoint3d.y *= d;
/* 214 */     paramPoint3d.z *= d;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\CachedFrustum.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */