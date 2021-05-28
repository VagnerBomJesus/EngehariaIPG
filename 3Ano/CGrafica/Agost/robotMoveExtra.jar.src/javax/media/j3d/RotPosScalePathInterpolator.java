/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotPosScalePathInterpolator
/*     */   extends PathInterpolator
/*     */ {
/*  35 */   private Transform3D rotation = new Transform3D();
/*     */   
/*  37 */   private Vector3f pos = new Vector3f();
/*  38 */   private Quat4f tQuat = new Quat4f();
/*  39 */   private Matrix4d tMat = new Matrix4d();
/*  40 */   private Matrix4d sMat = new Matrix4d();
/*     */   
/*     */   private Quat4f[] quats;
/*     */   
/*     */   private Point3f[] positions;
/*     */   
/*     */   private float[] scales;
/*  47 */   private float prevInterpolationValue = NaNF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private float prevAlphaValue = NaNF;
/*  54 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RotPosScalePathInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RotPosScalePathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float[] paramArrayOfFloat1, Quat4f[] paramArrayOfQuat4f, Point3f[] paramArrayOfPoint3f, float[] paramArrayOfFloat2) {
/*  84 */     super(paramAlpha, paramTransformGroup, paramTransform3D, paramArrayOfFloat1);
/*     */     
/*  86 */     if (paramArrayOfFloat1.length != paramArrayOfQuat4f.length) {
/*  87 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosScalePathInterpolator1"));
/*     */     }
/*  89 */     if (paramArrayOfFloat1.length != paramArrayOfPoint3f.length) {
/*  90 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosScalePathInterpolator0"));
/*     */     }
/*  92 */     if (paramArrayOfFloat1.length != paramArrayOfFloat2.length) {
/*  93 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosScalePathInterpolator2"));
/*     */     }
/*  95 */     setPathArrays(paramArrayOfQuat4f, paramArrayOfPoint3f, paramArrayOfFloat2);
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
/* 106 */   public void setQuat(int paramInt, Quat4f paramQuat4f) { this.quats[paramInt].set(paramQuat4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void getQuat(int paramInt, Quat4f paramQuat4f) { paramQuat4f.set(this.quats[paramInt]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void setPosition(int paramInt, Point3f paramPoint3f) { this.positions[paramInt].set(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void getPosition(int paramInt, Point3f paramPoint3f) { paramPoint3f.set(this.positions[paramInt]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void setScale(int paramInt, float paramFloat) { this.scales[paramInt] = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public float getScale(int paramInt) { return this.scales[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPathArrays(float[] paramArrayOfFloat1, Quat4f[] paramArrayOfQuat4f, Point3f[] paramArrayOfPoint3f, float[] paramArrayOfFloat2) {
/* 181 */     if (paramArrayOfFloat1.length != paramArrayOfQuat4f.length) {
/* 182 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosScalePathInterpolator1"));
/*     */     }
/* 184 */     if (paramArrayOfFloat1.length != paramArrayOfPoint3f.length) {
/* 185 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosScalePathInterpolator0"));
/*     */     }
/* 187 */     if (paramArrayOfFloat1.length != paramArrayOfFloat2.length) {
/* 188 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosScalePathInterpolator2"));
/*     */     }
/* 190 */     setKnots(paramArrayOfFloat1);
/* 191 */     setPathArrays(paramArrayOfQuat4f, paramArrayOfPoint3f, paramArrayOfFloat2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPathArrays(Quat4f[] paramArrayOfQuat4f, Point3f[] paramArrayOfPoint3f, float[] paramArrayOfFloat) {
/* 200 */     this.quats = new Quat4f[paramArrayOfQuat4f.length]; byte b;
/* 201 */     for (b = 0; b < paramArrayOfQuat4f.length; b++) {
/* 202 */       this.quats[b] = new Quat4f();
/* 203 */       this.quats[b].set(paramArrayOfQuat4f[b]);
/*     */     } 
/*     */     
/* 206 */     this.positions = new Point3f[paramArrayOfPoint3f.length];
/* 207 */     for (b = 0; b < paramArrayOfPoint3f.length; b++) {
/* 208 */       this.positions[b] = new Point3f();
/* 209 */       this.positions[b].set(paramArrayOfPoint3f[b]);
/*     */     } 
/*     */     
/* 212 */     this.scales = new float[paramArrayOfFloat.length];
/* 213 */     for (b = 0; b < paramArrayOfFloat.length; b++) {
/* 214 */       this.scales[b] = paramArrayOfFloat[b];
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
/*     */   public void getQuats(Quat4f[] paramArrayOfQuat4f) {
/* 229 */     for (byte b = 0; b < this.quats.length; b++) {
/* 230 */       paramArrayOfQuat4f[b].set(this.quats[b]);
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
/*     */   public void getPositions(Point3f[] paramArrayOfPoint3f) {
/* 245 */     for (byte b = 0; b < this.positions.length; b++) {
/* 246 */       paramArrayOfPoint3f[b].set(this.positions[b]);
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
/*     */   public void getScales(float[] paramArrayOfFloat) {
/* 260 */     for (byte b = 0; b < this.scales.length; b++) {
/* 261 */       paramArrayOfFloat[b] = this.scales[b];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public void setAxisOfRotPosScale(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public Transform3D getAxisOfRotPosScale() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void computeTransform(float paramFloat, Transform3D paramTransform3D) {
/*     */     float f;
/* 299 */     computePathInterpolation(paramFloat);
/*     */     
/* 301 */     if (this.currentKnotIndex == 0 && this.currentInterpolationValue == 0.0F) {
/*     */       
/* 303 */       this.tQuat.x = (this.quats[0]).x;
/* 304 */       this.tQuat.y = (this.quats[0]).y;
/* 305 */       this.tQuat.z = (this.quats[0]).z;
/* 306 */       this.tQuat.w = (this.quats[0]).w;
/* 307 */       this.pos.x = (this.positions[0]).x;
/* 308 */       this.pos.y = (this.positions[0]).y;
/* 309 */       this.pos.z = (this.positions[0]).z;
/* 310 */       f = this.scales[0];
/*     */     } else {
/* 312 */       double d = ((this.quats[this.currentKnotIndex]).x * (this.quats[this.currentKnotIndex + 1]).x + (this.quats[this.currentKnotIndex]).y * (this.quats[this.currentKnotIndex + 1]).y + (this.quats[this.currentKnotIndex]).z * (this.quats[this.currentKnotIndex + 1]).z + (this.quats[this.currentKnotIndex]).w * (this.quats[this.currentKnotIndex + 1]).w);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 320 */       if (d < 0.0D) {
/* 321 */         (this.quats[this.currentKnotIndex]).x += (-(this.quats[this.currentKnotIndex + 1]).x - (this.quats[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 324 */         (this.quats[this.currentKnotIndex]).y += (-(this.quats[this.currentKnotIndex + 1]).y - (this.quats[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 327 */         (this.quats[this.currentKnotIndex]).z += (-(this.quats[this.currentKnotIndex + 1]).z - (this.quats[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 330 */         (this.quats[this.currentKnotIndex]).w += (-(this.quats[this.currentKnotIndex + 1]).w - (this.quats[this.currentKnotIndex]).w) * this.currentInterpolationValue;
/*     */       }
/*     */       else {
/*     */         
/* 334 */         (this.quats[this.currentKnotIndex]).x += ((this.quats[this.currentKnotIndex + 1]).x - (this.quats[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 337 */         (this.quats[this.currentKnotIndex]).y += ((this.quats[this.currentKnotIndex + 1]).y - (this.quats[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 340 */         (this.quats[this.currentKnotIndex]).z += ((this.quats[this.currentKnotIndex + 1]).z - (this.quats[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 343 */         (this.quats[this.currentKnotIndex]).w += ((this.quats[this.currentKnotIndex + 1]).w - (this.quats[this.currentKnotIndex]).w) * this.currentInterpolationValue;
/*     */       } 
/*     */ 
/*     */       
/* 347 */       this.pos.x = (this.positions[this.currentKnotIndex]).x + ((this.positions[this.currentKnotIndex + 1]).x - (this.positions[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */       
/* 350 */       this.pos.y = (this.positions[this.currentKnotIndex]).y + ((this.positions[this.currentKnotIndex + 1]).y - (this.positions[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */       
/* 353 */       this.pos.z = (this.positions[this.currentKnotIndex]).z + ((this.positions[this.currentKnotIndex + 1]).z - (this.positions[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */ 
/*     */       
/* 356 */       f = this.scales[this.currentKnotIndex] + (this.scales[this.currentKnotIndex + 1] - this.scales[this.currentKnotIndex]) * this.currentInterpolationValue;
/*     */     } 
/*     */ 
/*     */     
/* 360 */     this.tQuat.normalize();
/*     */     
/* 362 */     this.sMat.set(f);
/* 363 */     this.tMat.set(this.tQuat);
/* 364 */     this.tMat.mul(this.sMat);
/*     */ 
/*     */     
/* 367 */     this.tMat.m03 = this.pos.x;
/* 368 */     this.tMat.m13 = this.pos.y;
/* 369 */     this.tMat.m23 = this.pos.z;
/* 370 */     this.rotation.set(this.tMat);
/*     */ 
/*     */     
/* 373 */     paramTransform3D.mul(this.axis, this.rotation);
/* 374 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 392 */     RotPosScalePathInterpolator rotPosScalePathInterpolator = new RotPosScalePathInterpolator();
/* 393 */     rotPosScalePathInterpolator.duplicateNode(this, paramBoolean);
/* 394 */     return rotPosScalePathInterpolator;
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
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 421 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 423 */     RotPosScalePathInterpolator rotPosScalePathInterpolator = (RotPosScalePathInterpolator)paramNode;
/*     */ 
/*     */     
/* 426 */     int i = rotPosScalePathInterpolator.getArrayLengths();
/*     */ 
/*     */     
/* 429 */     this.positions = new Point3f[i];
/* 430 */     this.quats = new Quat4f[i];
/* 431 */     this.scales = new float[i];
/*     */     
/* 433 */     Point3f point3f = new Point3f();
/* 434 */     Quat4f quat4f = new Quat4f();
/*     */     
/* 436 */     for (byte b = 0; b < i; b++) {
/* 437 */       this.positions[b] = new Point3f();
/* 438 */       rotPosScalePathInterpolator.getPosition(b, point3f);
/* 439 */       setPosition(b, point3f);
/*     */       
/* 441 */       this.quats[b] = new Quat4f();
/* 442 */       rotPosScalePathInterpolator.getQuat(b, quat4f);
/* 443 */       setQuat(b, quat4f);
/*     */       
/* 445 */       setScale(b, rotPosScalePathInterpolator.getScale(b));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\RotPosScalePathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */