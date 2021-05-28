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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PointSound
/*     */   extends Sound
/*     */ {
/*     */   public static final int ALLOW_POSITION_READ = 32;
/*     */   public static final int ALLOW_POSITION_WRITE = 33;
/*     */   public static final int ALLOW_DISTANCE_GAIN_READ = 34;
/*     */   public static final int ALLOW_DISTANCE_GAIN_WRITE = 35;
/* 104 */   private static final int[] readCapabilities = { 32, 34 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public PointSound() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointSound(MediaContainer paramMediaContainer, float paramFloat, Point3f paramPoint3f) {
/* 138 */     super(paramMediaContainer, paramFloat);
/*     */ 
/*     */     
/* 141 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 143 */     ((PointSoundRetained)this.retained).setPosition(paramPoint3f);
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
/*     */   public PointSound(MediaContainer paramMediaContainer, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 160 */     super(paramMediaContainer, paramFloat1);
/*     */ 
/*     */     
/* 163 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 165 */     ((PointSoundRetained)this.retained).setPosition(paramFloat2, paramFloat3, paramFloat4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, Point3f paramPoint3f, Point2f[] paramArrayOfPoint2f) {
/* 199 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2);
/*     */ 
/*     */ 
/*     */     
/* 203 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 205 */     ((PointSoundRetained)this.retained).setPosition(paramPoint3f);
/* 206 */     ((PointSoundRetained)this.retained).setDistanceGain(paramArrayOfPoint2f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Point2f[] paramArrayOfPoint2f) {
/* 238 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2);
/*     */ 
/*     */ 
/*     */     
/* 242 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 244 */     ((PointSoundRetained)this.retained).setPosition(paramFloat3, paramFloat4, paramFloat5);
/* 245 */     ((PointSoundRetained)this.retained).setDistanceGain(paramArrayOfPoint2f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, Point3f paramPoint3f, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 276 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2);
/*     */ 
/*     */ 
/*     */     
/* 280 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 282 */     ((PointSoundRetained)this.retained).setPosition(paramPoint3f);
/* 283 */     ((PointSoundRetained)this.retained).setDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointSound(MediaContainer paramMediaContainer, float paramFloat1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Bounds paramBounds, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 317 */     super(paramMediaContainer, paramFloat1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBounds, paramFloat2);
/*     */ 
/*     */ 
/*     */     
/* 321 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 323 */     ((PointSoundRetained)this.retained).setPosition(paramFloat3, paramFloat4, paramFloat5);
/* 324 */     ((PointSoundRetained)this.retained).setDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 333 */     this.retained = new PointSoundRetained();
/* 334 */     this.retained.setSource(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(Point3f paramPoint3f) {
/* 344 */     if (isLiveOrCompiled() && 
/* 345 */       !getCapability(33)) {
/* 346 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound0"));
/*     */     }
/* 348 */     ((PointSoundRetained)this.retained).setPosition(paramPoint3f);
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
/*     */   public void setPosition(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 360 */     if (isLiveOrCompiled() && 
/* 361 */       !getCapability(33)) {
/* 362 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound0"));
/*     */     }
/* 364 */     ((PointSoundRetained)this.retained).setPosition(paramFloat1, paramFloat2, paramFloat3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPosition(Point3f paramPoint3f) {
/* 375 */     if (isLiveOrCompiled() && 
/* 376 */       !getCapability(32)) {
/* 377 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound2"));
/*     */     }
/* 379 */     ((PointSoundRetained)this.retained).getPosition(paramPoint3f);
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
/*     */   public void setDistanceGain(Point2f[] paramArrayOfPoint2f) {
/* 392 */     if (isLiveOrCompiled() && 
/* 393 */       !getCapability(35)) {
/* 394 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound3"));
/*     */     }
/* 396 */     ((PointSoundRetained)this.retained).setDistanceGain(paramArrayOfPoint2f);
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
/*     */   public void setDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 414 */     if (isLiveOrCompiled() && 
/* 415 */       !getCapability(35)) {
/* 416 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound3"));
/*     */     }
/* 418 */     ((PointSoundRetained)this.retained).setDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDistanceGainLength() {
/* 428 */     if (isLiveOrCompiled() && 
/* 429 */       !getCapability(34)) {
/* 430 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound4"));
/*     */     }
/* 432 */     return ((PointSoundRetained)this.retained).getDistanceGainLength();
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
/*     */   public void getDistanceGain(Point2f[] paramArrayOfPoint2f) {
/* 445 */     if (isLiveOrCompiled() && 
/* 446 */       !getCapability(34)) {
/* 447 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound4"));
/*     */     }
/* 449 */     ((PointSoundRetained)this.retained).getDistanceGain(paramArrayOfPoint2f);
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
/*     */   public void getDistanceGain(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {
/* 461 */     if (isLiveOrCompiled() && 
/* 462 */       !getCapability(34)) {
/* 463 */       throw new CapabilityNotSetException(J3dI18N.getString("PointSound4"));
/*     */     }
/* 465 */     ((PointSoundRetained)this.retained).getDistanceGain(paramArrayOfFloat1, paramArrayOfFloat2);
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
/* 483 */     PointSound pointSound = new PointSound();
/* 484 */     pointSound.duplicateNode(this, paramBoolean);
/* 485 */     return pointSound;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 520 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { checkDuplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 546 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 548 */     PointSoundRetained pointSoundRetained1 = (PointSoundRetained)paramNode.retained;
/* 549 */     PointSoundRetained pointSoundRetained2 = (PointSoundRetained)this.retained;
/*     */     
/* 551 */     Point3f point3f = new Point3f();
/* 552 */     pointSoundRetained1.getPosition(point3f);
/* 553 */     pointSoundRetained2.setPosition(point3f);
/*     */     
/* 555 */     int i = pointSoundRetained1.getDistanceGainLength();
/* 556 */     float[] arrayOfFloat1 = new float[i];
/* 557 */     float[] arrayOfFloat2 = new float[i];
/* 558 */     pointSoundRetained1.getDistanceGain(arrayOfFloat1, arrayOfFloat2);
/* 559 */     pointSoundRetained2.setDistanceGain(arrayOfFloat1, arrayOfFloat2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\PointSound.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */