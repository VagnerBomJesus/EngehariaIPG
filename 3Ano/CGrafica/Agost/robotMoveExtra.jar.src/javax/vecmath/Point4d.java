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
/*     */ public class Point4d
/*     */   extends Tuple4d
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1733471895962736949L;
/*     */   
/*  37 */   public Point4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) { super(paramDouble1, paramDouble2, paramDouble3, paramDouble4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public Point4d(double[] paramArrayOfDouble) { super(paramArrayOfDouble); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public Point4d(Point4d paramPoint4d) { super(paramPoint4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public Point4d(Point4f paramPoint4f) { super(paramPoint4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public Point4d(Tuple4f paramTuple4f) { super(paramTuple4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public Point4d(Tuple4d paramTuple4d) { super(paramTuple4d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public Point4d(Tuple3d paramTuple3d) { super(paramTuple3d.x, paramTuple3d.y, paramTuple3d.z, 1.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point4d() {}
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
/* 123 */     this.x = paramTuple3d.x;
/* 124 */     this.y = paramTuple3d.y;
/* 125 */     this.z = paramTuple3d.z;
/* 126 */     this.w = 1.0D;
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
/*     */   public final double distanceSquared(Point4d paramPoint4d) {
/* 139 */     double d1 = this.x - paramPoint4d.x;
/* 140 */     double d2 = this.y - paramPoint4d.y;
/* 141 */     double d3 = this.z - paramPoint4d.z;
/* 142 */     double d4 = this.w - paramPoint4d.w;
/* 143 */     return d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4;
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
/*     */   public final double distance(Point4d paramPoint4d) {
/* 156 */     double d1 = this.x - paramPoint4d.x;
/* 157 */     double d2 = this.y - paramPoint4d.y;
/* 158 */     double d3 = this.z - paramPoint4d.z;
/* 159 */     double d4 = this.w - paramPoint4d.w;
/* 160 */     return Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4);
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
/* 172 */   public final double distanceL1(Point4d paramPoint4d) { return Math.abs(this.x - paramPoint4d.x) + Math.abs(this.y - paramPoint4d.y) + Math.abs(this.z - paramPoint4d.z) + Math.abs(this.w - paramPoint4d.w); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double distanceLinf(Point4d paramPoint4d) {
/* 185 */     double d1 = Math.max(Math.abs(this.x - paramPoint4d.x), Math.abs(this.y - paramPoint4d.y));
/* 186 */     double d2 = Math.max(Math.abs(this.z - paramPoint4d.z), Math.abs(this.w - paramPoint4d.w));
/*     */     
/* 188 */     return Math.max(d1, d2);
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
/* 201 */     double d = 1.0D / paramPoint4d.w;
/* 202 */     paramPoint4d.x *= d;
/* 203 */     paramPoint4d.y *= d;
/* 204 */     paramPoint4d.z *= d;
/* 205 */     this.w = 1.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Point4d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */