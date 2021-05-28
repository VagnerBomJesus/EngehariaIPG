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
/*     */ public class Vector3f
/*     */   extends Tuple3f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -7031930069184524614L;
/*     */   
/*  36 */   public Vector3f(float paramFloat1, float paramFloat2, float paramFloat3) { super(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Vector3f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public Vector3f(Vector3f paramVector3f) { super(paramVector3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public Vector3f(Vector3d paramVector3d) { super(paramVector3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public Vector3f(Tuple3f paramTuple3f) { super(paramTuple3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public Vector3f(Tuple3d paramTuple3d) { super(paramTuple3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector3f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public final float lengthSquared() { return this.x * this.x + this.y * this.y + this.z * this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public final float length() { return (float)Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void cross(Vector3f paramVector3f1, Vector3f paramVector3f2) {
/* 126 */     float f1 = paramVector3f1.y * paramVector3f2.z - paramVector3f1.z * paramVector3f2.y;
/* 127 */     float f2 = paramVector3f2.x * paramVector3f1.z - paramVector3f2.z * paramVector3f1.x;
/* 128 */     this.z = paramVector3f1.x * paramVector3f2.y - paramVector3f1.y * paramVector3f2.x;
/* 129 */     this.x = f1;
/* 130 */     this.y = f2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public final float dot(Vector3f paramVector3f) { return this.x * paramVector3f.x + this.y * paramVector3f.y + this.z * paramVector3f.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize(Vector3f paramVector3f) {
/* 151 */     float f = (float)(1.0D / Math.sqrt((paramVector3f.x * paramVector3f.x + paramVector3f.y * paramVector3f.y + paramVector3f.z * paramVector3f.z)));
/* 152 */     paramVector3f.x *= f;
/* 153 */     paramVector3f.y *= f;
/* 154 */     paramVector3f.z *= f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize() {
/* 164 */     float f = (float)(1.0D / Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z)));
/*     */     
/* 166 */     this.x *= f;
/* 167 */     this.y *= f;
/* 168 */     this.z *= f;
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
/*     */   public final float angle(Vector3f paramVector3f) {
/* 180 */     double d = (dot(paramVector3f) / length() * paramVector3f.length());
/* 181 */     if (d < -1.0D) d = -1.0D; 
/* 182 */     if (d > 1.0D) d = 1.0D; 
/* 183 */     return (float)Math.acos(d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Vector3f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */