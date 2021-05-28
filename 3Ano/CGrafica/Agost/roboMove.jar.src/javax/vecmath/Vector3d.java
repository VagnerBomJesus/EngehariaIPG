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
/*     */ public class Vector3d
/*     */   extends Tuple3d
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3761969948420550442L;
/*     */   
/*  36 */   public Vector3d(double paramDouble1, double paramDouble2, double paramDouble3) { super(paramDouble1, paramDouble2, paramDouble3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Vector3d(double[] paramArrayOfDouble) { super(paramArrayOfDouble); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public Vector3d(Vector3d paramVector3d) { super(paramVector3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public Vector3d(Vector3f paramVector3f) { super(paramVector3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public Vector3d(Tuple3f paramTuple3f) { super(paramTuple3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Vector3d(Tuple3d paramTuple3d) { super(paramTuple3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector3d() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void cross(Vector3d paramVector3d1, Vector3d paramVector3d2) {
/* 108 */     double d1 = paramVector3d1.y * paramVector3d2.z - paramVector3d1.z * paramVector3d2.y;
/* 109 */     double d2 = paramVector3d2.x * paramVector3d1.z - paramVector3d2.z * paramVector3d1.x;
/* 110 */     this.z = paramVector3d1.x * paramVector3d2.y - paramVector3d1.y * paramVector3d2.x;
/* 111 */     this.x = d1;
/* 112 */     this.y = d2;
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
/*     */   public final void normalize(Vector3d paramVector3d) {
/* 124 */     double d = 1.0D / Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
/* 125 */     paramVector3d.x *= d;
/* 126 */     paramVector3d.y *= d;
/* 127 */     paramVector3d.z *= d;
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
/* 138 */     double d = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/* 139 */     this.x *= d;
/* 140 */     this.y *= d;
/* 141 */     this.z *= d;
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
/* 152 */   public final double dot(Vector3d paramVector3d) { return this.x * paramVector3d.x + this.y * paramVector3d.y + this.z * paramVector3d.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public final double lengthSquared() { return this.x * this.x + this.y * this.y + this.z * this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public final double length() { return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double angle(Vector3d paramVector3d) {
/* 184 */     double d = dot(paramVector3d) / length() * paramVector3d.length();
/* 185 */     if (d < -1.0D) d = -1.0D; 
/* 186 */     if (d > 1.0D) d = 1.0D; 
/* 187 */     return Math.acos(d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\Vector3d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */