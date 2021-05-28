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
/*     */ public class Point2d
/*     */   extends Tuple2d
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1133748791492571954L;
/*     */   
/*  34 */   public Point2d(double paramDouble1, double paramDouble2) { super(paramDouble1, paramDouble2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public Point2d(double[] paramArrayOfDouble) { super(paramArrayOfDouble); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public Point2d(Point2d paramPoint2d) { super(paramPoint2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Point2d(Point2f paramPoint2f) { super(paramPoint2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public Point2d(Tuple2d paramTuple2d) { super(paramTuple2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public Point2d(Tuple2f paramTuple2f) { super(paramTuple2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2d() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double distanceSquared(Point2d paramPoint2d) {
/* 104 */     double d1 = this.x - paramPoint2d.x;
/* 105 */     double d2 = this.y - paramPoint2d.y;
/* 106 */     return d1 * d1 + d2 * d2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double distance(Point2d paramPoint2d) {
/* 117 */     double d1 = this.x - paramPoint2d.x;
/* 118 */     double d2 = this.y - paramPoint2d.y;
/* 119 */     return Math.sqrt(d1 * d1 + d2 * d2);
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
/* 130 */   public final double distanceL1(Point2d paramPoint2d) { return Math.abs(this.x - paramPoint2d.x) + Math.abs(this.y - paramPoint2d.y); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public final double distanceLinf(Point2d paramPoint2d) { return Math.max(Math.abs(this.x - paramPoint2d.x), Math.abs(this.y - paramPoint2d.y)); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\vecmath\Point2d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */