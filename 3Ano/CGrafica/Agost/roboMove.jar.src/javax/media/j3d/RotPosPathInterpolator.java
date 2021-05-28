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
/*     */ public class RotPosPathInterpolator
/*     */   extends PathInterpolator
/*     */ {
/*  32 */   private Transform3D rotation = new Transform3D();
/*     */   
/*  34 */   private Vector3f pos = new Vector3f();
/*  35 */   private Quat4f tQuat = new Quat4f();
/*  36 */   private Matrix4d tMat = new Matrix4d();
/*     */   
/*     */   private Quat4f[] quats;
/*     */   
/*     */   private Point3f[] positions;
/*  41 */   private float prevInterpolationValue = NaNF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private float prevAlphaValue = NaNF;
/*  48 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RotPosPathInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RotPosPathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float[] paramArrayOfFloat, Quat4f[] paramArrayOfQuat4f, Point3f[] paramArrayOfPoint3f) {
/*  75 */     super(paramAlpha, paramTransformGroup, paramTransform3D, paramArrayOfFloat);
/*     */     
/*  77 */     if (paramArrayOfFloat.length != paramArrayOfPoint3f.length) {
/*  78 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosPathInterpolator0"));
/*     */     }
/*  80 */     if (paramArrayOfFloat.length != paramArrayOfQuat4f.length) {
/*  81 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosPathInterpolator0"));
/*     */     }
/*  83 */     setPathArrays(paramArrayOfQuat4f, paramArrayOfPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setQuat(int paramInt, Quat4f paramQuat4f) { this.quats[paramInt].set(paramQuat4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void getQuat(int paramInt, Quat4f paramQuat4f) { paramQuat4f.set(this.quats[paramInt]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setPosition(int paramInt, Point3f paramPoint3f) { this.positions[paramInt].set(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void getPosition(int paramInt, Point3f paramPoint3f) { paramPoint3f.set(this.positions[paramInt]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPathArrays(float[] paramArrayOfFloat, Quat4f[] paramArrayOfQuat4f, Point3f[] paramArrayOfPoint3f) {
/* 146 */     if (paramArrayOfFloat.length != paramArrayOfQuat4f.length) {
/* 147 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosPathInterpolator0"));
/*     */     }
/* 149 */     if (paramArrayOfFloat.length != paramArrayOfPoint3f.length) {
/* 150 */       throw new IllegalArgumentException(J3dI18N.getString("RotPosPathInterpolator0"));
/*     */     }
/* 152 */     setKnots(paramArrayOfFloat);
/* 153 */     setPathArrays(paramArrayOfQuat4f, paramArrayOfPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPathArrays(Quat4f[] paramArrayOfQuat4f, Point3f[] paramArrayOfPoint3f) {
/* 161 */     this.quats = new Quat4f[paramArrayOfQuat4f.length]; byte b;
/* 162 */     for (b = 0; b < paramArrayOfQuat4f.length; b++) {
/* 163 */       this.quats[b] = new Quat4f();
/* 164 */       this.quats[b].set(paramArrayOfQuat4f[b]);
/*     */     } 
/*     */     
/* 167 */     this.positions = new Point3f[paramArrayOfPoint3f.length];
/* 168 */     for (b = 0; b < paramArrayOfPoint3f.length; b++) {
/* 169 */       this.positions[b] = new Point3f();
/* 170 */       this.positions[b].set(paramArrayOfPoint3f[b]);
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
/* 185 */     for (byte b = 0; b < this.quats.length; b++) {
/* 186 */       paramArrayOfQuat4f[b].set(this.quats[b]);
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
/* 201 */     for (byte b = 0; b < this.positions.length; b++) {
/* 202 */       paramArrayOfPoint3f[b].set(this.positions[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setAxisOfRotPos(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public Transform3D getAxisOfRotPos() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 237 */     computePathInterpolation(paramFloat);
/*     */     
/* 239 */     if (this.currentKnotIndex == 0 && this.currentInterpolationValue == 0.0F) {
/*     */       
/* 241 */       this.tQuat.x = (this.quats[0]).x;
/* 242 */       this.tQuat.y = (this.quats[0]).y;
/* 243 */       this.tQuat.z = (this.quats[0]).z;
/* 244 */       this.tQuat.w = (this.quats[0]).w;
/* 245 */       this.pos.x = (this.positions[0]).x;
/* 246 */       this.pos.y = (this.positions[0]).y;
/* 247 */       this.pos.z = (this.positions[0]).z;
/*     */     } else {
/* 249 */       double d = ((this.quats[this.currentKnotIndex]).x * (this.quats[this.currentKnotIndex + 1]).x + (this.quats[this.currentKnotIndex]).y * (this.quats[this.currentKnotIndex + 1]).y + (this.quats[this.currentKnotIndex]).z * (this.quats[this.currentKnotIndex + 1]).z + (this.quats[this.currentKnotIndex]).w * (this.quats[this.currentKnotIndex + 1]).w);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 257 */       if (d < 0.0D) {
/* 258 */         (this.quats[this.currentKnotIndex]).x += (-(this.quats[this.currentKnotIndex + 1]).x - (this.quats[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 261 */         (this.quats[this.currentKnotIndex]).y += (-(this.quats[this.currentKnotIndex + 1]).y - (this.quats[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 264 */         (this.quats[this.currentKnotIndex]).z += (-(this.quats[this.currentKnotIndex + 1]).z - (this.quats[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 267 */         (this.quats[this.currentKnotIndex]).w += (-(this.quats[this.currentKnotIndex + 1]).w - (this.quats[this.currentKnotIndex]).w) * this.currentInterpolationValue;
/*     */       }
/*     */       else {
/*     */         
/* 271 */         (this.quats[this.currentKnotIndex]).x += ((this.quats[this.currentKnotIndex + 1]).x - (this.quats[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 274 */         (this.quats[this.currentKnotIndex]).y += ((this.quats[this.currentKnotIndex + 1]).y - (this.quats[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 277 */         (this.quats[this.currentKnotIndex]).z += ((this.quats[this.currentKnotIndex + 1]).z - (this.quats[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 280 */         (this.quats[this.currentKnotIndex]).w += ((this.quats[this.currentKnotIndex + 1]).w - (this.quats[this.currentKnotIndex]).w) * this.currentInterpolationValue;
/*     */       } 
/*     */ 
/*     */       
/* 284 */       this.pos.x = (this.positions[this.currentKnotIndex]).x + ((this.positions[this.currentKnotIndex + 1]).x - (this.positions[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */       
/* 287 */       this.pos.y = (this.positions[this.currentKnotIndex]).y + ((this.positions[this.currentKnotIndex + 1]).y - (this.positions[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */       
/* 290 */       this.pos.z = (this.positions[this.currentKnotIndex]).z + ((this.positions[this.currentKnotIndex + 1]).z - (this.positions[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */     } 
/*     */ 
/*     */     
/* 294 */     this.tQuat.normalize();
/*     */ 
/*     */     
/* 297 */     this.tMat.set(this.tQuat);
/*     */ 
/*     */     
/* 300 */     this.tMat.m03 = this.pos.x;
/* 301 */     this.tMat.m13 = this.pos.y;
/* 302 */     this.tMat.m23 = this.pos.z;
/* 303 */     this.rotation.set(this.tMat);
/*     */ 
/*     */     
/* 306 */     paramTransform3D.mul(this.axis, this.rotation);
/* 307 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/* 325 */     RotPosPathInterpolator rotPosPathInterpolator = new RotPosPathInterpolator();
/* 326 */     rotPosPathInterpolator.duplicateNode(this, paramBoolean);
/* 327 */     return rotPosPathInterpolator;
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
/* 354 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 356 */     RotPosPathInterpolator rotPosPathInterpolator = (RotPosPathInterpolator)paramNode;
/*     */     
/* 358 */     int i = rotPosPathInterpolator.getArrayLengths();
/*     */ 
/*     */     
/* 361 */     this.positions = new Point3f[i];
/* 362 */     this.quats = new Quat4f[i];
/*     */     
/* 364 */     Point3f point3f = new Point3f();
/* 365 */     Quat4f quat4f = new Quat4f();
/*     */     
/* 367 */     for (byte b = 0; b < i; b++) {
/* 368 */       this.positions[b] = new Point3f();
/* 369 */       rotPosPathInterpolator.getPosition(b, point3f);
/* 370 */       setPosition(b, point3f);
/*     */       
/* 372 */       this.quats[b] = new Quat4f();
/* 373 */       rotPosPathInterpolator.getQuat(b, quat4f);
/* 374 */       setQuat(b, quat4f);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\RotPosPathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */