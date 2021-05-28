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
/*     */ public class Vector2d
/*     */   extends Tuple2d
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 8572646365302599857L;
/*     */   
/*  34 */   public Vector2d(double paramDouble1, double paramDouble2) { super(paramDouble1, paramDouble2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public Vector2d(double[] paramArrayOfDouble) { super(paramArrayOfDouble); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public Vector2d(Vector2d paramVector2d) { super(paramVector2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Vector2d(Vector2f paramVector2f) { super(paramVector2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public Vector2d(Tuple2d paramTuple2d) { super(paramTuple2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public Vector2d(Tuple2f paramTuple2f) { super(paramTuple2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector2d() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public final double dot(Vector2d paramVector2d) { return this.x * paramVector2d.x + this.y * paramVector2d.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public final double length() { return Math.sqrt(this.x * this.x + this.y * this.y); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public final double lengthSquared() { return this.x * this.x + this.y * this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize(Vector2d paramVector2d) {
/* 133 */     double d = 1.0D / Math.sqrt(paramVector2d.x * paramVector2d.x + paramVector2d.y * paramVector2d.y);
/* 134 */     paramVector2d.x *= d;
/* 135 */     paramVector2d.y *= d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize() {
/* 145 */     double d = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y);
/*     */     
/* 147 */     this.x *= d;
/* 148 */     this.y *= d;
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
/*     */   public final double angle(Vector2d paramVector2d) {
/* 160 */     double d = dot(paramVector2d) / length() * paramVector2d.length();
/* 161 */     if (d < -1.0D) d = -1.0D; 
/* 162 */     if (d > 1.0D) d = 1.0D; 
/* 163 */     return Math.acos(d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Vector2d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */