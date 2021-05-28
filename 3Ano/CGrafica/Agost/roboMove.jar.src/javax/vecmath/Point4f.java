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
/*     */ 
/*     */ public class Point4f
/*     */   extends Tuple4f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 4643134103185764459L;
/*     */   
/*  37 */   public Point4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { super(paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public Point4f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public Point4f(Point4f paramPoint4f) { super(paramPoint4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public Point4f(Point4d paramPoint4d) { super(paramPoint4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public Point4f(Tuple4f paramTuple4f) { super(paramTuple4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public Point4f(Tuple4d paramTuple4d) { super(paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public Point4f(Tuple3f paramTuple3f) { super(paramTuple3f.x, paramTuple3f.y, paramTuple3f.z, 1.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point4f() {}
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
/* 123 */     this.x = paramTuple3f.x;
/* 124 */     this.y = paramTuple3f.y;
/* 125 */     this.z = paramTuple3f.z;
/* 126 */     this.w = 1.0F;
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
/*     */   public final float distanceSquared(Point4f paramPoint4f) {
/* 139 */     float f1 = this.x - paramPoint4f.x;
/* 140 */     float f2 = this.y - paramPoint4f.y;
/* 141 */     float f3 = this.z - paramPoint4f.z;
/* 142 */     float f4 = this.w - paramPoint4f.w;
/* 143 */     return f1 * f1 + f2 * f2 + f3 * f3 + f4 * f4;
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
/*     */   public final float distance(Point4f paramPoint4f) {
/* 156 */     float f1 = this.x - paramPoint4f.x;
/* 157 */     float f2 = this.y - paramPoint4f.y;
/* 158 */     float f3 = this.z - paramPoint4f.z;
/* 159 */     float f4 = this.w - paramPoint4f.w;
/* 160 */     return (float)Math.sqrt((f1 * f1 + f2 * f2 + f3 * f3 + f4 * f4));
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
/* 173 */   public final float distanceL1(Point4f paramPoint4f) { return Math.abs(this.x - paramPoint4f.x) + Math.abs(this.y - paramPoint4f.y) + Math.abs(this.z - paramPoint4f.z) + Math.abs(this.w - paramPoint4f.w); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float distanceLinf(Point4f paramPoint4f) {
/* 187 */     float f1 = Math.max(Math.abs(this.x - paramPoint4f.x), Math.abs(this.y - paramPoint4f.y));
/* 188 */     float f2 = Math.max(Math.abs(this.z - paramPoint4f.z), Math.abs(this.w - paramPoint4f.w));
/*     */     
/* 190 */     return Math.max(f1, f2);
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
/* 204 */     float f = 1.0F / paramPoint4f.w;
/* 205 */     paramPoint4f.x *= f;
/* 206 */     paramPoint4f.y *= f;
/* 207 */     paramPoint4f.z *= f;
/* 208 */     this.w = 1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\Point4f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */