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
/*     */ public class Point2f
/*     */   extends Tuple2f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -4801347926528714435L;
/*     */   
/*  34 */   public Point2f(float paramFloat1, float paramFloat2) { super(paramFloat1, paramFloat2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public Point2f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public Point2f(Point2f paramPoint2f) { super(paramPoint2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public Point2f(Point2d paramPoint2d) { super(paramPoint2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public Point2f(Tuple2d paramTuple2d) { super(paramTuple2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public Point2f(Tuple2f paramTuple2f) { super(paramTuple2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float distanceSquared(Point2f paramPoint2f) {
/* 105 */     float f1 = this.x - paramPoint2f.x;
/* 106 */     float f2 = this.y - paramPoint2f.y;
/* 107 */     return f1 * f1 + f2 * f2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float distance(Point2f paramPoint2f) {
/* 118 */     float f1 = this.x - paramPoint2f.x;
/* 119 */     float f2 = this.y - paramPoint2f.y;
/* 120 */     return (float)Math.sqrt((f1 * f1 + f2 * f2));
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
/* 131 */   public final float distanceL1(Point2f paramPoint2f) { return Math.abs(this.x - paramPoint2f.x) + Math.abs(this.y - paramPoint2f.y); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public final float distanceLinf(Point2f paramPoint2f) { return Math.max(Math.abs(this.x - paramPoint2f.x), Math.abs(this.y - paramPoint2f.y)); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Point2f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */