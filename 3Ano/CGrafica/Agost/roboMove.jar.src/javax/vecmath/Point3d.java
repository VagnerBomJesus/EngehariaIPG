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
/*     */ public class Point3d
/*     */   extends Tuple3d
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 5718062286069042927L;
/*     */   
/*  35 */   public Point3d(double paramDouble1, double paramDouble2, double paramDouble3) { super(paramDouble1, paramDouble2, paramDouble3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public Point3d(double[] paramArrayOfDouble) { super(paramArrayOfDouble); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public Point3d(Point3d paramPoint3d) { super(paramPoint3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public Point3d(Point3f paramPoint3f) { super(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public Point3d(Tuple3f paramTuple3f) { super(paramTuple3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public Point3d(Tuple3d paramTuple3d) { super(paramTuple3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point3d() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double distanceSquared(Point3d paramPoint3d) {
/* 107 */     double d1 = this.x - paramPoint3d.x;
/* 108 */     double d2 = this.y - paramPoint3d.y;
/* 109 */     double d3 = this.z - paramPoint3d.z;
/* 110 */     return d1 * d1 + d2 * d2 + d3 * d3;
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
/*     */   public final double distance(Point3d paramPoint3d) {
/* 123 */     double d1 = this.x - paramPoint3d.x;
/* 124 */     double d2 = this.y - paramPoint3d.y;
/* 125 */     double d3 = this.z - paramPoint3d.z;
/* 126 */     return Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
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
/* 138 */   public final double distanceL1(Point3d paramPoint3d) { return Math.abs(this.x - paramPoint3d.x) + Math.abs(this.y - paramPoint3d.y) + Math.abs(this.z - paramPoint3d.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double distanceLinf(Point3d paramPoint3d) {
/* 152 */     double d = Math.max(Math.abs(this.x - paramPoint3d.x), Math.abs(this.y - paramPoint3d.y));
/*     */     
/* 154 */     return Math.max(d, Math.abs(this.z - paramPoint3d.z));
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
/*     */   public final void project(Point4d paramPoint4d) {
/* 167 */     double d = 1.0D / paramPoint4d.w;
/* 168 */     this.x = paramPoint4d.x * d;
/* 169 */     this.y = paramPoint4d.y * d;
/* 170 */     this.z = paramPoint4d.z * d;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\Point3d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */