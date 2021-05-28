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
/*     */ public class Vector2f
/*     */   extends Tuple2f
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -2168194326883512320L;
/*     */   
/*  34 */   public Vector2f(float paramFloat1, float paramFloat2) { super(paramFloat1, paramFloat2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public Vector2f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public Vector2f(Vector2f paramVector2f) { super(paramVector2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Vector2f(Vector2d paramVector2d) { super(paramVector2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public Vector2f(Tuple2f paramTuple2f) { super(paramTuple2f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public Vector2f(Tuple2d paramTuple2d) { super(paramTuple2d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector2f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public final float dot(Vector2f paramVector2f) { return this.x * paramVector2f.x + this.y * paramVector2f.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public final float length() { return (float)Math.sqrt((this.x * this.x + this.y * this.y)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public final float lengthSquared() { return this.x * this.x + this.y * this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize(Vector2f paramVector2f) {
/* 134 */     float f = (float)(1.0D / Math.sqrt((paramVector2f.x * paramVector2f.x + paramVector2f.y * paramVector2f.y)));
/* 135 */     paramVector2f.x *= f;
/* 136 */     paramVector2f.y *= f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void normalize() {
/* 146 */     float f = (float)(1.0D / Math.sqrt((this.x * this.x + this.y * this.y)));
/*     */     
/* 148 */     this.x *= f;
/* 149 */     this.y *= f;
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
/*     */   public final float angle(Vector2f paramVector2f) {
/* 161 */     double d = (dot(paramVector2f) / length() * paramVector2f.length());
/* 162 */     if (d < -1.0D) d = -1.0D; 
/* 163 */     if (d > 1.0D) d = 1.0D; 
/* 164 */     return (float)Math.acos(d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Vector2f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */