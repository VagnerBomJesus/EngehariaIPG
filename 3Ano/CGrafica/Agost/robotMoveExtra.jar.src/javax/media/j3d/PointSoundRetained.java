/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point2f;
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PointSoundRetained
/*     */   extends SoundRetained
/*     */ {
/*  31 */   Point3f position = new Point3f(0.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   Point3f xformPosition = new Point3f();
/*  37 */   Transform3D trans = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float[] attenuationDistance;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float[] attenuationGain;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPosition(Point3f paramPoint3f) {
/*  53 */     if (this.staticTransform != null) {
/*  54 */       this.staticTransform.transform.transform(paramPoint3f, this.position);
/*     */     } else {
/*  56 */       this.position.set(paramPoint3f);
/*     */     } 
/*     */     
/*  59 */     getLastLocalToVworld().transform(paramPoint3f, this.xformPosition);
/*     */     
/*  61 */     dispatchAttribChange(64, new Point3f(this.position));
/*  62 */     if (this.source != null && this.source.isLive()) {
/*  63 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPosition(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  74 */     this.position.x = paramFloat1;
/*  75 */     this.position.y = paramFloat2;
/*  76 */     this.position.z = paramFloat3;
/*  77 */     if (this.staticTransform != null) {
/*  78 */       this.staticTransform.transform.transform(this.position);
/*     */     }
/*     */     
/*  81 */     getLastLocalToVworld().transform(this.position, this.xformPosition);
/*     */     
/*  83 */     dispatchAttribChange(64, new Point3f(this.position));
/*  84 */     if (this.source != null && this.source.isLive()) {
/*  85 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getPosition(Point3f paramPoint3f) {
/*  94 */     if (this.staticTransform != null) {
/*  95 */       Transform3D transform3D = this.staticTransform.getInvTransform();
/*  96 */       transform3D.transform(this.position, paramPoint3f);
/*     */     } else {
/*  98 */       paramPoint3f.set(this.position);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 103 */   void getXformPosition(Point3f paramPoint3f) { paramPoint3f.set(this.xformPosition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDistanceGain(Point2f[] paramArrayOfPoint2f) {
/* 113 */     if (paramArrayOfPoint2f == null) {
/* 114 */       this.attenuationDistance = null;
/* 115 */       this.attenuationGain = null;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 121 */     int i = paramArrayOfPoint2f.length;
/* 122 */     this.attenuationDistance = new float[i];
/* 123 */     this.attenuationGain = new float[i];
/* 124 */     for (byte b = 0; b < i; b++) {
/* 125 */       this.attenuationDistance[b] = (paramArrayOfPoint2f[b]).x;
/* 126 */       this.attenuationGain[b] = (paramArrayOfPoint2f[b]).y;
/*     */     } 
/* 128 */     dispatchAttribChange(128, paramArrayOfPoint2f);
/* 129 */     if (this.source != null && this.source.isLive()) {
/* 130 */       notifySceneGraphChanged(false);
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
/*     */   void setDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 142 */     if (paramArrayOfFloat1 == null) {
/* 143 */       this.attenuationDistance = null;
/* 144 */       this.attenuationGain = null;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 150 */     int i = paramArrayOfFloat2.length;
/* 151 */     int j = paramArrayOfFloat1.length;
/* 152 */     this.attenuationDistance = new float[j];
/* 153 */     this.attenuationGain = new float[j];
/*     */     
/* 155 */     System.arraycopy(paramArrayOfFloat1, 0, this.attenuationDistance, 0, j);
/*     */     
/* 157 */     if (j <= i) {
/* 158 */       System.arraycopy(paramArrayOfFloat2, 0, this.attenuationGain, 0, j);
/*     */     } else {
/*     */       
/* 161 */       System.arraycopy(paramArrayOfFloat2, 0, this.attenuationGain, 0, i);
/*     */ 
/*     */       
/* 164 */       for (int k = i; k < j; k++) {
/* 165 */         this.attenuationGain[k] = paramArrayOfFloat2[i - 1];
/*     */       }
/*     */     } 
/* 168 */     Point2f[] arrayOfPoint2f = new Point2f[j];
/* 169 */     for (byte b = 0; b < j; b++) {
/* 170 */       arrayOfPoint2f[b] = new Point2f(this.attenuationDistance[b], this.attenuationGain[b]);
/*     */     }
/*     */     
/* 173 */     dispatchAttribChange(128, arrayOfPoint2f);
/* 174 */     if (this.source != null && this.source.isLive()) {
/* 175 */       notifySceneGraphChanged(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getDistanceGainLength() {
/* 184 */     if (this.attenuationDistance == null) {
/* 185 */       return 0;
/*     */     }
/* 187 */     return this.attenuationDistance.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getDistanceGain(Point2f[] paramArrayOfPoint2f) {
/* 198 */     if (paramArrayOfPoint2f == null)
/*     */       return; 
/* 200 */     if (this.attenuationDistance == null || this.attenuationGain == null) {
/*     */       return;
/*     */     }
/* 203 */     int i = paramArrayOfPoint2f.length;
/*     */     
/* 205 */     int j = this.attenuationDistance.length;
/* 206 */     if (j > i)
/* 207 */       j = i; 
/* 208 */     for (byte b = 0; b < j; b++) {
/* 209 */       (paramArrayOfPoint2f[b]).x = this.attenuationDistance[b];
/* 210 */       (paramArrayOfPoint2f[b]).y = this.attenuationGain[b];
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
/*     */   void getDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 222 */     if (paramArrayOfFloat1 == null || paramArrayOfFloat2 == null)
/*     */       return; 
/* 224 */     if (this.attenuationDistance == null || this.attenuationGain == null) {
/*     */       return;
/*     */     }
/* 227 */     int i = this.attenuationDistance.length;
/* 228 */     int j = paramArrayOfFloat1.length;
/* 229 */     if (j > i)
/* 230 */       j = i; 
/* 231 */     System.arraycopy(this.attenuationDistance, 0, paramArrayOfFloat1, 0, j);
/* 232 */     i = this.attenuationDistance.length;
/* 233 */     int k = paramArrayOfFloat2.length;
/* 234 */     if (k > i)
/* 235 */       k = i; 
/* 236 */     System.arraycopy(this.attenuationGain, 0, paramArrayOfFloat2, 0, k);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 247 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 248 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/* 249 */     SoundRetained[] arrayOfSoundRetained = (SoundRetained[])paramArrayOfObject[3];
/* 250 */     if (i == -1) {
/*     */       
/* 252 */       initMirrorObject((PointSoundRetained)paramArrayOfObject[2]);
/*     */       return;
/*     */     } 
/* 255 */     if ((i & 0x40) != 0) {
/* 256 */       for (byte b = 0; b < j; b++) {
/* 257 */         PointSoundRetained pointSoundRetained = (PointSoundRetained)arrayOfSoundRetained[b];
/* 258 */         pointSoundRetained.position = (Point3f)paramArrayOfObject[4];
/* 259 */         pointSoundRetained.getLastLocalToVworld().transform(pointSoundRetained.position, pointSoundRetained.xformPosition);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 265 */     super.updateMirrorObject(paramArrayOfObject);
/*     */   }
/*     */   
/*     */   void initMirrorObject(PointSoundRetained paramPointSoundRetained) {
/* 269 */     initMirrorObject(paramPointSoundRetained);
/* 270 */     paramPointSoundRetained.position.set(this.position);
/* 271 */     paramPointSoundRetained.xformPosition.set(this.xformPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   void updateTransformChange() {
/* 276 */     super.updateTransformChange();
/* 277 */     getLastLocalToVworld().transform(this.position, this.xformPosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) {
/* 286 */     super.mergeTransform(paramTransformGroupRetained);
/* 287 */     paramTransformGroupRetained.transform.transform(this.position, this.position);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PointSoundRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */