/*     */ package javax.media.j3d;
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
/*     */ public abstract class PathInterpolator
/*     */   extends TransformInterpolator
/*     */ {
/*     */   private float[] knots;
/*     */   protected float currentInterpolationValue;
/*     */   protected int currentKnotIndex;
/*     */   
/*     */   PathInterpolator() {}
/*     */   
/*  86 */   public PathInterpolator(Alpha paramAlpha, float[] paramArrayOfFloat) { this(paramAlpha, null, paramArrayOfFloat); }
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
/*     */   public PathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, float[] paramArrayOfFloat) {
/* 103 */     super(paramAlpha, paramTransformGroup);
/* 104 */     setKnots(paramArrayOfFloat);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float[] paramArrayOfFloat) {
/* 122 */     super(paramAlpha, paramTransformGroup, paramTransform3D);
/* 123 */     setKnots(paramArrayOfFloat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public int getArrayLengths() { return this.knots.length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public void setKnot(int paramInt, float paramFloat) { this.knots[paramInt] = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public float getKnot(int paramInt) { return this.knots[paramInt]; }
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
/*     */   protected void setKnots(float[] paramArrayOfFloat) {
/* 167 */     if (paramArrayOfFloat[0] < -1.0E-4D || paramArrayOfFloat[0] > 1.0E-4D) {
/* 168 */       throw new IllegalArgumentException(J3dI18N.getString("PathInterpolator0"));
/*     */     }
/*     */     
/* 171 */     if ((paramArrayOfFloat[paramArrayOfFloat.length - 1] - 1.0F) < -1.0E-4D || (paramArrayOfFloat[paramArrayOfFloat.length - 1] - 1.0F) > 1.0E-4D) {
/* 172 */       throw new IllegalArgumentException(J3dI18N.getString("PathInterpolator1"));
/*     */     }
/*     */     
/* 175 */     this.knots = new float[paramArrayOfFloat.length];
/* 176 */     for (byte b = 0; b < paramArrayOfFloat.length; b++) {
/* 177 */       if (b && paramArrayOfFloat[b] < paramArrayOfFloat[b - true]) {
/* 178 */         throw new IllegalArgumentException(J3dI18N.getString("PathInterpolator2"));
/*     */       }
/* 180 */       this.knots[b] = paramArrayOfFloat[b];
/*     */     } 
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
/*     */   public void getKnots(float[] paramArrayOfFloat) {
/* 194 */     for (byte b = 0; b < this.knots.length; b++) {
/* 195 */       paramArrayOfFloat[b] = this.knots[b];
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computePathInterpolation(float paramFloat) {
/* 216 */     for (byte b = 0; b < this.knots.length; b++) {
/* 217 */       if ((!b && paramFloat <= this.knots[b]) || (b && paramFloat >= this.knots[b - true] && paramFloat <= this.knots[b])) {
/*     */ 
/*     */         
/* 220 */         if (!b) {
/* 221 */           this.currentInterpolationValue = 0.0F;
/* 222 */           this.currentKnotIndex = 0;
/*     */           break;
/*     */         } 
/* 225 */         this.currentInterpolationValue = (paramFloat - this.knots[b - true]) / (this.knots[b] - this.knots[b - true]);
/*     */         
/* 227 */         this.currentKnotIndex = b - true;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computePathInterpolation() {
/* 239 */     float f = this.alpha.value();
/* 240 */     computePathInterpolation(f);
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
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 266 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 268 */     PathInterpolator pathInterpolator = (PathInterpolator)paramNode;
/*     */     
/* 270 */     int i = pathInterpolator.getArrayLengths();
/*     */ 
/*     */     
/* 273 */     this.knots = new float[i];
/*     */     
/* 275 */     for (byte b = 0; b < i; b++)
/* 276 */       setKnot(b, pathInterpolator.getKnot(b)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\PathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */