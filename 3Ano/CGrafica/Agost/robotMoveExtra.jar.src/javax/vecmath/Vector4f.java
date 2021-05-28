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
/*     */ public class Vector4f
/*     */   extends Tuple4f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 8749319902347760659L;
/*     */   
/*  36 */   public Vector4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { super(paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Vector4f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public Vector4f(Vector4f paramVector4f) { super(paramVector4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public Vector4f(Vector4d paramVector4d) { super(paramVector4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public Vector4f(Tuple4f paramTuple4f) { super(paramTuple4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Vector4f(Tuple4d paramTuple4d) { super(paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Vector4f(Tuple3f paramTuple3f) { super(paramTuple3f.x, paramTuple3f.y, paramTuple3f.z, 0.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector4f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3f paramTuple3f) {
/* 122 */     this.x = paramTuple3f.x;
/* 123 */     this.y = paramTuple3f.y;
/* 124 */     this.z = paramTuple3f.z;
/* 125 */     this.w = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public final float length() { return (float)Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public final float lengthSquared() { return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public final float dot(Vector4f paramVector4f) { return this.x * paramVector4f.x + this.y * paramVector4f.y + this.z * paramVector4f.z + this.w * paramVector4f.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize(Vector4f paramVector4f) {
/* 169 */     float f = (float)(1.0D / Math.sqrt((paramVector4f.x * paramVector4f.x + paramVector4f.y * paramVector4f.y + paramVector4f.z * paramVector4f.z + paramVector4f.w * paramVector4f.w)));
/*     */     
/* 171 */     paramVector4f.x *= f;
/* 172 */     paramVector4f.y *= f;
/* 173 */     paramVector4f.z *= f;
/* 174 */     paramVector4f.w *= f;
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
/* 185 */     float f = (float)(1.0D / Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w)));
/*     */     
/* 187 */     this.x *= f;
/* 188 */     this.y *= f;
/* 189 */     this.z *= f;
/* 190 */     this.w *= f;
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
/*     */   public final float angle(Vector4f paramVector4f) {
/* 203 */     double d = (dot(paramVector4f) / length() * paramVector4f.length());
/* 204 */     if (d < -1.0D) d = -1.0D; 
/* 205 */     if (d > 1.0D) d = 1.0D; 
/* 206 */     return (float)Math.acos(d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Vector4f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */