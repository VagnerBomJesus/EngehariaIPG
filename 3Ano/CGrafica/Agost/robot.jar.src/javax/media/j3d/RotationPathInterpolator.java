/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Quat4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotationPathInterpolator
/*     */   extends PathInterpolator
/*     */ {
/*  31 */   private Transform3D rotation = new Transform3D();
/*     */   
/*  33 */   private Quat4f tQuat = new Quat4f();
/*     */   
/*     */   private Quat4f[] quats;
/*     */   
/*  37 */   private float prevInterpolationValue = NaNF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private float prevAlphaValue = NaNF;
/*  44 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RotationPathInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RotationPathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float[] paramArrayOfFloat, Quat4f[] paramArrayOfQuat4f) {
/*  68 */     super(paramAlpha, paramTransformGroup, paramTransform3D, paramArrayOfFloat);
/*     */     
/*  70 */     if (paramArrayOfFloat.length != paramArrayOfQuat4f.length) {
/*  71 */       throw new IllegalArgumentException(J3dI18N.getString("RotationPathInterpolator0"));
/*     */     }
/*  73 */     setPathArrays(paramArrayOfQuat4f);
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
/*  84 */   public void setQuat(int paramInt, Quat4f paramQuat4f) { this.quats[paramInt].set(paramQuat4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void getQuat(int paramInt, Quat4f paramQuat4f) { paramQuat4f.set(this.quats[paramInt]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPathArrays(float[] paramArrayOfFloat, Quat4f[] paramArrayOfQuat4f) {
/* 114 */     if (paramArrayOfFloat.length != paramArrayOfQuat4f.length) {
/* 115 */       throw new IllegalArgumentException(J3dI18N.getString("RotationPathInterpolator0"));
/*     */     }
/* 117 */     setKnots(paramArrayOfFloat);
/* 118 */     setPathArrays(paramArrayOfQuat4f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPathArrays(Quat4f[] paramArrayOfQuat4f) {
/* 124 */     this.quats = new Quat4f[paramArrayOfQuat4f.length];
/* 125 */     for (byte b = 0; b < paramArrayOfQuat4f.length; b++) {
/* 126 */       this.quats[b] = new Quat4f();
/* 127 */       this.quats[b].set(paramArrayOfQuat4f[b]);
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
/* 142 */     for (byte b = 0; b < this.quats.length; b++) {
/* 143 */       paramArrayOfQuat4f[b].set(this.quats[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setAxisOfRotation(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public Transform3D getAxisOfRotation() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 180 */     computePathInterpolation(paramFloat);
/*     */ 
/*     */     
/* 183 */     if (this.currentKnotIndex == 0 && this.currentInterpolationValue == 0.0F) {
/*     */       
/* 185 */       this.tQuat.x = (this.quats[0]).x;
/* 186 */       this.tQuat.y = (this.quats[0]).y;
/* 187 */       this.tQuat.z = (this.quats[0]).z;
/* 188 */       this.tQuat.w = (this.quats[0]).w;
/*     */     } else {
/* 190 */       double d = ((this.quats[this.currentKnotIndex]).x * (this.quats[this.currentKnotIndex + 1]).x + (this.quats[this.currentKnotIndex]).y * (this.quats[this.currentKnotIndex + 1]).y + (this.quats[this.currentKnotIndex]).z * (this.quats[this.currentKnotIndex + 1]).z + (this.quats[this.currentKnotIndex]).w * (this.quats[this.currentKnotIndex + 1]).w);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 198 */       if (d < 0.0D) {
/* 199 */         (this.quats[this.currentKnotIndex]).x += (-(this.quats[this.currentKnotIndex + 1]).x - (this.quats[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 202 */         (this.quats[this.currentKnotIndex]).y += (-(this.quats[this.currentKnotIndex + 1]).y - (this.quats[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 205 */         (this.quats[this.currentKnotIndex]).z += (-(this.quats[this.currentKnotIndex + 1]).z - (this.quats[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 208 */         (this.quats[this.currentKnotIndex]).w += (-(this.quats[this.currentKnotIndex + 1]).w - (this.quats[this.currentKnotIndex]).w) * this.currentInterpolationValue;
/*     */       }
/*     */       else {
/*     */         
/* 212 */         (this.quats[this.currentKnotIndex]).x += ((this.quats[this.currentKnotIndex + 1]).x - (this.quats[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 215 */         (this.quats[this.currentKnotIndex]).y += ((this.quats[this.currentKnotIndex + 1]).y - (this.quats[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 218 */         (this.quats[this.currentKnotIndex]).z += ((this.quats[this.currentKnotIndex + 1]).z - (this.quats[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */ 
/*     */         
/* 221 */         (this.quats[this.currentKnotIndex]).w += ((this.quats[this.currentKnotIndex + 1]).w - (this.quats[this.currentKnotIndex]).w) * this.currentInterpolationValue;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 227 */     this.tQuat.normalize();
/* 228 */     this.rotation.set(this.tQuat);
/*     */ 
/*     */     
/* 231 */     paramTransform3D.mul(this.axis, this.rotation);
/* 232 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/* 250 */     RotationPathInterpolator rotationPathInterpolator = new RotationPathInterpolator();
/* 251 */     rotationPathInterpolator.duplicateNode(this, paramBoolean);
/* 252 */     return rotationPathInterpolator;
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
/* 278 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 280 */     RotationPathInterpolator rotationPathInterpolator = (RotationPathInterpolator)paramNode;
/*     */ 
/*     */     
/* 283 */     int i = rotationPathInterpolator.getArrayLengths();
/*     */ 
/*     */     
/* 286 */     this.quats = new Quat4f[i];
/* 287 */     Quat4f quat4f = new Quat4f();
/*     */     
/* 289 */     for (byte b = 0; b < i; b++) {
/* 290 */       this.quats[b] = new Quat4f();
/* 291 */       rotationPathInterpolator.getQuat(b, quat4f);
/* 292 */       setQuat(b, quat4f);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\RotationPathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */