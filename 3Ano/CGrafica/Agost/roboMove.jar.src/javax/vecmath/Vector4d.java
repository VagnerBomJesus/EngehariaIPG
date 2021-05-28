/*     */ package javax.vecmath;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Vector4d
/*     */   extends Tuple4d
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3938123424117448700L;
/*     */   
/*  36 */   public Vector4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) { super(paramDouble1, paramDouble2, paramDouble3, paramDouble4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Vector4d(double[] paramArrayOfDouble) { super(paramArrayOfDouble); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public Vector4d(Vector4d paramVector4d) { super(paramVector4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Vector4d(Vector4f paramVector4f) { super(paramVector4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public Vector4d(Tuple4f paramTuple4f) { super(paramTuple4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public Vector4d(Tuple4d paramTuple4d) { super(paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public Vector4d(Tuple3d paramTuple3d) { super(paramTuple3d.x, paramTuple3d.y, paramTuple3d.z, 0.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector4d() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3d paramTuple3d) {
/* 118 */     this.x = paramTuple3d.x;
/* 119 */     this.y = paramTuple3d.y;
/* 120 */     this.z = paramTuple3d.z;
/* 121 */     this.w = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public final double length() { return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public final double lengthSquared() { return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public final double dot(Vector4d paramVector4d) { return this.x * paramVector4d.x + this.y * paramVector4d.y + this.z * paramVector4d.z + this.w * paramVector4d.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize(Vector4d paramVector4d) {
/* 166 */     double d = 1.0D / Math.sqrt(paramVector4d.x * paramVector4d.x + paramVector4d.y * paramVector4d.y + paramVector4d.z * paramVector4d.z + paramVector4d.w * paramVector4d.w);
/* 167 */     paramVector4d.x *= d;
/* 168 */     paramVector4d.y *= d;
/* 169 */     paramVector4d.z *= d;
/* 170 */     paramVector4d.w *= d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize() {
/* 181 */     double d = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
/*     */     
/* 183 */     this.x *= d;
/* 184 */     this.y *= d;
/* 185 */     this.z *= d;
/* 186 */     this.w *= d;
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
/*     */   public final double angle(Vector4d paramVector4d) {
/* 199 */     double d = dot(paramVector4d) / length() * paramVector4d.length();
/* 200 */     if (d < -1.0D) d = -1.0D; 
/* 201 */     if (d > 1.0D) d = 1.0D; 
/* 202 */     return Math.acos(d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\Vector4d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */