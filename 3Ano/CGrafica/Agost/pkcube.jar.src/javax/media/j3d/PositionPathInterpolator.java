/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3f;
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
/*     */ public class PositionPathInterpolator
/*     */   extends PathInterpolator
/*     */ {
/*  32 */   private Transform3D position = new Transform3D();
/*  33 */   private Vector3f pos = new Vector3f();
/*     */   
/*     */   private Point3f[] positions;
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
/*     */   
/*     */   PositionPathInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PositionPathInterpolator(Alpha paramAlpha, TransformGroup paramTransformGroup, Transform3D paramTransform3D, float[] paramArrayOfFloat, Point3f[] paramArrayOfPoint3f) {
/*  70 */     super(paramAlpha, paramTransformGroup, paramTransform3D, paramArrayOfFloat);
/*     */     
/*  72 */     if (paramArrayOfFloat.length != paramArrayOfPoint3f.length)
/*  73 */       throw new IllegalArgumentException(J3dI18N.getString("PositionPathInterpolator0")); 
/*  74 */     setPathArrays(paramArrayOfPoint3f);
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
/*  85 */   public void setPosition(int paramInt, Point3f paramPoint3f) { this.positions[paramInt].set(paramPoint3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void getPosition(int paramInt, Point3f paramPoint3f) { paramPoint3f.set(this.positions[paramInt]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPathArrays(float[] paramArrayOfFloat, Point3f[] paramArrayOfPoint3f) {
/* 116 */     if (paramArrayOfFloat.length != paramArrayOfPoint3f.length) {
/* 117 */       throw new IllegalArgumentException(J3dI18N.getString("PositionPathInterpolator0"));
/*     */     }
/* 119 */     setKnots(paramArrayOfFloat);
/* 120 */     setPathArrays(paramArrayOfPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPathArrays(Point3f[] paramArrayOfPoint3f) {
/* 127 */     this.positions = new Point3f[paramArrayOfPoint3f.length];
/* 128 */     for (byte b = 0; b < paramArrayOfPoint3f.length; b++) {
/* 129 */       this.positions[b] = new Point3f();
/* 130 */       this.positions[b].set(paramArrayOfPoint3f[b]);
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
/* 145 */     for (byte b = 0; b < this.positions.length; b++) {
/* 146 */       paramArrayOfPoint3f[b].set(this.positions[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setAxisOfTranslation(Transform3D paramTransform3D) { setTransformAxis(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public Transform3D getAxisOfTranslation() { return getTransformAxis(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 177 */     computePathInterpolation(paramFloat);
/*     */     
/* 179 */     if (this.currentKnotIndex == 0 && this.currentInterpolationValue == 0.0F) {
/*     */       
/* 181 */       this.pos.x = (this.positions[0]).x;
/* 182 */       this.pos.y = (this.positions[0]).y;
/* 183 */       this.pos.z = (this.positions[0]).z;
/*     */     } else {
/* 185 */       this.pos.x = (this.positions[this.currentKnotIndex]).x + ((this.positions[this.currentKnotIndex + 1]).x - (this.positions[this.currentKnotIndex]).x) * this.currentInterpolationValue;
/*     */ 
/*     */       
/* 188 */       this.pos.y = (this.positions[this.currentKnotIndex]).y + ((this.positions[this.currentKnotIndex + 1]).y - (this.positions[this.currentKnotIndex]).y) * this.currentInterpolationValue;
/*     */ 
/*     */       
/* 191 */       this.pos.z = (this.positions[this.currentKnotIndex]).z + ((this.positions[this.currentKnotIndex + 1]).z - (this.positions[this.currentKnotIndex]).z) * this.currentInterpolationValue;
/*     */     } 
/*     */ 
/*     */     
/* 195 */     this.position.setIdentity();
/* 196 */     this.position.setTranslation(this.pos);
/*     */ 
/*     */     
/* 199 */     paramTransform3D.mul(this.axis, this.position);
/* 200 */     paramTransform3D.mul(paramTransform3D, this.axisInverse);
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
/* 218 */     PositionPathInterpolator positionPathInterpolator = new PositionPathInterpolator();
/* 219 */     positionPathInterpolator.duplicateNode(this, paramBoolean);
/* 220 */     return positionPathInterpolator;
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
/* 246 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 248 */     PositionPathInterpolator positionPathInterpolator = (PositionPathInterpolator)paramNode;
/*     */ 
/*     */     
/* 251 */     int i = positionPathInterpolator.getArrayLengths();
/*     */ 
/*     */     
/* 254 */     this.positions = new Point3f[i];
/*     */     
/* 256 */     Point3f point3f = new Point3f();
/* 257 */     for (byte b = 0; b < i; b++) {
/* 258 */       this.positions[b] = new Point3f();
/* 259 */       positionPathInterpolator.getPosition(b, point3f);
/* 260 */       setPosition(b, point3f);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\PositionPathInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */