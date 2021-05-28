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
/*     */ public class Point3f
/*     */   extends Tuple3f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -8689337816398030143L;
/*     */   
/*  36 */   public Point3f(float paramFloat1, float paramFloat2, float paramFloat3) { super(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Point3f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public Point3f(Point3f paramPoint3f) { super(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public Point3f(Point3d paramPoint3d) { super(paramPoint3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public Point3f(Tuple3f paramTuple3f) { super(paramTuple3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Point3f(Tuple3d paramTuple3d) { super(paramTuple3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point3f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float distanceSquared(Point3f paramPoint3f) {
/* 109 */     float f1 = this.x - paramPoint3f.x;
/* 110 */     float f2 = this.y - paramPoint3f.y;
/* 111 */     float f3 = this.z - paramPoint3f.z;
/* 112 */     return f1 * f1 + f2 * f2 + f3 * f3;
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
/*     */   public final float distance(Point3f paramPoint3f) {
/* 125 */     float f1 = this.x - paramPoint3f.x;
/* 126 */     float f2 = this.y - paramPoint3f.y;
/* 127 */     float f3 = this.z - paramPoint3f.z;
/* 128 */     return (float)Math.sqrt((f1 * f1 + f2 * f2 + f3 * f3));
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
/* 141 */   public final float distanceL1(Point3f paramPoint3f) { return Math.abs(this.x - paramPoint3f.x) + Math.abs(this.y - paramPoint3f.y) + Math.abs(this.z - paramPoint3f.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float distanceLinf(Point3f paramPoint3f) {
/* 155 */     float f = Math.max(Math.abs(this.x - paramPoint3f.x), Math.abs(this.y - paramPoint3f.y));
/* 156 */     return Math.max(f, Math.abs(this.z - paramPoint3f.z));
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
/*     */   public final void project(Point4f paramPoint4f) {
/* 170 */     float f = 1.0F / paramPoint4f.w;
/* 171 */     this.x = paramPoint4f.x * f;
/* 172 */     this.y = paramPoint4f.y * f;
/* 173 */     this.z = paramPoint4f.z * f;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Point3f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */